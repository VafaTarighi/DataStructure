package recursion;

public class PalindromeDetector {

    public static boolean detect(String s) {
        return compareChars(s, 0, s.length()-1);
    }

    private static boolean compareChars(String s, int start, int end) {
        // Base Case: if we reach to the middle character of the word
        if (!(start < end))
            return true;

        // compare two start and end characters
        if (s.charAt(start) != s.charAt(end))
            return false;
        // increase start and decrease end and compareChars of remaining
        return compareChars(s, start + 1, end - 1);

    }


    public static void main(String[] args) {
        System.out.println(detect("gohangasalamiimalasagnahog")); // true
        System.out.println(detect("racecar")); // true
        System.out.println(detect("breakingbad")); // false
    }
}
