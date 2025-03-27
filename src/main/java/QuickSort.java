import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class QuickSort {

    // This class should not be instantiated.
    private QuickSort() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
        @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        Collections.shuffle(Arrays.asList(a));
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) {
        //if low is greater than high we are done with sorting no more sorting occured
        if (hi <= lo) return;
        //started divide array and get index called j
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }


    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            // try to find the element on left to swap
            while (less(a[++i], v)) {
                //if current pointer i is equal to "hi" break to prevent error
                if (i == hi) break;
            }

            // try to find element on right to swap
            while (less(v, a[--j])) {
                //if current pointer i is equal to "lo" break to prevent error
                if (j == lo) break;
            }

            // if pointer cross each other we do not want that
            if (i >= j) break;
            exch(a, i, j);
        }
        
        // Swap with partitioning item
        exch(a, lo, j);
        return j;
    }

    



   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Enter strings separated by spaces:");
        String[] a = scanner.nextLine().split("\\s+");
        scanner.close();
        QuickSort.sort(a);
        show(a);
        assert isSorted(a);
    }

}