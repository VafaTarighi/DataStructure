package recursion;

/**
 * An algorithm to calculate product of two numbers by an recursive approach
 *
 * recursive approach: A * B == A + A*(B - 1)
 */

public class RecursiveProduct {

    public static int productOf(int a, int b) {

        // sending smaller integer az second parameter for better performance
        if (a < b)
            return prodOf(b, a);
        else
            return prodOf(a, b);
    }

    private static int prodOf(int a, int b) {
        // Base Case: if b is 0 return 0
        if (b == 0)
            return 0;


        // answer is a*b == a + a*(b-1)
        return a + prodOf(a, b-1);
    }

    public static void main(String[] args) {
        System.out.println(productOf(2432, 523453));
        System.out.println(2432*523453);
    }

}
