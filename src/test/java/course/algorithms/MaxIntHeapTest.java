package course.algorithms;

import course.algorithms.MaxIntHeap;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MaxIntHeapTest {

    private MaxIntHeap maxHeap;

    @Test
    public void ExtractMax() {
        maxHeap = new MaxIntHeap();
        maxHeap.insert(42);
        maxHeap.insert(29);
        maxHeap.insert(18);

        // Insert(35)
        maxHeap.insert(35);



        // Test insert
        assertEquals(42, maxHeap.items[0]);
        assertEquals(35, maxHeap.items[1]);
        assertEquals(18, maxHeap.items[2]);
        assertEquals(29, maxHeap.items[3]);

        // Text extract max
        assertEquals(42, maxHeap.extractMax());
        assertEquals(35, maxHeap.extractMax());
        assertEquals(29, maxHeap.extractMax());
        assertEquals(18, maxHeap.extractMax());

        maxHeap.print();
    }

    @Test
    public void ExtractMaxBigger() {
        maxHeap = new MaxIntHeap();
        maxHeap.insert(42);
        maxHeap.insert(29);
        maxHeap.insert(18);
        maxHeap.insert(14);
        maxHeap.insert(7);
        maxHeap.insert(18);
        maxHeap.insert(12);
        maxHeap.insert(11);
        maxHeap.insert(13);

        assertEquals(42, maxHeap.extractMax());
        assertEquals(29, maxHeap.extractMax());
        assertEquals(18, maxHeap.extractMax());
        assertEquals(18, maxHeap.extractMax());
        assertEquals(14, maxHeap.extractMax());
        assertEquals(13, maxHeap.extractMax());
        assertEquals(12, maxHeap.extractMax());
        assertEquals(11, maxHeap.extractMax());
        assertEquals(7, maxHeap.extractMax());
    }

    @Test
    public void Insert35() {
        System.out.println("9/2 = " + 9 / 2);
        System.out.println("7/2 = " + 7 / 2);

    }

}
