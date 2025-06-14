package myPractice.round1.datastructures;

public class DynamicArray<T> {
    // Variables
    private int size = 0;
    private int capacity = 10;
    private Object[] data;


    // Constructor
    public DynamicArray(int initialCapacity) {
        this.capacity = initialCapacity;
        data = new Object[this.capacity];
    }

    public DynamicArray() {
        data = new Object[capacity];
    }


    // Create

    /// Add at end of array
    public boolean add(T newElement) {
        if (size == capacity) {
            resize(capacity*2);
        }
        if(newElement==null) {
            throw new IllegalArgumentException("New value cannot be null");
        }
        data[size] = newElement;
        size++;
        return true;
    }


    /// Add at index
    public boolean add(int index, T newElement) {
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException(index);
        }
        if (size == capacity) {
            resize(capacity*2);
        }
        if(newElement==null) {
            throw new IllegalArgumentException("New value cannot be null");
        }
        // copy right
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        // add element at index
        data[index] = newElement;
        size++;
        return true;
    }


    // Retrieve

    /// Get from index
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        return (T)data[index];
    }


    // Update

    /// Set at index
    public void set(int index, T newValue) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }

        if(newValue==null) {
            throw new IllegalArgumentException("New value cannot be null");
        }
        data[index] = newValue;
    }

    //Delete
    /// Delete at index or at end
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        // copy left
        for (int i = index; i<size-1; i ++) {
            data[i] = data[i+1];
        }

        // clear last element
        data[size-1] = null;
        size--;
        if (size>0 && size<capacity/4) {
            resize(capacity/2);
        }
    }

    // Others
    /// get Size of array
    public int size() {
        return size;
    }

    /// Check if Array is Empty
    public boolean isEmpty() {
        return size == 0;
    }

    /// resize array
    private void resize(int newCapacity) {
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData,0, size);
        data = newData;
        capacity = newCapacity;
    }

    /// contains
    public boolean contains (T searchElement) {
        for (int i=0; i<size; i++) {
            T currentValue = (T) data[i];
            if(searchElement.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /// print array
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i=0; i<size; i++) {
            sb.append(data[i]);
            if(i<size-1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
