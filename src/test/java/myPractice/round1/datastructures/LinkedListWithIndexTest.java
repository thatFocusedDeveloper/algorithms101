package myPractice.round1.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListWithIndexTest {
    private LinkedListWithIndex<Integer> linkedList;

    @BeforeEach
    public void setUp() {
        linkedList = new LinkedListWithIndex<>();
    }

    // Test addFront functionality
    @Test
    public void testAddFront() {
        linkedList.addFront(10);
        assertEquals(10, linkedList.getByIndex(0));

        linkedList.addFront(20);
        assertEquals(20, linkedList.getByIndex(0));
        assertEquals(10, linkedList.getByIndex(1));

        linkedList.addFront(30);
        assertEquals(30, linkedList.getByIndex(0));
        assertEquals(20, linkedList.getByIndex(1));
        assertEquals(10, linkedList.getByIndex(2));
    }

    // Test addBack functionality
    @Test
    public void testAddBack() {
        linkedList.addBack(10);
        assertEquals(10, linkedList.getByIndex(0));

        linkedList.addBack(20);
        assertEquals(10, linkedList.getByIndex(0));
        assertEquals(20, linkedList.getByIndex(1));

        linkedList.addBack(30);
        assertEquals(10, linkedList.getByIndex(0));
        assertEquals(20, linkedList.getByIndex(1));
        assertEquals(30, linkedList.getByIndex(2));
    }

    // Test add at specific index
    @Test
    public void testAddAtIndex() {
        linkedList.addBack(10);
        linkedList.addBack(30);

        // Insert at middle
        linkedList.add(1, 20);
        assertEquals(10, linkedList.getByIndex(0));
        assertEquals(20, linkedList.getByIndex(1));
        assertEquals(30, linkedList.getByIndex(2));

        // Insert at beginning
        linkedList.add(0, 5);
        assertEquals(5, linkedList.getByIndex(0));
        assertEquals(10, linkedList.getByIndex(1));
        assertEquals(20, linkedList.getByIndex(2));
        assertEquals(30, linkedList.getByIndex(3));
    }

    // Test getByIndex functionality
    @Test
    public void testGetByIndex() {
        linkedList.addBack(100);
        linkedList.addBack(200);
        linkedList.addBack(300);

        assertEquals(100, linkedList.getByIndex(0));
        assertEquals(200, linkedList.getByIndex(1));
        assertEquals(300, linkedList.getByIndex(2));
    }

    // Test error handling
    @Test
    public void testNullDataThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            linkedList.addFront(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            linkedList.addBack(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            linkedList.add(0, null);
        });
    }

    @Test
    public void testIndexOutOfBounds() {
        linkedList.addBack(10);
        linkedList.addBack(20);

        // Test getByIndex bounds
        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.getByIndex(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.getByIndex(2);
        });

        // Test add bounds
        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.add(-1, 100);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.add(3, 100);
        });
    }

    // Test edge cases
    @Test
    public void testEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.getByIndex(0);
        });
    }

    @Test
    public void testSingleElement() {
        linkedList.addFront(42);
        assertEquals(42, linkedList.getByIndex(0));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.getByIndex(1);
        });
    }

    // Test mixed operations
    @Test
    public void testMixedOperations() {
        linkedList.addFront(20);    // [20]
        linkedList.addBack(30);     // [20, 30]
        linkedList.addFront(10);    // [10, 20, 30]
        linkedList.add(1, 15);      // [10, 15, 20, 30]

        assertEquals(10, linkedList.getByIndex(0));
        assertEquals(15, linkedList.getByIndex(1));
        assertEquals(20, linkedList.getByIndex(2));
        assertEquals(30, linkedList.getByIndex(3));
    }
}
