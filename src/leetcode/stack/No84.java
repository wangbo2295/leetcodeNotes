package leetcode.stack;

import java.util.Stack;

public class No84 {
    public int largestRectangleArea(int[] heights) {
        Stack<int[]> stack = new Stack<>();
        int maxArea = 0, n = heights.length;
        for (int i = 0; i <= n; i++) {
            int width = 0;
            while (!stack.empty() && (i == n || stack.peek()[1] >= heights[i])) {
                int[] pre = stack.pop();
                width += pre[0];
                maxArea = Math.max(maxArea, pre[1] * width);
            }
            if (i < n)
                stack.push(new int[]{width + 1, heights[i]});
        }
        return maxArea;
    }

    public static void main(String[] args) {
        No84 no84 = new No84();
        int[] heights = {2,1,5,6,2,3};
        no84.largestRectangleArea(heights);
    }
}
