/**
 * Manjil Pradhan
 * This class is responsible for creating a dictonary using
 * Trie data structure. It is used by calling the constructor
 * by passing file name which have all the valid words.
 */

package client.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * This class is responsible for creating a dictionary using
 * a Trie data structure. It is used by calling the constructor
 * and passing the file name containing all the valid words.
 */
public class Dictionary {

    private TrieNode root;
    private String filename;
    private FileReader reader;

    /**
     * Constructor of Dictonary class.
     * @param filename- Valid words file
     * @throws FileNotFoundException
     */
    public Dictionary(String filename) throws FileNotFoundException {
        this.filename = filename;
        reader = new FileReader(filename);
        root = new TrieNode();
    }

    /**
     * Creates a new data structure by reading the file given in the
     * parameter.
     * @return the root of the complete trie
     * @throws FileNotFoundException
     */
    public TrieNode makeTree() throws FileNotFoundException {
        TrieNode root = new TrieNode();
        Scanner scan = new Scanner(reader);
        String line = scan.nextLine();
        while (scan.hasNextLine()){
            root.insert(line);
            line = scan.nextLine();
        }
        return root;
    }

}
