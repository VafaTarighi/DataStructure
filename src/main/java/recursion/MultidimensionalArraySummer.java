package recursion;

/**
 * An recursive algorithm to compute sum of a NxN array of integers
 *
 */

public class MultidimensionalArraySummer {

    public static int deepSum(int[][] array) {
        return deepSum(array, 0);
    }

    private static int deepSum(int[][] array, int i) {
        // Base Case: if we reach to the last column
        if (i == array.length)
            return 0;
        // return the sum of fist row and other rows
        return sum(array[i], 0) + deepSum(array, i + 1);
    }

    private static int sum(int[] array, int i) {
        // Base Case: if we reach to the end of the array
        if (array.length == i)
            return 0;

        // return the sum of fist integer in the array and remaining integers in the array
        return array[i] + sum(array, i + 1);
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1,1,1},
                {1,1,1},
                {1,1,1}
        };

        System.out.println(deepSum(arr));
    }
}
