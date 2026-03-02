import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A document store that manages a stream of documents using a circular buffer.
 * 
 * This store supports:
 * - Adding documents to the store (append-only)
 * - Undoing the most recent addition
 * - Retrieving the last K documents
 * - Replaying all documents in ingestion order
 * 
 * DESIGN CONSTRAINTS:
 * - Must use your CircularBuffer implementation
 * - Must NOT use any other collection classes for primary storage
 * - All operations should be efficient given the circular buffer's capabilities
 */
public class DocumentStore {
    
    // TODO: Declare your instance variables
    // Hint: You need a CircularBuffer<Document>
    
    /**
     * Constructs an empty document store.
     */
    public DocumentStore() {
        // TODO: Initialize your circular buffer
    }
    
    /**
     * Adds a document to the store.
     * Documents are appended in the order they are added.
     *
     * @param document the document to add
     * @throws IllegalArgumentException if document is null
     */
    public void addDocument(Document document) {
        // TODO: Implement this method
        // This should run in amortized O(1) time
    
       
    }
    
    /**
     * Removes and returns the most recently added document.
     * This is an "undo" operation.
     *
     * @return the document that was removed
     * @throws IllegalStateException if the store is empty
     */
    public Document undoLast() {
        // TODO: Implement this method
        //
        
        throw new IllegalStateException("Document store is empty");
    }
    
    /**
     * Returns the last K documents in the order they were added.
     * If K is greater than the number of documents, returns all documents.
     *
     * @param k the number of recent documents to retrieve
     * @return a list of the last k documents (oldest to newest)
     * @throws IllegalArgumentException if k is negative
     */
    public List<Document> getLastK(int k) {
        // TODO: Implement this method
        //
        
        // Hint: Straightforward approach is to iterate through all documents and only
        // keep the last k. Think about how to do this efficiently.
        
        throw new IllegalArgumentException("k must be non-negative");
    }
    
    /**
     * Returns an iterator that replays all documents in ingestion order
     * (from first added to most recently added).
     *
     * @return an iterator over all documents
     */
    public Iterator<Document> replayAll() {
        // TODO: Implement this method
        //
        // Hint: Your circular buffer already provides an iterator
        // that goes from oldest to newest. Can you use it?
        
        return null;
    }
    
    /**
     * Returns the number of documents currently in the store.
     *
     * @return the number of documents
     */
    public int size() {
        // TODO: Implement this method
    }
    
    /**
     * Returns true if the store contains no documents.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        // TODO: Implement this method
        return size() == 0;
    }
    
    // =========================================================================
    // OPTIONAL: Additional helper methods
    // =========================================================================
    
    /**
     * Returns a string representation of the store (for debugging).
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "DocumentStore{size=" + size() + "}";
    }
}
