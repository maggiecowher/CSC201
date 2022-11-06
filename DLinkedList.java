/**
 * The DLinkedList class implements MyList, so it must implement the insert(), append(), clear(), isEmpty(), size(),
 * replace(), remove(), and get() methods. The methods addAtTail() and addAtHead() were created outside the MyList
 * interface to clean up the code. This class has the functions necessary to create a DoublyLinkedList, meaning that
 * it has a head and tail pointer, and can be traversed forward and backwards. The nested private Node class uses
 * encapsulation to prevent errors from users, and creates the next and previous pointers as well as a data Object.
 * </p>
 * Maggie Cowher
 */

public class DLinkedList implements MyList{
    Node head; // Header node
    Node tail; // Trailer node
    int counter; // Variable to keep track of the size of the list

    // Null constructor
    public DLinkedList() {
       this.head = null;
       this.tail = null;
       this.counter = 0;
    }

    // This method inserts an item at a give index
    // If the list is empty it will print an error message
    // If the index is invalid, meaning it is negative or larger than the size of the list, it will print an error message
    // Else if the index is valid it will insert the item at the correct index and move pointers as needed
    @Override
    public void insert(int index, Object item) {
        // Create node with item
        Node n = new Node();
        n.setData(item);

        if (isEmpty()) { // If list is empty
            if (index == 0) { // If the index is 0 then it is a valid call
                head = n;
                tail = n;
                counter++; // Update counter because an item was added
            } else {
                System.out.println("Error: The list is empty");
            }
        } else if ((index < 0) || (index > size() - 1)) { // Index is negative or larger than the size and therefore invalid
            System.out.println("Error: That is an invalid index");
        } else if (index == 0) { // Insert before the head
            addAtHead(n);
            counter++;
        } else if (index == size() -1) { // Insert before the tail
            tail.setPrev(n);
            n.setNext(tail);
            counter++;
        } else { // The index is somewhere in the list but not the head or tail
            Node curr = head; // Curr pointer
            int i = 0; // Counter to compare to index

            // Iterate through the list until reaching the index before the index to insert or reaching the end of the list
            while (curr.getNext() != null) {
                if (i + 1 == index) { // Insert item and change pointers
                    curr.setNext(n);
                    n.setPrev(curr);
                    n.setNext(curr.getNext());
                    curr.getNext().setPrev(n);
                    counter++;
                    break;
                }
                i++;
                curr = curr.getNext();
            }
        }
    }

    // This method inserts an item at the end of the list
    // If the list is empty it will initialize both the head and tail with the item
    @Override
    public void append(Object item) {
        // Create a node with the data item
        Node n = new Node();
        n.setData(item);

        if (isEmpty()) { // If the list is empty, set the tail and head to new node
            head = n;
            tail = n;
        } else { // else add at tail
            addAtTail(n);
        }
        counter++; // Update counter by 1 because one element was added
    }

    // This method clears the entire list, if the list is already empty it does nothing
    // If the list is not empty, it sets head and tail to null
    // By doing this, all pointers (links) to other elements are null and the list is empty
    @Override
    public void clear() {
        if (!isEmpty()) {
            head = null;
            tail = null;
            counter = 0; // Update the counter variable to 0 because the list is now empty
        }
        System.out.println("The list is now empty");
    }

    // This method returns true if the list is empty, meaning both the head and tail are null, else it returns false
    @Override
    public boolean isEmpty() {
        return ((head == null) && (tail == null));
    }

    // This method returns the size of the list, which is stored by a counter value
    // The global counter variable tracks the size and is updated throughout the class
    @Override
    public int size() { // return the size of the list, else -1. Must require O(1) ops.
        return counter;
    }

