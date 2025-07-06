package myPractice.round1.datastructures;

/**
 * Generic HashTable implementation using separate chaining for collision resolution.
 * Interview-ready implementation with proper error handling and performance optimizations.
 */
public class HashTable<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private HashEntry<K, V>[] buckets;
    private int size;
    private int capacity;
    private final double loadFactor;

    /**
     * Inner class representing a key-value pair in the hash table.
     * Made static to avoid holding reference to outer class instance.
     */
    static class HashEntry<K, V> {
        final K key;  // Make key final for immutability
        V value;
        HashEntry<K, V> next;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    // Constructors
    public HashTable() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTable(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity, double loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        if (loadFactor <= 0 || loadFactor > 1) {
            throw new IllegalArgumentException("Load factor must be between 0 and 1");
        }

        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.buckets = new HashEntry[capacity];
        this.size = 0;
    }

    /**
     * Inserts or updates a key-value pair.
     * Time Complexity: O(1) average, O(n) worst case
     */
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        // Check if resize is needed before insertion
        if (size >= capacity * loadFactor) {
            resize();
        }

        int index = getIndex(key);
        HashEntry<K, V> entry = buckets[index];

        // If bucket is empty, create new entry
        if (entry == null) {
            buckets[index] = new HashEntry<>(key, value);
            size++;
            return null;
        }

        // Traverse the chain to find existing key or end of chain
        HashEntry<K, V> prev = null;
        while (entry != null) {
            if (entry.key.equals(key)) {
                // Key exists, update value
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
            prev = entry;
            entry = entry.next;
        }

        // Key doesn't exist, add new entry at end of chain
        prev.next = new HashEntry<>(key, value);
        size++;
        return null;
    }

    /**
     * Retrieves value associated with the given key.
     * Time Complexity: O(1) average, O(n) worst case
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = getIndex(key);
        HashEntry<K, V> entry = buckets[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;  // Key not found
    }

    /**
     * Removes the key-value pair for the given key.
     * Time Complexity: O(1) average, O(n) worst case
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = getIndex(key);
        HashEntry<K, V> entry = buckets[index];
        HashEntry<K, V> prev = null;

        while (entry != null) {
            if (entry.key.equals(key)) {
                // Found the key to remove
                if (prev == null) {
                    // Removing first entry in chain
                    buckets[index] = entry.next;
                } else {
                    // Removing entry from middle/end of chain
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }

        return null;  // Key not found
    }

    /**
     * Checks if the hash table contains the given key.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Returns the number of key-value pairs in the hash table.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the hash table is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all key-value pairs from the hash table.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new HashEntry[capacity];
        size = 0;
    }

    /**
     * Returns the current load factor of the hash table.
     */
    public double getCurrentLoadFactor() {
        return (double) size / capacity;
    }

    /**
     * Converts key to array index using hash function.
     * Handles negative hash codes properly.
     */
    private int getIndex(K key) {
        int hashCode = key.hashCode();
        // Use bitwise AND with 0x7fffffff to ensure positive index
        // 0x7fffffff = 01111111111111111111111111111111 (removes sign bit)
        return (hashCode & 0x7fffffff) % capacity;
    }

    /**
     * Resizes the hash table when load factor threshold is exceeded.
     * Rehashes all existing entries to maintain performance.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        HashEntry<K, V>[] oldBuckets = buckets;
        int oldCapacity = capacity;

        // Double the capacity
        capacity *= 2;
        buckets = new HashEntry[capacity];
        size = 0;  // Will be incremented during rehashing

        // Rehash all existing entries
        for (int i = 0; i < oldCapacity; i++) {
            HashEntry<K, V> entry = oldBuckets[i];
            while (entry != null) {
                HashEntry<K, V> next = entry.next;
                // Reinsert entry (this will increment size)
                put(entry.key, entry.value);
                entry = next;
            }
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        for (int i = 0; i < capacity; i++) {
            HashEntry<K, V> entry = buckets[i];
            if (entry != null) {
                sb.append("  bucket[").append(i).append("]: ");
                while (entry != null) {
                    sb.append(entry.toString());
                    if (entry.next != null) {
                        sb.append(" -> ");
                    }
                    entry = entry.next;
                }
                sb.append("\n");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
