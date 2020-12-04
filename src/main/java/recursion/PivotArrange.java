package recursion;

import java.util.Arrays;
import java.util.Random;

public class PivotArrange {

    public static void arrange(int[] array ,int k) {
        arrange(array, k + 1, 0);
    }

    private static int arrange(int[] array, int pivot, int pos) {
        // skip the integers less than or equal to k
        // and find the index of the first integer that is bigger than k in the array
        for (int i = pos; i < array.length; ++i) {
            if (array[pos] >= pivot) {
                break;
            }
            pos = i;
        }
        // Base Case: if we reach to the end of the array then return pos or pos-1 depending on the condition
        if (pos == array.length - 1)
            return pos - (array[pos] < pivot ? 0 : 1);

        // find the last index of the array witch after that index all elements are
        // bigger than k
        int end = arrange(array, pivot, pos + 1);

        // swap array[pos] and array[end]
        if (end != pos) {
            int tmp = array[pos];
            array[pos] = array[end];
            array[end] = tmp;
        }

        // now that array[end] has a value bigger than k then return end-1 that array[end-1]
        // has a value less or equal to k
        return end - 1;

    }

    public static void main(String[] args) {
//        Random rand = new Random();
//        int[] arr = new int[10];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = rand.nextInt(20);
//        }

//        int[] arr = {1,6,3,5,7,9,5,2,6,3};
//        int[] arr = {15, 12, 14, 0, 9, 1, 10, 10, 5, 15};
        int[] arr = {1,3,5,7,9,5,2,3};
        System.out.println(Arrays.toString(arr));

        arrange(arr, 5);

        System.out.println(Arrays.toString(arr));


    }
}
