package myPractice.round1.datastructures;

import java.util.Objects;

public class LinkedList<T> {
    private Node head;
    private Node tail;
    private int size=0;

    private class Node<T> {
        T data;           // Package-private (no modifier)
        Node next;        // Package-private (no modifier)

        public Node (T data) {
            this.data = data;
        }
    }

    // Others
    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    // Insert
    // Add to head
    public void addFront(T data) {
        Node newNode = new Node(data);
        if(head==null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }



    // Add to Tail
    public void addBack(T data) {
        if(data == null){
            throw new IllegalArgumentException("Data cannot be null");
        }
        Node newNode = new Node(data);
        if(head==null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }



    // Get Head
    public T getFirst() {
        if(head==null) {
            throw new IllegalStateException("List is empty");
        }
        return (T) head.data;
    }

    public T getLast() {
        if(head==null) {
            throw new IllegalStateException("List is empty");
        }
        return (T) tail.data;
    }


    // Delete
    public void deleteFirst() {
        if(head == null) {
            throw new IllegalStateException("List is empty");
        }
        Node curHead = head;
        head = head.next;
        curHead.next = null;
        size--;
    }

    public void deleteLast() {
        if(head == null){
            throw new IllegalStateException("List is empty");
        }
        Node prevNode = null;
        Node curNode = head;
        while(curNode.next != null) {
            prevNode = curNode;
            curNode = curNode.next;
        }
        prevNode.next=null;
        tail=prevNode;
        size--;
    }

    public boolean delete(T data) {
        if (head == null) {
            return false; // Return false instead of throwing exception for empty list
        }
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        // Handle deleting the head node
        if (Objects.equals(head.data, data)) {
            if (head == tail) { // Single element list
                head = null;
                tail = null;
            } else {
                head = head.next;
            }
            size--;
            return true;
        }

        // Handle deleting from middle or end
        Node prevNode = head;
        Node curNode = head.next;

        while (curNode != null) { // Fixed: check curNode != null instead of curNode.next != null
            if (Objects.equals(curNode.data, data)) {
                prevNode.next = curNode.next;
                if (curNode == tail) { // Update tail if we're deleting the last node
                    tail = prevNode;
                }
                curNode.next = null; // Help GC
                size--;
                return true;
            }
            prevNode = curNode;
            curNode = curNode.next;
        }

        return false; // Element not found
    }

    public boolean contains(Integer data) {
        Node curNode = head;
        while(curNode!= null){
            if(Objects.equals(curNode.data, data)) {
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    public void clear() {
        head= null;
        tail=null;
        size=0;
    }
}
