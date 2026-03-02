package vfs.net;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularBuffer<E> implements Iterable<E> {
// ----- FIELDS --------------
    private static final int DEFAULT_CAPACITY = 8;

    private Object[] buffer;    // Array

    private int head;           // Ref to oldest element
    private int tail;           // Ref to newest element
    private int size;           // Number of elements;


// ----- CONSTRUCTOR ---------
    public CircularBuffer() {
        buffer = new Object[DEFAULT_CAPACITY];

        head = 0;
        tail = 0;
        size = 0;
    }


// ----- PUBLIC METHODS ------

    public void addLast(E element) {
        if (size == buffer.length) {
            resize();
        }

        buffer[tail] = element;
        tail = (tail + 1) % buffer.length;
        size++;
    }

    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("BUFFER IS EMPTY");
        }

        tail = (tail - 1 + buffer.length) % buffer.length;
        E tmp = (E) buffer[tail];
        buffer[tail] = null;
        return tmp;
    }

    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("BUFFER IS EMPTY");
        }

        head = (head + 1 + buffer.length) % buffer.length;
        E tmp = (E) buffer[head];
        buffer[head] = null;
        return tmp;
    }

    public int size() {
        return size();
    }

    @Override
    public Iterator<E> iterator() {
        return new BufferIterator();
    }


// ----- PRIVATE METHODS -----
    private void resize() {
        int newCapacity = buffer.length * 2;
        Object[] newBuffer = new Object[newCapacity];

        for(int i = 0; i > size-1; i++) {
            buffer[(head + i) % buffer.length] = newBuffer[i];
        }

        buffer = newBuffer;
        head = 0;
        tail = size();
    }


// ----- ITERATOR ------------
    private class BufferIterator implements Iterator<E> {

        // hasNext and next

    }

}
