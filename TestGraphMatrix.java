/**
 * This class exists to test the GraphMatrix.java class, and thus only consists of a main method with several statements
 * to ensure that GraphMatrix.java runs properly.
 * </p>
 * Maggie Cowher
 */

import java.util.ArrayList;

public class TestGraphMatrix {
    public static void main(String[] args) {
        GraphMatrix test1 = new GraphMatrix();
        test1.init(5);
        test1.addEdge(0, 1, 1);
        test1.addEdge(0, 4, 1);
        test1.addEdge(4, 1, 1);
        test1.addEdge(1, 3, 1);
        test1.addEdge(3, 2, 1);
        test1.addEdge(2, 4, 1);
        ArrayList<Integer> neighbors1 = test1.neighbors(0);
        System.out.println(neighbors1.toString());
        ArrayList<Integer> bfs1 = test1.BFS(0);
        test1.resetVisited();
        System.out.println(bfs1.toString());
        boolean path = test1.hasPath(3,0);
        test1.resetVisited();
        System.out.println(path);
        GraphMatrix test2 = new GraphMatrix();
        test2.init(6);
        test2.addEdge(5,2,1);
        test2.addEdge(5,0,1);
        test2.addEdge(4,0,1);
        test2.addEdge(4,1,1);
        test2.addEdge(2,3,1);
        test2.addEdge(3,1,1);
        ArrayList<Integer> topSort = test2.topologicalSort();
        System.out.println(topSort.toString());
    }
}
