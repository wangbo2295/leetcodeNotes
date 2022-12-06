package leetcode.string;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class No316 {
    public String removeDuplicateLetters(String s) {
        Deque<Character> deque = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        int[] hash = new int[26];
        for (char c : s.toCharArray()) hash[c - 'a']++;
        for (char c : s.toCharArray()) {
            if (set.contains(c))    {
                hash[c - 'a']--;
                continue;
            }
            while (!deque.isEmpty() && deque.getLast() > c && hash[deque.getLast() - 'a'] > 0) {
                set.remove(deque.pollLast());
            }
            deque.addLast(c);
            set.add(c);
            hash[c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty())    sb.append(deque.pollFirst());
        return sb.toString();
    }
}
