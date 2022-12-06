package leetcode.priorityQueue;

import java.util.*;

/**
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * 如果不存在满足条件的子数组，则返回 0 。
 */
public class No1438 {
    /**
     * 解法一：有序集合 + 滑动窗口
     * 利用 TreeSet 维护滑动窗口的最大、最小值，set中保存数组下标
     * 时间复杂度：O（n ^ 2）
     */
    public int longestSubarray(int[] nums, int limit) {
        int ans = 0, n = nums.length;
        TreeSet<Integer> treeSet = new TreeSet<>(Comparator.comparingInt(o->nums[o]));
        for (int i = 0, j = 0; j < n; j++) {
            treeSet.add(j);
            while (nums[treeSet.last()] - nums[treeSet.first()] > limit) {
                if (i == treeSet.last() || i == treeSet.first()) {
                    treeSet.remove(i);
                }
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    /**
     * 解法二：单调队列 + 滑动窗口
     * 用两个单调队列分别维护窗口中的最大、最小值
     * 时间复杂度: O(n)
     */
    public int longestSubarray2(int[] nums, int limit) {
        Deque<Integer> min = new LinkedList<>();
        Deque<Integer> max = new LinkedList<>();
        int ans = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (!min.isEmpty() && nums[min.getLast()] >= nums[i]) min.pollLast();
            while (!max.isEmpty() && nums[max.getLast()] <= nums[i]) max.pollLast();
            min.offerLast(i);
            max.offerLast(i);
            while (!min.isEmpty() && !max.isEmpty() && nums[max.getFirst()] - nums[min.getFirst()] > limit) {
                if (j == min.getFirst()) min.pollFirst();
                if (j == max.getFirst()) max.pollFirst();
                j++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
