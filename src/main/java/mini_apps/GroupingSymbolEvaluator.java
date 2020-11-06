package mini_apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class GroupingSymbolEvaluator {
    private static final Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        GroupingSymbolEvaluator gse = new GroupingSymbolEvaluator();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("_-_-__Grouping Symbols Evaluator__-_-_\n");
        System.out.println("Enter Arithmetic Expressions to check\n" +
                " it's grouping symbols pair correctness.\n" +
                "Supported Symbols: {} [] () <>\n" +
                "To exit enter 'exit'\n-----------------");

        String input;
        System.out.print(">> ");
        while (!(input = reader.readLine()).equalsIgnoreCase("exit")) {
            System.out.println(gse.evaluate(input)? "valid" : "Invalid");
            System.out.print(">> ");
        }


        reader.close();
    }

    public boolean evaluate(String input) {
        boolean result = checkParentheses(input);
        stack.clear();
        return result;
    }

    private boolean checkParentheses(String input) {
        String open = "(<[{";
        String close = ")>]}";
        for (char c: input.toCharArray()) {
            if (open.indexOf(c) != -1)
                stack.push(c);
            else if (close.indexOf(c) != -1) {
                if (stack.isEmpty())
                    return false;
                if (open.indexOf(stack.pop()) != close.indexOf(c))
                    return false;
            }
        }

        return stack.isEmpty();
    }
}
