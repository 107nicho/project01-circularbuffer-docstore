import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Provided tests for Project 1: Circular Buffer & Document Store
 * 
 * These tests verify the basic correctness of your implementation.
 * Passing all these tests is necessary but not sufficient for full credit.
 * 
 * You should:
 * 1. Use these tests to guide your implementation
 * 2. Run these tests frequently as you develop
 * 3. Write additional tests in StudentTests.java
 * 
 * DO NOT MODIFY THIS FILE.
 */
public class ProvidedTests {
    
    private CircularBuffer<String> buffer;
    private DocumentStore store;
    
    @Before
    public void setUp() {
        buffer = new CircularBuffer<>();
        store = new DocumentStore();
    }
    
    // =========================================================================
    // CircularBuffer: Basic Operations
    // =========================================================================
    
    @Test
    public void testEmptyBufferSize() {
        assertEquals("New buffer should have size 0", 0, buffer.size());
        assertTrue("New buffer should be empty", buffer.isEmpty());
    }
    
    @Test
    public void testAddLastSingleElement() {
        buffer.addLast("first");
        assertEquals("Size should be 1 after adding one element", 1, buffer.size());
        assertFalse("Buffer should not be empty", buffer.isEmpty());
    }
    
    @Test
    public void testAddLastMultipleElements() {
        buffer.addLast("first");
        buffer.addLast("second");
        buffer.addLast("third");
        assertEquals("Size should be 3 after adding three elements", 3, buffer.size());
    }
    
    @Test
    public void testRemoveLastSingleElement() {
        buffer.addLast("only");
        String removed = buffer.removeLast();
        assertEquals("Removed element should be 'only'", "only", removed);
        assertEquals("Size should be 0 after removing", 0, buffer.size());
        assertTrue("Buffer should be empty after removing last element", buffer.isEmpty());
    }
    
