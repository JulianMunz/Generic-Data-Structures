import org.junit.jupiter.api.Test;
import za.ac.sun.cs.cs712.HashTable;
import za.ac.sun.cs.cs712.MapEntry;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HashTable.java
 */
public class HashTable_Test {
    @Test
    public void testInit() {
        HashTable<Integer, Integer> table = new HashTable<>();
        /* Ensure that the */
        assertTrue(true);
    }

    @Test
    public void testInitCap() {
        HashTable<Integer, Integer> table = new HashTable<>(32);
        /* Ensure that the */
        //assertEquals(32, table.capacity);
        assertTrue(true);
    }

    @Test
    public void testInitCapDing() {
        HashTable<Integer, Integer> table = new HashTable<>(32, 0.7f);
        /* Ensure that the */
        //assertEquals(32, table.capacity);
        assertTrue(true);
    }

    @Test
    public void testPut() {
        HashTable<Integer, Integer> table = new HashTable<>();
        table.put(1,1);
        assertTrue(true);
    }

    @Test
    public void testGet() {
        HashTable<Integer, Integer> table = new HashTable<>();
        table.put(1,2);
        Integer item = table.get(1);
        assertTrue(true);
        assertEquals(2, (int) item);
        assertEquals(1, table.size());
        assertTrue(table.containsKey(1));
        assertTrue(table.containsValue(2));
    }

    @Test
    public void testRemove() {
        HashTable<Integer, Integer> table = new HashTable<>();
        table.put(1,2);
        Integer item = table.get(1);
        assertTrue(true);
        assertEquals(2, (int) item);
        assertEquals(1, table.size());
        table.remove(1);
        assertTrue(table.isEmpty());
        assertFalse(table.containsKey(1));
        assertFalse(table.containsValue(2));
    }

    @Test
    public void testKeyIterator() {
        HashTable<Integer, Integer> table = new HashTable<>();
        table.put(1,1221);
        table.put(432,244);
        table.put(11,888);
        table.put(1765,21);
        table.put(2,231);
        table.remove(2);

        Iterator<Integer> it = table.keys().iterator();
        int i = it.next();
        i = it.next();
        i = it.next();
        i = it.next();
        assertFalse(it.hasNext());
        assertTrue(true);
    }

    @Test
    public void testResize() {
        HashTable<Integer, Integer> tab = new HashTable<>();
        tab.put(432,244);
        tab.put(11,888);
        tab.put(1765,21);
        tab.put(1,1221);
        tab.put(2,231);
        tab.put(3,4);
        tab.put(4,12112);
        tab.put(5,231);
        tab.put(6,4);
        tab.put(7,12112);
        tab.put(8,231);
        tab.put(9,4);
        tab.put(10,12112);
        assertEquals(13, tab.size());
        //assertEquals(32, tab.capacity);
    }

    @Test
    public void testDoubleResize() {
        HashTable<Integer, Integer> tab = new HashTable<>();
        tab.put(432,244);
        tab.put(1765,21);
        tab.put(1,1221);
        tab.put(2,231);
        tab.put(3,4);
        tab.put(4,12112);
        tab.put(5,231);
        tab.put(6,4);
        tab.put(7,12112);
        tab.put(8,231);
        tab.put(9,4);
        tab.put(10,12112);
        tab.put(11,888);
        assertEquals(13, tab.size());
        assertEquals(32, tab.capacity);
        tab.remove(4);
        assertFalse(tab.containsKey(4));
        tab.remove(5);
        assertFalse(tab.containsKey(5));
        tab.remove(6);
        assertFalse(tab.containsKey(6));
        tab.remove(7);
        assertFalse(tab.containsKey(7));
        tab.remove(8);
        assertFalse(tab.containsKey(8));
        tab.remove(9);
        assertFalse(tab.containsKey(9));
        tab.remove(10);
        assertFalse(tab.containsKey(10));
        assertTrue(tab.containsKey(3));
        assertTrue(tab.containsKey(2));
        assertTrue(tab.containsKey(1));
        assertTrue(tab.containsKey(432));
        assertTrue(tab.containsKey(1765));
        assertTrue(tab.containsKey(11));

        assertEquals(32, tab.capacity);
        assertEquals(6,tab.size());
        tab.remove(11);
        assertFalse(tab.containsKey(11));
        //assertEquals(16, tab.capacity);
        assertEquals(5,tab.size());
    }

    @Test
    public void testValueIterator() {
        HashTable<Integer, Integer> table = new HashTable<>();
        table.put(1,1221);
        table.put(432,244);
        table.put(11,888);
        table.put(1765,21);
        table.put(2,231);
        assertEquals(231, table.put(2,132));

        Iterator<Integer> it = table.values().iterator();
        int i = it.next();
        i = it.next();
        i = it.next();
        i = it.next();
        i = it.next();
        assertFalse(it.hasNext());
        assertTrue(true);
    }

    @Test
    public void testWholeIterator() {
        HashTable<Integer, Integer> table = new HashTable<>();
        table.put(1,1221);
        table.put(432,244);
        table.put(11,888);
        table.put(1765,21);
        table.put(2,231);

        Iterator<MapEntry<Integer,Integer>> it = table.entries().iterator();
        MapEntry<Integer,Integer> i = it.next();
         i = it.next();
         i = it.next();
         i = it.next();
        i = it.next();
        assertFalse(it.hasNext());
        assertTrue(true);
    }

    @Test
    public void testStressResize() {
        HashTable<Integer, Integer> tab = new HashTable<>();
        tab.put(1,1);
        tab.remove(1);
//        System.out.println(tab.capacity);
        tab.put(1,1);
        tab.remove(1);
//        System.out.println(tab.capacity);
        tab.put(1,1);
        tab.remove(1);
//        System.out.println(tab.capacity);
        tab.put(1,1);
        tab.remove(1);
//        System.out.println(tab.capacity);
        tab.put(1,1);
        tab.remove(1);
//        System.out.println(tab.capacity);
        tab.put(1,1);
        tab.remove(1);
//        System.out.println(tab.capacity);
        assertTrue(true);
    }

}
