package leetcode.practice;

import java.util.Arrays;

public class No821 {
    /**
     * 对每个c往两边扫描，多次被扫描到的字符取最小值
     * @param s
     * @param c
     * @return
     */
    public int[] shortestToChar(String s, char c) {
        int[] res = new int[s.length()];
        Arrays.fill(res, Integer.MAX_VALUE);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                res[i] = 0;
                int left = i - 1, right = i + 1;
                while (left >= 0 && s.charAt(left) != c) {
                    res[left] = Math.min(res[left], i - left);
                    left--;
                }
                while (right < s.length() && s.charAt(right) != c) {
                    res[right] = Math.min(res[right], right - i);
                    right++;
                }
            }
        }
        return res;
    }

    /**
     * 从左往右扫描一次，从右往左扫描一次
     * @param s
     * @param c
     * @return
     */
    public int[] shortestToChar2(String s, char c) {
        int[] res = new int[s.length()];
        int distance = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) distance = 0;
            res[i] = distance++;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == c) distance = 0;
            res[i] = Math.min(res[i], distance++);
        }
        return res;
    }

    public static void main(String[] args) {
        No821 no821 = new No821();
        int[] ints = no821.shortestToChar("loveleetcode", 'e');
        for (int i: ints) {
            System.out.print(i + ",");
        }
    }
}
