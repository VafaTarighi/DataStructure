package sorting;

import java.util.Random;

public class SortTest {

    static int[] a100k = new int[100000];
    static int[] a200k = new int[200000];
    static int[] a300k = new int[300000];
    static int[] a400k = new int[400000];
    static int[] a500k = new int[500000];
    static int[] a600k = new int[600000];
    static int[] a700k = new int[700000];
    static int[] a800k = new int[800000];
    static int[] a900k = new int[900000];

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(6000);

        System.out.println("\n_shuffling arrays_");
        shuffleAll();
        System.out.println("_shuffling finished_\n");

        System.out.println("Q Sort:");
        System.out.println("100k =>" + IntArraySorter.sort(a100k, Algorithm.Q));
        System.out.println("200k =>" + IntArraySorter.sort(a200k, Algorithm.Q));
        System.out.println("300k =>" + IntArraySorter.sort(a300k, Algorithm.Q));
        System.out.println("400k =>" + IntArraySorter.sort(a400k, Algorithm.Q));
        System.out.println("500k =>" + IntArraySorter.sort(a500k, Algorithm.Q));
        System.out.println("600k =>" + IntArraySorter.sort(a600k, Algorithm.Q));
        System.out.println("700k =>" + IntArraySorter.sort(a700k, Algorithm.Q));
        System.out.println("800k =>" + IntArraySorter.sort(a800k, Algorithm.Q));
        System.out.println("900k =>" + IntArraySorter.sort(a900k, Algorithm.Q));

        System.out.println("\n_shuffling arrays_");
        shuffleAll();
        System.out.println("_shuffling finished_\n");

        System.out.println("Q Sort:");
        System.out.println("100k =>" + IntArraySorter.sort(a100k, Algorithm.JAVA));
        System.out.println("200k =>" + IntArraySorter.sort(a200k, Algorithm.JAVA));
        System.out.println("300k =>" + IntArraySorter.sort(a300k, Algorithm.JAVA));
        System.out.println("400k =>" + IntArraySorter.sort(a400k, Algorithm.JAVA));
        System.out.println("500k =>" + IntArraySorter.sort(a500k, Algorithm.JAVA));
        System.out.println("600k =>" + IntArraySorter.sort(a600k, Algorithm.JAVA));
        System.out.println("700k =>" + IntArraySorter.sort(a700k, Algorithm.JAVA));
        System.out.println("800k =>" + IntArraySorter.sort(a800k, Algorithm.JAVA));
        System.out.println("900k =>" + IntArraySorter.sort(a900k, Algorithm.JAVA));



        System.out.println("_shuffling arrays_");
        shuffleAll();
        System.out.println("_shuffling finished_\n");

        System.out.println("Selection Sort:");
        System.out.println("100k =>" + IntArraySorter.sort(a100k, Algorithm.SELECTION));
        System.out.println("200k =>" + IntArraySorter.sort(a200k, Algorithm.SELECTION));
//        System.out.println("300k =>" + IntArraySorter.sort(a300k, Algorithm.SELECTION));
//        System.out.println("400k =>" + IntArraySorter.sort(a400k, Algorithm.SELECTION));
//        System.out.println("500k =>" + IntArraySorter.sort(a500k, Algorithm.SELECTION));
//        System.out.println("600k =>" + IntArraySorter.sort(a600k, Algorithm.SELECTION));
//        System.out.println("700k =>" + IntArraySorter.sort(a700k, Algorithm.SELECTION));
//        System.out.println("800k =>" + IntArraySorter.sort(a800k, Algorithm.SELECTION));
//        System.out.println("900k =>" + IntArraySorter.sort(a900k, Algorithm.SELECTION));

        System.out.println("\n_shuffling arrays_");
        shuffleAll();
        System.out.println("_shuffling finished_\n");

        System.out.println("Bubble Sort:");
        System.out.println("100k =>" + IntArraySorter.sort(a100k, Algorithm.BUBBLE));
        System.out.println("200k =>" + IntArraySorter.sort(a200k, Algorithm.BUBBLE));
//        System.out.println("300k =>" + IntArraySorter.sort(a300k, Algorithm.BUBBLE));
//        System.out.println("400k =>" + IntArraySorter.sort(a400k, Algorithm.BUBBLE));
//        System.out.println("500k =>" + IntArraySorter.sort(a500k, Algorithm.BUBBLE));
//        System.out.println("600k =>" + IntArraySorter.sort(a600k, Algorithm.BUBBLE));
//        System.out.println("700k =>" + IntArraySorter.sort(a700k, Algorithm.BUBBLE));
//        System.out.println("800k =>" + IntArraySorter.sort(a800k, Algorithm.BUBBLE));
//        System.out.println("900k =>" + IntArraySorter.sort(a900k, Algorithm.BUBBLE));

