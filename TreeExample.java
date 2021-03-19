

public class TreeExample<T extends Comparable<T>> extends Tree<T> {
    
    // for real implementations this would be much more complicated
    public boolean add(T o) {
        root.add(new TreeNode<T>(o));
        return true;
    }

    public boolean remove(T o) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        Tree<String> t = new TreeExample<>();
        t.root=new Tree.TreeNode<String>("ARSENAL");

        t.root.add(new Tree.TreeNode<String>("Forty"));
        t.root.add(new Tree.TreeNode<String>("Nine"));
        t.root.add(new Tree.TreeNode<String>("Undefeated"));

        t.root.children.get(0).add(new Tree.TreeNode<String>("I"));
        t.root.children.get(0).add(new Tree.TreeNode<String>("Bet"));
        t.root.children.get(1).add(new Tree.TreeNode<String>("You"));
        t.root.children.get(2).add(new Tree.TreeNode<String>("Are"));
        t.root.children.get(2).add(new Tree.TreeNode<String>("Sick"));
        t.root.children.get(2).add(new Tree.TreeNode<String>("Of"));
        t.root.children.get(2).add(new Tree.TreeNode<String>("Arsenal"));
        t.root.children.get(2).children.get(1).add(new Tree.TreeNode<String>("Examples"));

        String[] testy={"Arsenal","Totts"};
        System.out.println("iterative breadth first search:");
        for (String s : testy) {
            if (t.iterativeBreadthFirstContains(s)) {
                System.out.println("Tree contains " + s);
            } else {
                System.out.println("Tree doesn't contain " + s);
            }
        }
        System.out.println("recursive depth first search:");
        for (String s : testy) {
            if (t.recursiveDepthFirstContains(s)) {
                System.out.println("Tree contains " + s);
            } else {
                System.out.println("Tree doesn't contain " + s);
            }
        }
        System.out.println("iterative breadth first search:");
        for (String s : testy) {
            if (t.iterativeDepthFirstContains(s)) {
                System.out.println("Tree contains " + s);
            } else {
                System.out.println("Tree doesn't contain " + s);
            }
        }

        System.out.println("\nbreadth first iteration");
        for (String s : t.new BreadthFirstIterator()) {
            System.out.println(s);
        }
    }
}
