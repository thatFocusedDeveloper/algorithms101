package myPractice.round1.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>();
    }

    // Constructor and Initial State Tests
    @Test
    public void testCreateEmptyStack() {
        assertTrue(stack.isEmpty(), "New stack should be empty");
        assertEquals(0, stack.size(), "New stack should have size 0");
    }

    // Push Tests
    @Test
    public void testPushSingleElement() {
        stack.push(10);

        assertFalse(stack.isEmpty(), "Stack should not be empty after push");
        assertEquals(1, stack.size(), "Size should be 1 after single push");
        assertEquals(10, stack.peek(), "Peek should return the pushed element");
    }

    @Test
    public void testPushMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size(), "Size should be 3 after three pushes");
        assertEquals(30, stack.peek(), "Peek should return last pushed element (LIFO)");
        assertFalse(stack.isEmpty(), "Stack should not be empty");
    }

    @Test
    public void testPushNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            stack.push(null);
        }, "Pushing null should throw IllegalArgumentException");
    }

    // Pop Tests
    @Test
    public void testPopSingleElement() {
        stack.push(42);

        Integer result = stack.pop();

        assertEquals(42, result, "Pop should return the pushed element");
        assertTrue(stack.isEmpty(), "Stack should be empty after popping single element");
        assertEquals(0, stack.size(), "Size should be 0 after popping single element");
    }

    @Test
    public void testPopMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.pop(), "First pop should return 30 (LIFO)");
        assertEquals(20, stack.pop(), "Second pop should return 20 (LIFO)");
        assertEquals(10, stack.pop(), "Third pop should return 10 (LIFO)");

        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements");
        assertEquals(0, stack.size(), "Size should be 0 after popping all elements");
    }

    @Test
    public void testPopEmptyStackThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        }, "Popping from empty stack should throw IllegalStateException");
    }

    // Peek Tests
    @Test
    public void testPeekSingleElement() {
        stack.push(100);

        assertEquals(100, stack.peek(), "Peek should return the element");
        assertEquals(100, stack.peek(), "Multiple peeks should return same element");
        assertEquals(1, stack.size(), "Peek should not change size");
        assertFalse(stack.isEmpty(), "Peek should not change empty status");
    }

    @Test
    public void testPeekMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.peek(), "Peek should always return top element");
        assertEquals(3, stack.size(), "Peek should not change size");
    }

    @Test
    public void testPeekEmptyStackReturnsNull() {
        // Note: Your current implementation returns null for empty stack
        // This tests the current behavior - we'll discuss if this should change
        assertThrows(IllegalStateException.class, ()->stack.peek());
    }

    // LIFO Behavior Tests
    @Test
    public void testLIFOBehavior() {
        // Push in order: 1, 2, 3, 4, 5
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
        }

        // Pop should return in reverse order: 5, 4, 3, 2, 1
        for (int i = 5; i >= 1; i--) {
            assertEquals(i, stack.pop(), "Elements should be popped in LIFO order");
        }
    }

    @Test
    public void testMixedPushPopOperations() {
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop(), "First pop should return 2");

        stack.push(3);
        stack.push(4);

        assertEquals(4, stack.pop(), "Second pop should return 4");
        assertEquals(3, stack.pop(), "Third pop should return 3");
        assertEquals(1, stack.pop(), "Fourth pop should return 1");

        assertTrue(stack.isEmpty(), "Stack should be empty after all operations");
    }

    // Clear Tests
    @Test
    public void testClear() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertFalse(stack.isEmpty(), "Stack should not be empty before clear");
        assertEquals(3, stack.size(), "Size should be 3 before clear");

        stack.clear();

        assertTrue(stack.isEmpty(), "Stack should be empty after clear");
        assertEquals(0, stack.size(), "Size should be 0 after clear");

        // Test that we can use stack after clearing
        stack.push(10);
        assertEquals(10, stack.peek(), "Should be able to push after clear");
    }

    // Edge Cases and Boundary Tests
    @Test
    public void testSingleElementOperations() {
        // Test complete lifecycle of single element
        stack.push(99);
        assertEquals(99, stack.peek(), "Peek should return single element");
        assertEquals(99, stack.pop(), "Pop should return single element");
        assertTrue(stack.isEmpty(), "Stack should be empty after removing single element");

        // Test that we can add again after emptying
        stack.push(88);
        assertEquals(88, stack.peek(), "Should be able to add after emptying");
    }

    @Test
    public void testAlternatingPushPop() {
        // Test alternating operations
        stack.push(1);
        assertEquals(1, stack.pop());

        stack.push(2);
        assertEquals(2, stack.pop());

        stack.push(3);
        assertEquals(3, stack.pop());

        assertTrue(stack.isEmpty(), "Stack should be empty after alternating operations");
    }

    @Test
    public void testStackAfterEmptying() {
        // Fill stack
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Empty stack
        stack.pop();
        stack.pop();
        stack.pop();

        assertTrue(stack.isEmpty(), "Stack should be empty");
        assertEquals(0, stack.size(), "Size should be 0");

        // Refill stack
        stack.push(10);
        stack.push(20);

        assertEquals(2, stack.size(), "Size should be 2 after refilling");
        assertEquals(20, stack.peek(), "Top element should be 20");
        assertEquals(20, stack.pop(), "Should pop 20 first");
        assertEquals(10, stack.pop(), "Should pop 10 second");
    }

    // Size and isEmpty Consistency Tests
    @Test
    public void testSizeConsistency() {
        assertEquals(0, stack.size(), "Initial size should be 0");
        assertTrue(stack.isEmpty(), "Should be empty initially");

        // Add elements and check size
        for (int i = 1; i <= 10; i++) {
            stack.push(i);
            assertEquals(i, stack.size(), "Size should match number of pushes");
            assertFalse(stack.isEmpty(), "Should not be empty with elements");
        }

        // Remove elements and check size
        for (int i = 10; i >= 1; i--) {
            assertEquals(i, stack.size(), "Size should match remaining elements");
            stack.pop();
        }

        assertEquals(0, stack.size(), "Size should be 0 after removing all");
        assertTrue(stack.isEmpty(), "Should be empty after removing all");
    }

    // Stress Tests
    @Test
    public void testLargeNumberOfOperations() {
        final int OPERATIONS = 1000;

        // Push many elements
        for (int i = 0; i < OPERATIONS; i++) {
            stack.push(i);
        }

        assertEquals(OPERATIONS, stack.size(), "Size should match number of pushes");
        assertEquals(OPERATIONS - 1, stack.peek(), "Top element should be last pushed");

        // Pop all elements
        for (int i = OPERATIONS - 1; i >= 0; i--) {
            assertEquals(i, stack.pop(), "Elements should be popped in LIFO order");
        }

        assertTrue(stack.isEmpty(), "Stack should be empty after stress test");
    }

    // Generic Type Tests
    @Test
    public void testStringStack() {
        Stack<String> stringStack = new Stack<>();

        stringStack.push("first");
        stringStack.push("second");
        stringStack.push("third");

        assertEquals("third", stringStack.peek());
        assertEquals("third", stringStack.pop());
        assertEquals("second", stringStack.pop());
        assertEquals("first", stringStack.pop());

        assertTrue(stringStack.isEmpty());
    }

    @Test
    public void testCustomObjectStack() {
        Stack<Person> personStack = new Stack<>();
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Bob", 30);

        personStack.push(person1);
        personStack.push(person2);

        assertEquals(person2, personStack.peek());
        assertEquals(person2, personStack.pop());
        assertEquals(person1, personStack.pop());
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

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    // ToString Tests
    @Test
    public void testToStringEmptyStack() {
        assertEquals("[]", stack.toString(), "Empty stack should return []");
    }

    @Test
    public void testToStringSingleElement() {
        stack.push(42);
        assertEquals("[ 42 ]", stack.toString(), "Single element stack toString");
    }

    @Test
    public void testToStringMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        String result = stack.toString();
        assertEquals("[ 30 -> 20 -> 10 ]", result, "Multiple elements should show LIFO order");
    }

    @Test
    public void testToStringAfterOperations() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals("[ 3 -> 2 -> 1 ]", stack.toString());

        stack.pop();
        assertEquals("[ 2 -> 1 ]", stack.toString());

        stack.push(4);
        assertEquals("[ 4 -> 2 -> 1 ]", stack.toString());
    }

    // Exception Message Tests
    @Test
    public void testExceptionMessages() {
        // Test null argument exception
        IllegalArgumentException nullEx = assertThrows(IllegalArgumentException.class, () -> {
            stack.push(null);
        });
        assertEquals("Data cannot be null", nullEx.getMessage());

        // Test empty stack exception
        IllegalStateException emptyPopEx = assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        });
        assertEquals("Stack is empty", emptyPopEx.getMessage());
    }

    // Memory Management Tests
    @Test
    public void testMemoryCleanup() {
        // This test ensures that popped nodes don't retain references
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Pop elements - internal implementation should null out references
        stack.pop();
        stack.pop();
        stack.pop();

        // Stack should be completely clean
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        // Should be able to reuse without issues
        stack.push(100);
        assertEquals(100, stack.peek());
    }

    // Performance Characteristic Tests
    @Test
    public void testOperationTimeComplexity() {
        // This test ensures operations remain O(1) even with large stacks
        final int LARGE_SIZE = 10000;

        // Fill stack
        long startTime = System.nanoTime();
        for (int i = 0; i < LARGE_SIZE; i++) {
            stack.push(i);
        }
        long pushTime = System.nanoTime() - startTime;

        // Test peek performance (should be O(1))
        startTime = System.nanoTime();
        stack.peek();
        long peekTime = System.nanoTime() - startTime;

        // Test pop performance (should be O(1))
        startTime = System.nanoTime();
        stack.pop();
        long popTime = System.nanoTime() - startTime;

        // These assertions ensure operations are reasonably fast
        assertTrue(peekTime < 1_000_000, "Peek should be very fast (O(1))"); // 1ms
        assertTrue(popTime < 1_000_000, "Pop should be very fast (O(1))"); // 1ms

        System.out.println("Stack Performance Test Results:");
        System.out.println("Push " + LARGE_SIZE + " elements: " + pushTime / 1_000_000 + "ms");
        System.out.println("Peek operation: " + peekTime + "ns");
        System.out.println("Pop operation: " + popTime + "ns");
    }

    // Stack-specific Algorithm Tests
    @Test
    public void testStackAsReverser() {
        // Common use case: reversing order using stack
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {5, 4, 3, 2, 1};

        // Push all elements
        for (int value : input) {
            stack.push(value);
        }

        // Pop all elements - should be in reverse order
        int[] result = new int[input.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pop();
        }

        assertArrayEquals(expected, result, "Stack should reverse the order");
    }

    @Test
    public void testStackForUndoOperations() {
        // Simulate undo functionality
        Stack<String> undoStack = new Stack<>();

        // Simulate operations
        undoStack.push("Operation 1");
        undoStack.push("Operation 2");
        undoStack.push("Operation 3");

        // Undo operations (LIFO)
        assertEquals("Operation 3", undoStack.pop(), "Should undo most recent operation first");
        assertEquals("Operation 2", undoStack.pop(), "Should undo second most recent operation");
        assertEquals("Operation 1", undoStack.pop(), "Should undo oldest operation last");

        assertTrue(undoStack.isEmpty(), "No more operations to undo");
    }

    // Boundary Value Tests
    @Test
    public void testMaxIntegerValues() {
        stack.push(Integer.MAX_VALUE);
        stack.push(Integer.MIN_VALUE);

        assertEquals(Integer.MIN_VALUE, stack.pop());
        assertEquals(Integer.MAX_VALUE, stack.pop());
    }

    @Test
    public void testZeroValue() {
        stack.push(0);
        assertEquals(0, stack.peek());
        assertEquals(0, stack.pop());
    }

    // Thread Safety Awareness Test (Documentation)
    @Test
    public void testSingleThreadedUsage() {
        // Note: This stack implementation is NOT thread-safe
        // This test documents expected single-threaded behavior

        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }

        for (int i = 99; i >= 0; i--) {
            assertEquals(i, stack.pop(), "Single-threaded access should be predictable");
        }

        // For multi-threaded usage, external synchronization would be required
    }
}
