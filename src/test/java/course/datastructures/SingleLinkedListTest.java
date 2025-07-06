package course.datastructures;

import course.datastructures.SingleLinkedList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingleLinkedListTest {

    private SingleLinkedList sll;

    @BeforeEach
    public void SetUp() {
        sll = new SingleLinkedList<Integer>();
    }

    @Test
    public void InitialState() {
        assertNull(sll.head);
        assertEquals(0, sll.size);
    }

    @Test
    public void lastIndexOf() {
        sll.push(99);
        sll.push(100);
        sll.push(101);
        assertEquals(2, sll.lastIndexOf(99));
        assertEquals(1, sll.lastIndexOf(100));
        assertEquals(0, sll.lastIndexOf(101));
    }

    @Test
    public void peek() {
        sll.push(99);
        assertEquals(99, sll.peek());
    }

    @Test
    public void peekEmpty() {
        assertEquals(null, sll.peek());
    }

    @Test
    public void pushAndPop() {
        sll.push(99);
        sll.push(100);
        sll.push(101);
        assertEquals(101, sll.pop());
        assertEquals(100, sll.pop());
        assertEquals(99, sll.pop());
    }

    @Test
    public void popEmptyList() {
        assertNull(sll.pop());
    }

    @Test
    public void remove() {
        sll.push(99);
        sll.push(100);
        sll.push(101);

        assertTrue(sll.remove(101));
        assertEquals(100, sll.peek());

        assertTrue(sll.remove(100));
        assertEquals(99, sll.peek());

        assertTrue(sll.remove(99));
        assertEquals(null, sll.peek());
    }

    @Test
    public void pushSize() {
        assertEquals(0, sll.size);
        sll.push(99);
        assertEquals(1, sll.size);
        sll.push(100);
        assertEquals(2, sll.size);
    }

    @Test
    public void popSize() {
        sll.push(99);
        sll.push(100);
        assertEquals(2, sll.size);
        sll.pop();
        assertEquals(1, sll.size);
    }

    @Test
    public void removeSize() {
        sll.push(99);
        sll.push(100);
        assertEquals(2, sll.size);
        sll.remove(100);
        assertEquals(1, sll.size);
    }

    @Test
    public void setIndex() {
        sll.push(99);
        sll.push(100);
        sll.push(101);

        sll.set(0, 49);
        sll.set(1, 50);
        sll.set(2, 51);

        assertEquals(0, sll.lastIndexOf(49));
        assertEquals(1, sll.lastIndexOf(50));
        assertEquals(2, sll.lastIndexOf(51));
    }

//    @Test
//    public void realLinkedList() {
//
//        // Here are some tests written against the Java datastructures.LinkedList class
//
//        LinkedList<Integer> linkedList = new LinkedList<Integer>();
//        linkedList.push(99);
//        linkedList.push(100);
//        linkedList.push(101);
//        assertEquals(2, linkedList.lastIndexOf(99));
//        assertEquals(1, linkedList.lastIndexOf(100));
//        assertEquals(0, linkedList.lastIndexOf(101));
//
//        linkedList.peek();
//    }
}
