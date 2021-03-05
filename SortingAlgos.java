import java.lang.reflect.Array;
import java.lang.Class;

public class SortingAlgos {

    /* ************************************************************* */
    // Name:        Selection Sort
    // Basis:       Comparison
    // Complexity:   - Number of comparisons O(n^2)
    //               - Number of swaps O(n)
    // Space:       Constant (in place)
    // Stability:   Not Stable
    // Informal Description:
    //          1. Find the minimum value in the list
    //          2. Swap it with the value in the first position
    //          3. Repeat the above steps for the rest of the list,
    //             starting at the second, third, etc. position each
    //             time
    // Notes:
    //          Don't confuse this with insertion sort. This algoritm
    //          isn't used much since its pretty inefficient and
    //          it's not stable.
    /* ************************************************************* */
    public static <T extends Comparable<T>> T[] selectionSort(T[] A) {
        int min;
        T temp = A[0];
        for (int i = 0; i < A.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < A.length; j++) {
                if (A[j].compareTo(A[min]) < 0) {
                    min = j;
                }
            }
            if (i != min) {
                temp = A[i];
                A[i] = A[min];
            }
            A[min] = temp;
        }
        return A;
    }

    /* ************************************************************* */
    // Name:        Bubble Sort
    // Basis:       Comparison
    // Complexity:   - Number of comparisons O(n^2)
    //               - Number of swaps O(n^2)
    // Space:       Constant (in place)
    // Stability:   Stable
    // Informal Description:
    //          1. Scan the array, comparing pairs
    //          2. If pairs are the wrong way round, swap them
    //          3. Repeat until the array is sorted
    // Notes:
    //          This algorithm isn't great. No-one uses it. The only
    //          advantage I can think of is that it's simple to
    //          understand. At least it's stable and constant.
    /* ************************************************************* */
    public static <T extends Comparable<T>> T[] bubbleSort(T[] A) {
        T temp;
        for (int i = A.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (A[j].compareTo(A[j + 1]) > 0) {
                    temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }
        return A;
    }

    /* ************************************************************* */
    // Name:        Insertion Sort
    // Basis:       Comparison
    // Complexity:   - Number of comparisons O(n^2)
    //               - Number of swaps O(n^2)
    //               - Best case O(n)
    // Space:       Constant (in place)
    // Stability:   Stable
    // Informal Description:
    //          1. Insert the first element into a new list
    //             (A list with only one value in it is already sorted)
    //          2. Insert the second element into the already sorted
    //             list
    //          3. For the third element to the nth, put in the correct
    //             place in the new list
    // Notes:
    //          Even though this algorithm is worst case O(n^2), its
    //          best case is O(n). This is because it doesn't touch
    //          elements which are already sorted. In some ways, this
    //          makes it better than some algoritms like mergesort
    //          who's best and worst case are the same since it always
    //          deals with every element. This algorithm is actually
    //          used in real life, for example parts of it are used in
    //          timsort. It's also uses constant space, and is stable,
    //          which is poggers.
    /* ************************************************************* */
    public static <T extends Comparable<T>> T[] insertionSort(T[] A) {
        T temp;
        int j;
        for (int i = 1; i < A.length; i++) {
            temp = A[i];
            j = i - 1;
            while (j >= 0 && temp.compareTo(A[j]) < 0) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = temp;
        }
        return A;
    }

    /* ************************************************************* */
    // Name:        Merge Sort
    // Basis:       Comparison
    // Complexity:   - O(nlogn) in all cases
    // Space:       O(n) memory usage (cannot sort in place)
    // Stability:   Stable
    // Informal Description:
    //          Merge sort is a recursive algorithm. It works under
    //          the principal that an array with 1 item in it is sorted.
    //          In the divide stage, the array is split up into many 
    //          arrays with single items using recursion. Then in the
    //          conquer stage, elements are recursively merged together
    //          linearlly.
    //              Base Case:
    //                  A single element, return it
    //              Divide Stage:
    //                  Split the array into two halves, left and right
    //                  Recursively call mergeSort on these halves
    //              Conquer Stage:
    //                  Combine the two sorted arrays into one sorted
    //                  array, using the merge() algorithm
    // Notes:
    //          Invented by von Neumann in 1945.
    //          The operation is O(nlogn) which is pretty much as good
    //          as you can get for general-purpose sorting algorithms.
    //          It has the same complexity in every case, since it
    //          fiddles with elements even if they're already sorted.
    //          Consequently insertionSort will be faster if lots of the
    //          elements are already sorted. Timsort merges the two
    //          algorithms for the best preformance.
    /* ************************************************************* */
    public static <T extends Comparable<T>> T[] mergeSort(T[] A) {
        throw new UnsupportedOperationException("Not supported yet.");
        // return mergeSortRecurse(A, 0, A.length - 1);
    }


    public static <T> void printArray(T[] A) {
        System.out.print("[");
        for (T i : A) {
            System.out.print(i + ", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Integer myArray[] = {5, 6, 42, 12, 62, 9, 0, 21, 19};
        // printArray(selectionSort(myArray));
        // printArray(bubbleSort(myArray));
        // printArray(insertionSort(myArray));
        
    }
}