/** The BST class has many parts, but first begins with a few private global variables. The num_nodes variable keeps
 * track of how many elements there are in the BST, which is helpful when printing inOrder, postOrder, or preOrder, as
 * they require creating an array that must have a specified size. This variable is updated appropriately in the insert
 * and delete methods. The root variable is of TreeNode type, and is the root of the BST. The orderArray array and
 * counter integer are used in the specific order traversals, and help to clean up code for those three functions. Each
 * of these data members has public getters and setters to maintain good encapsulation practice. The BST class also has
 * a private nested class called TreeNode, that defines behaviors each element of the BST should have. These behaviors
 * are a left, right, and data member. Again, each of these variables are private and have public getters and setters.
 * The insert, delete, find, ArrayToTree, preorder_traversal, postorder_traversal, inorder_traversal, findMin, and
 * findMax methods are all public and have private helper methods. This style was chosen to allow for recursion without
 * the user having to know how the tree gets traversed, and so that the public wrapper function can begin the recursion
 * at the root of the tree.
 * </p>
 * The insert method takes in an integer and properly inserts it as a TreeNode while maintaining BST properties. The
 * find method takes an integer argument and returns true or false if it was present in the tree. The delete method
 * takes an integer argument and deletes it if it exists in the tree, or prints an error message if it does not. The
 * ArrayToTree method takes an integer array and turns it into a balances BST. The preorder_traversal returns an integer
 * array of the BST in preorder. The postorder_traversal returns an integer array of the BST in post-order. The
 * inorder_traversal returns an integer array of the BST in-order. The findMin and findMax takes a TreeNode argument as
 * the root of a subtree and returns the minimum or maximum values (respectively) of that tree. There is a main method
 * that was used by the creator of the program to test the methods.
 * </p>
 * Maggie Cowher
 */

import java.util.Arrays;

public class BST {
    // the below variable should keep track of the number of nodes in the BST. Must increment/decrement num_nodes appropriately in insert/delete and ArrayToTree.

    private int num_nodes;
    private TreeNode root;
    private int[] orderArray; // Global array variable used in the three order traversals
    private int counter; // Global integer variable used in the three order traversals

    private class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private int data;

        // Public setters and getters to access left and right pointers as well as integer data members
        public TreeNode getRight() {return right;}

        public TreeNode getLeft() {return left;}

        public void setLeft(TreeNode left) {this.left = left;}

        public void setRight(TreeNode right) {this.right = right;}

        public int getData() {return data;}

