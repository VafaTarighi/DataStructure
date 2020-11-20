package sorting;

import java.util.Arrays;
import java.util.Random;

public class StableSelectionSort {

    public static void sort(int[] array) {
        int l = array.length;
        int min;
        int minIndex;

        for (int i = 0; i < l - 1; i++) {
            minIndex = i;

            for (int j = i + 1; j < l; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }
            min = array[minIndex];
            while (minIndex > i) {
                array[minIndex] = array[minIndex - 1];
                minIndex--;
            }

            array[i] = min;
        }
    }


    public static void main(String[] args) {
        int[] a1hk = new int[100000];
        Random random = new Random();
        for (int i = 0; i < a1hk.length; i++) {
            a1hk[i] = random.nextInt();
        }

        int[] java = a1hk.clone();
        int[] me = a1hk.clone();

        if (Arrays.equals(java, me))
            System.out.println("Equal");
        else
            System.out.println("Not equal");

        Arrays.sort(java);
        sort(me);

        if (Arrays.equals(java, me))
            System.out.println("Equal");
        else
            System.out.println("Not equal");

    }
}
