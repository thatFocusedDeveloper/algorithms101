package myPractice.round1.datastructures;

public class Queue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size=0;

    class Node<T> {
        T data;
        Node<T> next;
        public Node(T data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return size==0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head= null;
        tail=null;
        size=0;
    }

    public T peek() {
        if(head == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.data;
    }

    public void enqueue(T data) {
        if(data == null){
            throw new IllegalArgumentException("Data cannot be null");
        }
        Node<T> newNode = new Node<>(data);
        if(head==null) {
            head= newNode;
            tail = newNode;
        } else{
            tail.next=newNode;
            tail=newNode;
        }
        size++;
    }

    public T dequeue() {
        if(head == null) {
            throw new IllegalStateException("Queue is empty");
        }
        Node<T> curNode = head;
        T data = curNode.data;
        head=head.next;
        curNode.next=null;
        if(head==null) {
            tail=null;
        }
        size--;
        return data;
    }
}
