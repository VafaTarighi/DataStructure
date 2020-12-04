package recursion;

import java.text.ParseException;

public class RecursiveIntegerParser {

    public static int parse(String str) {
        // validations
        if (!str.matches("-?\\d+"))
            throw new IllegalArgumentException("The string '" + str + "' can't be parsed to type string.");

        int result;

        // check if the number is negative
        if (str.charAt(0) == '-')
            result = -1 * parseInteger(str.substring(1));
        else
            result = parseInteger(str);


        return result;
    }

    private static int parseInteger(String str) {
        // Base Case: for single digit number directly convert and return it.
        if (str.length() == 1)
            return parseSingleDigit(str);

        // take first character from the string and parse it to the integer.
        int n = parseSingleDigit(str.substring(0,1));
        // then multiply it with 10^(str.len-1) and add it to the parsed integer of
        // remaining characters in the string sequence.
        return n * (int) Math.pow(10, str.length()-1) + parseInteger(str.substring(1));

    }

    private static int parseSingleDigit(String str) {
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        System.out.println("-75829562");
        System.out.println(parse("-75829562") + 1);
    }
}
