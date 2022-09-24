package za.ac.sun.cs.cs712;

import java.util.Comparator;

@SuppressWarnings("unused")
public class PriorityQueue<E> implements Queue<E>{

    ArrayHeap<E> queue ;

    public PriorityQueue(Comparator<? super E> comparator) {
        queue = new ArrayHeap<>(comparator);
    }

    public PriorityQueue() {
        queue = new ArrayHeap<>();
    }
    /**
     * Removes and returns the element at the front this queue.
     *
     * @return the element at the front of this queue
     * @throws EmptyCollectionException if this queue contains no elements
     */
    @Override
    public E dequeue() throws EmptyCollectionException {
        if (queue.isEmpty()) throw new EmptyCollectionException();
        return queue.removeMinimum();
    }

    /**
     * Adds an element to the rear of this queue.
     *
     * @param element the element to add
     */
    @Override
    public void enqueue(E element) {
        queue.addElement(element);
    }

    /**
     * Returns, but does not remove, the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws NullPointerException if this queue contains no elements
     */
    @Override
    public E peek() {
        try {
            return queue.findMinimum();
        } catch (EmptyCollectionException e) {
            throw new NullPointerException();
        }

    }

    /**
     * Determines whether this queue contains no elements.
     *
     * @return <code>true</code> if this queue contains no elements; otherwise, <code>false>
     */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    @Override
    public int size() {
        return queue.size();
    }
}