        public void setData(int data) {this.data = data;}
    }

    // Public setters and getters to access private class data members
    public int getNumNodes() {return this.num_nodes;}

    public void setNum_nodes(int num_nodes) {this.num_nodes = num_nodes;}

    public void setRoot(TreeNode root) {this.root = root;}

    public TreeNode getRoot() {return root;}

    // Inserts item and calls helper method so that recursion can be used
    // User does not have to keep track of the current variable while traversing the tree
    public void insert(int item) {
        // If the BST is currently empty, fill the root with the data item
        if (getRoot() == null) {
            TreeNode n = new TreeNode(); // Create new TreeNode to be assigned to root
            n.setData(item); // Fill with data
            setRoot(n); // Set the root with the new data
            setNum_nodes(1); // Properly set the number of nodes in the tree to 1
        } else {
            insertHelper(getRoot(), item); // If the tree is not empty, call the private helper function
        }
    }

    // Helper method to allow for recursion
    // Current pointer will move in direction dependent on item and comparison to current's data value
    // If the current pointer is a leaf node, the right or left pointer will be set accordingly with the new node
    // The new node has item as its data member
    private void insertHelper(TreeNode curr, int item) {
        if (item < curr.getData()) { // Insert item is smaller than the current TreeNode
            if (curr.getLeft() == null) { // If you are at a leaf node, set left pointer to new node with data item
                TreeNode n = new TreeNode();
                n.setData(item);
                curr.setLeft(n);
                setNum_nodes(getNumNodes() + 1); // Update num_nodes
            } else { // Traverse left subtree
                insertHelper(curr.getLeft(), item);
            }
        } else { // Insert item is greater than the current TreeNode
            if (curr.getRight() == null) { // If you are at a leaf node, set right pointer to new node with data item
                TreeNode n = new TreeNode();
                n.setData(item);
                curr.setRight(n);
                setNum_nodes(getNumNodes() + 1); // Update num_nodes
            } else { // Traverse right subtree
                insertHelper(curr.getRight(), item);
            }
        }
    }

    // Uses helper method to use recursion without user having to keep track of the current node
    public boolean find(int item) {
        if (getRoot() == null) {
            return false;
        } // Empty tree
        return findHelper(item, getRoot());
    }

    // Implement binary search with recursion to see if the item exists in the binary tree
    // Begin at the root, and if not found look at left or right subtree depending on if the item is smaller or larger than current
    // Will return true if item is found, false if not
    private boolean findHelper(int item, TreeNode curr) {
        if (curr == null) { // The item does not exist
            return false;
        }

        if (curr.getData() == item) { // Item found
            return true;
        }

        if (item < curr.getData()) { // Item not yet found, search proper subtree
            return findHelper(item, curr.getLeft());
        } else {
            return findHelper(item, curr.getRight());
        }

    }

    // The delete method takes an integer argument that needs to be deleted and searches the tree until it finds the item
    // If the tree is empty or if the item is not in the tree it will print an error message
    // Uses helper method to clean up recursion
    public TreeNode delete(int item){
        if (getRoot() == null) { // Empty tree, print error message
            System.out.println("Your BST is empty, there is nothing to delete");
            return null;
        }
        return deleteHelper(item, getRoot());

    }

    // Helper method to allow for recursive call
    // If the item is equal to curr's data member, delete
    // Three cases for delete: no children, one child, two children
    // If no children, delete. If one child, reset pointers.
    // If two children, replace using the findMin of right subtree or findMax of left subtree
    private TreeNode deleteHelper(int item, TreeNode curr) {
       if (curr == null) { // Print error message if the item was not in the tree
           System.out.println("Error: The item you specified does not exist");
           return curr;
       }

       if (item < curr.getData()) { // If the item is less than current data, traverse left subtree
           curr.setLeft(deleteHelper(item, curr.getLeft()));
       } else if (item > curr.getData()) { // If the item is greater than the current data, traverse right subtree
           curr.setRight(deleteHelper(item, curr.getRight()));
       } else { // The item is the current data, so delete
           setNum_nodes(getNumNodes() - 1); // Update number of nodes in the tree
           if (curr.getLeft() == null) { // Has one or no children
               return curr.getRight();
           } else if (curr.getRight() == null) { // Has one or no children
               return curr.getLeft();
           }

           // If it has two children, replace it with the minimum of the right subtree
           curr.setData(findMin(curr.getRight()));
           curr.setRight(deleteHelper(item, curr.getRight()));
       }

       return curr;

    }

    // Public method visible to user has recursive helper method so that user does not have to track low and high values
    public TreeNode ArrayToTree(int[] array){
        // Create new tree by filling the root with the root made in the helper method
        // The root will be linked to the rest of the tree so passing the root is sufficient
        BST arrayTree = new BST(); // New tree to create with array
        arrayTree.setRoot(ArrayToTree_helper(array, 0, array.length - 1));
        return arrayTree.getRoot();
    }


    // Private helper method allows for recursion without the user having to know or keep track of low and high
    // This method builds a new empty tree, and fills it while keeping it balanced
    // Because the array passed in the argument is sorted,
    // The balance is maintained by filling nodes with the middle element of the sub-array
    private TreeNode ArrayToTree_helper(int[] array, int low, int high){
        BST arrayTree = new BST(); // New tree to create with array
        TreeNode n = new TreeNode(); // New TreeNode that will be filled with a data member and then added to the tree

        // Base case checks if the sub-array is of length 0, then recursion is done and return the single element
        // Else if the length is negative, then the child is null of the predecessor so return null
        if ((high - low) == 0) {
            n.setData(array[high]);
            return n;
        } else if ((high - low) < 0) {
            return null;
        }

        int mid = (high + low) / 2; // Middle index is what is added next on the tree to maintain balance
        n.setData(array[mid]); // Fill the new node with the middle element of the array

        if (arrayTree.getRoot() == null) { // If the tree is empty
            arrayTree.setRoot(n); // Set the root of the tree as the above TreeNode
        }

        n.setLeft(ArrayToTree_helper(array, low, mid - 1)); // Begin filling left subtree
        n.setRight(ArrayToTree_helper(array, mid + 1, high)); // Begin filling right subtree

        return n; // Will eventually return the root to the above public method
    }

    // This public method allows the user to call pre-order traversal without having to keep track of the counter variable
    // The method creates and fills an array with a pre-order traversal of a BST
    // Uses a global array and counter to clean up recursive code
    public int[] preorder_traversal(){
        this.orderArray = new int[num_nodes]; // Initialize a new array with the size of the current amount of nodes in the BST
        this.counter = 0; // Array index begins at zero

        // Call a private helper method to begin the recursion at the root
        preOrderHelper(getRoot());
        return this.orderArray;
    }

    // This private helper method allows for recursion to traverse the BST
    // The function first adds the current TreeNode, which begins at the parent, then traverses the left, and then the right
    // The counter variable updates with each recursive call so that the value is appropriately added to the array
    private void preOrderHelper(TreeNode curr) {
        if (curr == null) {return;} // Base case

        this.orderArray[this.counter++] = curr.getData(); // First add the current TreeNode, which first adds parent of all subtree
        preOrderHelper(curr.getLeft()); // Traverse the left subtree
        preOrderHelper(curr.getRight()); // Traverse the right subtree

    }

    // This public method allows the user to call post-order traversal without having to keep track of the counter variable
    // The method creates and fills an array with a post-order traversal of a BST
    // Uses a global array and counter to clean up recursive code
    public int[] postorder_traversal(){
        this.orderArray = new int[getNumNodes()]; // Create a new array with the size of the current amount of nodes in the BST
        this.counter = 0; // Array index begins at zero

        // Call a private helper method to begin the recursion at the root
        postOrderHelper(getRoot());
        return this.orderArray;
    }

    // This private helper method allows for recursion to traverse the BST
    // The function first traverses the left, then  the right, and then adds the current TreeNode
    // The counter variable updates with each recursive call so that the value is appropriately added to the array
    private void postOrderHelper(TreeNode curr) {
        if (curr == null) {return;} // Base case

        postOrderHelper(curr.getLeft()); // Traverse left subtree
        postOrderHelper(curr.getRight()); // Traverse right subtree
        this.orderArray[this.counter++] = curr.getData(); // Add the data element to the array
    }

    // This public method allows the user to call in-order traversal without having to keep track of the counter variable
    // The method creates and fills an array with an in-order traversal of a BST
    // Uses a global array and counter to clean up recursive code
    public int[] inorder_traversal(){
        this.orderArray = new int[getNumNodes()]; // Create a new array with the size of the current amount of nodes in the BST
        this.counter = 0; // Array index begins at zero

        // Call a private helper method to begin the recursion at the root
        inOrderHelper(getRoot());
        return this.orderArray;
    }

    // This private helper method allows for recursion to traverse the BST
    // The function first traverses the left, then adds the current TreeNode, then traverses the right
    // The counter variable updates with each recursive call so that the value is appropriately added to the array
    private void inOrderHelper(TreeNode curr) {
        if (curr == null) {return;} // Base case

        inOrderHelper(curr.getLeft()); // Traverse the left subtree
        this.orderArray[this.counter++] = curr.getData(); // Add after reaching leaf node
        inOrderHelper(curr.getRight()); // Traverse the right subtree
    }

    // Search for the largest value in the BST
    // If the root is null then the tree is empty, this is an error
    // Otherwise, call a private helper function that recursively looks for the largest element
    public int findMax(TreeNode curr){
        if (curr == null) { // If the tree is empty, that is an error, findMax cannot be implemented
            System.out.println("Error: The tree is empty");
            return -1;
        }
        return findMaxHelper(curr); // Call private helper function to search tree
    }

    // The private helper function allows for recursion by calling the root initially in the wrapper function
    // This way the current TreeNode can update properly with each recursive call
    // The method will go as far right as possible, because that is the largest value in a BST
    // When the right pointer is null, return the data at the current element
    private int findMaxHelper(TreeNode curr) {
        if (curr.getRight() == null) { // If the current TreeNode is the rightmost node in the BST, return its data
            return curr.getData();
        }
        return findMaxHelper(curr.getRight()); // Else traverse the right subtree
    }

    // Search for the smallest value in the BST
    // If the root is null then the tree is empty, this is an error
    // Otherwise, call a private helper function that recursively looks for the smaller element
    public int findMin(TreeNode curr){
        if (curr == null) { // If the tree is empty, that is an error, findMax cannot be implemented
            System.out.println("Error: The tree is empty");
            return -1;
        }
        return findMinHelper(curr); // Call private helper function to search tree
    }

    // The private helper function allows for recursion by calling the root initially in the wrapper function
    // This way the current TreeNode can update properly with each recursive call
    // The method will go as far left as possible, because that is the smallest value in a BST
    // When the left pointer is null, return the data at the current element
    private int findMinHelper(TreeNode curr) {
        if (curr.getLeft() == null) { // If the current TreeNode is the leftmost node in the BST, return its data
            return curr.getData();
        }
        return findMinHelper(curr.getLeft()); // Else traverse the left subtree
    }

    // Driver code to test function
    public static void main(String[] args) {
        BST test1 = new BST(); // Create BST object
        test1.insert(15);
        test1.insert(14);
        test1.insert(16);
        test1.insert(13);
        System.out.println(test1.find(16));
        int[] test2 = new int[8];
        for (int i = 0; i < 8; i++) {
            test2[i] = i + 1;
        }
        BST test3 = new BST();
        test3.setRoot(test3.ArrayToTree(test2));
        System.out.println(test3.find(3));
        int[] inOrder = test1.inorder_traversal();
        int[] preOrder = test1.preorder_traversal();
        int[] postOrder = test1.postorder_traversal();
        System.out.println("In Order: " + Arrays.toString(inOrder));
        System.out.println("Pre Order: " + Arrays.toString(preOrder));
        System.out.println("Post Order: " + Arrays.toString(postOrder));
        System.out.println("Max: " + test1.findMax(test1.getRoot()));
        System.out.println("Min: " + test1.findMin(test1.getRoot()));
        test1.delete(14);
        inOrder = test1.inorder_traversal();
        System.out.println("In Order: " + Arrays.toString(inOrder));
    }
}