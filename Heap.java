import java.util.ArrayList;
import java.lang.Math;

public class Heap<T extends Comparable<T>> {
    
    private ArrayList<T> array = new ArrayList<>();

    public Heap(){}

    public Heap(ArrayList<T> sourceArr) {
        array = sourceArr;
        int pos = (int)Math.floor(array.size());
        while (pos >= 0) {
            siftDown(pos);
            pos--;
        }
        // using a siftDown() algo for creating a new heap is worst case O(n)
        // using a siftUp() algo would be worst case O(nlogn)
    }

    public T getMax() {
        return array.get(0);
    }

    public T deleteMax() {
        T toReturn = array.get(0);
        array.set(0, array.get(array.size() - 1));
        array.set(array.size() - 1, null);
        siftDown(0);
        return toReturn;
    }

    public void add(T value) {
        array.add(value);
        siftUp(array.size() - 1);
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = getParentIndex(index);
            if (array.get(index).compareTo(array.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                return;
            }
        }
    }

    private void siftDown(int index) {
        while (index * 2 <= array.size() - 1) {
            int childIndex = index * 2;
            if (childIndex + 1 <= array.size() - 1 && array.get(childIndex).compareTo(array.get(childIndex + 1)) < 0) {
                childIndex++;
            }
            if (array.get(index).compareTo(array.get(childIndex)) < 0) {
                swap(index, childIndex);
                index = childIndex;
            } else {
                return;
            }
        }
    }

    private void swap(int index1, int index2) {
        T temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }

    public static int getLeftChildIndex(int index) {
        return 2 * index;
    }

    public static int getRightChildIndex(int index) {
        return (2 * index) + 1;
    }

    public static int getParentIndex(int index) {
        return (int)Math.floor(index / 2);
    }

    public String toString() {
        String out = "[";
        for (T elem : array) {
            out += elem.toString() + ", ";
        }
        out += "]\n";
        return out;
    }

    public static void main(String[] args) {
        ArrayList<Integer> startArr = new ArrayList<>();
        for (Integer i : new Integer[] {44, 59, 63, 10, 85, 72, 67, 12}) {
            startArr.add(i);
        }
        Heap<Integer> heap = new Heap<>(startArr);
        System.out.println(heap);
        heap.add(73);
        System.out.println(heap);
    }
}
