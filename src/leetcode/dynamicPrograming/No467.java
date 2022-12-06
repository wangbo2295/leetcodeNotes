package leetcode.dynamicPrograming;

import java.util.HashMap;
import java.util.Map;

/**
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以s 看起来是这样的：
 *
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 现在给定另一个字符串 p 。返回s 中唯一 的 p 的 非空子串的数量。
 */
public class No467 {
    /**
     * 思路: CV
     * @param p
     * @return
     */
    public int findSubstringInWraproundString(String p) {
        int ans = 0;
        int[] map = new int[26];
        int len = 1;
        for (int i = 0; i < p.length(); i++) {
            char cur = p.charAt(i), pre = i > 0 ? p.charAt(i - 1) : cur;
            if (cur - pre == 1 || cur - pre == -25) {
                len++;
            }else {
                len = 1;
            }
            map[cur - 'a'] = Math.max(map[cur - 'a'], len);
        }
        for (int n: map) ans += n;
        return ans;
    }
}
