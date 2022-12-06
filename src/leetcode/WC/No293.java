package leetcode.WC;

import java.util.*;

public class No293 {
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        int[][] map = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) {
                map[i][c - 'a']++;
            }
        }
        List<String> res = new ArrayList<>();
        int cur = 0;
        for (int i = 1; i < n; i++) {
            boolean isSame = true;
            for (int j = 0; j < 26; j++) {
                if (map[cur][j] != map[i][j]) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) {
                res.add(words[cur]);
                cur = i;
            }
        }
        res.add(words[cur]);
        return res;
    }

    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = special[0] - bottom;
        for (int i = 1; i < special.length; i++) {
            max = Math.max(special[i] - special[i - 1] - 1, max);
        }
        max = Math.max(max, top - special[special.length - 1]);
        return max;
    }


    /**
     * 对于每一位，统计该位不为0的数的个数，取最大值
     * @param candidates
     * @return
     */
    public int largestCombination(int[] candidates) {
        int ans = 0;
        for (int i = 0; i < 30; i++) {
            int cnt = 0;
            for (int num: candidates) {
                if ((num << i & 1) == 1) cnt++;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

}




