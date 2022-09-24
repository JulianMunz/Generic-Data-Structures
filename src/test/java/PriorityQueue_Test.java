import org.junit.jupiter.api.Test;
import za.ac.sun.cs.cs712.EmptyCollectionException;
import za.ac.sun.cs.cs712.PriorityQueue;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriorityQueue_Test {
    @Test
    public void testIsEmpty() {
        PriorityQueue<String> queue = new PriorityQueue<String>();
        assertTrue(queue.isEmpty());
        queue.enqueue("food");
        assertFalse(queue.isEmpty());

    }

    @Test
    public void testSize() {
        PriorityQueue<String> queue = new PriorityQueue<String>();
        assertEquals(0, queue.size());
        queue.enqueue("food");
        assertEquals(1, queue.size());
        queue.enqueue("apple");
        assertEquals(2, queue.size());
        queue.enqueue("spork");
        assertEquals(3, queue.size());
    }

    @Test
    public void singleItemTest() throws EmptyCollectionException {
        PriorityQueue<String> queue = new PriorityQueue<String>();
        queue.enqueue("kiwi");
        assertEquals("kiwi", queue.peek());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testElement() throws EmptyCollectionException {
        PriorityQueue<String> queue = new PriorityQueue<String>();
        queue.enqueue("boo");
        queue.enqueue("achoo");
        assertEquals("achoo", queue.peek());
        assertEquals("achoo", queue.peek());
    }

    @Test
    public void threeItemsTest() throws EmptyCollectionException {
        PriorityQueue<String> queue = new PriorityQueue<String>();
        queue.enqueue("kiwi");
        queue.enqueue("apple");
        queue.enqueue("orange");

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());
        assertEquals("apple", queue.peek());
    }

    @Test
    public void iteratorTest() throws EmptyCollectionException {
        PriorityQueue<String> queue = new PriorityQueue<String>();
        queue.enqueue("kiwi");
        queue.enqueue("apple");
        queue.enqueue("orange");

        String[] expected = { "apple", "kiwi", "orange"};

        for (String fruit : expected) {
            assertEquals(queue.dequeue(), fruit);
        }
    }

    @Test
    public void removeTest() throws EmptyCollectionException {
        PriorityQueue<String> queue = new PriorityQueue<String>();
        queue.enqueue("kiwi");
        queue.enqueue("apple");
        queue.enqueue("orange");

        assertEquals("apple", queue.dequeue());
        assertEquals(2, queue.size());

        assertEquals("kiwi", queue.peek());
        assertEquals("kiwi", queue.dequeue());
        assertEquals(1, queue.size());

        assertEquals("orange", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

    }


    @Test
    public void testNoSuchElementException() {
        PriorityQueue<String> queue = new PriorityQueue<String>();
        try {
            queue.dequeue();
            assertTrue(false);
        } catch (EmptyCollectionException e) {
            assertTrue(true);
        }

    }

    @Test
    public void resize() throws EmptyCollectionException {
        PriorityQueue<String> queue = new PriorityQueue<String>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.enqueue("f");
        queue.enqueue("g");
        queue.enqueue("h");
        queue.enqueue("i");
        queue.enqueue("j");
        queue.enqueue("k");
        queue.enqueue("l");
        queue.enqueue("m");
        queue.enqueue("n");
        queue.enqueue("o");
        queue.enqueue("p");
        queue.enqueue("q");
        queue.enqueue("r");

        String[] expected = { "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r"};

        for (String fruit : expected) {
            assertEquals(queue.dequeue(), fruit);
        }
    }


}
