package leetcode.doublePointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串s ，请你返回满足以下条件且出现次数最大的任意子串的出现次数：
 * 子串中不同字母的数目必须小于等于 maxLetters 。
 * 子串的长度必须大于等于minSize 且小于等于maxSize 。
 */
public class No1297 {
    /**
     * 思路：对于子数组个数问题，一般采用枚举结尾元素的做法统计
     * 枚举子数组的结尾元素 r，找到满足条件的最小左边界 l, 对于 (l, r) ~ (r - minsize + 1, r) 均满足题目要求
     * 进一步思考，所有满足条件的字串一定至少包含一个长度为 minsize 的字串也满足条件，所以出现次数最多的字串一定有长度为 minsize 的字串
     * 因此只需要枚举所有长度为 minsize 的字串
     * 将这些字串加入hashMap计数，最后返回最大出现次数的字串即可。
     * 优化：利用hashmap需要每次取子串，虽然最长只有 26，但显然有很多重复计算，可以预先处理 s 的前缀哈希，就可以 O（1）得出hash值，再放入map计数
     */
    long[] hash;
    long[] p;
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        hash = new long[n + 1];
        p = new long[n + 1];
        p[0] = 1;
        long base = 13331;
        for (int i = 0; i < s.length(); i++) {
            hash[i + 1] = hash[i] * base + s.charAt(i) - 'a' + 1;
            p[i + 1] = p[i] * base;
        }
        Map<Long, Integer> map = new HashMap<>();
        int[] cnt = new int[26];
        int diff = 0;
        for (int i = 0; i < minSize - 1; i++) {
            if (++cnt[s.charAt(i) - 'a'] == 1) diff++;
        }
        for (int i = -1, j = minSize - 1; j < n; j++, i++) {
            if (++cnt[s.charAt(j) - 'a'] == 1) diff++;
            if (i >= 0 && --cnt[s.charAt(i) - 'a'] == 0) diff--;
            if (diff <= maxLetters) {
                long hash = get(i + 2, j + 1);
                map.put(hash, map.getOrDefault(hash, 0) + 1);
            }
        }
        int ans = 0;
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            ans = Math.max(ans, entry.getValue());
        }
        return ans;
    }

    public long get(int l, int r) {
        return hash[r] - hash[l - 1] * p[r - l + 1];
    }
}
