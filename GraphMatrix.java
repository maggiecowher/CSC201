/**
 * This class implements the Graph.java interface to create an adjacency matrix. The methods implemented from the
 * interface are: init() which initializes the adjacency matrix with some number of vertices; nodeCount() which returns
 * the number of vertices; edgeCount() which returns the number of edges; addEdge() which creates a directed edge from
 * one vertex to another with some weight; getWeight() and setWeight() to maintain good encapsulation practice;
 * removeEdge() to remove a directed edge from some adjacent vertices; hasEdge() which determines if there is an edge
 * between some vertices; neighbors() which returns an ArrayList of the neighbors of some vertex; and BFS() and
 * resetVisited() which performs a breadth-first-search of the graph and resets the boolean array to track if some
 * vertex has been visited.
 * </p>
 * The class has two unique methods, hasPath() and topologicalSort(). hasPath() uses the BFS() method to determine if
 * there exists a path between two vertices. topologicalSort() reworks the BFS algorithm to sort the graph in
 * topological order, and returns a sorted ArrayList.
 * </p>
 * Maggie Cowher
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GraphMatrix implements Graph {
    private int[][] matrix;
    private Object[] nodeVals;
    private int edges;
    private boolean[] visited;

    // This method uses the number of vertices in the graph to initialize the private variable to track said number.
    // The method also uses the integer argument to initialize the adjacency matrix dimensions.
    public void init(int n) {
        this.nodeVals = new Object[n];
        this.matrix = new int[n][n];
        this.visited = new boolean[n];
    }

    // This method returns the number of nodes (or vertices) in the graph.
    // Because there is a global variable for this value, just return that data.
    public int nodeCount() {
        return this.nodeVals.length;
    }

    // This method returns the number of edges.
    // In order for this method to run in constant time, a global variable must keep track of the number of edges.
    // This method then simply returns the data from the global variable.
    public int edgeCount() {
        return this.edges;
    }

    // This method adds an edge with some weight to connect two vertices.
    // The vertices to be connected and the weight are arguments in the method signature.
    // This method creates the edge from the first vertex specified to the second vertex specified.
    // This method also updates the global edge variable if there was not already an edge between the two vertices.
    public void addEdge(int v, int w, int wgt) {
        // Error conditions to check that the indices and weight are valid
        if (v < 0 || v >= this.matrix.length || w < 0 || w >= this.matrix.length || wgt <= 0) {
            return;
        }
        // Check if an edge already existed, if not, update edge count
        if (this.matrix[w][w] == 0 && this.matrix[v][w] == 0) {
            this.edges++;
        }
        this.matrix[v][w] = wgt; // Add edge with weight
    }

    // This method returns the weight of the edge connected two vertices that are in the argument of the method signature.
    public int getWeight(int v, int w) {
        if (v < 0 || v >= this.matrix.length || w < 0 || w >= this.matrix.length) {return 0;} // Error condition if the indices are invalid
        return this.matrix[v][w];
    }

    // This method sets the weight of the edge in the direction going from the first vertex to the second vertex argued.
    public void setWeight(int v, int w, int wgt) {
        if (v < 0 || v >= this.matrix.length || w < 0 || w >= this.matrix.length) {return;} // Error condition if the indices are invalid
        this.matrix[v][w] = wgt;
    }

    // This method removes the edge in the direction going from the first vertex to the second vertex argued.
    // The method also updates the edge counter.
    public void removeEdge(int v, int w) {
        if (v < 0 || v >= this.matrix.length || w < 0 || w >= this.matrix.length) {return;} // Error condition if the indices are invalid
        if (matrix[v][w] != 0) { // Only deletes the edge if it is not already empty
            this.matrix[v][w] = 0;
            this.edges--;
        }
    }

    // This method checks if there is an edge between the two specified vertices.
    // The direction is not important in this method so both row/column locations must be checked.
    // If the value at both is zero, that means there is no edge connected the vertices.
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v >= this.matrix.length || w < 0 || w >= this.matrix.length) {return false;} // Error condition if the indices are invalid
        return this.matrix[v][w] != 0 || this.matrix[w][v] != 0;
    }

    // This method returns all the neighbors of a given vertex.
    // This is done by creating an empty array list, and traversing the row and column of the given vertex.
    // If there is a value other than zero at any location in the row, it will return the column. The same goes for any
    // non-zero value in the column and its corresponding row, as these are its neighbors.
    public ArrayList<Integer> neighbors(int v) {
        if (v < 0 || v >= this.matrix.length) {return null;} // Error condition if the index is invalid
        ArrayList<Integer> n = new ArrayList<>(); // Initialize ArrayList to be returned
        // The neighbors of some vertex is defined as being the "to" part in a directed graph.
        // Therefore, it is sufficient to only check the row of the given vertex,
        // because if the graph is undirected, there will be an edge in both the row and column location of the vertex
        for (int i = 0; i < this.matrix.length; i++) {
            if (this.matrix[v][i] != 0) { // If there is an edge
                n.add(i);
            }
        }
        return n;
    }

    // This method resets the global visited array to all false using the java utilities package.
    public void resetVisited() {
        Arrays.fill(this.visited, false);
    }

    // This method fills the ArrayList with the BFS performed in the function.
    // An ArrayList and a queue with a LinkedList structure are initialized at the beginning of the function.
    // The queue is enqueued with the starting vertex, which is in the argument of the method signature
    // When a vertex is added to the queue, the visited flag for that index is changed to true
    // There is a while loop in this method that continues until the queue is empty. Within this loop, the integer in
    // the argument is filled with the popped value from the queue (which is the head of the linked list) and then added
    // to the BFS ArrayList. There is a for loop inside the while loop that checks the row of the integer that was just
    // removed to see if there are any non-visited neighbors, and if so those are added to the queue and the boolean
    // array is updated properly.
    public ArrayList<Integer> BFS(int v) {
        // Initialize data structures to hold elements
        ArrayList<Integer> bfs = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        // Add the starting vertex to the queue and change its visited flag to true
        queue.add(v);
        this.visited[v] = true;

        // Continues until the queue is empty, meaning that all vertices reachable from the starting vertex have been visited
        while (queue.size() != 0) {
            // Dequeue the queue and add it to the BFS ArrayList
            v = queue.poll();
            bfs.add(v);

            // For loop to traverse a row to find unvisited neighbors
            for (int i = 0; i < this.matrix.length; i++) {
                if (this.matrix[v][i] != 0 && !this.visited[i]) {
                    queue.add(i);
                    this.visited[i] = true;
                }
            }
        }

        return bfs; // Return BFS array
    }

    // The hasPath method utilizes the BFS function to determine if there is a path from the vertex v to the vertex w.
    // The method calls the BFS function with the vertex v as an argument.
    // This means that if there is a path from v to w, visited[w] will be true after running the BFS function
    public boolean hasPath(int v, int w) {
        BFS(v); // Call BFS method with the "from" vertex as the argument
        // If w was visited, there is a path from v to w, and the visited array is an array of booleans,
        // so it will return true if there is a path from v to w
        return this.visited[w];
    }

    // This method performs a topological sort on the graph by filling an ArrayList with the graph's elements in the
    // topological order. This is done by first adding the vertices with inDegree zero to a queue, then removing the
    // first element in that queue and adding it to the arraylist to be removed, and decrementing its neighbor's
    // in-degrees by one, and repeating this pattern until the queue is empty.
    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> queue = new ArrayList<>(); // Queue to hold vertices with in degrees of 0
        ArrayList<Integer> inDegree = new ArrayList<>(); // ArrayList to hold the inDegree values of each vertex
        ArrayList<Integer> sorted = new ArrayList<>(); // The ArrayList that will be filled with the topologically sorted vertices

        // Traverse the matrix to find the inDegree of each value
        for (int i = 0; i < this.matrix.length; i++) {
            int inD = 0;
            for (int j = 0; j < this.matrix.length; j++) {
                if (this.matrix[j][i] != 0) {
                    inD++;
                }
            }
            inDegree.add(i, inD);
        }

        // Enqueue all vertices of in-degree 0
        for (int i = 0; i < inDegree.size(); i++) {
            if (inDegree.get(i) == 0) {
                queue.add(i);
            }
        }

        // While loop continues until the queue is empty
        while (!queue.isEmpty()) {
            // Dequeue the element at the front of the queue and add it to the sorted list
            int removed = queue.remove(0);
            sorted.add(removed);
            // Decrease the in-degree of the neighbors of the removed element
            ArrayList<Integer> n = neighbors(removed); // Use neighbors() method to fill an arraylist with the vertex's neighbors
            for (int i = 0; i < inDegree.size(); i++) {
                // If the current element in the inDegree list is a neighbor of the removed element, reduce that neighbor's in-degree
                if (n.contains(i)) {
                    inDegree.set(i, inDegree.get(i) - 1);
                    if (inDegree.get(i) == 0) { // If inDegree becomes 0, add to queue
                        queue.add(i);
                    }
                }
            }
        }

        return sorted;
    }


}
