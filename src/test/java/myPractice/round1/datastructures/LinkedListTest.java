package myPractice.round1.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD Test Template for LinkedList Implementation
 *
 * Test Categories:
 * 1. Constructor & Initial State
 * 2. Add Operations (addFirst, addLast)
 * 3. Remove Operations (removeFirst, removeLast, remove)
 * 4. Get Operations (getFirst, getLast)
 * 5. Utility Operations (size, isEmpty, contains)
 * 6. Edge Cases & Error Handling
 * 7. Complex Operations (toString, iteration)
 */
public class LinkedListTest {

    private LinkedList list;

    @BeforeEach
    public void setUp() {
        list = new LinkedList();
    }

    // Constructor and Initial State Tests
    @Test
    public void testCreateEmptyList() {
        assertTrue(list.isEmpty(), "New list should be empty");
        assertEquals(0, list.size(), "Size of new list should be 0");
        assertThrows(IllegalStateException.class, ()->list.getFirst());
    }

    // Add Operations Tests
    @Test
    public void testAddFirstElement() {
        list.addFront(10);
        assertFalse(list.isEmpty());
        assertEquals(10, list.getFirst());
    }

    @Test
    public void testAddFront() {
        list.addFront(10);
        list.addFront(20);
        list.addFront(30);

        assertEquals(30, (int) list.getFirst());
    }


    @Test
    public void testAddBack() {
        list.addBack(10);
        list.addBack(20);
        list.addBack(30);

        assertEquals(10, (int) list.getFirst());
        assertEquals(30, (int) list.getLast());
        assertThrows(IllegalArgumentException.class, ()->list.addBack(null));
    }


    @Test
    public void testGetFirst() {
        assertThrows(IllegalStateException.class, ()->list.getFirst());

        list.addFront(10);
        list.addFront(20);
        list.addFront(30);

        assertEquals(30, (int) list.getFirst());
    }

    @Test
    public void testGetLast() {
        assertThrows(IllegalStateException.class, () -> list.getLast() );

        list.addFront(10);
        list.addFront(20);
        list.addFront(30);
        list.addBack(40);

        assertEquals(40, (int) list.getLast());
        assertEquals(30, (int) list.getFirst());
    }


    @Test
    public void testDeteleteFirst() {
        list.addFront(10);
        list.addFront(20);
        list.addFront(30);

        assertEquals(30, (int) list.getFirst());
        assertEquals(3, list.size());

        list.deleteFirst();
        assertEquals(20, (int) list.getFirst());
        assertEquals(2, list.size());

        list.deleteFirst();
        assertEquals(10, (int) list.getFirst());
        assertEquals(1, list.size());
    }

    @Test
    public void testDeleteLast() {
        list.addFront(10);
        list.addFront(20);
        list.addFront(30);

        assertEquals(10, (int) list.getLast());
        assertEquals(3, list.size());

        list.deleteLast();
        assertEquals(20, (int) list.getLast());
        assertEquals(2, list.size());

        list.deleteLast();
        assertEquals(30, (int) list.getLast());
        assertEquals(1, list.size());
    }

    @Test
    public void testDelete() {
        list.addFront(10);
        list.addFront(20);
        list.addFront(30);

        assertEquals(3, list.size());

        list.delete(20);
        assertEquals(2, list.size());
        assertEquals(30, (int) list.getFirst());
        assertEquals(10, (int) list.getLast());

        list.delete(30);
        assertEquals(1, list.size());
        assertEquals(10, (int) list.getFirst());
        assertEquals(10, (int) list.getLast());

        list.delete(10);
        assertEquals(0, list.size());
    }

    @Test
    public void testContains() {
        assertFalse(()->list.contains(10));
        list.addFront(10);
        assertTrue(list.contains(10));
        list.addFront(20);
        assertTrue(list.contains(20));
        list.addFront(30);
        assertTrue(list.contains(30));
        list.delete(20);
        assertFalse(list.contains(20));
    }

    @Test
    public void testClear() {
        list.addFront(10);
        list.addFront(20);
        list.addFront(30);
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }
}