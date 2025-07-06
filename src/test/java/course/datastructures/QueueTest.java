package course.datastructures;

import course.datastructures.Queue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueTest {

    private Queue queue;

    @BeforeEach
    public void SetUp() {
        queue = new Queue();
    }

    @Test
    public void Add() {
        queue.add(5);
        queue.add(2);
        queue.add(21);

        assertEquals(5, queue.peek());
    }

    @Test
    public void Remove() {
        queue.add(8);
        queue.add(72);
        queue.add(11);
        queue.remove();
        queue.add(15);
        queue.add(35);
        queue.remove();

        assertEquals(11, queue.peek());
    }

    @Test
    public void IsEmpty() {
        queue.add(8);
        queue.remove();

        assertEquals(true, queue.isEmpty());
    }
}
