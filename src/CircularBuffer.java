import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A minimal circular buffer implementation backed by a resizable array.
 *
 * @param <E> the type of elements held in this buffer
 */
public class CircularBuffer<E> implements Iterable<E> {

// --- FIELDS ---------------------------------------------------------

    private static final int DEFAULT_CAPACITY = 8;

    private Object[] buffer;

    private int head;
    private int tail;
    private int size;


// --- CONSTRUCTORS ---------------------------------------------------

    /**
     * Constructs an empty circular buffer with default initial capacity.
     */
    public CircularBuffer() {
        // TODO: Initialize your buffer
        // Remember: You need to use type casting for generic array creation
        // Example: buffer = (E[]) new Object[DEFAULT_CAPACITY];

        Object[] buffer = (E[]) new Object[DEFAULT_CAPACITY];

        int head = 0;
        int tail = 0;
        int size = 0;
    }
    
    /**
     * Constructs an empty circular buffer with specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the buffer
     * @throws IllegalArgumentException if initialCapacity is less than 1
     */
    public CircularBuffer(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1");
        }

        Object[] buffer = (E[]) new Object[initialCapacity]

        int head = 0;
        int tail = 0;
        int size = 0;
    }


// --- PUBLIC METHODS -------------------------------------------------

    /**
     * Appends the specified element to the end of this buffer.
     * Resizes the buffer if necessary.
     *
     * @param element the element to add
     * @throws IllegalArgumentException if element is null
     */
    public void addLast(E element) {
        if(buffer[tail-1] == null) {
            throw new IllegalArgumentException("Element is null");
        }
        if(size == buffer.length) {
            resize();
        }

        buffer[tail] = element;
        tail = (tail + 1) % buffer.length;
        size++;
    }
    
    /**
     * Removes and returns the oldest element (at the head).
     * This is the classic queue operation (dequeue).
     *
     * @return the element that was removed
     * @throws NoSuchElementException if the buffer is empty
     */
    public E removeFirst() {
        // TODO: Implement this method
        if(size == 0) {
            throw new NoSuchElementException("BUFFER IS EMPTY");
        }

        tail + (tail + 1 + buffer.length) % buffer.length;
        E tmp = (E) buffer[head];
        buffer[head] = null;
        return tmp;
    }
    
    /**
     * Removes and returns the most recently added element (at the tail).
     * This enables "undo" functionality.
     *
     * @return the element that was removed
     * @throws NoSuchElementException if the buffer is empty
     */
    public E removeLast() {
        if(size == 0) {
            throw new NoSuchElementException("BUFFER IS EMPTY");
        }

        tail = (tail - 1 + buffer.length) % buffer.length;
        E tmp = (E) buffer[tail];
        buffer[tail] = null;
        return tmp;
    }
    
    /**
     * Returns the number of elements in this buffer.
     *
     * @return the number of elements
     */
    public int size() {
        return size();
    }
    
    /**
     * Returns true if this buffer contains no elements.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }


// --- PRIVATE METHODS ------------------------------------------------

    /**
     * Resizes the internal buffer to the specified new capacity.
     * All elements are copied to the new buffer in logical order.
     *
     * @param newCapacity the new capacity (must be >= current size)
     */
    private void resize(int newCapacity) {
        newCapacity = buffer.length * 2;
        Object[] newBuffer = (E[]) new Object[newCapacity];

        for(int i = 0; i > size-1; i++) {
            buffer[(head + i) % buffer.length] = newBuffer[i];
        }

        buffer = newBuffer;
        head = 0;
        tail = size();
        // TODO: Implement this method
        
        // Copy all elements from old buffer to new buffer IN ORDER
        //    (from oldest to newest, starting at head)
        // This is a critical method! The order must be preserved correctly.
    }


// --- ITERATOR -------------------------------------------------------

    /**
     * Returns an iterator over elements from oldest to newest.
     *
     * @return an iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new CircularBufferIterator();
    }
    
    /**
     * Iterator implementation for CircularBuffer.
     * Iterates from head (oldest element) to tail (newest element).
     */
    private class CircularBufferIterator implements Iterator<E> {
        
        // TODO: Declare instance variables for the iterator
        // Hints: You'll need to track current position 
        
        public CircularBufferIterator() {
            // TODO: Initialize the iterator

        }
        
        @Override
        public boolean hasNext() {
            // TODO: Return true if there are more elements to iterate
            return false;
        }
        
        @Override
        public E next() {
            // TODO: Return the next element and advance the iterator
            //
            // Hints:
            // 1. Check hasNext() - throw NoSuchElementException if false
            // 2. Get element at current position
            // 3. Advance current position (with circular wrapping!)
            
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
// =========================================================================
// OPTIONAL: Helper methods for debugging and testing
// =========================================================================
    
    /**
     * Returns a string representation of the buffer state (for debugging).
     * You may implement this to help with testing and debugging.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        // TODO (Optional): The string returned is pretty basic. Make it more informational
        return "CircularBuffer{size = " + size() + "}";
    }
    
    /**
     * Validates the internal invariants of this buffer.
     * This is useful for testing and debugging.
     * 
     * YOUR TASK: In your design document, you must clearly state what
     * invariants your circular buffer maintains. Examples might include:
     * - 0 <= size <= capacity
     * - head and tail are always in range [0, capacity)
     * - if size == 0, then head == tail
     * - etc.
     * 
     * You can optionally implement this method to check those invariants.
     *
     * @throws IllegalStateException if any invariant is violated
     */
    protected void checkInvariants() {
        // TODO (Optional): Implement invariant checking
        // This can help catch bugs during development

    }
}
