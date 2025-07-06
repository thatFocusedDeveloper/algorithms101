package course.algorithms;

import course.algorithms.QuickSort;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuickSortTest {

    private QuickSort quickSort;

    @BeforeEach
    public void SetUp() {
        quickSort = new QuickSort();
    }

    @Test
    public void Sort() {
        int[] array = {15, 3, 2, 1, 9, 5, 7, 8, 6};

        int[] sorted = quickSort.sort(array);

        assertEquals(1, sorted[0]);
        assertEquals(2, sorted[1]);
        assertEquals(3, sorted[2]);
        assertEquals(5, sorted[3]);
        assertEquals(6, sorted[4]);
        assertEquals(7, sorted[5]);
        assertEquals(8, sorted[6]);
        assertEquals(9, sorted[7]);
        assertEquals(15, sorted[8]);

        quickSort.prettyPrint(sorted);
    }

}
