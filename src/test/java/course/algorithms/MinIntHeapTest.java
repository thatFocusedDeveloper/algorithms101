package course.algorithms;

import course.algorithms.MinIntHeap;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinIntHeapTest {

    private MinIntHeap minHeap;

    @BeforeEach
    public void setUp() {
        minHeap = new MinIntHeap();
        minHeap.add(6);
        minHeap.add(5);
        minHeap.add(4);
        minHeap.add(3);
        minHeap.add(2);
        minHeap.add(1);
    }

    @Test
    public void Insert() {
        // Remember: The array walks top down / left to right
        assertEquals(1, minHeap.items[0]);
        assertEquals(3, minHeap.items[1]);
        assertEquals(2, minHeap.items[2]);
        assertEquals(6, minHeap.items[3]);
        assertEquals(4, minHeap.items[4]);
        assertEquals(5, minHeap.items[5]);
    }

    @Test
    public void ExtractMin() {
        assertEquals(1, minHeap.extractMin());
        assertEquals(2, minHeap.extractMin());
        assertEquals(3, minHeap.extractMin());
        assertEquals(4, minHeap.extractMin());
        assertEquals(5, minHeap.extractMin());
        assertEquals(6, minHeap.extractMin());
    }
}
