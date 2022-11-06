/**
 * This program sorts a stack only using another stack. The program continually moves the elements in the stack back
 * and forth from a stack meant for the ascending order and another meant for the descending order. When the elements
 * switch stacks, a comparison value is used to sort in its respective order. After swapping from descending to
 * ascending, and then back to descending, the program checks if the stack is sorted. All of these operations are done
 * with private helper methods, so that the user is not involved in the sorting. The descending and ascending stacks are
 * global variables because they are updated frequently and used in every method, thus reducing excessive code.
 * </p>
 * Maggie Cowher
 */

import java.util.Stack;
import java.util.Random;

public class StackSorting{
    // Global variables for stacks so that the helper methods do not need arguments or return statements
    private Stack<Integer> descending;
    private Stack<Integer> ascending;

    private StackSorting() {
        descending = new Stack<>();
        ascending = new Stack<>();
    }

    public Stack<Integer> sort(Stack<Integer> s){
        boolean isSorted = false; // Flag to check if the stack is sorted

        while (!s.isEmpty()) { // Fill the descending stack with the values of the unsorted stack
            descending.push(s.pop());
        }

        // While loop that calls helper methods to move the list back and forth (sorting it) until it is sorted
       while (!isSorted) {
           moveToAscending(); // Call helper method to move the list from the descending to ascending stack
           moveToDescending(); // Call helper method to move the list from the ascending to descending stack
           isSorted = checkSorted(); // Update flag
        }

        return descending;
    }

    // Moves the elements from the descending stack to the ascending stack
    // Pops the first element and holds as the smallest
    // Pops the second element to temp value and compares to smallest, if smaller, swap and push larger element
    // If larger, push
    // Continue until the descending stack is empty
    private void moveToAscending() {
        Integer smallest = descending.pop(); // Comparison variable initialized with the top of the descending stack
        Integer temp; // temporary variable to compare to the smallest variable

        // While loop compares the current variable being popped to the variable stored as the smallest and swaps if necessary
        while (!descending.isEmpty()) { // Continues until the descending stack is empty
            temp = descending.pop(); // Temporary variable becomes the element being popped
            if (temp < smallest) { // If the temporary variable is smaller than the current smallest variable
                ascending.push(smallest); // Push the larger variable (previous smaller)
                smallest = temp; // Smallest variable updates
            } else {
                ascending.push(temp); // If the temp variable is larger, push to the ascending stack
            }
        }
        ascending.push(smallest); // Push the straggler
    }

    // Moves the elements from the ascending stack to the descending stack
    // Pops the first element and holds as the largest
    // Pops the second element to temp value and compares to largest, if larger, swap and push smaller element
    // If smaller, push
    // Continue until the ascending stack is empty
    private void moveToDescending() {
        Integer largest = ascending.pop(); // Comparison variable initialized with the top of the ascending stack
        Integer temp; // temporary variable to compare to the largest variable

        // While loop compares the current variable being popped to the variable stored as the largest and swaps if necessary
        while (!ascending.isEmpty()) { // Continues until the ascending stack is empty
            temp = ascending.pop(); // Temporary variable becomes the element being popped
            if (temp > largest) { // If the temporary variable is larger than the current largest variable
                descending.push(largest); // Push the smaller variable (previous largest)
                largest = temp; // largest variable updates
            } else {
                descending.push(temp); // If the temp variable is smaller, push to the descending stack
            }
        }
        descending.push(largest); // Push the straggler
    }

    // Checks if the descending stack is in descending order
    private boolean checkSorted() {
        boolean sorted = true; // Sorted flag that only changes to false if the list is not in order

        // Create a temporary stack that allows the method to check the entire list (pop()) without deleting it all
        Stack<Integer> copy = new Stack<>();
        while (!descending.isEmpty()) { // Move elements to copy stack
            copy.push(descending.pop());
        }

        Integer temp1 = copy.pop(); // Initialize with top of stack
        descending.push(temp1); // Move back to descending stack
        Integer temp2;

        // While loop continues until the stack is empty
        // Pops the top of the stack and sets that to the temp2 value
        // If temp2 is less than temp1 return false, else set temp1 to temp2
        // Checking for ascending order because copy stack is reversed
        while (!copy.isEmpty()) { // Must run through the whole list so that it can be recopied
            temp2 = copy.pop();
            descending.push(temp2);
            if (temp2 < temp1) {
                sorted = false;
            }
            temp1 = temp2; // Update temp1 value to move through the list
        }
        return sorted; // Return whether the list was sorted
    }

    // Driver to test code
    public static void main(String[] args) {
        Stack<Integer> test = new Stack<>(); // Declare and initialize test stack to be sorted
        Random rand = new Random(); // Create random variable that will be used to fill the stack

        // Fill the stack with random integers
        for (int i = 0; i < 10; i++) {
            test.push(rand.nextInt(10));
        }

        StackSorting sort = new StackSorting();
        test = sort.sort(test);

        // Print sorted stack
        while (!test.isEmpty()) {
            System.out.print(test.pop() + " ");
        }


    }
}