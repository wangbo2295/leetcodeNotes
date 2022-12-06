package leetcode.DC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No80 {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8)  return false;
        int[] map = new int[4];
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (i > 0 && password.charAt(i - 1) == c)  return false;
            if (c >= 'a' && c <= 'z') {
                map[0]++;
            }
            if (c >= 'A' && c <= 'Z') {
                map[1]++;
            }
            if (c >= '0' && c <= '9') {
                map[2]++;
            }
            if (c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '^'
                || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '+') {
                map[3]++;
            }
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 0)    return false;
        }
        return true;
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] res = new int[spells.length];
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            int l = -1, r = potions.length;
            while (l + 1 < r) {
                int mid = l + (r - l >> 1);
                if ((long) spells[i] * potions[mid] >= success) {
                    r = mid;
                }else {
                    l = mid;
                }
            }
            res[i] = spells.length - r;
        }
        return res;
    }


    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        boolean[][] map = new boolean[128][128];
        for (int i = 0; i < mappings.length; i++) {
            map[mappings[i][0]][mappings[i][1]] = true;
        }

        for (int i = 0; i <= s.length() - sub.length(); i++) {
            boolean match = true;
            for (int j = 0; j < sub.length(); j++) {
                char cs = s.charAt(i + j), cb = sub.charAt(j);
                if (cs != cb && !map[cb][cs]) {
                    match = false;
                    break;
                }
            }
            if (match)  return true;
        }
        return false;
    }



    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        int cnt = 0, i = 0, j = 0;
        for (; j < n; j++) {
            long points = (long) (j - i + 1) * (presum[j + 1] - presum[i]);
            if (points >= k) {
                cnt += j - i++;
            }
        }
        if ((long) (presum[n] - presum[i]) * (n - i) < k) {
            cnt += (1 + n - i) * (n - i) / 2;
        }
        return cnt;
    }

    public static void main(String[] args) {
        No80 no80 = new No80();
        int[] nums = {1,1,1};
        no80.countSubarrays(nums, 5);
    }
}
