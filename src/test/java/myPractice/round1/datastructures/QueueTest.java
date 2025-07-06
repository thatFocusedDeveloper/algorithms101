package myPractice.round1.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    private Queue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new Queue<>();
    }

    // Constructor and Initial State Tests
    @Test
    public void testCreateEmptyQueue() {
        assertTrue(queue.isEmpty(), "New queue should be empty");
        assertEquals(0, queue.size(), "New queue should have size 0");
    }

    // Enqueue Tests
    @Test
    public void testEnqueueSingleElement() {
        queue.enqueue(10);

        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue");
        assertEquals(1, queue.size(), "Size should be 1 after single enqueue");
        assertEquals(10, queue.peek(), "Peek should return the enqueued element");
    }

    @Test
    public void testEnqueueMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.size(), "Size should be 3 after three enqueues");
        assertEquals(10, queue.peek(), "Peek should return first enqueued element (FIFO)");
        assertFalse(queue.isEmpty(), "Queue should not be empty");
    }

    @Test
    public void testEnqueueNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            queue.enqueue(null);
        }, "Enqueuing null should throw IllegalArgumentException");
    }

    // Dequeue Tests
    @Test
    public void testDequeueSingleElement() {
        queue.enqueue(42);

        Integer result = queue.dequeue();

        assertEquals(42, result, "Dequeue should return the enqueued element");
        assertTrue(queue.isEmpty(), "Queue should be empty after dequeuing single element");
        assertEquals(0, queue.size(), "Size should be 0 after dequeuing single element");
    }

    @Test
    public void testDequeueMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.dequeue(), "First dequeue should return 10 (FIFO)");
        assertEquals(20, queue.dequeue(), "Second dequeue should return 20 (FIFO)");
        assertEquals(30, queue.dequeue(), "Third dequeue should return 30 (FIFO)");

        assertTrue(queue.isEmpty(), "Queue should be empty after dequeuing all elements");
        assertEquals(0, queue.size(), "Size should be 0 after dequeuing all elements");
    }

    @Test
    public void testDequeueEmptyQueueThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            queue.dequeue();
        }, "Dequeuing from empty queue should throw IllegalStateException");
    }

    // Peek Tests
    @Test
    public void testPeekSingleElement() {
        queue.enqueue(100);

        assertEquals(100, queue.peek(), "Peek should return the element");
        assertEquals(100, queue.peek(), "Multiple peeks should return same element");
        assertEquals(1, queue.size(), "Peek should not change size");
        assertFalse(queue.isEmpty(), "Peek should not change empty status");
    }

    @Test
    public void testPeekMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.peek(), "Peek should always return first element");
        assertEquals(3, queue.size(), "Peek should not change size");
    }

    @Test
    public void testPeekEmptyQueueThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            queue.peek();
        }, "Peeking empty queue should throw IllegalStateException");
    }

    // FIFO Behavior Tests
    @Test
    public void testFIFOBehavior() {
        // Enqueue in order: 1, 2, 3, 4, 5
        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i);
        }

        // Dequeue should return in same order: 1, 2, 3, 4, 5
        for (int i = 1; i <= 5; i++) {
            assertEquals(i, queue.dequeue(), "Elements should be dequeued in FIFO order");
        }
    }

    @Test
    public void testMixedEnqueueDequeueOperations() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.dequeue(), "First dequeue should return 1");

        queue.enqueue(3);
        queue.enqueue(4);

        assertEquals(2, queue.dequeue(), "Second dequeue should return 2");
        assertEquals(3, queue.dequeue(), "Third dequeue should return 3");
        assertEquals(4, queue.dequeue(), "Fourth dequeue should return 4");

        assertTrue(queue.isEmpty(), "Queue should be empty after all operations");
    }

    // Edge Cases and Boundary Tests
    @Test
    public void testSingleElementOperations() {
        // Test complete lifecycle of single element
        queue.enqueue(99);
        assertEquals(99, queue.peek(), "Peek should return single element");
        assertEquals(99, queue.dequeue(), "Dequeue should return single element");
        assertTrue(queue.isEmpty(), "Queue should be empty after removing single element");

        // Test that we can add again after emptying
        queue.enqueue(88);
        assertEquals(88, queue.peek(), "Should be able to add after emptying");
    }

    @Test
    public void testAlternatingEnqueueDequeue() {
        // Test alternating operations
        queue.enqueue(1);
        assertEquals(1, queue.dequeue());

        queue.enqueue(2);
        assertEquals(2, queue.dequeue());

        queue.enqueue(3);
        assertEquals(3, queue.dequeue());

        assertTrue(queue.isEmpty(), "Queue should be empty after alternating operations");
    }

    @Test
    public void testQueueAfterEmptying() {
        // Fill queue
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // Empty queue
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        assertTrue(queue.isEmpty(), "Queue should be empty");
        assertEquals(0, queue.size(), "Size should be 0");

        // Refill queue
        queue.enqueue(10);
        queue.enqueue(20);

        assertEquals(2, queue.size(), "Size should be 2 after refilling");
        assertEquals(10, queue.peek(), "First element should be 10");
        assertEquals(10, queue.dequeue(), "Should dequeue 10 first");
        assertEquals(20, queue.dequeue(), "Should dequeue 20 second");
    }

    // Size and isEmpty Consistency Tests
    @Test
    public void testSizeConsistency() {
        assertEquals(0, queue.size(), "Initial size should be 0");
        assertTrue(queue.isEmpty(), "Should be empty initially");

        // Add elements and check size
        for (int i = 1; i <= 10; i++) {
            queue.enqueue(i);
            assertEquals(i, queue.size(), "Size should match number of enqueues");
            assertFalse(queue.isEmpty(), "Should not be empty with elements");
        }

        // Remove elements and check size
        for (int i = 10; i >= 1; i--) {
            assertEquals(i, queue.size(), "Size should match remaining elements");
            queue.dequeue();
        }

        assertEquals(0, queue.size(), "Size should be 0 after removing all");
        assertTrue(queue.isEmpty(), "Should be empty after removing all");
    }

    // Stress Tests
    @Test
    public void testLargeNumberOfOperations() {
        final int OPERATIONS = 1000;

        // Enqueue many elements
        for (int i = 0; i < OPERATIONS; i++) {
            queue.enqueue(i);
        }

        assertEquals(OPERATIONS, queue.size(), "Size should match number of enqueues");
        assertEquals(0, queue.peek(), "First element should be 0");

        // Dequeue all elements
        for (int i = 0; i < OPERATIONS; i++) {
            assertEquals(i, queue.dequeue(), "Elements should be dequeued in order");
        }

        assertTrue(queue.isEmpty(), "Queue should be empty after stress test");
    }

    // Generic Type Tests
    @Test
    public void testStringQueue() {
        Queue<String> stringQueue = new Queue<>();

        stringQueue.enqueue("first");
        stringQueue.enqueue("second");
        stringQueue.enqueue("third");

        assertEquals("first", stringQueue.peek());
        assertEquals("first", stringQueue.dequeue());
        assertEquals("second", stringQueue.dequeue());
        assertEquals("third", stringQueue.dequeue());

        assertTrue(stringQueue.isEmpty());
    }

    @Test
    public void testCustomObjectQueue() {
        Queue<Person> personQueue = new Queue<>();
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Bob", 30);

        personQueue.enqueue(person1);
        personQueue.enqueue(person2);

        assertEquals(person1, personQueue.peek());
        assertEquals(person1, personQueue.dequeue());
        assertEquals(person2, personQueue.dequeue());
    }

    // Helper class for testing custom objects
    private static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && name.equals(person.name);
        }
    }

    // Exception Message Tests
    @Test
    public void testExceptionMessages() {
        // Test null argument exception
        IllegalArgumentException nullEx = assertThrows(IllegalArgumentException.class, () -> {
            queue.enqueue(null);
        });
        assertEquals("Data cannot be null", nullEx.getMessage());

        // Test empty queue exceptions
        IllegalStateException emptyPeekEx = assertThrows(IllegalStateException.class, () -> {
            queue.peek();
        });
        assertEquals("Queue is empty", emptyPeekEx.getMessage());

        IllegalStateException emptyDequeueEx = assertThrows(IllegalStateException.class, () -> {
            queue.dequeue();
        });
        assertEquals("Queue is empty", emptyDequeueEx.getMessage());
    }

    // Performance Characteristic Tests
    @Test
    public void testOperationTimeComplexity() {
        // This test ensures operations remain O(1) even with large queues
        final int LARGE_SIZE = 10000;

        // Fill queue
        long startTime = System.nanoTime();
        for (int i = 0; i < LARGE_SIZE; i++) {
            queue.enqueue(i);
        }
        long enqueueTime = System.nanoTime() - startTime;

        // Test peek performance (should be O(1))
        startTime = System.nanoTime();
        queue.peek();
        long peekTime = System.nanoTime() - startTime;

        // Test dequeue performance (should be O(1))
        startTime = System.nanoTime();
        queue.dequeue();
        long dequeueTime = System.nanoTime() - startTime;

        // These assertions ensure operations are reasonably fast
        // (actual times will vary by system, but should be very small)
        assertTrue(peekTime < 1_000_000, "Peek should be very fast (O(1))"); // 1ms
        assertTrue(dequeueTime < 1_000_000, "Dequeue should be very fast (O(1))"); // 1ms

        System.out.println("Performance Test Results:");
        System.out.println("Enqueue " + LARGE_SIZE + " elements: " + enqueueTime / 1_000_000 + "ms");
        System.out.println("Peek operation: " + peekTime + "ns");
        System.out.println("Dequeue operation: " + dequeueTime + "ns");
    }
}
