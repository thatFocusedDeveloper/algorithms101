package course.algorithms.graphs.DFS;

import course.algorithms.graphs.DFS.PathFinder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PathFinderTest {

    @Test
    public void FindPath() {
        PathFinder g = new PathFinder(8);

        g.addEdge(0, 1);
        g.addEdge(1, 4);
        g.addEdge(4, 6);
        g.addEdge(1, 5);
        g.addEdge(5, 3);
        g.addEdge(5, 2);
        g.addEdge(2, 7);
        g.addEdge(0, 3); // this edge missed! naive :)


        System.out.println("Depth First Search HasPath:");

        assertEquals("[0, 1, 5, 2, 7]", g.findPath(0,7));
        assertEquals("[0, 1, 4, 6]", g.findPath(0,6));
        assertEquals("[0, 3]", g.findPath(0,3));

        assertEquals("[5, 2]", g.findPath(5,2));
        assertEquals("[5, 2, 7]", g.findPath(5,7));
    }
}
