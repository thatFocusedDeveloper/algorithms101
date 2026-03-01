package myPractice.round1.datastructures;

import java.util.*;

/**
 * Problem: Find the maximum length subarray where the running balance
 * (cumulative sum from start of subarray) never goes negative.
 */
public class MaxBalancedSubarray {

    /**
     * BRUTE FORCE APPROACH
     * 
     * For each starting position, extend as far as possible while
     * maintaining a non-negative running balance.
     * 
     * Time Complexity: O(n²) - nested loops over all subarrays
     * Space Complexity: O(1) - only using a few variables
     */
    public static int bruteForce(int[] transactions) {
        int n = transactions.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int balance = 0;
            for (int j = i; j < n; j++) {
                balance += transactions[j];
                if (balance < 0) {
                    break; // Running balance went negative, stop extending
                }
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }

        return maxLen;
    }

    /**
     * OPTIMAL APPROACH using Monotonic Stack
     * 
     * Key Insight:
     * - Compute prefix sums: prefix[i] = sum of arr[0..i-1]
     * - For subarray starting at index i to be valid up to index j:
     *   All running balances must be non-negative, i.e.,
     *   prefix[k] >= prefix[i] for all k in [i+1, j+1]
     * - Use monotonic stack to find "next smaller element" in prefix array
     * - This tells us exactly where we must stop for each starting position
     * 
     * Time Complexity: O(n) - single pass for prefix sums, single pass for stack
     * Space Complexity: O(n) - prefix array and nextSmaller array
     */
    public static int optimal(int[] transactions) {
        int n = transactions.length;
        if (n == 0) return 0;

        // Step 1: Compute prefix sums
        // prefix[i] = sum of transactions[0..i-1], prefix[0] = 0
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + transactions[i];
        }

        // Step 2: Find next smaller element for each position using monotonic stack
        // nextSmaller[i] = first index j > i where prefix[j] < prefix[i]
        int[] nextSmaller = new int[n + 1];
        Arrays.fill(nextSmaller, n + 1); // Default: no smaller element exists

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            // Pop all elements that are greater than current (found their next smaller)
            while (!stack.isEmpty() && prefix[stack.peek()] > prefix[i]) {
                nextSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }

        // Step 3: Calculate maximum length subarray
        // For starting index i, valid length = nextSmaller[i] - i - 1
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int len = nextSmaller[i] - i - 1;
            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] test1 = {100, -50, -25, 200, -75, -150};
        System.out.println("Input: " + Arrays.toString(test1));
        System.out.println("Brute Force: " + bruteForce(test1));
        System.out.println("Optimal:     " + optimal(test1));
        printValidSubarray(test1, 0, bruteForce(test1));
        System.out.println();

        // Test Case 2
        int[] test2 = {100, -150, 200, -50};
        System.out.println("Input: " + Arrays.toString(test2));
        System.out.println("Brute Force: " + bruteForce(test2));
        System.out.println("Optimal:     " + optimal(test2));
        printValidSubarray(test2, 2, 2); // Starting at index 2
        System.out.println();

        // Additional Test Cases
        int[] test3 = {-10, 20, -5, 30};
        System.out.println("Input: " + Arrays.toString(test3));
        System.out.println("Brute Force: " + bruteForce(test3));
        System.out.println("Optimal:     " + optimal(test3));

        int[] test4 = {50, 50, -25, -25, -25, -25};
        System.out.println("\nInput: " + Arrays.toString(test4));
        System.out.println("Brute Force: " + bruteForce(test4));
        System.out.println("Optimal:     " + optimal(test4));
    }

    // Helper method to visualize the running balance
    private static void printValidSubarray(int[] arr, int start, int length) {
        if (length == 0) {
            System.out.println("No valid subarray");
            return;
        }
        System.out.print("Subarray [");
        int balance = 0;
        StringBuilder balanceStr = new StringBuilder("Running balance: ");
        for (int i = start; i < start + length; i++) {
            System.out.print(arr[i] + (i < start + length - 1 ? ", " : ""));
            balance += arr[i];
            balanceStr.append(balance).append(i < start + length - 1 ? " → " : "");
        }
        System.out.println("]");
        System.out.println(balanceStr);
    }
}
