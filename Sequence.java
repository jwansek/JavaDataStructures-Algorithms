public interface Sequence<T> extends Iterable<T> {

    T get(int index);               // Get value at index
    void set(int index, T value);	// Replace value at index
    void add(int index, T value);	// Add item at index (increase size)
    T remove(int index);            // Remove item at index
    void add(T value);              // Append item 
    T remove();	                    // Pop item
    int size();                     // get size
    boolean isEmpty();              // size is zero
}
    