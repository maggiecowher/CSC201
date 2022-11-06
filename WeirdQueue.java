/**
 * The WeirdQueue class implements a list with the behavior of a queue but uses two stacks to do so. This is done by
 * having one stack for insertion and one stack for deletion. The program moves the items from stack to stack when
 * inserting or deleting, and by doing this, reverses the list order. This means that one stack can be used to insert
 * at the end of the list, while the other can be used to delete from the beginning of the list.
 * </p>
 * Maggie Cowher
 */

import java.util.Stack;

public class WeirdQueue{
    Stack insertS; // Stack used to insert into the queue
    Stack removeS; // Stack used to remove from the queue

    public WeirdQueue(){
        this.insertS = new Stack();
        this.removeS = new Stack();
    }

    // To insert an element, if the elements are in the remove stack, pop them all to the insert stack
    // Then push to the top of the stack
    public void enqueue(Object item){ // O(n)
        if (insertS.isEmpty()) {
            while (!removeS.isEmpty()) { // Only happens if elements are in the removal stack, meaning the queue is not empty
                    insertS.push(removeS.pop()); // Reverses the order while moving the elements from the removal stack to the insertion stack
            }
        }
        insertS.push(item); // Insert the item
    }

    // To remove an element, if the elements are in the insert stack, pop them all to the remove stack
    // Then pop from the top of the stack
    public Object dequeue(){ // O(n)
        if (removeS.isEmpty()) { // Error condition to make sure the queue is not empty, if it is, nothing can be deleted
            System.out.println("Error: The WeirdQueue is empty");
            return null;
        }

        while (!insertS.isEmpty()) { // Only happens if insertion stack has elements
            removeS.push(insertS.pop()); // Reverses the order while moving the elements from the insertion stack to the removal stack
        }

        Object popped = removeS.pop(); // Create a temporary object to hold the item that was removed (it won't be able to be accessed from the stack after removing)
        return popped; // Return the deleted item

    }

    // Driver to test the code
    public static void main(String[] args) {
        WeirdQueue test = new WeirdQueue();
        Object item = new Object();
        item = 4;
        test.enqueue(item);
        item = 3;
        test.enqueue(item);
        item = 2;
        test.enqueue(item);
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
    }
}