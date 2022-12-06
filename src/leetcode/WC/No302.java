package leetcode.WC;

import java.util.*;

public class No302 {

    public int[] numberOfPairs(int[] nums) {
        int[] map = new int[101];
        int[] ans = {0, 0};
        for (int i : nums) {
            map[i]++;
            ans[1]++;
        }
        for (int i = 0; i < map.length; i++) {
            ans[0] += map[i] >> 1;
            ans[1] -= map[i] >> 1 << 1;
        }
        return ans;
    }

    public int maximumSum(int[] nums) {
        int n = nums.length;
        int[][] digits = new int[n][2];
        for (int i = 0; i < n; i++) {
            int sum = 0, num = nums[i];
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            digits[i][0] = nums[i];
            digits[i][1] = sum;
        }
        int max = -1;
        Arrays.sort(digits, Comparator.comparingInt((int[] o) -> o[1]).thenComparingInt(o -> o[0]));
        for (int i = 1; i < n; i++) {
            if (digits[i][1] == digits[i - 1][1]) {
                max = Math.max(max, digits[i][0] + digits[i - 1][0]);
            }
        }
        return max;
    }

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length, m = nums[0].length();
        int[] table = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            String min = nums[0].substring(m - i);
            int minidx = 0;
            for (int j = 1; j < n; j++) {
                String cur = nums[j].substring(m - i);
                if (compareString(cur, min) < 0) {
                    min = cur;
                    minidx = j;
                }
            }
            table[i] = minidx;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = table[queries[i][1]];
        }
        return ans;
    }

    int compareString(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) > s2.charAt(i)) {
                return 1;
            } else if (s1.charAt(i) < s2.charAt(i)) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Integer[] integers = list.stream().distinct().toArray(Integer[]::new);
        int[] res = new int[integers.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = integers[i];
        }
    }
}
