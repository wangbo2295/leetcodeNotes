package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 用map 将nums2中的下一个更大的数映射
 */
public class No496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] next = new int[10001];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.empty() && stack.peek() < nums2[i]) {
                Integer pop = stack.pop();
                next[pop] = nums2[i];
            }
            stack.push(nums2[i]);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = next[nums1[i]];
        }
        return res;
    }

    public static void main(String[] args) {
        No496 no496 = new No496();
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        no496.nextGreaterElement(nums1, nums2);
    }
}
