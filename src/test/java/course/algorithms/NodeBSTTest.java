package course.algorithms;

import course.algorithms.NodeBST;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NodeBSTTest {

    private NodeBST node;

    @BeforeEach
    public void SetUp() {
        node = new NodeBST(10);
        node.insert(5);
        node.insert(15);
        node.insert(8);
    }

    @Test
    public void Contains() {
        assertTrue(node.contains(5));
        assertTrue(node.contains(15));
        assertTrue(node.contains(8));
    }

    @Test
    public void PrintOrder() {
        node.printInOrder();
    }
}
