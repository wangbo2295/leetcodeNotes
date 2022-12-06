package leetcode.DC;

import java.util.*;

public class No86 {
    public boolean findSubarrays(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (map.containsKey(nums[i] + nums[i + 1])) return true;
            map.put(nums[i] + nums[i + 1], 1);
        }
        return false;
    }

    public boolean isStrictlyPalindromic(int n) {
        for (int i = 2; i < n - 1; i++) {
            if (!check(n, i)) return false;
        }
        return true;
    }

    private boolean check(int n, int b) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % b);
            n /= b;
        }
        return sb.equals(new StringBuilder(sb.reverse()));
    }

    public int maximumRows(int[][] mat, int cols) {
        int m = mat.length, n = mat[0].length, ans = 0;
        for (int i = 0; i < 1 << n; i++) {
            if (bitCount(i) > cols) continue;
            int cnt = 0;
            for (int j = 0; j < m; j++) {
                if (check(mat[j], i)) ++cnt;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    public int bitCount(int num) {
        int cnt = 0;
        for (; num > 0; num -= num & -num, ++cnt);
        return cnt;
    }

    public boolean check(int[] row, int p) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1 && (p >> i & 1) != 1) return false;
        }
        return true;
    }

    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        Deque<Integer> deque = new LinkedList<>();
        int n = chargeTimes.length, ans = 0;
        long sum = 0;
        for (int i = 0, j = 0; j < n; j++) {
            while (!deque.isEmpty() && chargeTimes[deque.peekLast()] <= chargeTimes[j]) deque.pollLast();
            deque.addLast(j);
            sum += runningCosts[j];
            while (!deque.isEmpty() && (long) chargeTimes[deque.getFirst()] + sum * (j - i + 1) > budget) {
                if (deque.getFirst() == i) {
                    deque.pollFirst();
                }
                sum -= runningCosts[i++];
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
