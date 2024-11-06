package LinkedDictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDictionary<K, V> implements DictionaryInterface<K, V> {
    class Pair<K, V> {
        private K key;
        private V value;
        private Pair<K, V> next;

        public Pair() {
        }

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Pair<K, V> getNext() {
            return next;
        }

        public void setNext(Pair<K, V> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return key + " : \"" + value + "\", ";
        }
    }

    private Pair<K, V> firstPair;
    private int numOfEntries;


    /**
     * Adds a new entry to this dictionary. If the given search key already
     * exists in the dictionary, replaces the corresponding value.
     *
     * @param key   An object search key of the new entry.
     * @param value An object associated with the search key.
     * @return Either null if the new entry was added to the dictionary
     * or the value that was associated with a key if that value
     * was replaced.
     */
    @Override
    public V add(K key, V value) {
        // new a pair
        Pair<K, V> newPair = new Pair<>(key, value);
        // if dict is empty,
        if (numOfEntries == 0) {
            firstPair = newPair;
            numOfEntries++;
            return null;
        }
        // iterate to the end.
        Pair<K, V> current = firstPair;
        V returnValue = null;
        while (current != null) {
            if (current.getKey() != null && current.getKey().equals(key)) {
                returnValue = current.getValue();
                current.setValue(value);
                return returnValue;
            }
            if (current.getNext() != null) {
                current = current.getNext();
            } else {
                break;
            }
        }

        // add the new pair to the end;
        assert current != null;
        current.setNext(newPair);
        numOfEntries++;
        return returnValue;
    }

    /**
     * Removes a specific entry from this dictionary.
     *
     * @param key An object search key of the entry to be removed.
     * @return Either the value that was associated with the search key
     * or null if no such object exists.
     */
    @Override
    public V remove(K key) {
        // throw exp if empty
        if (numOfEntries == 0) {
            throw new RuntimeException("Empty dictionary!");
        }

        // define current, previous, and returnValue
        Pair<K, V> current = firstPair;
        Pair<K, V> previous = null;
        V deleteValue;

        // iterate each current to find the match
        while (current != null) {
            // if match found
            if (current.getKey().equals(key)) {
                // update returnValue
                deleteValue = current.getValue();
                // if match is the first pair
                if (previous == null) {
                    firstPair = null;
                }
                // if match is NOT the first pair
                else {
                    previous.setNext(current.getNext());
                }
                // disconnect the current;
                current.setNext(null);
                // update entries count
                numOfEntries--;
                // return immediately after the job done,
                return deleteValue;
            }
            // update current and previous
            previous = current;
            current = current.getNext();
        }

        // return null if no match found
        return null;
    }

    /**
     * Retrieves from this dictionary the value associated with a given
     * search key.
     *
     * @param key An object search key of the entry to be retrieved.
     * @return Either the value that is associated with the search key
     * or null if no such object exists.
     */
    @Override
    public V getValue(K key) {
        if (numOfEntries == 0) {
            throw new RuntimeException("Empty dictionary!");
        }

        Pair<K, V> current = firstPair;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }

        return null;
    }

    /**
     * Sees whether a specific entry is in this dictionary.
     *
     * @param key An object search key of the desired entry.
     * @return True if key is associated with an entry in the dictionary.
     */
    @Override
    public boolean contains(K key) {
        Pair<K, V> current = firstPair;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Creates an iterator that traverses all search keys in this dictionary.
     *
     * @return An iterator that provides sequential access to the search
     * keys in the dictionary.
     */
    @Override
    public Iterator<K> getKeyIterator() {
        return new Iterator<>() {
            private Pair<K, V> current = firstPair;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more keys");
                }
                K key = current.getKey();
                current = current.getNext();
                return key;
            }
        };
    }

    /**
     * Creates an iterator that traverses all values in this dictionary.
     *
     * @return An iterator that provides sequential access to the values
     * in this dictionary.
     */
    @Override
    public Iterator<V> getValueIterator() {
        return new Iterator<>() {
            private Pair<K, V> current = firstPair;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more entries");
                }
                V value = current.getValue();
                current = current.getNext();
                return value;
            }
        };
    }

    /**
     * Sees whether this dictionary is empty.
     *
     * @return True if the dictionary is empty.
     */
    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    /**
     * Gets the size of this dictionary.
     *
     * @return The number of entries (key-value pairs) currently
     * in the dictionary.
     */
    @Override
    public int getSize() {
        return numOfEntries;
    }

    /**
     * Removes all entries from this dictionary.
     */
    @Override
    public void clear() {
        firstPair = null;
        numOfEntries = 0;
    }

    @Override
    public String toString() {
        StringBuilder returning = new StringBuilder("Dictionary={\n");
        Pair<K, V> current = firstPair;
        while (current != null) {
            returning.append(current);
            current = current.getNext();
        }
        return returning + "\n}";
    }
}
