package za.ac.sun.cs.cs712;

import java.util.NoSuchElementException;

/**
 * An unordered list. The elements do not have to be mutually comparable.
 *
 * @param <E> the type of element stored in this list
 */
public interface UnorderedList<E> extends List<E> {

  /**
   * Adds the specified element to the front of this list.
   *
   * @param element the element to be added
   */
  void addToFront(E element);

  /**
   * Adds the specified element to the back of this list.
   *
   * @param element the element to be added
   */
  void addToRear(E element);

  /**
   * Adds the specified element after the specified target in this list.
   *
   * @param element the element to be added
   * @param target the existing element after which to add the new element
   * @throws NoSuchElementException if the target element is not in this list
   */
  void addAfter(E element, E target) throws NoSuchElementException;

}
