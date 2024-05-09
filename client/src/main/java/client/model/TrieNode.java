/**
 * Manjil Pradhan
 * This class represents Trie data structure, which is a node
 * holding character data and has edges to an arbitary number of children.
 * The isWord node represents the current path is a complete word, while
 * something that is not a isWord node means the path is not complete.
 *
 */

package client.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a Trie data structure, which is a node
 * holding character data and has edges to an arbitrary number of children.
 * The isWord node represents the current path is a complete word, while
 * something that is not a isWord node means the path is not complete.
 */
public class TrieNode {

    private static final int CHAR_SIZE = 26;
    private boolean isWord;
    private Map<Character, TrieNode> childrens;

    // Constructor
    TrieNode() {
        isWord = false;
        childrens = new HashMap<>();
    }

    /**
     * This function adds a new node
     * @param key
     */
    public void insert(String key) {
        TrieNode curr = this;
        for (char c : key.toCharArray()) {
            if (!curr.childrens.containsKey(c)) {
                curr.childrens.put(c, new TrieNode());
            }
            curr = curr.childrens.get(c);
        }

        // mark the current node as a leaf
        curr.isWord = true;
    }

    // Iterative function

    /**
     * This is a iterative function to search a key in a Trie. It returns true
     * if the key is found in the Trie; otherwise, it returns false
     * @param key
     * @return
     */
    public TrieNode search(String key) {
        TrieNode curr = this;
        for (char c : key.toCharArray()) {
            curr = curr.childrens.get(c);
            if (curr == null) {
                return null;
            }
        }
        return curr;
    }

    /**
     * This function returns the isWord field of the given node.
     * @return
     */
    public boolean isCompleteWord() {
        return isWord;
    }

    /**
     * This function returns true if the node is a complete word, else
     * returns false.
     * @param key
     * @return
     */
    public boolean isWord(String key) {
        TrieNode node = search(key);
        if (node == null) {
            return false;
        }
        return node.isWord;
    }

    /**
     * This function returns all the children of the parent node.
     * @return Map
     */
    public Map<Character, TrieNode> getChild() {
        return childrens;
    }

}