        System.out.println("\n_shuffling arrays_");
        shuffleAll();
        System.out.println("_shuffling finished_\n");

        System.out.println("Insertion Sort:");
        System.out.println("100k =>" + IntArraySorter.sort(a100k, Algorithm.INSERTION));
        System.out.println("200k =>" + IntArraySorter.sort(a200k, Algorithm.INSERTION));
//        System.out.println("300k =>" + IntArraySorter.sort(a300k, Algorithm.INSERTION));
//        System.out.println("400k =>" + IntArraySorter.sort(a400k, Algorithm.INSERTION));
//        System.out.println("500k =>" + IntArraySorter.sort(a500k, Algorithm.INSERTION));
//        System.out.println("600k =>" + IntArraySorter.sort(a600k, Algorithm.INSERTION));
//        System.out.println("700k =>" + IntArraySorter.sort(a700k, Algorithm.INSERTION));
//        System.out.println("800k =>" + IntArraySorter.sort(a800k, Algorithm.INSERTION));
//        System.out.println("900k =>" + IntArraySorter.sort(a900k, Algorithm.INSERTION));
    }

    private static void shuffleAll() {
        shuffleArray(a100k, 1);
        shuffleArray(a200k, 2);
        shuffleArray(a300k, 3);
        shuffleArray(a400k, 4);
        shuffleArray(a500k, 5);
        shuffleArray(a600k, 6);
        shuffleArray(a700k, 7);
        shuffleArray(a800k, 8);
        shuffleArray(a900k, 9);
    }

    static void shuffleArray(int[] arr, int hk) {
        switch (hk) {
            case 1:
                System.arraycopy(a1hk, 0, arr, 0, arr.length);
                break;
            case 2:
                System.arraycopy(a2hk, 0, arr, 0, arr.length);
                break;
            case 3:
                System.arraycopy(a3hk, 0, arr, 0, arr.length);
                break;
            case 4:
                System.arraycopy(a4hk, 0, arr, 0, arr.length);
                break;
            case 5:
                System.arraycopy(a5hk, 0, arr, 0, arr.length);
                break;
            case 6:
                System.arraycopy(a6hk, 0, arr, 0, arr.length);
                break;
            case 7:
                System.arraycopy(a7hk, 0, arr, 0, arr.length);
                break;
            case 8:
                System.arraycopy(a8hk, 0, arr, 0, arr.length);
                break;
            case 9:
                System.arraycopy(a9hk, 0, arr, 0, arr.length);
                break;
        }

    }

    static int[] a1hk = new int[100000];
    static int[] a2hk = new int[200000];
    static int[] a3hk = new int[300000];
    static int[] a4hk = new int[400000];
    static int[] a5hk = new int[500000];
    static int[] a6hk = new int[600000];
    static int[] a7hk = new int[700000];
    static int[] a8hk = new int[800000];
    static int[] a9hk = new int[900000];

    static {
        Random random = new Random();
        for (int i = 0; i < a1hk.length; i++) {
            a1hk[i] = random.nextInt();
        }
        random = new Random();
        for (int i = 0; i < a2hk.length; i++) {
            a2hk[i] = random.nextInt();
        }
        random = new Random();
        for (int i = 0; i < a3hk.length; i++) {
            a3hk[i] = random.nextInt();
        }
        random = new Random();
        for (int i = 0; i < a4hk.length; i++) {
            a4hk[i] = random.nextInt();
        }
        random = new Random();
        for (int i = 0; i < a5hk.length; i++) {
            a5hk[i] = random.nextInt();
        }
        random = new Random();
        for (int i = 0; i < a6hk.length; i++) {
            a6hk[i] = random.nextInt();
        }
        random = new Random();
        for (int i = 0; i < a7hk.length; i++) {
            a7hk[i] = random.nextInt();
        }
        random = new Random();
        for (int i = 0; i < a8hk.length; i++) {
            a8hk[i] = random.nextInt();
        }
        random = new Random();
        for (int i = 0; i < a9hk.length; i++) {
            a9hk[i] = random.nextInt();
        }

    }

}
