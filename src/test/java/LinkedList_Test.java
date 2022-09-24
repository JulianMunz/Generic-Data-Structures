import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import za.ac.sun.cs.cs712.EmptyCollectionException;
import za.ac.sun.cs.cs712.LinkedList;

/**
 * Unit tests for LinkedList.java
 */
public class LinkedList_Test {

    @org.junit.jupiter.api.Test
    public void circular_testInit() {
        LinkedList<Integer> myCircularList = new LinkedList<Integer>();

        /* Ensure that the */
        assertTrue(true);
    }

    /**
     * You should not be able to call remove() without having called next()
     */
    @org.junit.jupiter.api.Test
    public void iterator_testRemoveWithoutNext() {
        Integer[] initialItems = new Integer[] { 1, 2, 3, 4, 5 };
        LinkedList<Integer> list = new LinkedList<Integer>(initialItems);
        Iterator<Integer> iterator = list.iterator();

        /**
         * Should not be able to call remove() here as the iterator has not called
         * next() yet
         */
        try {
            iterator.remove();
            assertTrue(false);
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }

    /**
     * You should be able to call remove() with having called next(), but then
     * calling remove() again must fail
     */
    @org.junit.jupiter.api.Test
    public void iterator_removeWithNext() {
        Integer[] initialItems = new Integer[] { 1, 2, 3, 4, 5 };
        LinkedList<Integer> list = new LinkedList<Integer>(initialItems);
        Iterator<Integer> iterator = list.iterator();

        /**
         * Calling next() must return 1
         *
         * Calling remove() must then not throw an IllegalStateException
         *
         * The NEW front of the list should now be 2
         *
         * Calling remove() AGAIN must throw an IllegalStateException
         */
        Integer iteratedElement = iterator.next();
        assertTrue(iteratedElement.equals(1));

        try {
            iterator.remove();
            assertTrue(true);
        } catch (IllegalStateException e) {
            assertTrue(false);
        }

        try {
            iterator.remove();
            assertTrue(false);
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }

    @org.junit.jupiter.api.Test
    public void newCircularList() {
        Integer[] initialItems = new Integer[5];
        initialItems = new Integer[] { 1, 2, 3, 4, 5 };

        LinkedList<Integer> d = new LinkedList<Integer>(initialItems);

        try {
            Integer firstNodeElement = d.first();
            Integer lastNodeElement = d.last();

            assertTrue(firstNodeElement == initialItems[0]);
            assertTrue(lastNodeElement == initialItems[4]);
        } catch (EmptyCollectionException e) {
            assertTrue(false);
        }
    }

    @org.junit.jupiter.api.Test
    public void circularList_addFrontTest() {
        LinkedList<Integer> list = new LinkedList<Integer>();

        try {
            assertTrue(!list.contains(69));

            try {
                list.first();
                assertTrue(false);
            } catch (EmptyCollectionException e) {
                assertTrue(true);
            }
            try {
                list.last();
                assertTrue(false);
            } catch (EmptyCollectionException e) {
                assertTrue(true);
            }

            Integer newElement = 69;
            list.addToFront(newElement);

            assertTrue(list.size() == 1);
            assertTrue(list.first() == newElement);
            assertTrue(list.first() != 1);

            assertTrue(!list.contains(1));
            assertTrue(list.contains(69));
        } catch (EmptyCollectionException e) {
            assertTrue(false);
        }
    }

    /**
     * Test addToRear
     */
    @org.junit.jupiter.api.Test
    public void circularList_addRearTest() throws EmptyCollectionException {
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.addToRear(5);
        assertTrue(list.size() == 1);

        /* Last must equal First (which is 5) */
        assertTrue(list.first().equals(5));
        assertTrue(list.last().equals(5));

        list.addToRear(6);
        assertTrue(list.size() == 2);

        /* Last must be 6 */
        assertTrue(list.last().equals(6));

        /* First must be 5 */
        assertTrue(list.first().equals(5));

        /* Test adding to rear again */
        list.addToRear(7);
        assertTrue(list.size() == 3);

        /* Last must be 7 */
        assertTrue(list.last().equals(7));

        /* First must be 5 */
        assertTrue(list.first().equals(5));

        /* Make sure they are in the list */
        assertTrue(list.contains(5));
        assertTrue(list.contains(6));
        assertTrue(list.contains(7));

        Integer[] array = list.toArray();
        assertArrayEquals(new Integer[] { 5, 6, 7 }, array);
    }

    @org.junit.jupiter.api.Test
    public void circularList_addElementAfterElement() {
        LinkedList<Integer> list = new LinkedList<Integer>();

        try {
            Integer newElement = 69;

            assertTrue(list.isEmpty() == true);
            assertTrue(list.size() == 0);

            list.addToFront(newElement);
            assertTrue(list.isEmpty() == false);
            assertTrue(list.size() == 1);
            assertTrue(list.first() == newElement);
            assertTrue(list.first() != 1);

            try {
                list.addAfter(21, newElement);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
            assertTrue(list.first() == newElement);
            assertTrue(list.last() == 21);
            assertTrue(list.first() != 21);
            assertTrue(list.last() != newElement);
            assertTrue(list.size() == 2);

            try {
                list.addAfter(22, 23);
                assertTrue(false);
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }

            /* Test `toArray()` */
            Integer[] array = list.toArray();
            assertTrue(array[0] == 69);
            assertTrue(array[1] == 21);
            System.out.println("toArray(): " + Arrays.toString(array));
        } catch (EmptyCollectionException e) {
            assertTrue(false);
        }
    }

    /**
     * Test removal of a node
     */
    @org.junit.jupiter.api.Test
    public void circularList_removeNode() {
        LinkedList<Integer> list = new LinkedList<Integer>();

        /* Add an item */
        Integer itemToAdd = 6009;
        list.addToRear(itemToAdd);

        /* Add another item */
        Integer anotherItemToAdd = 6007;
        list.addToFront(anotherItemToAdd);

        /* Ensure it has been added */
        try {
            assertTrue(list.first() == anotherItemToAdd);
            assertTrue(list.last() == itemToAdd);
            assertTrue(list.size() == 2);
        } catch (EmptyCollectionException e) {
            assertTrue(false);
        }

        /* Remove the element `6009` */
        try {
            list.remove(itemToAdd);
            assertTrue(list.size() == 1);
            assertTrue(list.first() == anotherItemToAdd);
            assertTrue(list.last() == anotherItemToAdd);
        } catch (EmptyCollectionException | NoSuchElementException e) {
            assertTrue(false);
        }

        /* Try and fail (that is good) removing an element not present */
        try {
            list.remove(itemToAdd);
            assertTrue(false);
        } catch (EmptyCollectionException e) {
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }

    }

    /**
     * Test the circular lists iterator and ensure that it is able to yield values
     * and properly stop iteration when the cycle has been completed
     */
    @org.junit.jupiter.api.Test
    public void circularList_iteratorTest() throws EmptyCollectionException, NoSuchElementException {
        Integer[] initialValues = new Integer[] { 1, 2, 3, 4 };
        LinkedList<Integer> list = new LinkedList<Integer>(initialValues);

        /**
         * Should not be empty
         */
        try {
            assertTrue(list.first().equals(1));
        } catch (EmptyCollectionException e) {
            assertTrue(false);
        }

        /**
         * Below tests make sure the iterator returned is a clone with new starting
         * state
         */

        int i = 1;
        int c = 0;
        for (Integer item : list) {
            System.out.println("Item: " + item);
            assertTrue(i == item);
            i++;

            c++;
        }

        assertTrue(c == 4);

        i = 1;
        for (Integer item : list) {
            System.out.println("Item2: " + item);
            assertTrue(i == item);
            i++;

            c++;
        }

        assertTrue(c == 8);

        /**
         * TODO: Sort out the below
         */

        /**
         * Calling `add-*()` whilst iterating should throw an error, on next()
         */
        try {
            for (Integer item : list) {
                list.addAfter(34, item);
            }

            assertTrue(false);
        } catch (ConcurrentModificationException e) {
            System.out.println("Got error: conc");
            assertTrue(true);
        }

        /**
         * Calling `remove-*()` whilst iterating should throw an error, on next()
         */
        try {
            for (Integer item : list) {
                list.remove(item);
            }

            assertTrue(false);
        } catch (ConcurrentModificationException e) {
            System.out.println("Got error: conc");
            assertTrue(true);
        } catch (EmptyCollectionException e) {
            System.out.println("Got error (BAD): empty collectionexception");
            assertTrue(false);
        }

        /**
         * Iterator `remove()` test
         */
        initialValues = new Integer[] { 1, 2, 3, 4 };
        list = new LinkedList<Integer>(initialValues);

        /* Values should be 1,2,3,4 */
        Integer[] currentValues = list.toArray();
        assertTrue(currentValues[0] == 1);
        assertTrue(currentValues[1] == 2);
        assertTrue(currentValues[2] == 3);
        assertTrue(currentValues[3] == 4);

        /**
         * Do one call to `.next()` and then call `.remove()` TODO
         */
        Iterator<Integer> iterator = list.iterator();
        Integer element = iterator.next();
        assertTrue(element == 1);

        /* Remove element `1` */
        assertTrue(list.first() == 1);
        iterator.remove();
        assertTrue(list.first() == 2);
        currentValues = list.toArray();
        assertTrue(currentValues[1] == 3);
        assertTrue(list.last() == 4);
        System.out.println("kaka: " + list.first());

        /* Remove element `2` */
        element = iterator.next();
        assertTrue(element == 2);
        assertTrue(list.first() == 2);
        iterator.remove();
        assertTrue(list.first() == 3);
        assertTrue(list.last() == 4);

        /* Remove element `3` */
        element = iterator.next();
        assertTrue(element == 3);
        assertTrue(list.first() == 3);
        iterator.remove();
        try {
            assertTrue(list.first() == 4);
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
        try {
            assertTrue(list.last() == 4);
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }

        /* Remove element `4` */
        element = iterator.next();
        assertTrue(element == 4);
        try {
            assertTrue(list.first() == 4);
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
        try {
            assertTrue(list.last() == 4);
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
        iterator.remove();
        assertTrue(iterator.hasNext() == false);
        assertTrue(list.isEmpty());

        /* This should fail now */
        try {
            list.first();
            assertTrue(false);
        } catch (EmptyCollectionException e) {
            assertTrue(true);
        }
    }

    /**
     * Tests the toString functionality of the list 1 item
     */
    @org.junit.jupiter.api.Test
    public void toString_testOneItem() {
        Integer[] initialValues = new Integer[] { 1 };
        LinkedList<Integer> list = new LinkedList<Integer>(initialValues);

        /* It should be (1) */
        assertTrue(list.toString().equals("(1)"));

    }

    /**
     * Tests the toString functionality of the list with no items
     */
    @org.junit.jupiter.api.Test
    public void toString_testEmptyList() {
        Integer[] initialValues = new Integer[] {};
        LinkedList<Integer> list = new LinkedList<Integer>(initialValues);

        /* It should be (- empty -) */
        assertTrue(list.toString().equals("( -empty- )"));

    }

    /**
     * Tests the toString functionality of the list with more than one item
     */
    @org.junit.jupiter.api.Test
    public void toString_testMultipleItems() {
        Integer[] initialValues = new Integer[] { 1, 2, 3, 4 };
        LinkedList<Integer> list = new LinkedList<Integer>(initialValues);

        /* It should be (1, 2, 3, 4) */
        assertTrue(list.toString().equals("(1, 2, 3, 4)"));
    }

    /**
     * Test object equality being used
     */
    @org.junit.jupiter.api.Test
    public void contains_testObjectEquality() throws EmptyCollectionException {
        /**
         * Strings are not compiler-time interned when using `new String()` instead of
         * `"constant"` therefore addresses or references will not be same, but equality
         * (overriden for String) will be true
         *
         * This should be reflected in my list funcitonality
         */
        String string1 = "Hello";
        String string2 = new String("Hello");
        assertTrue(string1 != string2);
        assertTrue(string1.equals(string2));

        LinkedList<String> list = new LinkedList<String>(new String[] { string2 });
        assertTrue(list.contains(string1));
    }

    /**
     * Tests toArray() on an empty list
     */
    @org.junit.jupiter.api.Test
    public void toArray_emptyList() {
        Integer[] initialElements = new Integer[] {};
        LinkedList<Integer> list = new LinkedList<Integer>(initialElements);

        try {
            list.toArray();
            assertTrue(false);
        } catch (EmptyCollectionException e) {
            assertTrue(true);
        }
    }

    /**
     * Tests toArray() on a non-null-filled list
     */
    @org.junit.jupiter.api.Test
    public void toArray_noNulls() {
        Integer[] initialElements = new Integer[] { 1, 2 };
        LinkedList<Integer> list = new LinkedList<Integer>(initialElements);

        Integer[] arr = new Integer[0];
        try {
            arr = list.toArray();
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(new Integer[] { 1, 2 }, arr);
    }

    /**
     * Tests toArray() on a partially null-filled list
     */
    @org.junit.jupiter.api.Test
    public void toArray_partialNulls() {
        Integer[] initialElements = new Integer[] { null, 1 };
        LinkedList<Integer> list = new LinkedList<Integer>(initialElements);

        Integer[] arr = new Integer[0];
        try {
            arr = list.toArray();
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(new Integer[] { null, 1 }, arr);
    }

    /**
     * Tests toArray() on a completely null-filled list
     */
    @org.junit.jupiter.api.Test
    public void toArray_allNulls() {
        Integer[] initialElements = new Integer[] { null, null };
        LinkedList<Integer> list = new LinkedList<Integer>(initialElements);

        try {
            list.toArray();
            assertTrue(false);
        } catch (IllegalStateException e) {
            assertTrue(true);
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a list this test should pass if the list is empty
     */
    public void circularList_emptinessChecks(LinkedList list) {
        /* Test `first()` to fail */
        try {
            list.first();
            assertTrue(false);
        } catch (EmptyCollectionException e) {
            assertTrue(true);
        }

        /* Test `last()` to fail */
        try {
            list.last();
            assertTrue(false);
        } catch (EmptyCollectionException e) {
            assertTrue(true);
        }

        /* Test `isEmpty() => true` and `size() => 0` */
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);

        /* Test `hasNext() => false` via iterator */
        for (Object element : list) {
            assertTrue(false);
        }

        /* Test toString() `( -empty- )` */
        assertTrue(list.toString().equals("( -empty- )"));
    }

    @org.junit.jupiter.api.Test
    public void add_nullElement() throws EmptyCollectionException {
        Integer[] initialElements = new Integer[] { null };
        LinkedList<Integer> list = new LinkedList<Integer>(initialElements);

        assertTrue(list.first() == null);
        assertTrue(list.last() == null);
        assertTrue(list.contains(null));

    }

    @org.junit.jupiter.api.Test
    public void add_mixedElements_oneNull() {
        Integer[] initialElements = new Integer[] { 1, null, 2 };
        LinkedList<Integer> list = new LinkedList<Integer>(initialElements);

        try {
            assertTrue(list.first().equals(1));
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
        assertTrue(list.contains(1));

        assertTrue(list.contains(null));

        try {
            assertTrue(list.last().equals(2));
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
        assertTrue(list.contains(2));
    }

    @org.junit.jupiter.api.Test
    public void add_mixedElements_multipleNull() {
        Integer[] initialElements = new Integer[] { null, 1, 2, null };
        LinkedList<Integer> list = new LinkedList<Integer>(initialElements);

        assertTrue(list.contains(null));

        try {
            assertTrue(list.first() == null);
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }

        assertTrue(list.contains(1));
        assertTrue(list.contains(2));

        try {
            assertTrue(list.last() == null);
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * Tests whether we can find the correct Node<E> when we store an E element
//     * `null`.
//     * 
//     * We want to make sure that we skip finding the element container (the
//     * `Node<E>`) when searching for the first occuring null
//     */
//    @Test
//    public void circularList_testNullElement() {
//	LinkedList<Integer> list = new LinkedList<Integer>();
//
//	/* Ensure the list is empty in all ways possible */
//	circularList_emptinessChecks(list);
//
//	/**
//	 * There should be no Node<E> containing a `null` element.
//	 * 
//	 * There is the sentinel node but it should be skipped
//	 */
//	Node<Integer> containingNode = list.find(null);
//	assertTrue(containingNode == null);
//	try {
//	    list.first();
//	    assertTrue(false);
//	} catch (EmptyCollectionException e) {
//	    assertTrue(true);
//	}
//	try {
//	    list.last();
//	    assertTrue(false);
//	} catch (EmptyCollectionException e) {
//	    assertTrue(true);
//	}
//
//	list.addToFront(null);
//	assertTrue(list.size() == 1);
//
//	try {
//	    Integer element = list.first();
//	    assertTrue(element == null);
//
//	    element = list.last();
//	    assertTrue(element == null);
//
//	    containingNode = list.find(null);
//	    assertTrue(list.isNodeSentinel(containingNode) == false);
//	} catch (EmptyCollectionException e) {
//	    assertTrue(false);
//	}
//    }

}