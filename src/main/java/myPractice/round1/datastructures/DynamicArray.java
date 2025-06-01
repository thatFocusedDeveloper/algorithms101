package myPractice.round1.datastructures;

import java.util.ArrayList;

public class DynamicArray<String> {
    private int size=0;
    private int initialCapacity;
    private Object[] arr;

    // Create new Dynamic Array
    DynamicArray(int capacity){
        this.initialCapacity=capacity;
        arr = new Object[initialCapacity];
    }


    // Add to the end of the Array
    public void add(Object obj) {
        arr[size]=(String)obj;
        size++;
    }
    // Insert at an index
    public void set(int index, int obj) {
        arr[index] = obj;
    }

    // Retrieve
    // -> Get from an index
    public Object get(int index) {
        return arr[index];
    }


    // Delete
    // Delete at an index
    // Delete from the end of the Array

    // Other Utilities
    // resize array

}
