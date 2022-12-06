package leetcode.DC;

import java.util.LinkedList;
import java.util.Queue;

public class No75 {
    public int minBitFlips(int start, int goal) {
        int cnt = 0;
        while (start != 0 || goal != 0) {
            if (((start ^ goal) & 1) == 1)  ++cnt;
            start >>= 1;
            goal >>= 1;
        }
        return cnt;
    }

    public int triangularSum(int[] nums) {
        Queue<Integer> queue = new LinkedList<>();
        for (int num : nums) {
            queue.offer(num);
        }
        while (queue.size() > 1) {
            int size = queue.size();
            for (int i = 0; i < size - 1; i++) {
                queue.offer((queue.poll() + queue.peek()) % 10);
            }
            queue.poll();
        }
        return queue.poll();
    }

    /**
     * 前缀和
     * @param s
     * @return
     */
    public long numberOfWays(String s) {
        int n = s.length();
        int[][] presum = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '0') {
                presum[i][0] = presum[i - 1][0] + 1;
                presum[i][1] = presum[i - 1][1];
            } else {
                presum[i][1] = presum[i - 1][1] + 1;
                presum[i][0] = presum[i - 1][0];
            }
        }
        long ans = 0;
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                ans += (long) presum[i][1] * (presum[n][1] - presum[i + 1][1]);
            } else{
                ans += (long) presum[i][0] * (presum[n][0] - presum[i + 1][0]);
            }
        }
        return ans;
    }
}
