package za.ac.sun.cs.cs712;

import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"unchecked", "unused"})
public class LinkedList<E> implements UnorderedList<E> {
    private Node<E> head;
    private int size = 0;
    private int modCount = 0;

    public LinkedList() {
        head = null;
    }

    public LinkedList(E[] items) {
        head = null;
        for (E item:items) {
            this.addToRear(item);
        }
    }

    private Node<E> find(E element) {
        Node<E> current = head;
        do {
            if (current.item == null) {
                if (element == null) {
                    return current;
                }
            } else {
                if (current.item.equals(element)) {
                    return current;
                }
            }
            current = current.next;
        } while (current != head) ;
        return null;
    }

    private Node<E> addAfter(E element, Node<E> target) {
        if (target != null) {
            Node<E> nextNode = target.next;
            target.next = new Node<>(element);
            target.next.previous = target;
            target.next.next = nextNode;
            nextNode.previous = target.next;
            size++;
            modCount++;
            return target.next;
        }
        return null;
    }

    private E remove_(Node<E> target) {
        E item = target.item;
        Node<E> prev = target.previous;
        prev.next = target.next;
        target.next.previous = prev;
        modCount++;
        size--;
        if (size == 0) {
            head = null;
        } else if (target == head){
            head = target.next;
        }
        return item;
    }

    /**
     * Determines whether this list contains the specified element.
     *
     * @param element the target element being sought in this list
     * @return <code>true</code> if the element is in the list; otherwise, <code>false</code>
     */
    @Override
    public boolean contains(E element) {
        if (!isEmpty()) {
            return find(element) != null;
        } else {
            return false;
        }
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws EmptyCollectionException if this list contains no elements
     */
    @Override
    public E first() throws EmptyCollectionException {
        if (!isEmpty()) {
            return head.item;
        } else {
            throw new EmptyCollectionException();
        }
    }

    /**
     * Determines whether this list contains no elements.
     *
     * @return <code>true</code> if this list contains no elements; otherwise, <code>false</code>
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the last element in this list.
     *
     * @return the last element in the list
     * @throws EmptyCollectionException if this list contains no elements
     */
    @Override
    public E last() throws EmptyCollectionException {
        if (!isEmpty()) {
            return head.previous.item;
        } else {
            throw new EmptyCollectionException();
        }
    }

    /**
     * Removes and returns the specified element in this list.
     *
     * @param element the element to remove in this list
     * @return the element that was removed
     * @throws EmptyCollectionException if this list contains no elements
     * @throws NoSuchElementException   if the specified element is not in this list
     */
    @Override
    public E remove(E element) throws EmptyCollectionException, NoSuchElementException {
        if (!isEmpty()) {
            Node<E> delNode = find(element);
            if (delNode != null) {
                return remove_(delNode);
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new EmptyCollectionException();
        }
    }

    /**
     * Removes and returns the first element in this list.
     *
     * @return the first element in this list
     * @throws EmptyCollectionException if this list contains no elements
     */
    @Override
    public E removeFirst() throws EmptyCollectionException {
        if (!isEmpty()) {
            return remove_(head);
        } else {
            throw new EmptyCollectionException();
        }
    }

    /**
     * Removes and returns the last element in this list.
     *
     * @return the last element in this list
     * @throws EmptyCollectionException if this list contains no elements
     */
    @Override
    public E removeLast() throws EmptyCollectionException {
        if (!isEmpty()) {
            return remove_(head.previous);
        } else {
            throw new EmptyCollectionException();
        }
    }

    /**
     * Returns the number of elements in this list
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an array representation of this list. Whether this returns deep or shallow
     * copies of the elements is implementation-dependent.
     *
     * @return an array representation of this list
     * @throws EmptyCollectionException if this list contains no elements
     */
    @Override
    public E[] toArray() throws EmptyCollectionException {
        if (!isEmpty()) {
            Node<E> current = head;
            while (current.item == null) {
                current = current.next;
                if (current == head) {
                    throw new EmptyCollectionException();
                }
            }
            E[] array = (E[]) Array.newInstance(current.item.getClass(), size);
            current = head;
            int count = 0;
            do {
                array[count] = current.item;
                current = current.next;
                count++;
            } while (current != head) ;
            return array;
        } else {
            throw new EmptyCollectionException();
        }
    }

    /**
     * Adds the specified element to the front of this list.
     *
     * @param element the element to be added
     */
    @Override
    public void addToFront(E element) {
        if (isEmpty()) {
            head = new Node<>(element);
            head.next = head;
            head.previous = head;
            size++;
            modCount++;
        } else {
            head = addAfter(element, head.previous);
        }
    }

    /**
     * Adds the specified element to the back of this list.
     *
     * @param element the element to be added
     */
    @Override
    public void addToRear(E element) {
        if (isEmpty()) {
            head = new Node<>(element) ;
            head.next = head;
            head.previous = head;
            size++;
            modCount++;
        } else {
            head.previous = addAfter(element, head.previous);
        }
    }

    /**
     * Adds the specified element after the specified target in this list.
     *
     * @param element the element to be added
     * @param target  the existing element after which to add the new element
     * @throws NoSuchElementException if the target element is not in this list
     */
    @Override
    public void addAfter(E element, E target) throws NoSuchElementException {
        Node<E> nTarget = find(target);
        if (nTarget != null) {
            addAfter(element, nTarget);
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns a string representation of this list.
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("( -empty- )");
        } else {
            Node<E> current = head;
            sb.append("(");
            sb.append(current.item);
            current = current.next;
            while (current != head) {
                sb.append(", ");
                sb.append(current.item);
                current = current.next;
            }
            sb.append(")");
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int knownMod = modCount;
            private Node<E> current = head;
            private boolean removed = true;

            private void checkMod() {
                if (modCount != knownMod) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkMod();
                if (isEmpty()) {
                    return false;
                } else {
                    return current != null;
                }
            }

            @Override
            public E next() {
                checkMod();
                if (current == null) {
                    throw new NoSuchElementException();
                } else {
                    E ret = current.item;
                    current = current.next;
                    removed = false;
                    if (current == head) {
                        current = null;
                    }
                    return ret;
                }
            }

            @Override
            public void remove() {
                checkMod();
                if (removed) {
                    throw new IllegalStateException();
                }
                if (current == null) {
                    throw new NoSuchElementException();
                } else {
                    Node<E> temp = current.next;
                    removed = true;
                    remove_(current);
                    current = temp;
                    if (current == head) {
                        current = null;
                    }
                    knownMod++;
                }
            }
        };
    }

    private static class Node<E>{
        public final E item;
        public Node<E> previous;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }

        public E item() {
            return item;
        }
    }
}
