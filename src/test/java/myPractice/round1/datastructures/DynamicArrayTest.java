package myPractice.round1.datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DynamicArrayTest {

    private DynamicArray<String> array;

    @Before
    public void setUp() {
        array = new DynamicArray<>(2);
    }

    @Test
    public void testAddElementToEnd() {
        array.add("Element1");
        Assert.assertEquals(1, array.size());
        Assert.assertEquals("Element1", array.get(0));
    }

    @Test
    public void testRetrieveElementFromIndex() {
        array.add("Element1");
        array.add("Element2");
        Assert.assertEquals("Element2", array.get(1));
    }


    @Test
    public void GetAndSet() {
        array.add("b");
        array.set(0, "a");
        Assert.assertEquals("a", array.get(0));
    }

    @Test
    public void Insert() {
        array.add("a"); // 0
        array.add("b"); // 1
        array.add("c"); // 2

        array.add(1, "d");

        Assert.assertEquals(4, array.size());
        Assert.assertEquals("a", array.get(0));
        Assert.assertEquals("d", array.get(1));
        Assert.assertEquals("b", array.get(2));
        Assert.assertEquals("c", array.get(3));
    }

    @Test
    public void testRemoveFirst() {
        array.add("a");
        array.add("b");
        array.add("c");

        array.remove(0);

        Assert.assertEquals(2, array.size());
        Assert.assertEquals("b", array.get(0));
        Assert.assertEquals("c", array.get(1));
    }

    @Test
    public void testRemoveMiddle() {
        array.add("a");
        array.add("b");
        array.add("c");

        array.remove(1);

        Assert.assertEquals(2, array.size());
        Assert.assertEquals("a", array.get(0));
        Assert.assertEquals("c", array.get(1));
    }

    @Test
    public void testRemoveLast() {
        array.add("a");
        array.add("b");
        array.add("c");

        array.remove(2);

        Assert.assertEquals(2, array.size());
        Assert.assertEquals("a", array.get(0));
        Assert.assertEquals("b", array.get(1));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(array.isEmpty());
        array.add("a");
        Assert.assertFalse(array.isEmpty());
    }

    @Test
    public void testContains()  {
        Assert.assertFalse(array.contains("a"));
        array.add("a");
        Assert.assertTrue(array.contains("a"));
        array.add("b");
        array.add("b");
        array.add("c");
        Assert.assertTrue(array.contains("b"));
        Assert.assertTrue(array.contains("c"));
        array.remove(3);
        Assert.assertFalse(array.contains("c"));
        array.remove(2);
        Assert.assertTrue(array.contains("b"));
        array.remove(1);
        Assert.assertFalse(array.contains("b"));
        array.remove(0);
        Assert.assertFalse(array.contains("a"));
    }
}
