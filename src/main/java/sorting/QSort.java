package sorting;

import java.util.Arrays;

public class QSort {

    private static int getPivot(int[] array) {
        int pivot = array[0];
        double ave = 0;

        for (int value : array) {
            ave += value;
        }
        ave /= array.length;

        double distance = Math.abs(ave - pivot);
        int target;
        double newDistance = Math.abs(ave - pivot);
        for (int i = 1; i < array.length; i++) {
            target = array[i];
            newDistance = Math.abs(ave - array[i]);
            if(newDistance < distance) {
                distance = newDistance;
                pivot = target;
            }
        }
        return pivot;
    }

    private static int[] getRight(int[] array, int pivot) {
        int[] right = new int[array.length];
        int count = 0;
        for (int value: array) {
            if(value > pivot) {
                right[count++] = value;
            }
        }
        return Arrays.copyOf(right, count);
    }

    private static int[] getLeft(int[] array, int pivot) {
        int[] left = new int[array.length];
        int count = 0;
        for (int value: array) {
            if(value < pivot) {
                left[count++] = value;
            }
        }
        return Arrays.copyOf(left, count);
    }

    public static void sort(int[] array) {
        if(array.length < 2) return;
        if (array.length == 2) {
            if(array[0] > array[1]) {
                int tmp = array[0];
                array[0] = array[1];
                array[1] = tmp;
            }
            return;
        }

        int pivot = getPivot(array);
        int[] left = getLeft(array, pivot);
        sort(left);
        int[] right = getRight(array, pivot);
        sort(right);

        int pivotCount = 0;
        for (int num: array) {
            if (num == pivot) pivotCount++;
        }

        System.arraycopy(left, 0, array, 0, left.length);
        int pivotIndex = left.length;
        for (int i = 0; i < pivotCount; i++){
                array[pivotIndex++] = pivot;
            }
        System.arraycopy(right, 0, array, pivotIndex, right.length);

    }

    public static void main(String[] args) {
        sort(new int[] {9,8,7,6,5,4,3,2,1});
    }
}
