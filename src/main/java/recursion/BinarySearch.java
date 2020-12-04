package recursion;

/**
 * Implementation of Binary Search that returns index of
 * the element that we're looking for.
 */

public class BinarySearch {

    public static int search(int[] array, int target) {
        return search(array, target, 0, array.length-1);
    }

    private static int search(int[] array, int target, int start, int end) {
        // Base Case: no such an element in the array
        if (start > end)
            return -1;

        // calculating the middle index of the array
        int mid = start + (end - start)/2;

        // check if array[mid] is the target. if yes return mid
        if (array[mid] == target)
            return mid;

        // check if target is smaller than array[mid]. if yes throw higher half
        // of the array away and perform binary search on lower half
        if (array[mid] < target)
            return search(array, target, mid + 1, end);

        // check if target is bigger than array[mid]. if yes throw lower half
        // of the array away and perform binary search on higher half
        if (array[mid] > target)
            return search(array, target, start, mid - 1);

        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        for (int i = 1; i < 11; i++) {
            // expected output: 0 1 2 3 4 5 6 7 8 9
            System.out.println(search(arr, i));
        }
    }

}
