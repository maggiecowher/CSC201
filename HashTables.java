/**
 * This java file has two functions that can be performed on a HashSet, along with a main function for testing. The
 * first function is findSumPair which takes a target integer and an array of integers. The goal of the function is to
 * find a pair of integers that sum to the target. The function first checks if the array is empty, and exits the
 * method if so. Otherwise, it will create a HashSet of type Integer and then add all the integers in the array to the
 * set. A boolean variable is used to flag whether a pair was found, so that if it is not, a message will print saying
 * as much. The method then loops through the entire length of the array and checks if the set contains a number equal
 * to the target minus the current integer element. This means that the current integer element plus that number are a
 * sum pair for the target. If this is true, the flag will change to true, an appropriate message will be printed to
 * indicate the pair found, and the loop will break.
 * </p>
 * The next function is DistinctValues which takes an array of integers as an argument. The goal of this function is to
 * return the number of unique or distinct values that exist in the array. The function first checks if the array is
 * empty, in which case it will return zero. If the array is not empty, it will declare a new HashSet and then loop
 * through the entire array and add its elements to the set. Because of the restrictions of a HashSet, the only integers
 * that will be added are ones that do not already exist in the set. The loop adds the absolute value of the array's
 * integer elements, so by the end of the loop the HashSet will be filled with all the distinct absolute value integers
 * that existed in the array. At this point, it is sufficient to return the size of the HashSet, because this value is
 * equal to the number of unique array elements.
 * </p>
 * Maggie Cowher
 * */

import java.util.HashSet;
import java.lang.Math;

public class HashTables{

    // This function finds a sum pair for a given target and given array. Because the values are put into a hash set,
    // the contains() and add() function are constant time. Therefore, the first for loop requires O(n) time, and the
    // second for loop requires O(n) time. This means that the entire method has a runtime of O(n).
    public static void findSumPair(int target, int[] array){
        // Check if array is null
        if (array == null) {return;}

        // Copy elements of the array into the hashset
        HashSet<Integer> set = new HashSet<>();
        for (int i : array) {
            set.add(i);
        }

        boolean pairFound = false; // Flag to print that no sum pair was found after the loop below

        // Find a sum pair
        for (int i : array) {
            if (set.contains(target - i)) { // If there is some value that is equal to the target minus the current value
                // Sum pair exists, print, change flag, and exit loop
                pairFound = true;
                System.out.println("Pair found: " + i + ", " + (target - i));
                break; // Do not need to find all sum pairs
            }
        }

        // If a sum pair was not found, print that none was found
        if (!pairFound) {
            System.out.println("No sum pair was found for the target " + target);
        }

    }

    // This function returns the number of distinct values in a given array
    // This is done using a hash set, and adding the absolute values of each element to the hash set
    // Because of the rules of hash sets, the values will only be added if they do not already exist in the hash set
    // This means that all the values in the hash set will be unique, so it suffices to return the size of the hash set
    // The add() method for a hash set has a constant run time,
    // so the runtime of the entire function is just the size of the array
    public static int DistinctValues(int[] array){
        // Check if the array is empty
        if (array == null) {return 0;}

        // Convert each array element to its absolute value and add it to the hash set
        // It will only be added if it does not already exist in the hash set
        HashSet<Integer> set = new HashSet<>();
        for (int i : array) {
            set.add(Math.abs(i));
        }

        return set.size(); // The size of the hash set will be the number of unique values because of the condition stated above
    }

    public static void main(String[] args){
        // For tests
        int[] array = {5, 2, 3, 4, 1};
        findSumPair(7, array);
        System.out.println("Distinct values: " + DistinctValues(array));
        int[] array1 = {1, 1, 1, 1};
        int[] array2 = {-1, 1};
        System.out.println("Distinct values: " + DistinctValues(array1));
        System.out.println("Distinct values: " + DistinctValues(array2));

    }
}
