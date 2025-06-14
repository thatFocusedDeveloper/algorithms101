package myPractice.round1.datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

    private LinkedList list;

    @Before
    public void setup() {
        list = new LinkedList();
    }

    @Test
    public void testCreateEmptyLinkedList() {
        Assert.assertNotNull(list);
    }

    @Test
    public void testAddElementsToLinkedList() {
        list.addFirst(1);
        list.addFirst(2);
        Assert.assertEquals(2, list.size());
    }

//    @Test
//    public void testRemoveElementsFromLinkedList() {
//        list.add(1);
//        list.add(2);
//        list.remove(1);
//        Assert.assertEquals(1, list.size());
//    }
//
//    @Test
//    public void testAccessElementFromEmptyLinkedList() {
//        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
//    }
//
//    @Test
//    public void testRemoveElementFromEmptyLinkedList() {
//        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
//    }
//
//    @Test
//    public void testAddNullElementToLinkedList() {
//        list.add(null);
//        Assert.assertEquals(1, list.size());
//        Assert.assertNull(list.get(0));
//    }
}