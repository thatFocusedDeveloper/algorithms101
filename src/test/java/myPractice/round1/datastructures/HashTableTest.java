package myPractice.round1.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for HashTable implementation.
 * Tests cover all major functionality and edge cases.
 */
public class HashTableTest {

    private HashTable<String, Integer> hashTable;
    private HashTable<Integer, String> intKeyTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable<>();
        intKeyTable = new HashTable<>();
    }

    // Constructor Tests
    @Test
    @DisplayName("Test default constructor")
    void testDefaultConstructor() {
        HashTable<String, String> table = new HashTable<>();
        assertEquals(0, table.size());
        assertTrue(table.isEmpty());
    }

    @Test
    @DisplayName("Test constructor with initial capacity")
    void testConstructorWithCapacity() {
        HashTable<String, String> table = new HashTable<>(32);
        assertEquals(0, table.size());
        assertTrue(table.isEmpty());
    }

    @Test
    @DisplayName("Test constructor with capacity and load factor")
    void testConstructorWithCapacityAndLoadFactor() {
        HashTable<String, String> table = new HashTable<>(32, 0.8);
        assertEquals(0, table.size());
        assertTrue(table.isEmpty());
    }

    @Test
    @DisplayName("Test constructor with invalid parameters")
    void testConstructorInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> new HashTable<>(-1));
        assertThrows(IllegalArgumentException.class, () -> new HashTable<>(0));
        assertThrows(IllegalArgumentException.class, () -> new HashTable<>(16, 0));
        assertThrows(IllegalArgumentException.class, () -> new HashTable<>(16, 1.5));
    }

    // Basic Operations Tests
    @Test
    @DisplayName("Test put and get operations")
    void testPutAndGet() {
        // Test basic put and get
        assertNull(hashTable.put("key1", 100));
        assertEquals(Integer.valueOf(100), hashTable.get("key1"));
        assertEquals(1, hashTable.size());
        assertFalse(hashTable.isEmpty());

        // Test multiple entries
        hashTable.put("key2", 200);
        hashTable.put("key3", 300);

        assertEquals(Integer.valueOf(100), hashTable.get("key1"));
        assertEquals(Integer.valueOf(200), hashTable.get("key2"));
        assertEquals(Integer.valueOf(300), hashTable.get("key3"));
        assertEquals(3, hashTable.size());
    }

    @Test
    @DisplayName("Test put with duplicate keys")
    void testPutDuplicateKeys() {
        // Insert initial value
        assertNull(hashTable.put("key1", 100));
        assertEquals(Integer.valueOf(100), hashTable.get("key1"));
        assertEquals(1, hashTable.size());

        // Update with same key
        assertEquals(Integer.valueOf(100), hashTable.put("key1", 200));
        assertEquals(Integer.valueOf(200), hashTable.get("key1"));
        assertEquals(1, hashTable.size()); // Size should not change
    }

    @Test
    @DisplayName("Test get with non-existent key")
    void testGetNonExistentKey() {
        assertNull(hashTable.get("nonexistent"));

        hashTable.put("key1", 100);
        assertNull(hashTable.get("key2"));
    }

    @Test
    @DisplayName("Test null key handling")
    void testNullKeyHandling() {
        assertThrows(IllegalArgumentException.class, () -> hashTable.put(null, 100));
        assertThrows(IllegalArgumentException.class, () -> hashTable.get(null));
        assertThrows(IllegalArgumentException.class, () -> hashTable.remove(null));
        assertThrows(IllegalArgumentException.class, () -> hashTable.containsKey(null));
    }

    // Remove Operations Tests
    @Test
    @DisplayName("Test remove operation")
    void testRemove() {
        // Test remove from empty table
        assertNull(hashTable.remove("key1"));

        // Add some entries
        hashTable.put("key1", 100);
        hashTable.put("key2", 200);
        hashTable.put("key3", 300);
        assertEquals(3, hashTable.size());

        // Remove existing key
        assertEquals(Integer.valueOf(200), hashTable.remove("key2"));
        assertEquals(2, hashTable.size());
        assertNull(hashTable.get("key2"));

        // Verify other keys still exist
        assertEquals(Integer.valueOf(100), hashTable.get("key1"));
        assertEquals(Integer.valueOf(300), hashTable.get("key3"));

        // Remove non-existent key
        assertNull(hashTable.remove("nonexistent"));
        assertEquals(2, hashTable.size());
    }

    @Test
    @DisplayName("Test remove all entries")
    void testRemoveAllEntries() {
        hashTable.put("key1", 100);
        hashTable.put("key2", 200);
        hashTable.put("key3", 300);

        assertEquals(Integer.valueOf(100), hashTable.remove("key1"));
        assertEquals(Integer.valueOf(200), hashTable.remove("key2"));
        assertEquals(Integer.valueOf(300), hashTable.remove("key3"));

        assertEquals(0, hashTable.size());
        assertTrue(hashTable.isEmpty());
    }

    // ContainsKey Tests
    @Test
    @DisplayName("Test containsKey operation")
    void testContainsKey() {
        assertFalse(hashTable.containsKey("key1"));

        hashTable.put("key1", 100);
        assertTrue(hashTable.containsKey("key1"));
        assertFalse(hashTable.containsKey("key2"));

        hashTable.remove("key1");
        assertFalse(hashTable.containsKey("key1"));
    }

    // Clear Tests
    @Test
    @DisplayName("Test clear operation")
    void testClear() {
        hashTable.put("key1", 100);
        hashTable.put("key2", 200);
        hashTable.put("key3", 300);
        assertEquals(3, hashTable.size());

        hashTable.clear();
        assertEquals(0, hashTable.size());
        assertTrue(hashTable.isEmpty());
        assertNull(hashTable.get("key1"));
        assertNull(hashTable.get("key2"));
        assertNull(hashTable.get("key3"));
    }

    // Collision Handling Tests
    @Test
    @DisplayName("Test collision handling")
    void testCollisionHandling() {
        // Create a small hash table to force collisions
        HashTable<String, Integer> smallTable = new HashTable<>(2);

        // Add multiple entries that will likely collide
        smallTable.put("a", 1);
        smallTable.put("b", 2);
        smallTable.put("c", 3);
        smallTable.put("d", 4);

        // Verify all entries can be retrieved
        assertEquals(Integer.valueOf(1), smallTable.get("a"));
        assertEquals(Integer.valueOf(2), smallTable.get("b"));
        assertEquals(Integer.valueOf(3), smallTable.get("c"));
        assertEquals(Integer.valueOf(4), smallTable.get("d"));
        assertEquals(4, smallTable.size());
    }

    @Test
    @DisplayName("Test collision with duplicate keys")
    void testCollisionWithDuplicates() {
        HashTable<String, Integer> smallTable = new HashTable<>(2);

        smallTable.put("a", 1);
        smallTable.put("b", 2);
        smallTable.put("a", 10); // Update existing key

        assertEquals(Integer.valueOf(10), smallTable.get("a"));
        assertEquals(Integer.valueOf(2), smallTable.get("b"));
        assertEquals(2, smallTable.size());
    }

    @Test
    @DisplayName("Test remove from collision chain")
    void testRemoveFromCollisionChain() {
        HashTable<String, Integer> smallTable = new HashTable<>(2);

        // Add entries that will collide
        smallTable.put("a", 1);
        smallTable.put("b", 2);
        smallTable.put("c", 3);

        // Remove middle entry from chain
        assertEquals(Integer.valueOf(2), smallTable.remove("b"));
        assertEquals(2, smallTable.size());

        // Verify other entries still accessible
        assertEquals(Integer.valueOf(1), smallTable.get("a"));
        assertEquals(Integer.valueOf(3), smallTable.get("c"));
        assertNull(smallTable.get("b"));
    }

    // Resizing Tests
    @Test
    @DisplayName("Test automatic resizing")
    void testAutomaticResizing() {
        HashTable<Integer, String> table = new HashTable<>(4, 0.75); // Small initial capacity

        // Add entries to trigger resize (4 * 0.75 = 3, so 4th entry should trigger resize)
        table.put(1, "one");
        table.put(2, "two");
        table.put(3, "three");
        assertEquals(3, table.size());

        // This should trigger resize
        table.put(4, "four");
        assertEquals(4, table.size());

        // Verify all entries are still accessible after resize
        assertEquals("one", table.get(1));
        assertEquals("two", table.get(2));
        assertEquals("three", table.get(3));
        assertEquals("four", table.get(4));
    }

    @Test
    @DisplayName("Test load factor calculation")
    void testLoadFactor() {
        HashTable<String, Integer> table = new HashTable<>(4);

        assertEquals(0.0, table.getCurrentLoadFactor(), 0.001);

        table.put("a", 1);
        assertEquals(0.25, table.getCurrentLoadFactor(), 0.001);

        table.put("b", 2);
        assertEquals(0.5, table.getCurrentLoadFactor(), 0.001);
    }

    // Edge Cases and Stress Tests
    @Test
    @DisplayName("Test with different key types")
    void testDifferentKeyTypes() {
        // Test with Integer keys
        intKeyTable.put(1, "one");
        intKeyTable.put(2, "two");
        intKeyTable.put(100, "hundred");

        assertEquals("one", intKeyTable.get(1));
        assertEquals("two", intKeyTable.get(2));
        assertEquals("hundred", intKeyTable.get(100));
        assertEquals(3, intKeyTable.size());
    }

    @Test
    @DisplayName("Test with negative hash codes")
    void testNegativeHashCodes() {
        // Create objects that might have negative hash codes
        HashTable<String, String> table = new HashTable<>();

        // These strings might produce negative hash codes
        String[] keys = {"test", "negative", "hash", "codes", "example"};

        for (int i = 0; i < keys.length; i++) {
            table.put(keys[i], "value" + i);
        }

        // Verify all can be retrieved
        for (int i = 0; i < keys.length; i++) {
            assertEquals("value" + i, table.get(keys[i]));
        }

        assertEquals(keys.length, table.size());
    }

    @Test
    @DisplayName("Test large number of entries")
    void testLargeNumberOfEntries() {
        HashTable<Integer, String> table = new HashTable<>();

        // Add 1000 entries
        for (int i = 0; i < 1000; i++) {
            table.put(i, "value" + i);
        }

        assertEquals(1000, table.size());

        // Verify all entries can be retrieved
        for (int i = 0; i < 1000; i++) {
            assertEquals("value" + i, table.get(i));
        }

        // Remove every other entry
        for (int i = 0; i < 1000; i += 2) {
            assertEquals("value" + i, table.remove(i));
        }

        assertEquals(500, table.size());

        // Verify remaining entries
        for (int i = 1; i < 1000; i += 2) {
            assertEquals("value" + i, table.get(i));
        }
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        // Test empty table
        assertEquals("{}", hashTable.toString());

        // Test with entries
        hashTable.put("key1", 100);
        hashTable.put("key2", 200);

        String result = hashTable.toString();
        assertTrue(result.contains("key1=100"));
        assertTrue(result.contains("key2=200"));
        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
    }
}
