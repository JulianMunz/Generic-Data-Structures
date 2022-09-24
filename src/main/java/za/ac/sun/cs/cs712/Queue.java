package za.ac.sun.cs.cs712;

/**
 * A queue, with the queue discipline to be specified by implementing classes.
 *
 * @param <E> the type of element stored in this queue
 */
public interface Queue<E> {

  /**
   * Removes and returns the element at the front this queue.
   *
   * @return the element at the front of this queue
   * @throws EmptyCollectionException if this queue contains no elements
   */
  E dequeue() throws EmptyCollectionException;

  /**
   * Adds an element to the rear of this queue.
   *
   * @param element the element to add
   */
  void enqueue(E element);

  /**
   * Returns, but does not remove, the element at the front of this queue.
   *
   * @return the element at the front of this queue
   * @throws NullPointerException if this queue contains no elements
   */
  E peek() throws EmptyCollectionException;

  /**
   * Determines whether this queue contains no elements.
   *
   * @return <code>true</code> if this queue contains no elements; otherwise, <code>false>
   */
  boolean isEmpty();

  /**
   * Returns the number of elements in this queue.
   *
   * @return the number of elements in this queue
   */
  int size();

  /**
   * Returns a string representation of this queue.
   *
   * @return a string representation of this queue
   */
  String toString();

}
