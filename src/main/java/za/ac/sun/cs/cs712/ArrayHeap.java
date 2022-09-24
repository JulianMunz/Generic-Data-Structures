package za.ac.sun.cs.cs712;

import java.util.Comparator;
import java.util.Iterator;


/**
 * An array-based implementation of {@link Heap}. The elements are ordered
 * either by their natural order, in which case they must be comparable, or by
 * a {@link java.util.Comparator} provided at heap construction time.
 *
 * @param <E> the type of element stored in this heap
 */
@SuppressWarnings({"SameParameterValue", "unused"})
public final class ArrayHeap<E> extends ArrayBinaryTree<E> implements Heap<E> {

  /**
   * The (optional) comparator if the natural ordering of the elements is
   * not to be used.
   */
  private final Comparator<? super E> comparator;

  /**
   * Constructs a new array-based heap that is ordered by the natural order of
   * the elements, which is to say, elements <strong>must</strong> implement
   * {@link }.
   */
  @SuppressWarnings("unchecked")
  public ArrayHeap() {
    size = 0;
    comparator = (Comparator<? super E>)Comparator.naturalOrder();
  }

  /**
   * Constructs a new array-based that is ordered using the specified
   * comparator.
   *
   * @param comparator the comparator that is to be used in ordering this heap
   * @throws NullPointerException if the comparator is <code>null</code>
   */
  public ArrayHeap(Comparator<? super E> comparator) {
    size = 0;
    if (comparator == null) {
      throw new NullPointerException();
    }
    this.comparator = comparator;
  }

  private int parent(int pos) { return (pos-1) / 2 ; }

  private int left(int pos) { return (2 * pos) + 1; }

  private int right(int pos) { return (2 * pos) + 2; }

  private void swap(int indOne, int indTwo) {
    E tmp = tree[indOne];
    tree[indOne] = tree[indTwo];
    tree[indTwo] = tmp;
  }

  private boolean less(E v, E w) {
    return comparator.compare(v,w) < 0;
  }

  public int findIndex(E element) {
    for (int i = 0; i < size; i++) {
      if (tree[i] != null) {
        if (comparator.compare(element, tree[i]) == 0) {
          return i;
        }
      }
    }
    return -1;
  }

  @Override
  public Iterator<E> iteratorLevelOrder() {
    LinkedList<E> list = new LinkedList<>();
    int depth = maxDepth(0);
    for (int i = 0; i < depth; i++) {
      addCurrentLevel(0,i,list);
    }
    return list.iterator();
  }

  private void addCurrentLevel(int node, int level, UnorderedList<E> list) {
    if (node >= size) {
      return;
    }
    if (level == 0) {
      list.addToRear(tree[node]);
    } else {
      addCurrentLevel(left(node), level-1, list);
      addCurrentLevel(right(node), level-1, list);
    }

  }

  private int maxDepth(int node) {
    if (node >= size) return 0;
      return 1 + Math.max(maxDepth(left(node)), maxDepth(right(node)));
  }



  /**
   * @throws ClassCastException if the specified element cannot be compared
   *         with the elements current in this heap according to the heap's
   *         ordering
   * @throws NullPointerException if the specified element is <code>null</code>
   */
  @Override
  public void addElement(E element) {
    if (element == null) {
      throw new NullPointerException();
    }
    ensureCapacity();
    tree[size] = element;
    size++;
    swim(0, element);
  }

  /**
   * @throws NullPointerException if the specified element is <code>null</code>
   */
  @Override
  public boolean contains(E element) {
    if (element == null) {
      throw new NullPointerException();
    }
    return super.contains(element);
  }

  /**
   * @throws NullPointerException if the specified element is <code>null</code>
   */
  @Override
  public E find(E element) {
    if (element == null) {
      throw new NullPointerException();
    }
    return super.find(element);
  }

  /**
   * @throws EmptyCollectionException if this heap is empty
   */
  @Override
  public E findMinimum() throws EmptyCollectionException {
    if (size == 0) {
      throw new EmptyCollectionException();
    }
    return getRoot();
  }

  @Override
  public E removeMinimum() {
    E min = tree[0];
    tree[0] = tree[size-1];
    tree[size-1] = null;
    size--;
    if (!isEmpty()) {
      sink(0,tree[0]);
    }
    return min;
  }

  /**
   * Reheapifies the specified element down to its proper place in the subtree
   * rooted at the specified index.
   *
   * @param k the array index at which the subtree is rooted @param element the
   *          element to sink down to its proper place
   */
  private void sink(int k, E element) {
    int i = findIndex(element);
    while (left(i) < size) {
      int j = left(i);
      if ((j < size-1) && less(tree[j+1],tree[j]))
        j++;
      if (less(tree[i],tree[j])) // k is greater than its children - stop sinking
        break;
      swap(i,j);  // swap child and parent k
      i = j;      // update position of this sinking parent,
    }
  }

  /**
   * Reheapifies the specified element up to its proper place in the subtree
   * rooted at the specified index.
   *
   * @param k the array index at which the subtree is rooted
   * @param element the element to swim up to its proper place
   */
  private void swim(int k, E element) {
    int i = findIndex(element);
    while ((i > k) && less(tree[i],tree[parent(i)])) {
      swap(parent(i),i);
      i = parent(i);
    }
  }

}
