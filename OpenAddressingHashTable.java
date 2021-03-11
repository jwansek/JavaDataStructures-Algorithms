
public class OpenAddressingHashTable extends HashTable {
    
    private class Element {

        int value;

        Element(int o) {            // only allow integers in this class for similicy of the
            this.value = o;         // hashing algorithm, real hash tables need to be generic
        }

        @Override
        public int hashCode() {
            return value % 13;
        }

        public String toString() {
            return "Element " + value + " with hash " + hashCode();
        }
    }

    // ************************************************************************
    // Hash tables have a problem. What to do when there's a hash collision?
    // (When two different elements have the same hash) some languages make
    // each hash element an arraylist so it can have multiple elements. But
    // eventually it will become more and more linear (which defeats the
    // purpose of a hash table). These solutions probe another space to put
    // the element. This needs to be done in a consistent manner. Removing
    // elements it more complicated unfortunately.
    // ************************************************************************
    private interface Probing {
        int increment(int j, int k);
    }

    // ************************************************************************
    // The simplest algorithm for probing the next location, simply find the
    // next space. This isn't very good since it leads to clustering
    // ************************************************************************
    public static class LinearProbing implements Probing {
        public int increment(int j, int k) {
            return (j + 1);
        }
    }

    // ************************************************************************
    // Find the next space in a non-linear way. Therefore each element will 
    // have its collisions put in a different relative location. This stops
    // clustering, but it means that the hash table length needs to be a prime
    // number
    // ************************************************************************
    public static class QuadraticProbing implements Probing {
        public int increment(int j, int k) {
            if (j == 0) {
                return j;
            }
            System.out.println("j = " + j + "; k = " + k + "; inc(j, k) = " + ((2 * j) - 1));
            return ((2 * j) - 1);
        }
    }

    // ************************************************************************
    // Another way of probing is to use another hashing algorithm
    // ************************************************************************
    public static class DoubleHashing implements Probing {
        public int increment(int j, int k) {
            if (j == 0) {
                return j;
            }
            System.out.println("j = " + j + "; k = " + k + "; inc(j, k) = " + hash2(k));
            return hash2(k);
        }

        private int hash2(int k) {
            return 5 - (k % 5);
        }
    }

    private Element[] elements;
    Probing probingAlgo;

    <T extends Probing> OpenAddressingHashTable(T probingAlgo) {
        elements = new Element[capacity];
        this.probingAlgo = probingAlgo;
    }

    public boolean add(int o) {
        Element e = new Element(o);

        int finalIndex = resolveCollision(e, 0, e.hashCode());
        size++;
        return true;
    }

    public boolean contains(int o) {
        return true;    //TODO
    }

    public boolean remove(int o) {
        return true;    //TODO
    }

    private int resolveCollision(Element e, int numMisses, int index) {
        if (elements[index] == null) {
            elements[index] = e;
            System.out.println(String.format("Placed %d at index %d.", e.value, index));
            return index;
        } else {
            int incr = (index + probingAlgo.increment(numMisses, e.value)) % capacity;
            if (index != incr) {
                System.out.println(String.format("Couldn't place %d at index %d, trying index %d...", e.value, index, incr));
            } 
            return resolveCollision(e, numMisses + 1, incr);
        }
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < capacity; i++) {
            try {
                out += String.format("%d = %d\n", i, elements[i].value);
            } catch (NullPointerException e) {
                out += String.format("%d = null\n", i);
            }     
        }
        return out;
    }

    public static void main(String[] args) {
        OpenAddressingHashTable ht1 = new OpenAddressingHashTable(new LinearProbing());
        ht1.add(8);
        ht1.add(42);
        ht1.add(29);
        ht1.add(51);
        ht1.add(47);
        ht1.add(13);
        ht1.add(34);
        System.out.println(ht1);
        System.out.println("\n\n");

        OpenAddressingHashTable ht = new OpenAddressingHashTable(new QuadraticProbing());
        ht.add(8);
        ht.add(42);
        ht.add(29);
        ht.add(51);
        ht.add(47);
        ht.add(13);
        ht.add(34);
        System.out.println(ht);
        System.out.println("\n\n");

        OpenAddressingHashTable ht2 = new OpenAddressingHashTable(new DoubleHashing());
        ht2.add(8);
        ht2.add(42);
        ht2.add(29);
        ht2.add(51);
        ht2.add(47);
        ht2.add(13);
        ht2.add(34);

        System.out.println(ht2);
    }
}
