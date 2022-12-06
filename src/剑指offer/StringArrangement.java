package 剑指offer;

import java.util.*;

public class StringArrangement {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    boolean[] used;
    public String[] permutation(String s) {
        used = new boolean[s.length()];
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        s = String.copyValueOf(chars);
        arrange(s, 0);
        return res.toArray(new String[0]);
    }

    private void arrange(String s, int k) {
        if (k == s.length()) {
            res.add(new String(sb));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (used[i] || i > 0 && !used[i - 1] && s.charAt(i) == s.charAt(i - 1)) continue;
            sb.append(s.charAt(i));
            used[i] = true;
            arrange(s, k + 1);
            used[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
