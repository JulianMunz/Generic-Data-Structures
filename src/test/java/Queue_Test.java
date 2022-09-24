import org.junit.jupiter.api.Test;
import za.ac.sun.cs.cs712.EmptyCollectionException;
import za.ac.sun.cs.cs712.FifoQueue;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Queue_Test {
    @Test
    public void testIsEmpty() {
        FifoQueue<String> queue = new FifoQueue<String>();
        assertTrue(queue.isEmpty());
        queue.enqueue("food");
        assertFalse(queue.isEmpty());

    }

    @Test
    public void testSize() {
        FifoQueue<String> queue = new FifoQueue<String>();
        assertEquals(0, queue.size());
        queue.enqueue("food");
        assertEquals(1, queue.size());
        queue.enqueue("apple");
        assertEquals(2, queue.size());
        queue.enqueue("spork");
        assertEquals(3, queue.size());
    }

    @Test
    public void singleItemTest() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.enqueue("kiwi");
        assertEquals("kiwi", queue.peek());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testElement() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.enqueue("boo");
        queue.enqueue("achoo");
        assertEquals("boo", queue.peek());
        assertEquals("boo", queue.peek());
    }

    @Test
    public void threeItemsTest() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.enqueue("kiwi");
        queue.enqueue("apple");
        queue.enqueue("orange");

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());
        assertEquals("kiwi", queue.peek());
    }

    @Test
    public void iteratorTest() throws EmptyCollectionException {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.enqueue("kiwi");
        queue.enqueue("apple");
        queue.enqueue("orange");

        String[] expected = { "kiwi", "apple", "orange"};

        for (String fruit : expected) {
            assertEquals(queue.dequeue(), fruit);
        }
    }

    @Test
    public void removeTest() throws EmptyCollectionException {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.enqueue("kiwi");
        queue.enqueue("apple");
        queue.enqueue("orange");

        assertEquals("kiwi", queue.dequeue());
        assertEquals(2, queue.size());

        assertEquals("apple", queue.peek());
        assertEquals("apple", queue.dequeue());
        assertEquals(1, queue.size());

        assertEquals("orange", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

    }


    @Test
    public void testNoSuchElementException() {
        FifoQueue<String> queue = new FifoQueue<String>();
        try {
            queue.dequeue();
            assertTrue(false);
        } catch (EmptyCollectionException e) {
            assertTrue(true);
        }

    }
}
