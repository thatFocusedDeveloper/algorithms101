package course.datastructures;

import course.datastructures.Stack;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTest {

    private Stack stack;

    @BeforeEach
    public void SetUp() {
        stack = new Stack();
    }

    @Test
    public void Push() {
        stack.push(15);
        stack.push(25);
        stack.push(75);

        assertEquals(3, stack.size());
        assertEquals(75, stack.peek());
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void Pop() {
        stack.push(15);
        stack.push(25);
        stack.pop();
        stack.push(35);
        stack.pop();

        assertEquals(15, stack.peek());
        assertEquals(1, stack.size());
        assertEquals(false, stack.isEmpty());
        assertEquals(15, stack.pop());
    }
}
