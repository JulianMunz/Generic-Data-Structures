package za.ac.sun.cs.cs712;

import java.util.Iterator;

/**
 * A skeletal array-based implementation of a binary tree.
 * <p>
 * This class does not permit <code>null</code> elements: Overriding classes <strong>must</strong>
 * inhibit this behaviour if necessary.
 *
 * @param <E> the type of element stored in this binary tree
 */
public abstract class ArrayBinaryTree<E> implements BinaryTree<E> {

  /**
   * The default initial capacity.
   */
  public static final int DEFAULT_INITIAL_CAPACITY = 16;

  /**
   * The number of nodes in this binary tree.
   */
  protected int size;

  /**
   * The array in which the elements are stored.
   */
  protected E[] tree;

  /**
   * Constructs a new empty array-based binary tree with the default initial capacity.
   */
  protected ArrayBinaryTree() {
    size = 0;
    tree = newArray(DEFAULT_INITIAL_CAPACITY);
  }

  /**
   * Ensures the capacity of tree by doubling the size of the tree array if necessary.
   */
  protected void ensureCapacity() {
    if (size * 2 >= tree.length) {
      E[] newTree = newArray(tree.length*2);
      System.arraycopy(tree, 0, newTree, 0, tree.length);
      tree = newTree;
    }
  }

  /**
   * Recursively traverses the subtree rooted at the specified node, and adds these nodes to the
   * specified list in-order.
   *
   * @param node the root of the subtree to traverse in-order
   * @param list the list to which to add the traversed nodes
   */
  protected void listInOrder(int node, UnorderedList<E> list) {
    int left = 2*node + 1;
    int right = 2*node + 2;
    if (node >= size) {
      return;
    }
    listInOrder(left, list);
    list.addToRear(tree[node]);
    listInOrder(right, list);
  }

  /**
   * Recursively traverses the subtree rooted at the specified node, and adds these nodes to the
   * specified list postorder.
   *
   * @param node the root of the subtree to traverse postorder
   * @param list the list to which to add the traversed nodes
   */
  protected void listPostOrder(int node, UnorderedList<E> list) {
    int left = 2*node + 1;
    int right = 2*node + 2;
    if (node >= size) {
      return;
    }
    listPostOrder(left, list);
    listPostOrder(right, list);
    list.addToRear(tree[node]);
  }

  /**
   * Recursively traverses the subtree rooted the specified node, and adds these nodes to the
   * specified list preorder.
   * 
   * @param node current index of traversal
   * @param list list to be outputted
   */
  protected void listPreOrder(int node, UnorderedList<E> list) {
    int left = 2*node + 1;
    int right = 2*node + 2;
    if (node >= size) {
      return;
    }
    list.addToRear(tree[node]);
    listPreOrder(left, list);
    listPreOrder(right, list);
  }

  /**
   * Returns a new array of tree nodes of the specified length and cast to the appropriate type.
   *
   * @param length the length of the new array
   * @return a new array of tree nodes, cast to the appropriate typabstract e
   */
  @SuppressWarnings("unchecked")
  protected E[] newArray(int length) {
    return (E[]) new Object[length];
  }

  @Override
  public boolean contains(E element) {
    return find(element) != null;
  }

  @Override
  public E find(E element) {
    for (E ding: tree) {
      if (ding != null) {
        if (ding.equals(element)) {
          return ding;
        }
      }
    }
    return null;
  }

  @Override
  public E getRoot() throws EmptyCollectionException {
    if (isEmpty()) throw new EmptyCollectionException();
    return tree[0];
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public Iterator<E> iteratorInOrder() {
    LinkedList<E> list = new LinkedList<>();
    listInOrder(0, list);
    return list.iterator();
  }

  @Override
  public Iterator<E> iteratorPostOrder() {
    LinkedList<E> list = new LinkedList<>();
    listPostOrder(0, list);
    return list.iterator();
  }

  @Override
  public Iterator<E> iteratorPreOrder() {
    LinkedList<E> list = new LinkedList<>();
    listPreOrder(0, list);
    return list.iterator();
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (isEmpty()) {
      sb.append("[ -empty- ]");
    } else {
      sb.append("[");
      sb.append(tree[0]);
      for (int i = 1; i < tree.length; i++) {
        if (tree[i] != null) {
          sb.append(", ");
          sb.append(tree[i]);
        }
      }
      sb.append("]");
    }
    return sb.toString();
  }

}
