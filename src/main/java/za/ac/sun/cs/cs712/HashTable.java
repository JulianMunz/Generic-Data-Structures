package za.ac.sun.cs.cs712;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

@SuppressWarnings({"unused"})
public class HashTable<K,V> implements Map<K,V>{
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final float DEFAULT_MAXIMUM_LOAD_FACTOR = 0.75f;
    private int capacity = DEFAULT_INITIAL_CAPACITY, scale = 0, shift = 0, size = 0;
    private float loadFactor = DEFAULT_MAXIMUM_LOAD_FACTOR;
    private final Random random = new Random();

    private HashTableEntry<K,V>[] table;

    public HashTable() {
        table = newTable(DEFAULT_INITIAL_CAPACITY) ;
        updateConst();
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        table = newTable(capacity);
        updateConst();
    }

    public HashTable(int capacity, float loadFactor) {
        this(capacity);
        this.loadFactor = loadFactor;
    }

    @SuppressWarnings("unchecked")
    private HashTableEntry<K,V>[] newTable (int size) {
        return new HashTableEntry[size];
    }

    private HashTableEntry<K,V> getEntry(K key) {
        HashTableEntry<K,V> entry = table[hash(key)];
        while (entry != null) {
            if (key.equals(entry.key)) {
                return entry;
            } else {
                entry = entry.next;
            }
        }
        return null;
    }

    private int hash(K key) {
        return Math.abs(scale * key.hashCode() + shift) % capacity;
    }

    private void resize(int newSize) {
        capacity = newSize;
        HashTableEntry<K,V>[] newTable = newTable(capacity) ;
        updateConst();
        HashTableEntry<K,V> entry;
        HashTableEntry<K,V> newEntry;
        for (HashTableEntry<K, V> kvHashTableEntry : table) {
            entry = kvHashTableEntry;
            if (entry != null) {
                do {
                    newEntry = new HashTableEntry<>(entry.getKey(), entry.getValue(), null);
                    if (newTable[hash(entry.getKey())] == null) {
                        newTable[hash(entry.getKey())] = newEntry;
                    } else {
                        HashTableEntry<K, V> putIn = newTable[hash(entry.getKey())];
                        while (putIn.next != null) {
                            putIn = putIn.next;
                        }
                        putIn.next = newEntry;
                    }
                    entry = entry.next;
                } while (entry != null) ;
            }
        }
        table = newTable;
    }

    private void updateConst() {
        int newA = random.nextInt(capacity);
        int newB = random.nextInt(capacity);
        while (scale == newA || shift == newB || newA == 0) {
            newA = random.nextInt(capacity);
            newB = random.nextInt(capacity);
        }
        scale = newA;
        shift = newB;
    }

    /**
     * Determines whether this map contains the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return <code>true</code> if this map contains a mapping for the specified key; otherwise,
     * <code>false</code>
     * @throws NullPointerException if the specified key is <code>null</code> and this map does not
     *                              permit <code>null</code> keys (optional)
     */
    @Override
    public boolean containsKey(K key) {
        if (isEmpty()) {
            return false;
        } else {
            return getEntry(key) != null;
        }
    }

