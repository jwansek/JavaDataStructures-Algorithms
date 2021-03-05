import java.util.Iterator;

public class LinkedList<T> implements Sequence<T> {

    private class ListNode<T> {
        T element;
        ListNode<T> next;
    
        ListNode(T o) {
            element = o;
            next = null;
        }
    
        void connect(ListNode<T> n) {
            next = n;
        }
    
        @Override
        public String toString() {
            return "Value =" + element;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        private int count;
        private ListNode<T> node;

        LinkedListIterator() {
            count = 0;
            node = firstNode;
        }

        public boolean hasNext() {
            return count < size();
        }

        public T next() {
            T retVal = node.element;
            count++;
            node = node.next;
            return retVal;
        }
    }

    protected ListNode<T> firstNode;
    protected int size;

    public LinkedList() {
        firstNode = null;
        size = 0;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public T get(int index) {
        // get head of list, traverse to index using currentNode.next index number of times, return the element at position index
        ListNode<T> node = firstNode;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                return node.element;
            } else {
                node = node.next;
            }
        }
        return null;
    }

    public void set(int index, T value) {
        ListNode<T> node = firstNode;
        for (int i = 0; i <= size; i++) {
            if (i == index) {
                node.element = value;
                break;
            } else {
                node = node.next;
            }
        }
    }

    public void add(int index, T value) {
        ListNode<T> newNode = new ListNode<>(value);
        if (index == 0) {
            if (size == 0) {
                firstNode = newNode;
            } else {
                newNode.connect(firstNode);
                firstNode = newNode;
            }
        } else {
            ListNode<T> node = firstNode;
            for (int i = 0; i <= index; i++) {
                if (i == index - 1) {
                    newNode.connect(node.next);
                    node.connect(newNode);
                    break;
                } else {
                    node = node.next;
                }
            }
        }            
        size += 1;
    }

    public T remove(int index) {
        ListNode<T> node = firstNode;
        T toReturn = firstNode.element;
        for (int i = 0; i <= index; i++) {
            if (i == index - 1) {
                toReturn = node.next.element;
                if (i != size() - 2) {      // if its the last value, we don't need to link
                    node.connect(node.next.next);
                }
                break;
            } else {
                node = node.next;
            }
        }
        size--;
        return toReturn;
    }

    public void add(T value) {
        if (size == 0) {
            firstNode = new ListNode<T>(value);          
        } else {
            ListNode<T> node = firstNode;
            for (int i = 0; i <= size; i++) {
                if (i == size - 1) {
                    node.connect(new ListNode<T>(value));
                    break;
                } else {
                    node = node.next;
                }
            }
        }
        size += 1;
    }

    public T remove() {
        return remove(size() - 1);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public String toString() {

        String s = "["; // better to use a StringBuilder etc.
        ListNode<T> currentNode = firstNode;
        for (int i = 0; i < size; i++) {
            s += currentNode.element.toString() + ", ";
            currentNode = currentNode.next;
        }

        //traverse list, add each eleemnt to s
        return s + "]";
    }

    
    public LinkedList<T> reverse() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Some basic test methods
    public static void main(String[] args) {
        LinkedList<String> m = new LinkedList<>();

        m.add("First");
        m.add("Second");
        m.add("Third");
        m.add("Fourth");
        m.add(0, "Zeroth");

        System.out.println(" List is now =\n" + m);

        System.out.println(m.remove(4));
        System.out.println(" List is now =\n" + m);

        // for (String e : m) {
        //     System.out.println(e);
        // }

// //Test Set
//         m.set(0, "XXXXX");

//         System.out.println("List is now =\n" + m);
//         m.set(2, "YYYYY");

// //Test remove
//         m.remove(2);
//         System.out.println("List is now =\n" + m);

//         m.remove(0);
//         System.out.println("List is now =\n" + m);

    }

}
