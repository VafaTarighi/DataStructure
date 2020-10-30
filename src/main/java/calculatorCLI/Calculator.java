package calculatorCLI;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private static final HashMap<String, Integer>oprPrecedence = new HashMap<>();
    static
    {
        // add and subtract
        oprPrecedence.put("+", 1);
        oprPrecedence.put("-", 1);

        // multiply and divide
        oprPrecedence.put("×", 2);
        oprPrecedence.put("*", 2);
        oprPrecedence.put("÷", 2);
        oprPrecedence.put("/", 2);

        // power
        oprPrecedence.put("^", 3);
    }

    public static BigInteger calculate(String input) throws IOException {

        if (!validateExpression(input))
            throw new IOException("Invalid Input");

        List<Object> postfixInput = shuntingYard(input);

        int i = 0;
        while (postfixInput.size() != 1) {
            if (postfixInput.get(i) instanceof String) {
                BigInteger a = (BigInteger) postfixInput.get(i - 2);
                BigInteger b = (BigInteger) postfixInput.get(i - 1);
                String operator = (String) postfixInput.get(i);

                BigInteger result = operate(a, b, operator);
                postfixInput.remove(i - 2);
                postfixInput.remove(i - 2);
                postfixInput.remove(i - 2);
                postfixInput.add(i - 2, result);
                i = 0;
            }
            i++;
        }

        return (BigInteger) postfixInput.get(0);
    }

    private static boolean validateExpression(String input) {
        return checkParentheses(input) && checkInvalidCases(input);

    }

    private static boolean checkParentheses(String input) {
        int openPar = 0;
        int closePar = 0;
        for (int i = 0; i < input.length(); i++) {
            if (openPar < closePar) return false;
            if (input.charAt(i) == '(')
                openPar++;
            else if (input.charAt(i) == ')')
                closePar++;
        }

        return openPar == closePar;
    }

    private static boolean checkInvalidCases(String input) {

        // for efficiency purposes it's better to shorten mathematical expression
        // to validate using regex.
        String sInput = input.replaceAll("\\d+", "1");

        Pattern invalidCase =
                Pattern.compile("[^1^\\-×+÷/*()]|1\\(|\\)1|[^1)]\\)|\\([^1\\-(]|\\)\\(|[\\^\\-×+÷/*]{2,}");

        return !invalidCase.matcher(sInput).find();
    }

    private static BigInteger operate(BigInteger a, BigInteger b, String operator) throws IOException {

        switch (operator) {
            case "+":
                return a.add(b);
            case "-":
                return a.subtract(b);
            case "*":
            case "×":
                return a.multiply(b);
            case "/":
            case "÷":
                if (b.equals(BigInteger.ZERO))
                    throw new ArithmeticException("Divide by 0");
                return a.divide(b);
            case "^":
                int ex = Integer.parseInt(b.toString());
                if (ex < 0)
                    throw new UnsupportedOperationException("Negative Exponent");
                return a.pow(ex);
            default:
                throw new IOException();
        }
    }

    private static List<Object> shuntingYard(String input) {
        Queue<String> items = new LinkedList<>();
        Stack<String> operators = new Stack<>();
        LinkedList<Object> output = new LinkedList<>();

        input = "(" + input + ")";
        input = input.replaceAll("-\\(", "-1*(");

        Matcher ext = Pattern.compile("\\d+|\\D").matcher(input);

        while (ext.find()) {
            items.add(ext.group());
        }

        boolean negative = false;
        while (!items.isEmpty()) {
            String element = items.poll();
            if (element.matches("\\D")) { // if element is a operator
                if (element.equals("(")) {
                    operators.push(element);
                    if (items.peek().equals("-")) {
                        negative = true;
                        items.poll();
                    }
                }
                else if (element.equals(")")) {
                    while (!operators.peek().equals("(")) {
                        output.add(operators.pop());
                    }
                    operators.pop();
                }
                else if (operators.isEmpty() || operators.peek().equals("(") || element.equals("^")
                        || oprPrecedence.get(element) > oprPrecedence.get(operators.peek())) {
                    operators.push(element);
                }
                else {
                    while (!operators.isEmpty() && !operators.peek().equals("(")
                    && oprPrecedence.get(element) <= oprPrecedence.get(operators.peek())) {
                        output.add(operators.pop());
                    }
                    operators.push(element);
                }
            } else { // if element is a number
                if (negative) {
                    output.add(new BigInteger("-" + element));
                    negative = false;
                }
                else
                    output.add(new BigInteger(element));
            }

        }

        while(!operators.isEmpty()) {
            output.add(operators.pop());
        }

        System.out.println("Postfix Expression: " + output);

        return output;
    }
}
