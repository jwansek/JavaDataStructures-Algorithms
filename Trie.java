import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;

public class Trie {

    public static class TrieNode  {
        private TrieNode children[] = new TrieNode[26];
        private boolean isWord = false;
        private char character;
        private TrieNode parent;

        public TrieNode(){}

        public TrieNode(char c, TrieNode parent) {
            this.character = c;
            this.parent = parent;
        }

        public TrieNode getChildren(char c) {
            return children[(int)c - 97];
        }

        public ArrayList<TrieNode> getAllChildren() {
            ArrayList<TrieNode> out = new ArrayList<>();
            for (TrieNode child : children) {
                if (child != null) {
                    out.add(child);
                }
            }
            return out;
        }

        public TrieNode setChild(char c, TrieNode parent) {
            children[(int)c - 97] = new TrieNode(c, parent);
            return children[(int)c - 97];
        }

        public void setWord(boolean isWord) {
            this.isWord = isWord;
        }

        public boolean isWord() {
            return isWord;
        }

        public char getChar() {
            return character;
        }

        public TrieNode getParent() {
            return parent;
        }
    }

    TrieNode root = new TrieNode();

    public void add(String key) {
        TrieNode temp = root;
        for (char c : key.toLowerCase().toCharArray()) {
            TrieNode next = temp.getChildren(c);
            if (next == null) {
                next = temp.setChild(c, temp);
            }
            temp = next;
        }
        temp.setWord(true);
    }

    public boolean contains(String str) {
        TrieNode temp = root;
        for (char c : str.toLowerCase().toCharArray()) {
            TrieNode next = temp.getChildren(c);
            if (next == null) {
                return false;
            }
            temp = next;
        }
        return temp.isWord();
    }

    public ArrayList<String> getWords(String prefix) {
        TrieNode temp = root;
        ArrayList<String> words = new ArrayList<>();

        for (char c : prefix.toLowerCase().toCharArray()) {
            TrieNode next = temp.getChildren(c);
            if (next == null) {
                System.out.println("No words...");
                return words;
            }
            temp = next;
        }

        TrieNode rel_root = temp;
        Queue<TrieNode> q = new LinkedList<TrieNode>();
        ArrayList<TrieNode> endingChars = new ArrayList<>();
        q.add(rel_root);
        while (q.isEmpty() == false) {
            temp = q.remove();
            if (temp.isWord()) {
                endingChars.add(temp);
            }
            for (TrieNode child : temp.getAllChildren()) {
                q.add(child);
            }
        }
        // using a stack and a depth first algo would be better.
        // instead we backtrack the parents until we get to the parent
        for (TrieNode t : endingChars) {
            temp = t;
            String word = String.format("%c", t.getChar());
            do {
                temp = temp.getParent();
                word = temp.getChar() + word;
            } while (temp != rel_root);
            words.add(prefix + word.substring(1));
        }
        
        return words;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        String dict[] = {"cheers", "cheese", "chat", "bat"};
        for (String w : dict) {
            trie.add(w);
            System.out.println(String.format("Added '%s' to trie...", w));
        }

        System.out.println("\nchips: " + trie.contains("chips"));
        System.out.println("cheese: " + trie.contains("cheese"));
        System.out.println("cat: " + trie.contains("cat"));
        System.out.println("bat: " + trie.contains("bat"));
    
        String prefixChecks[] = {"ch", "chee", "b"};
        for (String prefix : prefixChecks) {
            System.out.println("\nChecking prefixes for: '" + prefix + "':");
            for (String word : trie.getWords(prefix)) {
                System.out.println(word);
            }
        }
    }
}