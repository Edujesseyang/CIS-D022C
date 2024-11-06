package HashedDictionary;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Dictionary implementation with hashing feature
 * @author Jesse Yang
 * @param <K> key
 * @param <V>
 */

public class HashedDictionary<K, V> implements DictionaryInterface<K, V> {
    public static class Pair<K, V> {
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

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(Pair<K, V> next) {
            this.next = next;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public Pair<K, V> getNext() {
            return this.next;
        }

        @Override
        public String toString() {
            return this.key + " : \"" + this.value + "\"";
        }
    }

    // members
    private static final double LOAD_FACTOR = 0.7;
    // pre-create a prime number list for rehashing to avoid finding the next prime number; it can increase the efficient;
    // it also set the default table length and max table length.
    private static final int[] PRIME_LIST = {
            17, 29, 43, 67, 101, 151, 227, 337, 509, 761,
            1147, 1721, 2593, 3889, 5839, 8761, 13127
    };
    private int primeIndex = 0; // helper for prime list use
    private int tableOccupied;
    private int numOfEntries;
    private int tableSize;
    private Pair<K, V>[] hashTable;


    @SuppressWarnings("unchecked")
    public HashedDictionary() {
        this.tableOccupied = 0;
        this.numOfEntries = 0;
        this.tableSize = PRIME_LIST[0];
        hashTable = new Pair[tableSize];
    }

    /**
     * helper function, get hashed index;
     * @param key: K type
     * @return int: positive int based on the tableSize;
     */
    public int getHashIndex(K key) {
        String keyString = key.toString();
        int result = 0;
        for (Character c : keyString.toCharArray()) {
            result += c * 31 + c;
        }
        return Math.abs(result % tableSize);
    }

    @SuppressWarnings("unchecked")
    public void rehashing() {
        // check if rehashing needed
        if ((double) tableOccupied / tableSize >= LOAD_FACTOR) {// if so:
            if (primeIndex + 1 < PRIME_LIST.length) {
                // update new size
                this.tableSize = PRIME_LIST[++primeIndex];
            } else {
                // stop rehashing, let the link handle more collisions
                return;
            }
            // define a new table
            Pair<K, V>[] newHashTable = new Pair[tableSize];
            // copy the old table
            Pair<K, V>[] oldHashTable = hashTable;
            // point the member table to the new table
            this.hashTable = newHashTable;
            // update occupied num
            tableOccupied = 0;

            // iterate all indexes in the table
            for (Pair<K, V> pair : oldHashTable) {
                // create a current pair
                Pair<K, V> current = pair;
                // add the current pair to the new table;
                while (current != null) {
                    add(current.getKey(), current.getValue());
                    current = current.getNext();
                }
            }
        }
    }


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
        if (primeIndex < PRIME_LIST.length) {
            rehashing();
        }// check if rehashing needed
        Pair<K, V> newPair = new Pair<>(key, value); // create a new pair first
        int newPairIndex = getHashIndex(key); // calc the hashCode

        // if the spot is available, if so, set the new pair into the spot,
        if (hashTable[newPairIndex] == null) {
            hashTable[newPairIndex] = newPair;
            tableOccupied++;
            numOfEntries++;
        } else { // if not, start linking
            Pair<K, V> current = hashTable[newPairIndex];
            while (current != null) {
                // if key exists, replace value
                if (current.getKey().equals(key)) {
                    V returningValue = current.getValue();
                    current.setValue(value);
                    return returningValue;
                }
                // if not, traverse to the last pair, then set the new pair to be the last.
                if (current.getNext() == null) {
                    current.setNext(newPair);
                    numOfEntries++;
                    break;
                }
                // traverse
                current = current.getNext();
            }
        }
        return null;
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
        // throw exception if empty
        if (numOfEntries == 0) {
            return null;
        }
        // declare return val
        V returningValue;
        int keyHash = getHashIndex(key);

        Pair<K, V> current = hashTable[keyHash];
        Pair<K, V> previous = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                returningValue = current.getValue();
                // handling if there is only one entry in the bucket
                if (previous == null) {
                    hashTable[keyHash] = current.getNext();
                    if (hashTable[keyHash] == null) {
                        tableOccupied--; // update table occupation}
                    }
                } else { // handling if there are more than one
                    previous.setNext(current.getNext());
                }
                // disconnect current
                current.setNext(null);
                numOfEntries--; // update entry count
                return returningValue;
            }
            // traverse
            previous = current;
            current = current.getNext();
        }

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
        // get hash code for the key
        int hashIndex = getHashIndex(key);
        // make a current
        Pair<K, V> current = hashTable[hashIndex];
        // if the bucket is empty, return null
        if (current == null) {
            return null;
        }

        // if current is not null
        while (current != null) {
            // check if current match the key
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }

        // return if no found
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
        int hashIndex = getHashIndex(key);
        Pair<K, V> current = hashTable[hashIndex];

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
            private int currentIndex = 0;
            private Pair<K, V> current = null;

            {
                goToNextNonEmptySlot();
            }

            @Override
            public boolean hasNext() {
                return !(current == null);
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more keys.");
                }

                K key = current.getKey();
                current = current.getNext();

                if (current == null) {
                    currentIndex++;
                    goToNextNonEmptySlot();
                }
                return key;
            }

            private void goToNextNonEmptySlot() {
                while (currentIndex < hashTable.length) {
                    if (hashTable[currentIndex] != null) {
                        current = hashTable[currentIndex];
                        return;
                    }
                    currentIndex++;
                }
                current = null;
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
        return new Iterator<V>() {
            private int currentIndex = 0;
            private Pair<K, V> current = null;

            {
                goToNextNonEmptySlot();
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more values.");
                }
                V value = current.getValue();
                current = current.getNext();

                if (current == null) {
                    currentIndex++;
                    goToNextNonEmptySlot();
                }

                return value;
            }

            private void goToNextNonEmptySlot() {
                while (currentIndex < hashTable.length) {
                    if (hashTable[currentIndex] != null) {
                        current = hashTable[currentIndex];
                        return;
                    }
                    currentIndex++;
                }
                current = null;
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
        Arrays.fill(hashTable, null);
        numOfEntries = 0;
        tableOccupied = 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Dictionary = {\n");
        for (Pair<K, V> p : hashTable) {
            Pair<K, V> current = p;
            while (current != null) {
                result.append(current).append(", ");
                current = current.getNext();
            }
        }

        if (result.length() > 15) { // remove the last coma. 15 is the length of "Dictionary = {\n"
            result.setLength(result.length() - 2);
        }
        result.append("\n}");
        return result.toString();
    }
}
