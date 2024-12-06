public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        Deque<Character> nword = wordToDeque(word);
        if (nword.size() == 0 || nword.size() == 1) {
            return true;
        }

        return isPahelper(nword, nword.size());
    }

    private boolean isPahelper(Deque<Character> nword, int index) {
        if (index == 0 || index == 1) {
            return true;
        }
        if (nword.removeFirst() == nword.removeLast()) {
            return isPahelper(nword, nword.size());
        }
        return false;
    }





    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        Deque<Character> nword = wordToDeque(word);
        if (nword.size() == 0 || nword.size() == 1) {
            return true;
        }

        return isHelper(nword, nword.size(), cc);

    }

    private boolean isHelper(Deque<Character> nword, int index, CharacterComparator cc) {
        if (index == 0 || index == 1) {
            return true;
        }
        if (cc.equalChars(nword.removeFirst(), nword.removeLast())) {
            return isHelper(nword, nword.size(), cc);
        }
        return false;
    }
}