    /**
     * Determines whether this map contains a key to the specified value.
     *
     * @param value the value whose presence in this map is to be tested
     * @return <code>true</code> if this map contains one or more keys to the specified value;
     * otherwise, <code>false</code>
     * @throws NullPointerException if the specified value is <code>null</code> and this map does not
     *                              permit <code>null</code> values (optional)
     */
    @Override
    public boolean containsValue(V value) {
        HashTableEntry<K,V> entry;
        for (int i = 0; i < capacity; i++) {
            entry = table[i];
            while (entry != null) {
                if (value.equals(entry.getValue())) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    /**
     * Returns an iterable collection of the key--value pairs stored in this map.
     *
     * @return an iterable collection of the key--value pairs stored in this map
     */
    @Override
    public Iterable<MapEntry<K, V>> entries() {
        HashTableIterator<MapEntry<K, V>> it = new HashTableIterator<MapEntry<K, V>>() {
            @Override
            public MapEntry<K, V> next() {
                return getNext();
            }
        };
        return () -> it;
    }

    /**
     * Returns the value to which the specified key is mapped, or <code>null</code> if this map
     * contains no mapping for the key.
     * <p>
     * If this map permits <code>null</code> values, the return value of <code>null</code> does not
     * necessarily indicate that the map contains no mapping for the key; it's also possible that the
     * map explicitly maps the key to <code>null</code>. The {@link #containsKey(V)} method may be
     * used to distinguish these two cases.
     *
     * @param key the key for which to retrieve the value
     * @return the value associated with the specified key, or <code>null</code> if there was no
     * mapping for the key
     * @throws NullPointerException if the specified key is <code>null</code> and this map does no
     *                              permit <code>null</code> keys (optional)
     */
    @Override
    public V get(K key) {
        HashTableEntry<K,V> entry = getEntry(key);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    /**
     * Determines whether this map contains no mappings.
     *
     * @return <code>true</code> if this map has no mappings; otherwise, <code>false</code>
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns an iterable collection of the keys maintained by this map.
     *
     * @return an iterable collection of the keys maintained by this map
     */
    @Override
    public Iterable<K> keys() {
        HashTableIterator<K> it = new HashTableIterator<K>() {
            @Override
            public K next() {
                HashTableEntry<K,V> entry = getNext();
                return entry.getKey();
            }
        };
        return () -> it;
    }

    /**
     * Inserts a new map from the specified key to the specified value, and returns the old value if
     * the key is already maintained by this map.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the key, or <code>null</code> if there was no
     * mapping for the key; a <code>null</code> return value can also indicate that the map
     * previously associated <code>null</code> with the key if the implementation permits
     * <code>null</code> values.
     * @throws NullPointerException if the specified value is <code>null</code> and this map does not
     *                              permit <code>null</code> values (optional)
     */
    @Override
    public V put(K key, V value) {
        int index = hash(key);
        HashTableEntry<K, V> possibleEntry = getEntry(key);
        if (possibleEntry != null) {
            V oldValue = possibleEntry.value ;
            possibleEntry.value = value;
            return oldValue;
        } else {
            HashTableEntry<K, V> newEntry = new HashTableEntry<>(key,value, null);
            if (table[index] == null) {
                table[index] = newEntry;
            } else {
                HashTableEntry<K, V> entry = table[index];
                while (entry.next != null) {
                    entry = entry.next;

                }
                entry.next = newEntry;
            }
            size++;
            if ((int) (capacity*loadFactor) < size) {
                resize(capacity*2);

            }
            return null;
        }
    }

    /**
     * Removes the mapping associated with the specified key, and returns its value.
     *
     * @param key the key for which to remove the mapping
     * @return the value previously associated with the key, or <code>null</code> if there was no
     * mapping for the key; a <code>null</code> return value can also indicate that the map
     * previously associated <code>null</code> with the key if the implementation permits
     * <code>null</code> values
     * @throws NullPointerException if the specified key is <code>null</code> and this map does not
     *                              permit <code>null</code> keys (optional)
     */
    @Override
    public V remove(K key) {
        HashTableEntry<K,V> entry = table[hash(key)];
        HashTableEntry<K,V> prev = null;
        V returnable ;
        if (entry == null) {
            return null;
        }
        while (entry != null) {
            if (key.equals(entry.key)) {
                returnable = entry.value;
                if (prev == null) {
                    table[hash(key)] = null;
                } else {
                    prev.next = entry.next;
                }
                size--;
                if ((int) (capacity*loadFactor/4) > size) {
                    resize(capacity/2);
                }
                return returnable;
            } else {
                prev = entry;
                entry = entry.next;
            }
        }
        return null;
    }

    /**
     * Returns the number of entries in this map.
     *
     * @return the number of entries in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterable collection of the values stored in this map.
     *
     * @return an iterable collection of the values stored in this map
     */
    @Override
    public Iterable<V> values() {
        HashTableIterator<V> it = new HashTableIterator<V>() {
            @Override
            public V next() {
                HashTableEntry<K,V> entry = getNext();
                return entry.getValue();
            }
        };
        return () -> it;
    }

    private abstract class HashTableIterator<E> implements Iterator<E> {
        private int index;
        private HashTableEntry<K, V> next;

        public HashTableIterator() {
            this.index = 0;
            this.next = table[0];
            while (next == null) {
                index++;
                if (index == capacity) {
                    next = null;
                    break;
                } else {
                    next = table[index];
                }
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        public HashTableEntry<K, V> getNext() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                HashTableEntry<K, V> old = next;
                next = old.next;
                while (next == null) {
                    index++;
                    if (index == capacity) {
                        return old;
                    }
                    next = table[index];
                }
                return old;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static class HashTableEntry<K, V> implements MapEntry<K, V> {
        private final K key;
        private V value;
        private HashTableEntry<K, V> next;

        private HashTableEntry(K key, V value, HashTableEntry<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
        /**
         * Returns the key of this entry.
         *
         * @return the key of this entry
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Returns the value of this entry.
         *
         * @return the value of this entry
         */
        @Override
        public V getValue() {
            return value;
        }

    }

}
