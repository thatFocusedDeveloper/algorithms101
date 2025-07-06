package course.algorithms.graphs.DFS;

import course.algorithms.graphs.DFS.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {

    private Graph g;

    @BeforeEach
    public void SetUp() {
        g = new Graph(8);
    }

    @Test
    public void DepthFirstSearch() {

        g.addEdge(0, 1);
        g.addEdge(1, 0);
        g.addEdge(1, 4);
        g.addEdge(4, 1);
        g.addEdge(4, 6);
        g.addEdge(6, 4);
        g.addEdge(6, 0);
        g.addEdge(0, 6);
        g.addEdge(1, 5);
        g.addEdge(5, 1);
        g.addEdge(5, 3);
        g.addEdge(3, 5);
        g.addEdge(3, 0);
        g.addEdge(0, 3);
        g.addEdge(5, 2);
        g.addEdge(2, 5);
        g.addEdge(2, 7);
        g.addEdge(7, 2);

        System.out.println("Following is Depth First Traversal "+
                "(starting from vertex 0)");

        g.DFS(0);
    }

    @Test
    public void DepthFirstSearchDirected() {

        g.addEdge(0, 1);
        g.addEdge(1, 4);
        g.addEdge(4, 6);
        g.addEdge(6, 0);
        g.addEdge(1, 5);
        g.addEdge(5, 3);
        g.addEdge(3, 0);
        g.addEdge(5, 2);
        g.addEdge(2, 7);

        System.out.println("Following is Depth First Traversal "+
                "(starting from vertex 0)");

        g.DFS(0);
    }

}
