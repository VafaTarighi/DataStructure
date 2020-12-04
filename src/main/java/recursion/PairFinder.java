package recursion;

/**
 * This is a recursive algorithm that will check if an array A of integers contains
 * an integer A[i] that is the sum of two integers that appear earlier in A, that is, such
 * that A[i] = A[j]+A[k] for j,k < i.
 */

public class PairFinder {

    public static boolean containsPair(int[] array) {
        return containsPair(array, 0, 1);
    }

    private static boolean containsPair(int[] array, int j, int k) {
        // Base Case: if we reach to the end of the array
        if (j == array.length - 1)
            return false;
        // if second pair reaches the end reset pairs; increase first pair by one
        // then set second pair to first pair plus one
        if (k == array.length - 1)
            return containsPair(array, j + 1, j + 2);

        //Check if array[j] + array[k] == array[any i bigger than j&k]
        for (int i = k + 1; i < array.length; i++) {
            if (array[j] + array[k] == array[i])
                return true;
        }

        // increase second pair by one
        return containsPair(array, j, k + 1);
    }

    public static void main(String[] args) {

        int[] arr = {-1,-1,-1,   3   ,-1,-1,-1,-1,-1,-1,   4   ,-1,-1,-1,   7   };
        boolean ans = containsPair(arr);
        System.out.println(ans);

//        for (int i = 1000; i < 6000; i++) {
//            System.out.print(i + ",");
//        }
    }
}
