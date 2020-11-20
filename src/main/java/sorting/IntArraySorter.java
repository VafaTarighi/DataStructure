package sorting;

import java.util.Arrays;

public class IntArraySorter {

    public static long sort(int[] array, Algorithm alg) {
        long start = System.currentTimeMillis();
        switch (alg) {
            case SELECTION:
                selectionSort(array);
                break;
            case INSERTION:
                insertionSort(array);
                break;
            case BUBBLE:
                bubbleSort(array);
                break;
            case Q:
                qSort(array);
                break;
            case JAVA:
                Arrays.sort(array);
        }
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static void selectionSort(int[] array) {
        int l = array.length;
        int tmp;
        int minIndex;
        for (int i = 0; i < l; i++) {
            minIndex = i;

            for (int j = i + 1; j < l; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }

            tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;
        }
    }
    private static void insertionSort(int[] array) {
        int l = array.length;
        int tmp;

        for (int i = 1; i < l; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                } else break;
            }
        }

    }
    private static void bubbleSort(int[] array) {
        int l = array.length;
        int tmp;
        boolean isSwap;

        for (int i = 0; i < l; i++) {
            isSwap = false;
            for (int j = 0; j < l - (i + 1); j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSwap = true;
                }
            }
            if (!isSwap) break;
        }

    }

    private static void qSort(int[] array) {
        QSort.sort(array);
    }

    private static void insertion2(int[] array) {
        int l = array.length;
        int x;
        int i, j;

        for (i = 1; i < l; i++) {
            x = array[i];
            for (j = i - 1; j >= 0 && array[j] > x ; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = x;
        }
    }

    public static void main(String[] args) {
        int[] ar = {1,3,4,5,6,7,2};
        insertion2(ar);
        System.out.println(Arrays.toString(ar));

    }


}
