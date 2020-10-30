package calculatorCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class CalculatorCLI {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        System.out.println("\n_-_-_CommandLine Calculator_-_-_");
        System.out.println("\nOperators:\n" +
                "Summation:.............( + )\n" +
                "Subtraction:...........( - )\n" +
                "Multiplication:........( * or × )\n" +
                "Division:..............( / or ÷ )\n" +
                "Power:.................( ^ )\n" +
                "\nExit:................( exit )\n" +
                "\n\"Parentheses and Negative Numbers are Supported\"\n" +
                "--------------------------------------------------");

        while (true) {
            System.out.print(">>> ");
            input = reader.readLine();
            input = input.replaceAll("\\s", "");

            if (input.equalsIgnoreCase("exit"))
                break;

            try {
                BigInteger result = Calculator.calculate(input);
                System.out.println("Answer: " + result);
            } catch (Exception e) {
                System.out.println("Calculation failed: " + e.getMessage());
            }
        }
    }
}
