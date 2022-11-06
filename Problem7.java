import java.util.Random;
import java.util.Arrays;

public class Problem7 {
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

        System.out.println(Arrays.toString(array));

        boolean isSorted = my_sort(array);

    }

    public static boolean my_sort(int[] array) {
        for (int i = 0; i < array.length; i++) {            // (4n - 4)(n) = 4n^2 - 4n
            for (int j = 0; j < array.length - 1; j++) {    // 4(n - 1) = 4n - 4
                if (array[j] < array[j + 1]) {              // 1
                    int temp = array[j];                    // 1
                    array[j] = array[j + 1];                // 1
                    array[j + 1] = temp;                    // 1
                }
            }
        }                                                   // The big-Oh run time for this method is O(n^2)

        System.out.println(Arrays.toString(array));
        return true;
    }
}
