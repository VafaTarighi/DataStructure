package recursion;

/**
 * An recursive algorithm to find the i-th Harmonic Number.
 *
 * Harmonic Number definition: H_n = 1 + 1/2 + 1/3 + ... + 1/n = ∑1/k k=1 to n
 *
 * Recursive definition: H_n = 1/n + H_(n-1)
 */

public class HarmonicNumbers {

    public static double H_(int i) {
        if (i < 1)
            throw new IllegalArgumentException("Negative Index: " + i);

        // Base Case: H_1 = 1;
        if (i == 1)
            return 1;

        // answer is 1/i + H_(i-1)
        return 1.0/i + H_(i - 1);
    }

    public static void main(String[] args) {
        System.out.println(H_(69));
    }
}
