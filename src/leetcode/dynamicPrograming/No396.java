package leetcode.dynamicPrograming;

/**
 * 旋转函数
 */
public class No396 {
    public int maxRotateFunction(int[] nums) {
        int max = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            max += i * nums[i];
            sum += nums[i];
        }
        for (int i = nums.length - 1; i > 0; i--) {
            max = Math.max(max, max + sum - nums.length * nums[i]);
        }
        return max;
    }
}
