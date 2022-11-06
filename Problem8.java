import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Problem8 {
    public static void main(String[] args) {
        // Create int variable n for array size
        int n = 10;

        // Declare array
        int[] array = new int[n];

        // Fill array with random integers
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(20);
        }

        // Sort array
        array = my_sort(array);

        System.out.print(Arrays.toString(array));


        Scanner scnr = new Scanner(System.in);

        boolean isFound = fast_search(scnr.nextInt(), array);
        System.out.println(isFound);

    }

    public static boolean fast_search (int k, int[] array) {
        int begin = 0;              // 1
        int mid;
        int end = array.length - 1; // 2

        while (begin <= end) {      // log(n) + 8
            mid = (begin + end)/2;  // 3
            if (k == array[mid]) {  // 1
                return true;
            }
            if (k > array[mid]) {   // 2*1 = 2
                end = mid - 1;      // 2
            } else {
                begin = mid + 1;    // 2
            }
        }                           // The big-Oh run time for this method is O(log(n))

        return false;
    }

    // Method to sort array
    public static int[] my_sort(int[] array) {
        for (int i = 0; i < array.length; i++) {            // (4n - 4)(n) = 4n^2 - 4n
            for (int j = 0; j < array.length - 1; j++) {    // 4(n - 1) = 4n - 4
                if (array[j] < array[j + 1]) {              // 1
                    int temp = array[j];                    // 1
                    array[j] = array[j + 1];                // 1
                    array[j + 1] = temp;                    // 1
                }
            }
        }                                                   // The big-Oh run time for this method is O(n^2)

        return array;
    }
}
