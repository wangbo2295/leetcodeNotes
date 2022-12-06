package leetcode.dynamicPrograming.subSequence;

import java.util.Arrays;
import java.util.Comparator;

public class No522 {

    //暴力解法
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
        int n = strs.length;
        for (int i = n - 1; i >= 0; i--) {
            if (i > 0 && strs[i].equals(strs[i - 1]))   continue;
            boolean uniqe = true;
            for (int j = i + 1; j < n; j++) {
                int k = 0;
                for (int l = 0; l < strs[j].length(); l++) {
                    if (k == strs[i].length()) break;
                    if (strs[j].charAt(l) == strs[i].charAt(k)) {
                        ++k;
                    }
                }
                if (k == strs[i].length()) {
                    uniqe = false;
                    break;
                }
            }
            if (uniqe) {
                return strs[i].length();
            }
        }
        return -1;
    }
}
