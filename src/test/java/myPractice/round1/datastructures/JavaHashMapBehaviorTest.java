package myPractice.round1.datastructures;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.HashMap;

/**
 * Test to understand Java HashMap's resize behavior vs our implementation
 */
public class JavaHashMapBehaviorTest {

    @Test
    @DisplayName("Compare resize timing: Our HashTable vs Java HashMap")
    void compareResizeTiming() {
        System.out.println("=== RESIZE BEHAVIOR COMPARISON ===");
        System.out.println("Initial capacity: 16, Load factor: 0.75, Threshold: 12");
        System.out.println();

        // Test our implementation
        HashTable<Integer, String> ourTable = new HashTable<>(16, 0.75);
        System.out.println("🔹 OUR HASHTABLE BEHAVIOR:");

        for (int i = 1; i <= 15; i++) {
            double loadFactorBefore = ourTable.getCurrentLoadFactor();
            int sizeBefore = ourTable.size();

            ourTable.put(i, "value" + i);

            double loadFactorAfter = ourTable.getCurrentLoadFactor();
            int sizeAfter = ourTable.size();

            if (loadFactorAfter < loadFactorBefore && sizeAfter > sizeBefore) {
                System.out.printf("  ⚡ RESIZE at element %d: size %d->%d, load factor %.3f->%.3f\n",
                                i, sizeBefore, sizeAfter, loadFactorBefore, loadFactorAfter);
                System.out.println("     → Resize happened BEFORE insertion");
            }
        }

        System.out.println();
        System.out.println("🔹 JAVA HASHMAP BEHAVIOR (based on source code analysis):");
        System.out.println("  Java HashMap uses: if (++size > threshold) resize()");
        System.out.println("  This means:");
        System.out.println("  1. Insert the element first (++size)");
        System.out.println("  2. Then check if size > threshold");
        System.out.println("  3. If true, resize AFTER insertion");
        System.out.println();
        System.out.println("  So Java HashMap would resize at element 13:");
        System.out.println("  - Insert element 13: size becomes 13");
        System.out.println("  - Check: 13 > 12? YES → resize");
        System.out.println("  - Resize happens AFTER insertion");

        // Demonstrate the difference
        System.out.println();
        System.out.println("🔹 KEY DIFFERENCE:");
        System.out.println("  Our implementation: size >= capacity * loadFactor (BEFORE insertion)");
        System.out.println("  Java HashMap:       ++size > threshold (AFTER insertion)");
        System.out.println();
        System.out.println("  Both resize at the 13th element, but at different times:");
        System.out.println("  - Ours: Resize first, then insert");
        System.out.println("  - Java: Insert first, then resize");
    }

    @Test
    @DisplayName("Demonstrate the theoretical difference")
    void demonstrateTheoreticalDifference() {
        System.out.println("=== THEORETICAL RESIZE TIMING ===");
        System.out.println();

        System.out.println("📊 OUR APPROACH (Proactive):");
        System.out.println("  for (int i = 1; i <= 13; i++) {");
        System.out.println("    if (size >= capacity * loadFactor) resize(); // Check BEFORE");
        System.out.println("    insert(element);");
        System.out.println("  }");
        System.out.println();
        System.out.println("  Element 13: size=12, 12 >= 12? YES → resize first, then insert");
        System.out.println("  Result: Never exceeds load factor threshold");
        System.out.println();

        System.out.println("📊 JAVA APPROACH (Reactive):");
        System.out.println("  for (int i = 1; i <= 13; i++) {");
        System.out.println("    insert(element);");
        System.out.println("    if (++size > threshold) resize(); // Check AFTER");
        System.out.println("  }");
        System.out.println();
        System.out.println("  Element 13: insert first (size=13), 13 > 12? YES → resize after");
        System.out.println("  Result: Temporarily exceeds threshold before resizing");
        System.out.println();

        System.out.println("🎯 BOTH ARE VALID APPROACHES:");
        System.out.println("  ✅ Proactive (Ours): Prevents threshold violation");
        System.out.println("  ✅ Reactive (Java):  Simpler logic, slightly more efficient");
        System.out.println("  ✅ Both maintain O(1) average performance");
    }
}
