package leetcode.doublePointer;

import java.util.LinkedList;
import java.util.Queue;

public class No1004 {
    public int longestOnes(int[] nums, int k) {
        Queue<Integer> queue = new LinkedList<>();
        int max = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            if (nums[j] == 0) {
                queue.offer(j);
                --k;
            }
            if (k < 0) {
                i = queue.poll() + 1;
                ++k;
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
}
