import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class SelfBalancingBinarySearchTree<T extends Comparable<T>> {

    private BinaryTreeNode<T> root;

    public static class BinaryTreeNode<T> {

        private BinaryTreeNode<T> parent;
        private BinaryTreeNode<T> leftChild;
        private BinaryTreeNode<T> rightChild;
        private T data;
        public int level;

        public BinaryTreeNode(T data, BinaryTreeNode<T> parent) {
            this.parent = parent;
            this.data = data;
            if (parent == null) {
                level = 0;
            } else {
                level = parent.level + 1;
            }
        }

        public T getData() {
            return data;
        }

        public BinaryTreeNode<T> getParent() {
            return parent;
        }

        public boolean setLeftChild(BinaryTreeNode<T> child) {
            if (leftChild == null) {
                leftChild = child;
                return true;
            } else {
                return false;
            }
        }

        public BinaryTreeNode<T> getLeftChild() {
            return leftChild;
        }

        public boolean setRightChild(BinaryTreeNode<T> child) {
            if (rightChild == null) {
                rightChild = child;
                return true;
            } else {
                return false;
            }
        }

        public BinaryTreeNode<T> getRightChild() {
            return rightChild;
        }

        private int getHeight() {
            return getHeightRecurse(this);
        }

        private int getHeightRecurse(BinaryTreeNode<T> node) {
            if (node == null) {
                return -1;
            }
            return max(getHeightRecurse(node.getLeftChild()), getHeightRecurse(node.getRightChild())) + 1;
        }

        private int max(int num1, int num2) {
            if (num1 > num2) {
                return num1;
            } else if (num1 < num2) {
                return num2;
            }
            return num1;
        }

        public int getBalance() {
            int leftHeight, rightHeight;
            if (getLeftChild() == null) {
                leftHeight = -1;
            } else {
                leftHeight = getLeftChild().getHeight();
            }
            if (getRightChild() == null) {
                rightHeight = -1;
            } else {
                rightHeight = getRightChild().getHeight();
            }
            return leftHeight - rightHeight;
        }
    }

    public static class DuplicateItemException extends Exception {
        public DuplicateItemException(String message) {
            super(message);
        }
    }

    public class PreOrderIterator implements Iterable<T>, Iterator<T> {

        Stack<BinaryTreeNode<T>> stack = new Stack<>();

        public PreOrderIterator() {
            stack.push(root);
        }

        public T next() {
            BinaryTreeNode<T> curNode = stack.peek();
            T out = curNode.getData();
            stack.pop();

            if (curNode.getRightChild() != null) {
                stack.push(curNode.getRightChild());
            }
            if (curNode.getLeftChild() != null) {
                stack.push(curNode.getLeftChild());
            }
            return out;
        }

        public boolean hasNext() {
            return !(stack.isEmpty());
        }

        public Iterator<T> iterator() {
            return new PreOrderIterator();
        }
    }

    public class InOrderIterator implements Iterable<T>, Iterator<T> {

        private BinaryTreeNode<T> next;

        public InOrderIterator() {
            next = root;
            if (next == null) {
                return;
            }

            while (next.getLeftChild() != null) {
                next = next.getLeftChild();
            }
        }

        public boolean hasNext() {
            return next != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BinaryTreeNode<T> r = next;

            if (next.getRightChild() != null) {
                next = next.getRightChild();
                while (next.getLeftChild() != null) {
                    next = next.getLeftChild();
                }
                return r.getData();
            }

            while (true) {
                if (next.getParent() == null) {
                    next = null;
                    return r.getData();
                }
                if (next.getParent().getLeftChild() == next) {
                    next = next.getParent();
                    return r.getData();
                }
                next = next.getParent();
            }
        }

        public Iterator<T> iterator() {
            return new InOrderIterator();
        }
    }

    public class PostOrderIterator implements Iterable<T>, Iterator<T> {
        
        private Stack<BinaryTreeNode<T>> stack = new Stack<>();
        private BinaryTreeNode<T> prevNode = null;
        
        public PostOrderIterator() {
            stack.push(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public T next() {
            T out = null;
            BinaryTreeNode<T> curNode = stack.peek();
            if (prevNode == null || prevNode.getLeftChild() == curNode || prevNode.getRightChild() == curNode) {
                if (curNode.getLeftChild() != null) {
                    stack.push(curNode.getLeftChild());
                } else if (curNode.getRightChild() != null) {
                    stack.push(curNode.getRightChild());
                } else {
                    stack.pop();
                    out = curNode.getData();
                }
            } else if (curNode.getLeftChild() == prevNode) {
                if (curNode.getRightChild() != null) {
                    stack.push(curNode.getRightChild());
                } else {
                    stack.pop();
                    out = curNode.getData();
                }
            } else if (curNode.getRightChild() == prevNode) {
                stack.pop();
                out = curNode.getData();
            }
            prevNode = curNode;
            if (out == null) {
                return next();
            }
            return out;
        }

        public Iterator<T> iterator() {
            return new PostOrderIterator();
        }
    }

    public class BreadthFirstIterator implements Iterator<T>, Iterable<T> {

        Queue<BinaryTreeNode<T>> q = new LinkedList<BinaryTreeNode<T>>();
        
        BreadthFirstIterator() {
            q.add(root);
        }

        public boolean hasNext() {
            if (root == null) {
                return false;
            }
            return !q.isEmpty();
        }

        public T next() {
            BinaryTreeNode<T> temp = q.remove();
            if (temp.getLeftChild() != null) {
                q.add(temp.getLeftChild());
            }
            if (temp.getRightChild() != null) {
                q.add(temp.getRightChild());
            }
            return temp.getData();
        }

        public Iterator<T> iterator() {
            return new BreadthFirstIterator();
        }
    }

    public boolean iterativeContains(T e) {
        BinaryTreeNode<T> temp = root;
        while (temp != null) {
            if (temp.getData().compareTo(e) == 0) {
                return true;
            }
            if (temp.getData().compareTo(e) > 0) {
                temp = temp.getLeftChild();
            }
            if (temp.getData().compareTo(e) < 0) {
                temp = temp.getRightChild();
            }
        }
        return false;
    }

    public boolean recursiveContains(T e) {
        return recursiveContainsRecursion(e, root);
    }

    private boolean recursiveContainsRecursion(T e, BinaryTreeNode<T> node) {
        if (node == null) {
            return false;
        }
        if (node.data.equals(e)) {
            return true;
        }
        if (node.getData().compareTo(e) < 0) {
            return recursiveContainsRecursion(e, node.getRightChild());
        } else {
            return recursiveContainsRecursion(e, node.getLeftChild());
        }
    }

    public void iterativeAdd(T e) throws DuplicateItemException {
        if (root == null) {
            root = new BinaryTreeNode<T>(e, null);
        } else {
            BinaryTreeNode<T> temp = root;
            boolean inserted = false;
            while (!inserted) {
                if (temp.getData().equals(e)) {
                    throw new DuplicateItemException("The tree already has the item inside it. Cannot contain duplicate items");
                } else if (temp.getData().compareTo(e) > 0) {
                    if (temp.getLeftChild() == null) {
                        temp.setLeftChild(new BinaryTreeNode<T>(e, temp));
                        inserted = true;
                    } else {
                        temp = temp.getLeftChild();
                    }
                } else {
                    if (temp.getRightChild() == null) {
                        temp.setRightChild(new BinaryTreeNode<T>(e, temp));
                        inserted = true;
                    } else {
                        temp = temp.getRightChild();
                    }
                }
            }
        }
    }

    public void recursiveAdd(T e) throws DuplicateItemException {
        if (root == null) {
            root = new BinaryTreeNode<T>(e, null);
        } else {
            recursiveAddRecursion(e, root);
        }
    }

    private void recursiveAddRecursion(T e, BinaryTreeNode<T> node) throws DuplicateItemException {
        if (node.getData().equals(e)) {
            throw new DuplicateItemException("The tree already has the item inside it. Cannot contain duplicate items");
        } else if (node.getData().compareTo(e) < 0) {
            if (node.getRightChild() == null) {
                node.setRightChild(new BinaryTreeNode<T>(e, node));
            } else {
                recursiveAddRecursion(e, node.getRightChild());
            }
        } else {
            if (node.getLeftChild() == null) {
                node.setLeftChild(new BinaryTreeNode<T>(e, node));
            } else {
                recursiveAddRecursion(e, node.getLeftChild());
            }
        }
    }

    public String toString() {
        ArrayList<ArrayList<T>> levels = new ArrayList<>();
        ArrayList<T> level = new ArrayList<>();
        int levelNum = 0;
        for (T element : this.new BreadthFirstIterator()) {
            level.add(element);
            if (level.size() == Math.pow(2, levelNum)) {
                levels.add(level);
                level = new ArrayList<>();
                levelNum++;
            }
        }
        levelNum = 1;
        for (ArrayList<T> l : levels) {
            for (T e : l) {
                if (levelNum != levels.size()) {
                    System.out.print(String.format("%1$" + (levels.size() - levelNum) * 3 + "s", ""));
                }
                System.out.print(String.format("%1$" + 3 + "s", e.toString()) + " ");
            }
            System.out.println("\n");
            levelNum++;
        }
        return "";
    }
    
    public static void main(String[] args) {
        SelfBalancingBinarySearchTree<Integer> bst = new SelfBalancingBinarySearchTree<>();
        try {
            bst.recursiveAdd(25);
            bst.recursiveAdd(15);
            bst.recursiveAdd(50);
            bst.recursiveAdd(10);
            bst.recursiveAdd(22);
            bst.iterativeAdd(35);
            bst.iterativeAdd(70);
            bst.iterativeAdd(4);
            bst.iterativeAdd(12);
            bst.iterativeAdd(18);
            bst.iterativeAdd(24);
            bst.iterativeAdd(31);
            bst.iterativeAdd(44);
            bst.iterativeAdd(66);
            bst.iterativeAdd(90);
        } catch (DuplicateItemException e) {
            e.printStackTrace();
        }

        System.out.println("In-Order Traversal:");
        for (Integer s : bst.new InOrderIterator()) {
            System.out.print(s + ", ");
        }
        System.out.println("");
        System.out.println("\nPre-Order Traversal:");
        for (Integer s : bst.new PreOrderIterator()) {
            System.out.print(s + ", ");
        }
        System.out.println("");
        System.out.println("\nPost-Order Traversal:");
        for (Integer s : bst.new PostOrderIterator()) {
            System.out.print(s + ", ");
        }
        System.out.println("");
        System.out.println("\nBreadth first Traversal:");
        for (Integer s : bst.new BreadthFirstIterator()) {
            System.out.print(s + ", ");
        }
        System.out.println("\n");

        System.out.println(bst);
    }
}