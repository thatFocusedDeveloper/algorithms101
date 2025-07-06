package course.datastructures;

import course.datastructures.DynamicArray;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DynamicArrayTest {

    private DynamicArray array;

    @BeforeEach
    public void SetUp() {
        array = new DynamicArray<String>(2);
    }

    @Test
    public void GetAndSet() {
        array.set(0, "a");
        assertEquals("a", array.get(0));
    }

    @Test
    public void Insert() {
        array.add("a"); // 0
        array.add("b"); // 1
        array.add("c"); // 2

        array.insert(1, "d");

        assertEquals(4, array.size());
        assertEquals("a", array.get(0));
        assertEquals("d", array.get(1));
        assertEquals("b", array.get(2));
        assertEquals("c", array.get(3));
    }

    @Test
    public void DeleteFirst() {
        array.add("a");
        array.add("b");
        array.add("c");

        array.delete(0);

        assertEquals(2, array.size());
        assertEquals("b", array.get(0));
        assertEquals("c", array.get(1));
        assertEquals(null, array.get(2));
    }

    @Test
    public void DeleteMiddle() {
        array.add("a");
        array.add("b");
        array.add("c");

        array.delete(1);

        assertEquals(2, array.size());
        assertEquals("a", array.get(0));
        assertEquals("c", array.get(1));
        assertEquals(null, array.get(2));
    }

    @Test
    public void DeleteLast() {
        array.add("a");
        array.add("b");
        array.add("c");

        array.delete(2);

        assertEquals(2, array.size());
        assertEquals("a", array.get(0));
        assertEquals("b", array.get(1));
        assertEquals(null, array.get(2));
    }

    @Test
    public void isEmpty() {
        assertTrue(array.isEmpty());
        array.add("a");
        assertFalse(array.isEmpty());
    }

    @Test
    public void Contains()  {
        assertFalse(array.Contains("a"));
        array.add("a");
        assertTrue(array.Contains("a"));
        array.add("b");
        array.add("b");
        array.add("c");
        assertTrue(array.Contains("b"));
        assertTrue(array.Contains("c"));
        array.delete(3);
        assertFalse(array.Contains("c"));
        array.delete(2);
        assertTrue(array.Contains("b"));
        array.delete(1);
        assertFalse(array.Contains("b"));
        array.delete(0);
        assertFalse(array.Contains("a"));
    }

}
