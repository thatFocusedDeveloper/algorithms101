package myPractice.round1.datastructures;

import java.util.NoSuchElementException;

public class LinkedList {
    // Variables
    private Node headNode;
    private Node lastNode;
    int size;


    // Use Default Constructor


    // Create
    public void addFirst (int data) {
        Node newNode = new Node(data);
        if(headNode==null) {
            headNode = newNode;
        } else {
            newNode.next=headNode;
            headNode = newNode;
        }
        size++;
    }



    // Retrieve
    /// get First
    public int getFirst() {
        if(headNode==null) {
            throw new NoSuchElementException();
        }
        return headNode.data;
    }


    public int getLast() {
        if(headNode==null) {
            throw new NoSuchElementException();
        }
        Node current = headNode;
        // while we are not at the tail
        while(current.next != null) {
            current = current.next;
        }

        // return data of the tail
        return current.data;
    }


    // Update
    /// set

    // Delete
    /// remove


    // Others

    /// is empty

    ///  contains

    /// size()
    public int size() {
        return size;
    }


    /// to string


    private static class Node {
        int data;
        Node next;

        public Node (int data) {
            this.data = data;
        }
    }

}
