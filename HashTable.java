public abstract class HashTable {
    int capacity = 13;  // must be a prime number
    int size = 0;
    
    public int size() { 
        return size;
    }

    abstract boolean add(int o);        // only use integers since writing hashing algos is hard xD

    abstract boolean contains(int o);

    abstract boolean remove(int o);
}
