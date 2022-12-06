package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class No436 {
    public int[] findRightInterval(int[][] intervals) {
        int[] res = new int[intervals.length];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < intervals.length; i++) {
            while (!stack.empty() && intervals[stack.peek()][1] < intervals[i][0]) {
                res[stack.pop()] = i;
            }
            stack.push(i);
        }
        return res;
    }
}
