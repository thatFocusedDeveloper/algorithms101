package course.algorithms;

import course.algorithms.BinarySearchTree;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {

    private BinarySearchTree bst;

    @BeforeEach
    public void SetUp() {
        bst = new BinarySearchTree();
    }

    @Test
    public void Insert() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");
        bst.insert(4, "d");
        bst.insert(7, "g");
        bst.insert(6, "f");
        bst.insert(8, "h");

        assertEquals("e", bst.find(5));
        assertEquals("c", bst.find(3));
        assertEquals("b", bst.find(2));
        assertEquals("d", bst.find(4));
        assertEquals("g", bst.find(7));
        assertEquals("f", bst.find(6));
        assertEquals("h", bst.find(8));
        assertEquals(null, bst.find(99));

        bst.prettyPrint();

//        bst.printInOrderTraversal();
//        bst.printPreOrderTraversal();
        bst.printPostOrderTraversal();
    }

    @Test
    public void MinKey() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");

        assertEquals(2, bst.findMinKey());
    }

    @Test
    public void DeleteNoChild() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");
        bst.insert(4, "d");
        bst.insert(7, "g");
        bst.insert(6, "f");
        bst.insert(8, "h");

        bst.delete(2);

        assertNull(bst.find(2));

        bst.prettyPrint();
    }

    @Test
    public void DeleteOneChild() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");
        bst.insert(4, "d");
        bst.insert(7, "g");
        bst.insert(8, "h");

        bst.delete(7);

        assertNull(bst.find(7));

        bst.prettyPrint();
    }

    @Test
    public void DeleteTwoChildren() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");
        bst.insert(4, "d");
        bst.insert(7, "g");
        bst.insert(6, "f");
        bst.insert(8, "h");

        bst.delete(7);

        assertNull(bst.find(7));

        bst.prettyPrint();
    }

    @Test
    public void checkBST1() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");
        bst.insert(4, "d");
        bst.insert(7, "g");
        bst.insert(6, "f");
        bst.insert(8, "h");

        assertTrue(bst.checkBST(bst.root));
    }
}
