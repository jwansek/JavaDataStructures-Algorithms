import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Iterator;

public abstract class Tree<T extends Comparable<T>> {
    
    TreeNode<T> root;

    public void findAllLevels() {
        throw new UnsupportedOperationException();
    }

    // how to add to a tree? it normally depends on the context...
    abstract public boolean add(T element);

    abstract public boolean remove(T element);

    //How to search?? Use BFS or DFS
    public boolean contains(T element) {
        return iterativeBreadthFirstContains(element);
    }

    public boolean iterativeBreadthFirstContains(T element) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode<T>> q = new LinkedList<TreeNode<T>>();
        q.add(root);
        while (q.isEmpty() == false) {
            TreeNode<T> temp = q.remove();
            if (temp.obj.compareTo(element) == 0) {
                return true;
            }
            for (TreeNode<T> child : temp.getAllChildren()) {
                q.add(child);
            }
        }
        return false;
    }

    public boolean iterativeDepthFirstContains(T element) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode<T>> stack = new Stack<>();
        HashSet<TreeNode<T>> seen = new HashSet<>();
        stack.push(root);
        while (stack.isEmpty() == false) {
            TreeNode<T> temp = stack.peek();
            if (temp.obj.compareTo(element) == 0) {
                return true;
            }
            seen.add(temp);
            boolean found = false;
            Iterator<TreeNode<T>> iterator = temp.getAllChildren().iterator();
            TreeNode<T> next = null;
            while (!found && iterator.hasNext()) {
                next = iterator.next();
                if (!seen.contains(next)) {
                    found = true;
                }
            }
            if (!found) {
                stack.pop();
            } else {
                stack.push(next);
            }
        }
        return false;
    }

    public boolean recursiveDepthFirstContains(T element) {
        return depthFirstRecurse(element, root);
    }

    private boolean depthFirstRecurse(T e, TreeNode<T> n) {
        if (n == null) {
            return false;
        }
        if (n.obj.compareTo(e) == 0) {
            return true;
        }
        for (TreeNode<T> child : n.getAllChildren()) {
            if (depthFirstRecurse(e, child)) {
                return true;
            }
        }
        return false;
    }

    public static class TreeNode<T> {
        T obj;
        ArrayList<TreeNode<T>> children;

        TreeNode(T o) {
            obj = o;
            children = new ArrayList<>();
        }

        boolean add(TreeNode<T> tr) {
            return children.add(tr);
        }

        ArrayList<TreeNode<T>> getAllChildren() {
            return children;
        }

        public int height() {
            throw new UnsupportedOperationException();
        }
    }

    public class BreadthFirstIterator implements Iterator<T>, Iterable<T> {

        Queue<TreeNode<T>> q = new LinkedList<TreeNode<T>>();
        
        BreadthFirstIterator() {
            q.add(root);
        }

        @Override
        public boolean hasNext() {
            if (root == null) {
                return false;
            }
            return !q.isEmpty();
        }

        public T next() {
            TreeNode<T> temp = q.remove();
            for (TreeNode<T> child : temp.getAllChildren()) {
                q.add(child);
            }
            return temp.obj;
        }

        public Iterator<T> iterator() {
            return new BreadthFirstIterator();
        }
    }
}