    // replaces the existing Object  at 'index' with 'item'.
    // return true, if replace succeeds.
    // return false, otherwise.
    @Override
    public boolean replace(int index, Object item) {
        // Create new node with data item
        Node n = new Node();
        n.setData(item);

        boolean success = false; // Flag if element was added

        if ((index < 0) || (index > size() - 1)) { // Invalid index
            System.out.println("Error: That is an invalid index");
        } else if (index == 0) { // Replace head
            // Change links
            head.getNext().setPrev(n);
            n.setNext(head.next);
            // Change head pointer
            head = n;
            success = true; // Replace was successful
        } else if (index == size() -1) { // Replace tail
            // Change links
            tail.getPrev().setNext(n);
            n.setPrev(tail.getPrev());
            // Change tail pointer
            tail = n;
            success = true; // Replace was successful
        } else { // Else iterate through the list to reach the index
            Node curr = head;
            int i = 0;
            while (curr.getNext() != null) { // Iterate through the whole list
                if (i == index) {
                    curr.getNext().setPrev(n);
                    curr.getPrev().setNext(n);
                    curr = null;
                    success = true; // Replace was successful
                    break; // If index is reached, there's no need to keep iterating through the list
                }
                i++;
                curr = curr.getNext();
            }
        }

        return success;
    }

    // Remove the element at a given index (if the index is valid) and update the counter variable
    @Override
    public void remove(int index) {
        if ((index < 0) || (index > size() - 1)) { // The index is negative or larger than the size of the list
            System.out.println("Error: That is an invalid index");
        } else if (index == 0) { // Remove at the head
            head = head.getNext(); // Change head pointer
            head.setPrev(null); // Change link
            counter--; // Update counter because a node was removed
        } else if (index == size() - 1) { // Remove at the tail
            tail = tail.getPrev(); // Change tail pointer
            tail.setNext(null); // Change link
        } else { // Iterate through the list to find the index and remove the node
            Node curr = head;
            int i = 0;

            while (curr.getNext() != null) {
                if (i == index) {
                    // Change links and erase all memory of the current node
                    curr.getPrev().setNext(curr.getNext());
                    curr.getNext().setPrev(curr.getPrev());
                    curr = null;
                    counter--;
                    break;
                }
                i++;
                curr = curr.getNext();
            }
        }
    }

    // Check if the index is valid, if it is return (but do not delete) the element at the index
    @Override
    public Object get(int index) {
        if ((index < 0) || (index > size() - 1)) { // Index is negative or larger than the size of the list
            System.out.println("Error: That is an invalid index");
            return null;
        } else if (index == 0) { // Return the element at the head
            return head;
        } else if (index == size() - 1) { // Return the element at the tail
            return tail;
        } else { // Iterate through the list to find the element to return
            Node curr = head;
            int i = 0;

            while (curr.getNext() != null) {
                if (i == index) {
                    return curr;
                }
                i++;
                curr = curr.getNext();
            }
        }

        // Should not reach this, but just in case
        System.out.println("Error: Element could not be found");
        return null;
    }

    // Helper method to avoid repetitive code
    // Inserts the node after the tail and changed pointers
    public void addAtTail(Node n) {
        // Create links to item with next and prev
        tail.setNext(n);
        n.setPrev(tail);
        // Change tail pointer
        tail = n;
    }

    // Helper method to avoid repetitive code
    // Inserts the node before the head of the list and changes pointers
    public void addAtHead(Node n) {
        // Create links to item object with next and prev
        head.setPrev(n);
        n.setNext(head);
        // Change head pointer
        head = n;
    }

    // Define any variables you want with the Encapsulation design principle.
    // Private nested Node class
    private static class Node {
        private Object data;
        private Node next;
        private Node prev;

        public void setData(Object data) {
            this.data = data;
        }

        public Object getData() {
            return this.data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return this.next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getPrev() {
            return this.prev;
        }
    }

    // Driver to test LinkedList
    public static void main(String[] args) {
        DLinkedList test = new DLinkedList();
        test.append(1);
        System.out.println("Size: " + test.size());
        test.insert(0, 2);
        System.out.println("Size: " + test.size());
        test.insert(17, 3);
        System.out.println("Size: " + test.size());
        test.append(5);
        System.out.println("Size: " + test.size());
        test.append(6);
        System.out.println("Size: " + test.size());
        test.append(7);
        System.out.println("Size: " + test.size());
        System.out.println(test.replace(1, 3));
        System.out.println("Size: " + test.size());
        test.remove(0);
        System.out.println("Size: " + test.size());
    }
}