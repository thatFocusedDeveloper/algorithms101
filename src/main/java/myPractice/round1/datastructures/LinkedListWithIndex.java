package myPractice.round1.datastructures;

import java.util.Objects;

// Anti-Pattern: Defeats the purpose of LinkedList. Arugue that it should be an arraylist and has major design implecations
public class LinkedListWithIndex<T> {
    private Node head;
    private Node tail;
    private int size;

    class Node {
        int index;
        T data;
        Node next;
        public Node(int index, T data) {
            this.index=index;
            this.data=data;
        }
    }

    public void addFront(T data) {
        if(data == null){
            throw new IllegalArgumentException("Data cannot be null");
        }
        Node newNode = new Node(0, data);
        if(head == null){
            head=newNode;
            tail=newNode;
        } else {
            for(Node headNode = head; headNode.next!= null; headNode =headNode.next) {
                headNode.index++;
            }
            newNode.next=head;
            head = newNode;
        }
        size++;
    }

    public void add(Integer index, T data) {
        if(data == null ){
            throw new IllegalArgumentException("Data cannot be null");
        }
        if(index<0 || index>size) {
           throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if(index==0) {
            addFront(data);
        } else if (index == size) {
            addBack(data);
        } else {
            insertAtMiddle(index, data);
        }
    }

    private void insertAtMiddle(Integer index, T data) {
        if(data == null ){
            throw new IllegalArgumentException("Data cannot be null");
        }
        if(index<0 || index>size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node newNode = new Node(index, data);
        for (Node curNode = head; curNode !=null; curNode = curNode.next) {
            if(curNode.index == index -1) {
                newNode.next = curNode.next;
                curNode = newNode;
                size++;
            }
            if(curNode.index >= index) {
                curNode.index++;
            }
        }
    }

    public void addBack(T data) {
        if(data == null){
            throw new IllegalArgumentException("Data cannot be null");
        }
        Node newNode = new Node(size, data);
        if(head == null){
            head=newNode;
            tail=newNode;
        } else {
            tail.next=newNode;
            tail = newNode;
        }
        size++;
    }

    public T getByIndex(Integer index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node curNode = head;
        while(curNode!=null) {
            if(Objects.equals(index, curNode.index)) {
                return  curNode.data;
            }
            curNode = curNode.next;
        }
        return null;
    }
}
