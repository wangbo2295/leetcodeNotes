package leetcode.datastructure;

import java.util.*;

public class No862 {
    public int shortestSubarray(int[] nums, int k) {
        Deque<Integer> dequeue = new LinkedList<>();
        int n = nums.length;
        long[] presum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            presum[i] = presum[i - 1] + (long)nums[i - 1];
        }
        int min = n + 1;
        for (int i = 0; i <= n; i++) {
            while (!dequeue.isEmpty() && presum[dequeue.getLast()] > presum[i]) dequeue.pollLast();
            while (!dequeue.isEmpty() && presum[dequeue.getFirst()] + k <= presum[i])    {
                min = Math.min(min, i - dequeue.removeFirst());
            }
            dequeue.addLast(i);
        }
        return min == n + 1 ? -1 : min;
    }
}
