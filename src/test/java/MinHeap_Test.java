import org.junit.jupiter.api.Test;
import za.ac.sun.cs.cs712.ArrayHeap;
import za.ac.sun.cs.cs712.EmptyCollectionException;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeap_Test {
    @Test
    public void testMinHeap() {
        ArrayHeap<Integer> heap = new ArrayHeap<>();
        assertEquals(0, heap.size());
        assertFalse(heap.contains(0));
    }


    @Test
    public void testMinHeapIntComparatorOfQsuperT() {
        ArrayHeap<Integer> heap = new ArrayHeap<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        assertEquals(0, heap.size());
        assertFalse(heap.contains(0));
    }

    @Test
    public void testMinHeapCollectionOfQextendsT() {
        Integer[] items = {2, 1, 0, 2, 1};
        ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
        heap.addElement(2);
        heap.addElement(1);
        heap.addElement(0);
        heap.addElement(2);
        heap.addElement(1);
        assertEquals(5, heap.size());
        for (int i = 0; i < items.length; i++) {
            assertTrue(heap.contains(items[i]));
        }
        try {
            assertEquals(new Integer(0), heap.findMinimum());
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMinHeapCollectionOfQextendsTComparatorOfQsuperT() {
        Integer[] items = {2, 1, 0, 2, 1};
        ArrayHeap<Integer> heap = new ArrayHeap<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        heap.addElement(2);
        heap.addElement(1);
        heap.addElement(0);
        heap.addElement(2);
        heap.addElement(1);
        assertEquals(5, heap.size());
        for (int i = 0; i < items.length; i++) {
            assertTrue(heap.contains(items[i]));
        }
        try {
            assertEquals(new Integer(2), heap.findMinimum());
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSize() {
        Double[] items = {3.14, 2.718, 1.0, 7.0};
        ArrayHeap<Double> heap = new ArrayHeap<Double>();
        heap.addElement(3.14);
        heap.addElement(2.718);
        heap.addElement(1.0);
        heap.addElement(7.0);
        assertEquals(items.length, heap.size());
        heap.addElement(2.0);
        assertEquals(items.length + 1, heap.size());
        int timesToPoll = 3;
        for (int i = 0; i < timesToPoll; i++) {
            heap.removeMinimum();
        }
        assertEquals(items.length + 1 - timesToPoll, heap.size());
    }

    @Test
    public void testAddT() throws EmptyCollectionException {
        Integer[] items = {1, 3, 2};
        ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
        heap.addElement(1);
        heap.addElement(2);
        heap.addElement(3);
        assertEquals(3, heap.size());
        assertEquals(new Integer(1), heap.findMinimum());
    }

    @Test
    public void testContainsObject() {
        Integer[] items = {0, 1, 2};
        ArrayHeap<Integer> heap = new ArrayHeap<>();
        for (int i = 0; i < items.length; i++) {
            heap.addElement(items[i]);

        }
        for (int i = 0; i < items.length; i++) {
            assertTrue(heap.contains(items[i]));
        }

        assertFalse(heap.contains(3));
    }

    @Test
    public void testOffer() throws EmptyCollectionException {
        Integer[] items = {1, 3, 2};
        ArrayHeap<Integer> heap = new ArrayHeap<>();
        for (int i = 0; i < items.length; i++) {
            heap.addElement(items[i]);

        }

        heap.addElement(0);
        assertEquals(4, heap.size());
        assertEquals(new Integer(0), heap.findMinimum());
    }

    @Test
    public void testPeek() throws EmptyCollectionException {
        Integer[] items = {1, 0, 2};
        ArrayHeap<Integer> heap = new ArrayHeap<>();
        for (int i = 0; i < items.length; i++) {
            heap.addElement(items[i]);

        }
        assertEquals(new Integer(0), heap.findMinimum());
        assertEquals(3, heap.size());
    }

    @Test
    public void testPoll() {
        Integer[] items = {3, 1, 0, 2};
        ArrayHeap<Integer> heap = new ArrayHeap<>();
        for (int i = 0; i < items.length; i++) {
            heap.addElement(items[i]);

        }
        for (int i = 0; i < items.length; i++) {
            assertEquals(new Integer(i), heap.removeMinimum());
        }
        assertEquals(0, heap.size());
    }

    @Test
    public void testFind() {
        Integer[] items = {3, 1, 2};
        ArrayHeap<Integer> heap = new ArrayHeap<>();
        for (int i = 0; i < items.length; i++) {
            heap.addElement(items[i]);

        }
        assertEquals(0, heap.findIndex(1));
        assertEquals(-1, heap.findIndex(0));

    }

    @Test
    public void iteratorInOrderTest() throws EmptyCollectionException {
        Integer[] items = {1, 2, 3, 4, 5};
        ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
        for (int i = 0; i < items.length; i++) {
            heap.addElement(items[i]);
        }
        assertEquals(5, heap.size());
        Iterator<Integer> it = heap.iteratorInOrder();
        assertEquals(new Integer(1), heap.findMinimum());
        int i = it.next();
        assertEquals(new Integer(4), i);
        i = it.next();
        assertEquals(new Integer(2), i);
        i = it.next();
        assertEquals(new Integer(5), i);
        i = it.next();
        assertEquals(new Integer(1), i);
        i = it.next();
        assertEquals(new Integer(3), i);
    }

    @Test
    public void iteratorPreOrderTest() throws EmptyCollectionException {
        Integer[] items = {1, 2, 3, 4, 5};
        ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
        for (int i = 0; i < items.length; i++) {
            heap.addElement(items[i]);
        }
        assertEquals(5, heap.size());
        Iterator<Integer> it = heap.iteratorPreOrder();
        assertEquals(new Integer(1), heap.findMinimum());
        int i = it.next();
        assertEquals(new Integer(1), i);
        i = it.next();
        assertEquals(new Integer(2), i);
        i = it.next();
        assertEquals(new Integer(4), i);
        i = it.next();
        assertEquals(new Integer(5), i);
        i = it.next();
        assertEquals(new Integer(3), i);
    }

    @Test
    public void iteratorPostOrderTest() throws EmptyCollectionException {
        Integer[] items = {1, 2, 3, 4, 5};
        ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
        for (int i = 0; i < items.length; i++) {
            heap.addElement(items[i]);
        }
        assertEquals(5, heap.size());
        Iterator<Integer> it = heap.iteratorPostOrder();
        assertEquals(new Integer(1), heap.findMinimum());
        int i = it.next();
        assertEquals(new Integer(4), i);
        i = it.next();
        assertEquals(new Integer(5), i);
        i = it.next();
        assertEquals(new Integer(2), i);
        i = it.next();
        assertEquals(new Integer(3), i);
        i = it.next();
        assertEquals(new Integer(1), i);
    }

    @Test
    public void iteratorLevelOrderTest() throws EmptyCollectionException {
        Integer[] items = {1, 2, 3, 4, 5};
        ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
        for (int i = 0; i < items.length; i++) {
            heap.addElement(items[i]);
        }
        assertEquals(5, heap.size());

        Iterator<Integer> it = heap.iteratorLevelOrder();
        assertEquals(new Integer(1), heap.findMinimum());
        int i = it.next();
        assertEquals(new Integer(1), i);
        i = it.next();
        assertEquals(new Integer(2), i);
        i = it.next();
        assertEquals(new Integer(3), i);
        i = it.next();
        assertEquals(new Integer(4), i);
        i = it.next();
        assertEquals(new Integer(5), i);
    }
}
