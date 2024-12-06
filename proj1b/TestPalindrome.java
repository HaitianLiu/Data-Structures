import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void isPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("good"));
        assertFalse(palindrome.isPalindrome("gotgog"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertTrue(palindrome.isPalindrome("cac"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("abba"));
        assertTrue(palindrome.isPalindrome("aBBa"));
        assertFalse(palindrome.isPalindrome("Aa"));
    }


    @Test
    public void isPalindromeCC() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("acdb", obo));
        assertFalse(palindrome.isPalindrome("ahvyfdb", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));


    }

}
