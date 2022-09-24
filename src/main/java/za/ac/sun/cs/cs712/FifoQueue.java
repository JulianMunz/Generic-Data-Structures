package za.ac.sun.cs.cs712;

@SuppressWarnings("unused")
public class FifoQueue<E> implements Queue<E>{
    LinkedList<E> queue ;

    public FifoQueue() {
        queue = new LinkedList<>();
    }

    public FifoQueue(E[] items) {
        queue = new LinkedList<>(items);
    }

    /**
     * Removes and returns the element at the front this queue.
     *
     * @return the element at the front of this queue
     * @throws EmptyCollectionException if this queue contains no elements
     */
    @Override
    public E dequeue() throws EmptyCollectionException {
        if (queue.isEmpty()) {
            throw new EmptyCollectionException();
        }
        return queue.removeFirst();
    }

    /**
     * Adds an element to the rear of this queue.
     *
     * @param element the element to add
     */
    @Override
    public void enqueue(E element) {
        queue.addToRear(element);
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
            return queue.first();
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
