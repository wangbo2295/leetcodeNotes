package leetcode.hash;

import java.util.*;

public class No890 {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for (String s: words) {
            Character[] map1 = new Character[128];
            Character[] map2 = new Character[128];
            boolean match = true;
            for (int i = 0; i < pattern.length(); i++) {
                Character ch = map1[s.charAt(i)];
                Character ch2 = map2[pattern.charAt(i)];
                if (ch == null && ch2 == null) {
                    map1[s.charAt(i)] = pattern.charAt(i);
                    map2[pattern.charAt(i)] = s.charAt(i);
                } else if (ch == null || ch2 == null || ((!ch.equals(pattern.charAt(i))) || (!ch2.equals(s.charAt(i))))) {
                    match = false;
                    break;
                }
            }
            if (match) res.add(s);
        }
        return res;
    }
}
