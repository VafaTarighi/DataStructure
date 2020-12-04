package recursion;

import java.io.File;
import java.math.BigInteger;
import java.util.*;

public class RecursiveFunctionCollections {

    public static BigInteger factorial(int a) {
        if (a < 0)
            throw new IllegalArgumentException("negative numbers not supported for factorial.");

        return factorialBigInt(new BigInteger(String.valueOf(a)));
    }

    private static BigInteger factorialBigInt(BigInteger a) {
        if (a.equals(BigInteger.ZERO))
            return BigInteger.ONE;

        return a.multiply(factorialBigInt(a.subtract(BigInteger.ONE)));

    }

    public static int factorial1(int a) { // n! = n*(n-1)*(n-2)*...*2*1 -> n! = n*(n-1)! 1!=1
        //Base Case
        if (a == 1)
            return 1;
        else
            return a * factorial1(a-1);
    }


    public static void printRuler(int len, int mtl) {
        System.out.println("-".repeat(mtl) + " 0");
        for (int i = 1; i <= len; i++) {
            printIntervals(mtl - 1);
            System.out.println("-".repeat(mtl) + " " + i);
        }

    }

    private static void printIntervals(int mtl) {
        if (mtl == 0) return;

        printIntervals(mtl - 1);
        System.out.println("-".repeat(mtl));
        printIntervals(mtl - 1);
    }


    public static long discUsage(File root) {
        if (root == null)
            return -1;

        long total = root.length();
        if (root.isDirectory()) {
            for (String c : Objects.requireNonNull(root.list())) {
                File child = new File(root, c);
                total += discUsage(child);
            }
        }
        System.out.println(total + "\t" + root);
        return total;
    }

    public static boolean binarySearch(int[] data, int target, int low, int high) {
        if (low > high)
            return false;
        int mid = low + (high - low)/2;

        if (target == data[mid])
            return true;

        if (target < data[mid])
            high = mid - 1;
        else
            low = mid + 1;

        return binarySearch(data, target, low, high);
    }

    public static int linearSum(int[] array, int n) {
        if (n == 0)
            return 0;

        return array[n - 1] + linearSum(array, n-1);
    }

    public static void arrayReverse(int[] array, int low, int high) {
        if (!(low < high)) return;

        int tmp = array[low];
        array[low] = array[high];
        array[high] = tmp;

        arrayReverse(array, low + 1, high - 1);
    }

    public static long power(long x, int n) {
        if (n == 0)
            return 1;

        return x * power(x, n - 1);
    }

    public static long powerFast(long x, int n) {
        if (n == 0) return 1;

        long halfPow = powerFast(x, n/2);

        if (n%2 == 0)
            return halfPow*halfPow;
        else
            return x*halfPow*halfPow;
    }

    public static int binarySum(int[] array, int low, int high) {
        if (low == high) return array[low];
        if (low > high) return 0;

        int mid = (low+high)/2;
        return binarySum(array, low, mid) + binarySum(array, mid + 1, high);
    }

    public static void puzzleSolver(int len, StringBuffer sb, List<Character> U) {
        Iterator<Character> itr = U.listIterator();
        while (itr.hasNext()) {
            char c = itr.next();
            sb.append(c);
            itr.remove();

        }
    }

    public static int fibonacci1(int n) {
        if (n <= 1) return n;

        return fibonacci1(n-1) + fibonacci1(n-2);
    }

    public static long[] fibonacci2(int n) {
        if (n <= 1)  {
            return new long[]{n, 0};
        }

        long[] tmp = fibonacci2(n-1);
        return new long[] {tmp[0] + tmp[1], tmp[0]};
    }


    public static void main(String[] args) {

        printRuler(1, 6);

    }
}
