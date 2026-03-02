import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Student-written tests for Project 1.
 * 
 * INSTRUCTIONS:
 * You must write at least 3 tests that verify your design decisions and invariants.
 * Each test should include comments explaining:
 * 1. What you're testing
 * 2. Why it's important
 * 3. How it relates to your invariants or design choices
 * 
 * GRADING (15 points total):
 * - Test 1 (Invariant Test): 5 points
 * - Test 2 (Iterator Correctness): 5 points
 * - Test 3 (Edge Case of Your Choice): 5 points
 * 
 * Points awarded for:
 * - Correctness of the test (does it actually test what you claim?)
 * - Quality of explanation (do you demonstrate understanding?)
 * - Thoroughness (does the test adequately verify the concept?)
 */
public class StudentTests {
    
    private CircularBuffer<String> buffer;
    private DocumentStore store;
    
    @Before
    public void setUp() {
        buffer = new CircularBuffer<>();
        store = new DocumentStore();
    }
    
    // =========================================================================
    // TEST 1: Invariant Verification (5 points)
    // =========================================================================
    
    /**
     * TODO: Write a test that verifies one or more of your circular buffer invariants.
     * 
     * In your design document, you stated specific invariants that your buffer maintains.
     * This test should verify that those invariants hold after a sequence of operations.
     * 
     * Example invariants you might test:
     * - 0 <= size <= capacity (always)
     * - If size == 0, then isEmpty() returns true
     * - head and tail are always in range [0, capacity)
     * - After adding N elements and removing M elements, size == N - M
     * - Iterator visits exactly size() elements
     * 
     * Your test should:
     * 1. Perform a sequence of adds and/or removes
     * 2. Check that your invariants still hold
     * 3. Include comments explaining which invariant(s) you're testing
     * 
     * Example structure:
     * @Test
     * public void testMySpecificInvariant() {
     *     // Invariant being tested: [explain your invariant]
     *     
     *     // Perform operations
     *     buffer.addLast("A");
     *     buffer.addLast("B");
     *     
     *     // Verify invariant holds
     *     assertEquals(2, buffer.size());
     *     // ... more assertions
     * }
     */
    @Test
    public void testInvariant() {
        // TODO: Implement this test
        // 
        // Delete this fail() call and write your actual test.
        // Make sure to include detailed comments explaining what invariant
        // you're testing and why it's important.
        
        fail("You must implement testInvariant(). See comments above for guidance.");
    }
    
    // =========================================================================
    // TEST 2: Iterator Correctness (5 points)
    // =========================================================================
    
    /**
     * TODO: Write a test that thoroughly verifies iterator correctness.
     * 
     * Your iterator must iterate from oldest to newest element. This test should
     * verify this property under various conditions.
     * 
     * Consider testing:
     * - Iterator order after multiple adds
     * - Iterator order after adds and removes
     * - Iterator after buffer has resized
     * - Iterator behavior when buffer has wrapped around
     * 
     * Your test should demonstrate that you understand how the circular nature
     * of your buffer affects iteration.
     * 
     * Example structure:
     * @Test
     * public void testIteratorAfterComplexSequence() {
     *     // Add several elements
     *     buffer.addLast("first");
     *     buffer.addLast("second");
     *     // ... etc
     *     
     *     // Remove some
     *     buffer.removeLast();
     *     
     *     // Add more (might cause wraparound or resize)
     *     buffer.addLast("new");
     *     
     *     // Verify iteration order
     *     Iterator<String> it = buffer.iterator();
     *     assertEquals("first", it.next());
     *     // ... verify complete order
     * }
     */
    @Test
    public void testIteratorCorrectness() {
        // TODO: Implement this test
        // 
        // Your test should verify that iteration order is correct even after
        // complex sequences of operations.
        //
        // Include comments explaining:
        // - What sequence of operations you're testing
        // - Why this sequence might be tricky for iteration
        // - How your implementation handles it correctly
        
        fail("You must implement testIteratorCorrectness(). See comments above for guidance.");
    }
    
    // =========================================================================
    // TEST 3: Edge Case of Your Choice (5 points)
    // =========================================================================
    
    /**
     * TODO: Write a test for an edge case that you think is important.
     * 
     * Choose an edge case that you believe could expose bugs in an incorrect
     * implementation. This demonstrates your understanding of where things
     * could go wrong.
     * 
     * Possible edge cases to consider:
     * - Boundary conditions (exactly at capacity, exactly empty)
     * - Wraparound conditions (head or tail at end of array)
     * - Resize timing (resize when head/tail are at specific positions)
     * - getLastK() with boundary values
     * - Multiple iterators on the same buffer
     * - Very large number of operations
     * 
     * Your test should include comments explaining:
     * - What edge case you're testing
     * - Why this case is potentially tricky
     * - What could go wrong if the implementation is incorrect
     * - How your implementation handles it correctly
     * 
     * Example:
     * @Test
     * public void testResizeWhenHeadIsAtEnd() {
     *     // Edge case: This tests resizing when head is at the end of the array
     *     // This is tricky because...
     *     // If implemented incorrectly, this could cause...
     *     
     *     // Create specific condition
     *     CircularBuffer<String> small = new CircularBuffer<>(4);
     *     // ... set up the edge case
     *     
     *     // Verify correct behavior
     *     // ... assertions
     * }
     */
    @Test
    public void testEdgeCase() {
        // TODO: Implement this test
        // 
        // Choose an edge case that you find interesting or potentially problematic.
        // Include extensive comments explaining:
        // 1. What edge case you're testing
        // 2. Why it's tricky
        // 3. What could go wrong
        // 4. How to verify it's correct
        
        fail("You must implement testEdgeCase(). See comments above for guidance.");
    }
    
    // =========================================================================
    // OPTIONAL: Additional Tests (No Extra Credit, But Good Practice)
    // =========================================================================
    
    /**
     * You may add more tests here if you wish. Additional tests won't earn
     * extra credit, but they will:
     * - Help you verify your implementation is correct
     * - Give you practice writing good tests
     * - Demonstrate thoroughness in your work
     * 
     * Some ideas:
     * - Test DocumentStore operations in detail
     * - Test interaction between CircularBuffer and DocumentStore
     * - Performance tests (does your implementation scale?)
     * - Stress tests (very large N)
     */
    
    // Example optional test (you can delete this):
    // @Test
    // public void testSomethingElse() {
    //     // Your test here
    // }
}
