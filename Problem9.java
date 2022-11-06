import java.util.Random;
import java.util.Scanner;

public class Problem9 {
    public static void main(String[] args) {
        Random rand = new Random(); // Create random object for size of array
        int n = rand.nextInt(10); // Size of array variable generated randomly
        int[][] matrix = new int[n][n]; // Initialize square matrix with random size

        // Fill the matrix with integers that decrease from left to right and top to bottom
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = n*n - i - j;
            }
        }

        // Print matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        Scanner scnr = new Scanner(System.in); // Create scanner object to accept integer to find
        int k = scnr.nextInt(); // Get key integer

        // Find and print if integer is in matrix
        System.out.println(MatrixFind(k, matrix));



    }

    public static boolean MatrixFind(int k, int[][] matrix) {
        int i = 0;
        int n = matrix.length;
        int j = n -1;

        while (i < n && j >= 0) {
            if (matrix[i][j] == k) {
                return true;
            }
            if (matrix[i][j] < k) {
                j--;
            }
            else {
                i++;
            }
        }

        return false;
    }
}