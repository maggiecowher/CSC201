/**
 * The Quicksort class sorts an array of integers using the Quicksort algorithm. This class utilizes a private
 * quicksort helper method so that the user does not need to keep track of low and high pointers. The public method
 * checks if the array is null, and otherwise calls the helper method within the original unsorted array. The helper
 * method takes the sorted array, checks that the low pointer is less than the high pointer, and assigns a new pivot
 * index using the partition method. The partition method is the main worker in the Quicksort algorithm. This method
 * takes the array and high and low pointers as arguments, selects a pivot at the low pointer, moves the pivot to the
 * end of the sub-array using a swap method, then uses a for loop to move the pointers inward without reaching. If the
 * value at the low index is greater than the pivot, swap the value at the low pointer and the first index in the
 * sub-array. After the partition is done, move the pivot to its correct sorted location, and return the first index in
 * the right sub-array. This is the index of the new pivot position, which will be used to call the helper method
 * recursively on the left and right sub-arrays.
 * </p>
 * Maggie Cowher
 */

import java.util.Arrays;
import java.util.Random;

public class Quicksort{
    // Simplified function visible to users so that they do not need to know low/high
    public static void my_quicksort(int[] array){
        // Make sure array is not empty
        if (array == null) {
            return;
        }

        // Call helper method so that user does not need to know high and low
        quicksort_helper(array, 0, array.length - 1);
    }

    // Takes the array, with low and high as input and performs partitioning and recursive calls to the left and right
    private static void quicksort_helper(int[] array, int low, int high){
        if (low < high) { // While the bounds have not met, recursively partition array
            int newPivotIndex = partition(array, low, high);

            // Split array into two halves based on where pivot was placed after partitioning
            quicksort_helper(array, low, newPivotIndex - 1);
            quicksort_helper(array, newPivotIndex + 1, high);
        }

    }

    // Splitting the array into two halves based on the pivot and returns the index of the pivot
    private static int partition(int[] array, int low, int high){
        int pivot = array[low]; // Pivot is in the first element in the sub-array

        swap(array, high, low); // Put pivot at the end of partition
        for (int i = low; i < high; i++) { // For loop prevents low and high from meeting
            if (array[i] >= pivot) { // If the value is greater than the pivot, swap and move the low pointer to the left
                swap(array, low, i);
                low++;
            }
        }

        swap(array, low, high); // Move pivot to the correct position after sorting

        return low; // Returns the first index in the right array
    }

    // Helper method to avoid repetitive swap code
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Driver code to test the quicksort method
    public static void main(String[] args) {
        int[] array = new int[10]; // Declare and initialize array

        Random rand = new Random(); // Create random object

        // Fill array with random integer values
        for(int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(20);
        }

        // Print array before sorting
        System.out.println(Arrays.toString(array));

        // Sort with quicksort
        my_quicksort(array);

        // Print array after sorting
        System.out.println(Arrays.toString(array));
    }
}