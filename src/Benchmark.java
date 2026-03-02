/**
 * Benchmark for measuring DocumentStore performance.
 * 
 * YOUR TASK:
 * Complete this benchmark to measure:
 * 1. Time to append N documents
 * 2. Time to iterate over all documents
 * 
 * Test with at least two different values of N (e.g., 10,000 and 100,000).
 * 
 * Include your results in your design document with brief interpretation.
 */
public class Benchmark {
    
    /**
     * Generates a test document with the given ID.
     *
     * @param id the document ID
     * @return a new document
     */
    private static Document generateDocument(int id) {
        return new Document(
            "doc-" + id,
            "This is test document number " + id + ". It contains some sample text."
        );
    }
    
    /**
     * Benchmarks adding N documents to the store.
     *
     * @param n the number of documents to add
     * @return the time in milliseconds
     */
    public static long benchmarkAddDocuments(int n) {
        // TODO: Implement this benchmark
        //
        // Steps:
        // 1. Create a new DocumentStore
        // 2. Record start time
        // 3. Add n documents
        // 4. Record end time
        // 5. Return elapsed time in milliseconds
        //
        // Use System.nanoTime() for more precision, then convert to millis
        
        DocumentStore store = new DocumentStore();
        
        long startTime = System.nanoTime();
        
        // TODO: Add n documents
        
        long endTime = System.nanoTime();
        
        return (endTime - startTime) / 1_000_000; // Convert to milliseconds
    }
    
    /**
     * Benchmarks iterating over all documents in a store.
     *
     * @param n the number of documents to add before iterating
     * @return the time in milliseconds
     */
    public static long benchmarkIterateDocuments(int n) {
        // TODO: Implement this benchmark
        //
        // Steps:
        // 1. Create a new DocumentStore
        // 2. Add n documents
        // 3. Record start time
        // 4. Iterate through all documents (you must actually access each one)
        // 5. Record end time
        // 6. Return elapsed time
        
        DocumentStore store = new DocumentStore();
        
        // TODO: Add n documents first
        
        long startTime = System.nanoTime();
        
        // TODO: Iterate through all documents
        // Make sure you actually access each document (e.g., call getDocId())
        // to ensure the JVM doesn't optimize away the iteration
        
        long endTime = System.nanoTime();
        
        return (endTime - startTime) / 1_000_000;
    }
    
    /**
     * Main method to run benchmarks.
     */
    public static void main(String[] args) {
        System.out.println("=== Document Store Benchmark ===\n");
        
        // TODO: Run benchmarks with different sizes
        // Example sizes: 10000, 100000
        
        int[] sizes = {10_000, 100_000};
        
        for (int n : sizes) {
            System.out.println("Testing with N = " + n + " documents:");
            
            // Benchmark adding documents
            long addTime = benchmarkAddDocuments(n);
            System.out.println("  Add time: " + addTime + " ms");
            System.out.println("  Avg time per document: " + (addTime * 1000.0 / n) + " microseconds");
            
            // Benchmark iterating documents
            long iterateTime = benchmarkIterateDocuments(n);
            System.out.println("  Iterate time: " + iterateTime + " ms");
            System.out.println("  Avg time per document: " + (iterateTime * 1000.0 / n) + " microseconds");
            
            System.out.println();
        }
        
        // TODO (OPTIONAL): Add more benchmarks as needed
        // For example:
        // - How does performance change with different initial capacities?
        // - What's the overhead of resizing operations?
        // - What's the maximum time for add operations?
        
        System.out.println("=== Benchmark Complete ===");
        System.out.println("\nInclude these results in your design document.");
        System.out.println("Briefly interpret what these numbers tell you about your implementation.");
    }
}