    @Test
    public void testRemoveLastMultipleElements() {
        buffer.addLast("first");
        buffer.addLast("second");
        buffer.addLast("third");
        
        assertEquals("Should remove 'third'", "third", buffer.removeLast());
        assertEquals("Size should be 2", 2, buffer.size());
        
        assertEquals("Should remove 'second'", "second", buffer.removeLast());
        assertEquals("Size should be 1", 1, buffer.size());
        
        assertEquals("Should remove 'first'", "first", buffer.removeLast());
        assertEquals("Size should be 0", 0, buffer.size());
        assertTrue("Buffer should be empty", buffer.isEmpty());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastFromEmptyBuffer() {
        buffer.removeLast(); // Should throw NoSuchElementException
    }
    
    @Test
    public void testRemoveFirstSingleElement() {
        buffer.addLast("only");
        String removed = buffer.removeFirst();
        assertEquals("Removed element should be 'only'", "only", removed);
        assertEquals("Size should be 0 after removing", 0, buffer.size());
        assertTrue("Buffer should be empty after removing", buffer.isEmpty());
    }
    
    @Test
    public void testRemoveFirstMultipleElements() {
        buffer.addLast("first");
        buffer.addLast("second");
        buffer.addLast("third");
        
        assertEquals("Should remove 'first'", "first", buffer.removeFirst());
        assertEquals("Size should be 2", 2, buffer.size());
        
        assertEquals("Should remove 'second'", "second", buffer.removeFirst());
        assertEquals("Size should be 1", 1, buffer.size());
        
        assertEquals("Should remove 'third'", "third", buffer.removeFirst());
        assertEquals("Size should be 0", 0, buffer.size());
        assertTrue("Buffer should be empty", buffer.isEmpty());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstFromEmptyBuffer() {
        buffer.removeFirst(); // Should throw NoSuchElementException
    }
    
    @Test
    public void testMixedRemoveFirstAndLast() {
        buffer.addLast("A");
        buffer.addLast("B");
        buffer.addLast("C");
        buffer.addLast("D");
        
        assertEquals("Remove from front", "A", buffer.removeFirst());
        assertEquals("Remove from back", "D", buffer.removeLast());
        assertEquals("Remove from front", "B", buffer.removeFirst());
        assertEquals("Remove from back", "C", buffer.removeLast());
        
        assertTrue("Should be empty", buffer.isEmpty());
    }
    
    @Test
    public void testRemoveFirstPreservesOrder() {
        buffer.addLast("A");
        buffer.addLast("B");
        buffer.addLast("C");
        buffer.addLast("D");
        buffer.addLast("E");
        
        // Remove first two
        buffer.removeFirst();
        buffer.removeFirst();
        
        // Remaining should be C, D, E in order
        Iterator<String> it = buffer.iterator();
        assertEquals("C", it.next());
        assertEquals("D", it.next());
        assertEquals("E", it.next());
        assertFalse(it.hasNext());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromEmptyBuffer() {
        buffer.removeLast(); // Should throw NoSuchElementException
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullElement() {
        buffer.addLast(null); // Should throw IllegalArgumentException
    }
    
    // =========================================================================
    // CircularBuffer: Resizing
    // =========================================================================
    
    @Test
    public void testResizingPreservesOrder() {
        // Add elements to trigger at least one resize (default capacity is 8)
        for (int i = 0; i < 20; i++) {
            buffer.addLast("element" + i);
        }
        
        assertEquals("Size should be 20", 20, buffer.size());
        
        // Verify order is preserved
        Iterator<String> it = buffer.iterator();
        for (int i = 0; i < 20; i++) {
            assertTrue("Iterator should have next element", it.hasNext());
            assertEquals("Element should be in correct order", "element" + i, it.next());
        }
        assertFalse("Iterator should be exhausted", it.hasNext());
    }
    
    @Test
    public void testMultipleResizes() {
        // Add enough elements to trigger multiple resizes
        // Default capacity = 8, so this should resize 3-4 times
        for (int i = 0; i < 100; i++) {
            buffer.addLast("item" + i);
        }
        
        assertEquals("Size should be 100", 100, buffer.size());
        
        // Remove some and verify
        for (int i = 0; i < 10; i++) {
            buffer.removeLast();
        }
        
        assertEquals("Size should be 90 after removing 10", 90, buffer.size());
    }
    
    @Test
    public void testResizeAfterRemoval() {
        // Fill buffer
        for (int i = 0; i < 10; i++) {
            buffer.addLast("A" + i);
        }
        
        // Remove some
        for (int i = 0; i < 5; i++) {
            buffer.removeLast();
        }
        
        assertEquals("Size should be 5", 5, buffer.size());
        
        // Add more to trigger resize
        for (int i = 0; i < 20; i++) {
            buffer.addLast("B" + i);
        }
        
        assertEquals("Size should be 25", 25, buffer.size());
        
        // Verify order: A0-A4, then B0-B19
        Iterator<String> it = buffer.iterator();
        for (int i = 0; i < 5; i++) {
            assertEquals("A" + i, it.next());
        }
        for (int i = 0; i < 20; i++) {
            assertEquals("B" + i, it.next());
        }
    }
    
    // =========================================================================
    // CircularBuffer: Iterator
    // =========================================================================
    
    @Test
    public void testIteratorEmptyBuffer() {
        Iterator<String> it = buffer.iterator();
        assertFalse("Empty buffer iterator should not have next", it.hasNext());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextOnEmpty() {
        Iterator<String> it = buffer.iterator();
        it.next(); // Should throw NoSuchElementException
    }
    
    @Test
    public void testIteratorOrder() {
        buffer.addLast("first");
        buffer.addLast("second");
        buffer.addLast("third");
        
        Iterator<String> it = buffer.iterator();
        assertEquals("First element should be 'first'", "first", it.next());
        assertEquals("Second element should be 'second'", "second", it.next());
        assertEquals("Third element should be 'third'", "third", it.next());
        assertFalse("Iterator should be done", it.hasNext());
    }
    
    @Test
    public void testIteratorAfterRemoval() {
        buffer.addLast("A");
        buffer.addLast("B");
        buffer.addLast("C");
        buffer.removeLast(); // Remove "C"
        
        Iterator<String> it = buffer.iterator();
        assertEquals("A", it.next());
        assertEquals("B", it.next());
        assertFalse("Should only have 2 elements", it.hasNext());
    }
    
    // =========================================================================
    // CircularBuffer: Wraparound (Testing Circular Behavior)
    // =========================================================================
    
    @Test
    public void testCircularWraparound() {
        // Use small capacity to test wraparound
        CircularBuffer<Integer> smallBuffer = new CircularBuffer<>(4);
        
        // Fill to capacity
        smallBuffer.addLast(1);
        smallBuffer.addLast(2);
        smallBuffer.addLast(3);
        smallBuffer.addLast(4);
        
        // Remove some from end
        smallBuffer.removeLast(); // Remove 4
        smallBuffer.removeLast(); // Remove 3
        
        // Add more (this should wrap around)
        smallBuffer.addLast(5);
        smallBuffer.addLast(6);
        smallBuffer.addLast(7); // This should trigger resize
        
        assertEquals("Should have 5 elements", 5, smallBuffer.size());
        
        // Verify order
        Iterator<Integer> it = smallBuffer.iterator();
        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(5), it.next());
        assertEquals(Integer.valueOf(6), it.next());
        assertEquals(Integer.valueOf(7), it.next());
    }
    
    @Test
    public void testRemoveFirstWithWraparound() {
        // Test that removeFirst causes proper head wraparound
        CircularBuffer<Integer> small = new CircularBuffer<>(4);
        
        // Fill buffer
        small.addLast(1);
        small.addLast(2);
        small.addLast(3);
        small.addLast(4);
        
        // Remove from front (head moves forward)
        assertEquals(Integer.valueOf(1), small.removeFirst());
        assertEquals(Integer.valueOf(2), small.removeFirst());
        
        // Add more (tail wraps around)
        small.addLast(5);
        small.addLast(6);
        
        // Now we have: 3, 4, 5, 6 with head having moved
        assertEquals("Should have 4 elements", 4, small.size());
        
        // Remove all and verify order
        assertEquals(Integer.valueOf(3), small.removeFirst());
        assertEquals(Integer.valueOf(4), small.removeFirst());
        assertEquals(Integer.valueOf(5), small.removeFirst());
        assertEquals(Integer.valueOf(6), small.removeFirst());
        assertTrue(small.isEmpty());
    }
    
    // =========================================================================
    // DocumentStore: Basic Operations
    // =========================================================================
    
    @Test
    public void testEmptyDocumentStore() {
        assertEquals("New store should have size 0", 0, store.size());
        assertTrue("New store should be empty", store.isEmpty());
    }
    
    @Test
    public void testAddDocument() {
        Document doc = new Document("doc1", "Test document");
        store.addDocument(doc);
        
        assertEquals("Size should be 1", 1, store.size());
        assertFalse("Store should not be empty", store.isEmpty());
    }
    
    @Test
    public void testAddMultipleDocuments() {
        store.addDocument(new Document("doc1", "First"));
        store.addDocument(new Document("doc2", "Second"));
        store.addDocument(new Document("doc3", "Third"));
        
        assertEquals("Size should be 3", 3, store.size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullDocument() {
        store.addDocument(null); // Should throw IllegalArgumentException
    }
    
    @Test
    public void testUndoLast() {
        Document doc1 = new Document("doc1", "First");
        Document doc2 = new Document("doc2", "Second");
        
        store.addDocument(doc1);
        store.addDocument(doc2);
        
        Document removed = store.undoLast();
        assertEquals("Should remove doc2", doc2, removed);
        assertEquals("Size should be 1", 1, store.size());
        
        removed = store.undoLast();
        assertEquals("Should remove doc1", doc1, removed);
        assertEquals("Size should be 0", 0, store.size());
        assertTrue("Store should be empty", store.isEmpty());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testUndoFromEmptyStore() {
        store.undoLast(); // Should throw IllegalStateException
    }
    
    // =========================================================================
    // DocumentStore: getLastK
    // =========================================================================
    
    @Test
    public void testGetLastKWithKEqualsSize() {
        store.addDocument(new Document("doc1", "First"));
        store.addDocument(new Document("doc2", "Second"));
        store.addDocument(new Document("doc3", "Third"));
        
        List<Document> last3 = store.getLastK(3);
        assertEquals("Should return 3 documents", 3, last3.size());
        assertEquals("doc1", last3.get(0).getDocId());
        assertEquals("doc2", last3.get(1).getDocId());
        assertEquals("doc3", last3.get(2).getDocId());
    }
    
    @Test
    public void testGetLastKWithKLessThanSize() {
        store.addDocument(new Document("doc1", "First"));
        store.addDocument(new Document("doc2", "Second"));
        store.addDocument(new Document("doc3", "Third"));
        store.addDocument(new Document("doc4", "Fourth"));
        
        List<Document> last2 = store.getLastK(2);
        assertEquals("Should return 2 documents", 2, last2.size());
        assertEquals("doc3", last2.get(0).getDocId());
        assertEquals("doc4", last2.get(1).getDocId());
    }
    
    @Test
    public void testGetLastKWithKGreaterThanSize() {
        store.addDocument(new Document("doc1", "First"));
        store.addDocument(new Document("doc2", "Second"));
        
        List<Document> last10 = store.getLastK(10);
        assertEquals("Should return all 2 documents", 2, last10.size());
        assertEquals("doc1", last10.get(0).getDocId());
        assertEquals("doc2", last10.get(1).getDocId());
    }
    
    @Test
    public void testGetLastKWithKZero() {
        store.addDocument(new Document("doc1", "First"));
        store.addDocument(new Document("doc2", "Second"));
        
        List<Document> last0 = store.getLastK(0);
        assertEquals("Should return empty list", 0, last0.size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLastKWithNegativeK() {
        store.getLastK(-1); // Should throw IllegalArgumentException
    }
    
    // =========================================================================
    // DocumentStore: replayAll
    // =========================================================================
    
    @Test
    public void testReplayAllEmptyStore() {
        Iterator<Document> it = store.replayAll();
        assertFalse("Empty store should have no documents to replay", it.hasNext());
    }
    
    @Test
    public void testReplayAllOrder() {
        Document doc1 = new Document("doc1", "First");
        Document doc2 = new Document("doc2", "Second");
        Document doc3 = new Document("doc3", "Third");
        
        store.addDocument(doc1);
        store.addDocument(doc2);
        store.addDocument(doc3);
        
        Iterator<Document> it = store.replayAll();
        assertEquals(doc1, it.next());
        assertEquals(doc2, it.next());
        assertEquals(doc3, it.next());
        assertFalse("Should be no more documents", it.hasNext());
    }
    
    @Test
    public void testReplayAllAfterUndo() {
        store.addDocument(new Document("doc1", "First"));
        store.addDocument(new Document("doc2", "Second"));
        store.addDocument(new Document("doc3", "Third"));
        store.undoLast(); // Remove doc3
        
        Iterator<Document> it = store.replayAll();
        assertEquals("doc1", it.next().getDocId());
        assertEquals("doc2", it.next().getDocId());
        assertFalse("Should only have 2 documents", it.hasNext());
    }
    
    // =========================================================================
    // Integration Tests
    // =========================================================================
    
    @Test
    public void testComplexSequence() {
        // Add documents
        for (int i = 0; i < 15; i++) {
            store.addDocument(new Document("doc" + i, "Document " + i));
        }
        assertEquals(15, store.size());
        
        // Undo some
        for (int i = 0; i < 5; i++) {
            store.undoLast();
        }
        assertEquals(10, store.size());
        
        // Add more
        for (int i = 15; i < 25; i++) {
            store.addDocument(new Document("doc" + i, "Document " + i));
        }
        assertEquals(20, store.size());
        
        // Verify last 5
        List<Document> last5 = store.getLastK(5);
        assertEquals(5, last5.size());
        assertEquals("doc20", last5.get(0).getDocId());
        assertEquals("doc24", last5.get(4).getDocId());
    }
    
    @Test
    public void testLargeScale() {
        // Add many documents to test resizing under realistic load
        int numDocs = 1000;
        for (int i = 0; i < numDocs; i++) {
            store.addDocument(new Document("doc" + i, "Content " + i));
        }
        
        assertEquals("Should have 1000 documents", numDocs, store.size());
        
        // Verify first and last
        List<Document> firstDoc = store.getLastK(numDocs);
        assertEquals("doc0", firstDoc.get(0).getDocId());
        assertEquals("doc999", firstDoc.get(numDocs - 1).getDocId());
    }
}
