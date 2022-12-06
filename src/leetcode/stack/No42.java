package leetcode.stack;

import java.util.Stack;

public class No42 {
    public int trap(int[] height) {
        int n = height.length, ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && height[stack.peek()] < height[i]) {
                int low = stack.pop();
                int left = stack.peek();
                int add = (Math.min(height[left], height[i]) - height[low]) * (i - left - 1);
                ans += add;
                System.out.println(left + "-" + low + "-" + i + ", " + add);
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        No42 no42 = new No42();
        no42.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
    }
}
