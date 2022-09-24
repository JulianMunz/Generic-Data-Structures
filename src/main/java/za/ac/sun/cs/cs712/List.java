package za.ac.sun.cs.cs712;

import java.util.NoSuchElementException;

/**
 * A general (list) sequence.
 *
 * @param <E> the type of element stored in this list
 */
public interface List<E> extends Iterable<E> {

  /**
   * Determines whether this list contains the specified element.
   *
   * @param element the target element being sought in this list
   * @return <code>true</code> if the element is in the list; otherwise, <code>false</code>
   */
  boolean contains(E element) throws EmptyCollectionException;

  /**
   * Returns the first element in this list.
   *
   * @return the first element in this list
   * @throws EmptyCollectionException if this list contains no elements
   */
  E first() throws EmptyCollectionException;

  /**
   * Determines whether or not this list contains no elements.
   *
   * @return <code>true</code> if this list contains no elements; otherwise, <code>false</code>
   */
  boolean isEmpty();

  /**
   * Returns the last element in this list.
   *
   * @return the last element in the list
   * @throws EmptyCollectionException if this list contains no elements
   */
  E last() throws EmptyCollectionException;

  /**
   * Removes and returns the specified element in this list.
   *
   * @param element the element to remove in this list
   * @return the element that was removed
   * @throws EmptyCollectionException if this list contains no elements
   * @throws NoSuchElementException if the specified element is not in this list
   */
  E remove(E element) throws EmptyCollectionException, NoSuchElementException;

  /**
   * Removes and returns the first element in this list.
   *
   * @return the first element in this list
   * @throws EmptyCollectionException if this list contains no elements
   */
  E removeFirst() throws EmptyCollectionException;

  /**
   * Removes and returns the last element in this list.
   *
   * @return the last element in this list
   * @throws EmptyCollectionException if this list contains no elements
   */
  E removeLast() throws EmptyCollectionException;

  /**
   * Returns the number of elements in this list
   *
   * @return the number of elements in this list
   */
  int size();

  /**
   * Returns an array representation of this list. Whether or not this returns deep or shallow
   * copies of the elements is implementation-dependent.
   *
   * @return an array representation of this list
   * @throws EmptyCollectionException if this list contains no elements
   */
  E[] toArray() throws EmptyCollectionException;

  /**
   * Returns a string representation of this list.
   *
   * @return a string representation of this list
   */
  String toString();

}
