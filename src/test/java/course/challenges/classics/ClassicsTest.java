package course.challenges.classics;

import course.challenges.classics.Anagram;
import course.challenges.classics.CaesarCipherAdvanced;
import course.challenges.classics.CaesarCipherSimple;
import course.challenges.classics.CharacterCount;
import course.challenges.classics.FizzBuzz;
import course.challenges.classics.IntReverser;
import course.challenges.classics.RansomNote;
import course.challenges.classics.SieveOfEratosthenes;
import course.challenges.classics.StringReverser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class   ClassicsTest {

    private FizzBuzz fizzBuzz;
    private RansomNote ransomNote;
    private CaesarCipherSimple caesarSimple;
    private CaesarCipherAdvanced caesarAdvanced;
    private SieveOfEratosthenes sieveOfEratosthenes;
    private StringReverser stringReverser;
    private IntReverser intReverser;
    private Anagram anagram;
    private CharacterCount characterCount;

    @BeforeEach
    public void SetUp() {
        fizzBuzz = new FizzBuzz();
        ransomNote = new RansomNote();
        caesarSimple = new CaesarCipherSimple();
        caesarAdvanced = new CaesarCipherAdvanced();
        sieveOfEratosthenes = new SieveOfEratosthenes();
        stringReverser = new StringReverser();
        intReverser = new IntReverser();
        anagram = new Anagram();
        characterCount = new CharacterCount();
    }

    @Test
    public void FizzBuzz() {
        // Challenge: Write a program that prints 1 to 100.
        // But for multiples of three print 'Fizz' instead of the number.
        // And for multiples of five  print 'Buzz'.
        // For numbers which are multiples of both three and five print 'FizzBuzz'.
        //
        // Example
        // 1
        // 2
        // Fizz
        // 4
        // Buzz

        fizzBuzz.print();
    }

    @Test
    public void HarmlessRansomNote() {
        // Challenge: Write a ransom note by cutting letters out of the available
        // magazines and pasting them together to form a letter. Given an arbitrary
        // ransom note string and another string containing all the contents of all
        // the magazines, write a function that will return true if the ransom
        // note can be made from the magazines; otherwise, it will return false.
        // Every letter found in the magazine string can only be used once in your
        // ransom note.

        assertTrue(ransomNote.canWrite("Pay", "yaP"));
        assertTrue(ransomNote.canWrite("Pay", "yaP a"));
        assertTrue(ransomNote.canWrite("Pay me $1000", "ayPem0001$"));

        assertFalse(ransomNote.canWrite("Pay", "Pa"));
    }

    @Test
    public void CaesarCipher() {
        // Challenge: Implement a Caesar Cipher.
        //
        // A Caesar Cipher is a basic encryption algorithm that takes all the letters message
        // and encrypts them by shifting them over some fixed amount.
        //
        // For example a Caesar Cipher of x3 would map the alphabet like this:
        //
        // Plain:    ABCDEFGHIJKLMNOPQRSTUVWXYZ
        // Cipher:   XYZABCDEFGHIJKLMNOPQRSTUVW
        //
        // And the following text like this:
        //
        // Plaintext:  THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG
        // Ciphertext: QEB NRFZH YOLTK CLU GRJMP LSBO QEB IXWV ALD
        //
        // Write an encrypt and decrypt method of a Caesar Cipher using an offset of 3
        //

        // First attempt...

        assertEquals("XYZ", caesarSimple.encrypt("ABC"));
        assertEquals("X Y Z", caesarSimple.encrypt("A B C"));
        assertEquals("QEB NRFZH YOLTK CLU GRJMP LSBO QEB IXWV ALD", caesarSimple.encrypt("THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG"));

        assertEquals("ABC", caesarSimple.decrypt("XYZ"));

        // Second attempt (after some Googling...)

        assertEquals("DEF", caesarAdvanced.encrypt("ABC", 3));
        assertEquals("D E F", caesarAdvanced.encrypt("A B C", 3));
        assertEquals("WKH TXLFN EURZQ IRA MXPSV RYHU WKH ODCB GRJ", caesarAdvanced.encrypt("THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", 3));

        assertEquals("A B C", caesarAdvanced.decrypt("D E F", 3));

        // Note: The reason why the first attempt and second attempt have different expectations (ABC vs DEF)
        // is because the second attempt shifts the other way. I left the examples as is so they match wikiPedia examples
        // and to make the more advanced algorithm simpler and easier to understand.
    }

    @Test
    public void SieveOfEratosthenes() {
        // Challenge: Given a number n, print all primes smaller than or equal to n (assume n < 30).
        //
        // Hint: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
        //

        sieveOfEratosthenes.print(30);
    }

    @Test
    public void ReverseString() {
        // Challenge: Given a string, return it's reverse.
        // "abc" => "cba"

        // Soln1
        assertEquals("olleH", stringReverser.soln1("Hello"));
        assertEquals("maS", stringReverser.soln1("Sam"));

        // Soln2
        assertEquals("norT", stringReverser.soln2("Tron"));
        assertEquals("nnylF", stringReverser.soln2("Flynn"));
    }

    @Test
    public void ReverseInt() {
        // Challenge: Given an int, reverse its digits.
        // x = 123, return 321
        // x= -123, return -321

        assertEquals(321, intReverser.reverse(123));
        assertEquals(-321, intReverser.reverse(-123));
    }

    @Test
    public void Anagram() {
        // Challenge: Given two strings, write an algorithm to detect whether one word is an anagram of the other.
        // An anagram is a word that can be made by changing the order of the letters in another word.
        //
        // For example:
        // tar => rat
        // state => taste

        // Soln1
        assertTrue(anagram.isAnagram1("arc", "car"));
        assertTrue(anagram.isAnagram1("night", "thing"));
        assertFalse(anagram.isAnagram1("cat", "dog"));

        // Soln2
        assertTrue(anagram.isAnagram2("arc", "car"));
        assertTrue(anagram.isAnagram2("night", "thing"));
        assertFalse(anagram.isAnagram2("cat", "dog"));
    }

    @Test
    public void CharacterCount() {
        // Challenge: Given a string, return the character that occurs most often in the string.
        // In the event of a tie, return the larger character.
        //
        // For example:
        // abbbbc => b
        // abcxxxyyyzzz => z

        assertEquals("b", characterCount.maxChar("abbbbc"));
        assertEquals("z", characterCount.maxChar("shazzzzzam!"));

        // What about a tie?
    }

}
