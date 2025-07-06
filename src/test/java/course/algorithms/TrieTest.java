package course.algorithms;

import course.algorithms.Trie;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//
// Note: This is code for a future episode. Stay tuned!
//

public class TrieTest {

    private Trie trie;

    @BeforeEach
    public void SetUp() {
        trie = new Trie();
    }

    @Test
    public void StartsWith() {
        trie.insert("Apple");
        assertTrue(trie.startsWith("A"));
        assertTrue(trie.startsWith("Ap"));
        assertTrue(trie.startsWith("App"));
        assertTrue(trie.startsWith("Appl"));
        assertTrue(trie.startsWith("Apple"));
    }

    @Test
    public void ContainsWord() {
        trie.insert("pot");
        trie.insert("past");
        trie.insert("pass");
        trie.insert("part");

        assertTrue(trie.containsWord("pot"));
        assertTrue(trie.containsWord("past"));
        assertTrue(trie.containsWord("pass"));
        assertTrue(trie.containsWord("part"));
        assertFalse(trie.containsWord("ps"));
    }
}
