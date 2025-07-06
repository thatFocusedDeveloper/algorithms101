package myPractice.round1.datastructures;

public class Queue {
    private Node head;
    private Node tail;
    private int size=0;

    class Node {
        Integer data;
        Node next;
        public Node(Integer data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return head==null;
    }

    public int size() {
        return size;
    }

    public Integer peek() {
        if(head==null) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.data;
    }

    public void enqueue(Integer data) {
        if(data == null){
            throw new IllegalArgumentException("Data cannot be null");
        }
        Node newNode = new Node(data);
        if(head==null) {
            head= newNode;
            tail = newNode;
        } else{
            tail.next=newNode;
            tail=newNode;
        }
        size++;
    }
}
