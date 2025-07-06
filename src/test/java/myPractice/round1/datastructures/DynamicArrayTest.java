package myPractice.round1.datastructures;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DynamicArrayTest {

    private DynamicArray<String> array;

    @BeforeEach
    public void setUp() {
        array = new DynamicArray<>(2);
    }

    @Test
    public void testAddElementToEnd() {
        array.add("Element1");
        assertEquals(1, array.size());
        assertEquals("Element1", array.get(0));
    }

    @Test
    public void testRetrieveElementFromIndex() {
        array.add("Element1");
        array.add("Element2");
        assertEquals("Element2", array.get(1));
    }


    @Test
    public void GetAndSet() {
        array.add("b");
        array.set(0, "a");
        assertEquals("a", array.get(0));
    }

    @Test
    public void Insert() {
        array.add("a"); // 0
        array.add("b"); // 1
        array.add("c"); // 2

        array.add(1, "d");

        assertEquals(4, array.size());
        assertEquals("a", array.get(0));
        assertEquals("d", array.get(1));
        assertEquals("b", array.get(2));
        assertEquals("c", array.get(3));
    }

    @Test
    public void testRemoveFirst() {
        array.add("a");
        array.add("b");
        array.add("c");

        array.remove(0);

        assertEquals(2, array.size());
        assertEquals("b", array.get(0));
        assertEquals("c", array.get(1));
    }

    @Test
    public void testRemoveMiddle() {
        array.add("a");
        array.add("b");
        array.add("c");

        array.remove(1);

        assertEquals(2, array.size());
        assertEquals("a", array.get(0));
        assertEquals("c", array.get(1));
    }

    @Test
    public void testRemoveLast() {
        array.add("a");
        array.add("b");
        array.add("c");

        array.remove(2);

        assertEquals(2, array.size());
        assertEquals("a", array.get(0));
        assertEquals("b", array.get(1));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(array.isEmpty());
        array.add("a");
        assertFalse(array.isEmpty());
    }

    @Test
    public void testContains()  {
        assertFalse(array.contains("a"));
        array.add("a");
        assertTrue(array.contains("a"));
        array.add("b");
        array.add("b");
        array.add("c");
        assertTrue(array.contains("b"));
        assertTrue(array.contains("c"));
        array.remove(3);
        assertFalse(array.contains("c"));
        array.remove(2);
        assertTrue(array.contains("b"));
        array.remove(1);
        assertFalse(array.contains("b"));
        array.remove(0);
        assertFalse(array.contains("a"));
    }
}
