package sorting;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class SortCompareTest {

    private static int[][] SAMPLE_ARRAYS = {
            new int[100],
            new int[1_000],
            new int[10_000],
            new int[50_000],
            new int[100_000],
            new int[200_000],
            new int[500_000]
    };

    private static long msComps;
    private static long qsComps;
    private static long isComps;


    /**
     * Merge Sort implementation
     *
     * @param array the array to be sorted
     */
    public static void mergeSort(int[] array) {
        msComps = 0;
        mergeSortAlgorithm(array);
    }

    public static void mergeSortAlgorithm(int[] array) {
        // Base Case: array size is less than one return
        if (array.length <= 1)
            return;

        // Divide the array to 2 parts
        int mid = array.length/2;

        int[] arr1 = new int[mid];
        int[] arr2 = new int[array.length - mid];

        System.arraycopy(array, 0, arr1, 0, arr1.length);
        System.arraycopy(array, mid, arr2, 0, arr2.length);

        // sort two half arrays
        mergeSortAlgorithm(arr1);
        mergeSortAlgorithm(arr2);

        int pt1 = 0;
        int pt2 = 0;
        int point = 0;
        // merge sorted lists to right position in the main array
        while (true) {
            if (pt1 == arr1.length) {
                while (pt2 != arr2.length) {
                    array[point++] = arr2[pt2++];
                }
                break;
            }
            if (pt2 == arr2.length) {
                while (pt1 != arr1.length) {
                    array[point++] = arr1[pt1++];
                }
                break;
            }


            array[point++] = (arr1[pt1] < arr2[pt2])? arr1[pt1++] : arr2[pt2++];
            msComps++;
        }

    }



    public static void quickSort(int[] array) {
        qsComps = 0;
        quickSort(array, 0, array.length);
    }

    public static void quickSort(int[] array, int start, int len) {
        if(len < 2) return;
        if (len == 2) {
            if(array[start] > array[start+1]) {
                int end = start+1;
                int tmp = array[start];
                array[start] = array[end];
                array[end] = tmp;
            }
            return;
        }

        int pivot = (int) (Math.random()*len) + start;
        pivot = pivotArrange(array, start, len, pivot);

        quickSort(array, start, pivot-start);
        quickSort(array, pivot+1, len-(pivot-start)-1);



    }

    private static int pivotArrange(int[] array, int start, int len, int pivot) {
        int behind = start;
        int p = array[pivot];
        swap(array, pivot, start);

        for (int ahead = start+1; ahead <= start + len - 1; ahead++) {

            if (array[ahead] < p) {
                behind++;
                swap(array, behind, ahead);
            }
            qsComps++;
        }

        swap(array, behind, start);

        return behind;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp;
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



    public static void insertionSort(int[] array) {
        isComps = 0;
        int l = array.length;
        int tmp;

        for (int i = 1; i < l; i++) {
            for (int j = i; j > 0; j--) {
                isComps++;
                if (array[j] < array[j - 1]) {
                    tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                } else break;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        long[][] statistics = new long[SAMPLE_ARRAYS.length][10];

//        for (int i = 0; i < 10; i++) {
//            randomizeSamples();
//            for (int j = 0; j < SAMPLE_ARRAYS.length; j++) {
//                mergeSort(SAMPLE_ARRAYS[j]);
//                statistics[j][i] = msComps;
//                System.out.println("-------------["
//                        + ((i*10 + j)/(SAMPLE_ARRAYS.length*10)*100)
//                        + "%]");
//            }
//        }
        System.out.println("Merge Sort:");
        for (int i = 0; i < 10; i++) {
            randomizeSamples();
            for (int j = 0; j < SAMPLE_ARRAYS.length; j++) {
                mergeSort(SAMPLE_ARRAYS[j]);
                statistics[j][i] = msComps;
            }
            System.out.println("-------------["
                    + ((i + 1)*10)
                    + "%]");
        }
        writeStatistics("mergeStats", statistics, "src/main/resources");


        System.out.println("Quick Sort:");
        for (int i = 0; i < 10; i++) {
            randomizeSamples();
            for (int j = 0; j < SAMPLE_ARRAYS.length; j++) {
                quickSort(SAMPLE_ARRAYS[j]);
                statistics[j][i] = qsComps;
            }
            System.out.println("-------------["
                    + ((i + 1)*10)
                    + "%]");
        }
        writeStatistics("quickStats", statistics, "src/main/resources");


        System.out.println("Insertion Sort:");
        for (int i = 0; i < 10; i++) {
            randomizeSamples();
            for (int j = 0; j < SAMPLE_ARRAYS.length; j++) {
                insertionSort(SAMPLE_ARRAYS[j]);
                statistics[j][i] = isComps;
            }
            System.out.println("-------------["
                    + ((i + 1)*10)
                    + "%]");
        }
        writeStatistics("insertionStats", statistics, "src/main/resources");
    }

    private static void writeStatistics(String fileName, long[][] statistics, String directory) throws IOException {
        FileWriter writer = new FileWriter(directory + "/" + fileName + ".txt");
        StringBuilder sb = new StringBuilder();

        writer.write(fileName + ":" + System.lineSeparator());

        for (int i = 0; i < 10; i++) {
            sb.setLength(0);
            for (int j = 0; j < SAMPLE_ARRAYS.length; j++) {
                sb.append(statistics[j][i]).append(" | ");
            }
            sb.append(System.lineSeparator());
            writer.write(sb.toString());
            writer.flush();
        }

        // Calculate Average, Median and Range of data
        int n;
        String stats;
        for (int i = 0; i < SAMPLE_ARRAYS.length; i++) {
            n = SAMPLE_ARRAYS[i].length;
            Arrays.sort(statistics[i]);
            stats = String.format("Problem Size of (%d):   average=%d,   median=%d   range=(%d,%d)"
                    + System.lineSeparator(),
                    n,
                    getAverage(statistics[i]),
                    getMedian(statistics[i]),
                    statistics[i][0],
                    statistics[i][9]);


            writer.write(stats);
        }
        writer.close();
    }

    private static long getAverage(long[] statistic) {
        int size = statistic.length;
        long average = 0;

        for (long data : statistic) {
            average += data/size;
        }

        return average;
    }

    private static long getMedian(long[] statistic) { // {1,2,3,4,5}
        int size = statistic.length;


        if (size%2 == 0) {
            return statistic[size/2]/2 + statistic[size/2 - 1]/2;
        } else {
            return statistic[size/2];
        }
    }


    private static void randomizeSamples() {
        Random rand = new Random();

        for (int[] sampleArray : SAMPLE_ARRAYS) {
            for (int i = 0; i < sampleArray.length; i++) {
                sampleArray[i] = rand.nextInt();
            }
        }
    }
}
