package myPractice.round1.datastructures;

/**
 * A generic stack implementation using a singly-linked list.
 * Supports LIFO (Last-In-First-Out) operations with O(1) time complexity.
 *
 * @param <T> the type of elements stored in the stack
 */
public class Stack<T> {
    private Node<T> head;
    private int size = 0;

    /**
     * Internal node class for the linked list structure.
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     * Time Complexity: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the size of the stack
     * Time Complexity: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Removes all elements from the stack.
     * Time Complexity: O(1)
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Returns the top element without removing it.
     *
     * @return the top element
     * @throws IllegalStateException if the stack is empty
     * Time Complexity: O(1)
     */
    public T peek() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return head.data;
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param data the element to push (cannot be null)
     * @throws IllegalArgumentException if data is null
     * Time Complexity: O(1)
     */
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return the top element
     * @throws IllegalStateException if the stack is empty
     * Time Complexity: O(1)
     */
    public T pop() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }

        T data = head.data;
        Node<T> oldHead = head;
        head = head.next;
        oldHead.next = null; // Help GC
        size--;

        return data;
    }

    /**
     * Returns the theoretical capacity of the stack.
     * For linked-list implementation, this is limited only by available memory.
     *
     * @return Integer.MAX_VALUE indicating no fixed capacity limit
     */
    public int capacity() {
        return Integer.MAX_VALUE;
    }

    /**
     * Returns a string representation of the stack showing elements from top to bottom.
     * Format: "[ top -> ... -> bottom ]" or "[]" for empty stack.
     *
     * @return string representation of the stack
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[ ");

        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }

        sb.append(" ]");
        return sb.toString();
    }
}
