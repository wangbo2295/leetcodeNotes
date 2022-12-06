package leetcode.dynamicPrograming.rob;

/**
 * 环形打家劫舍
 * 因为第一个和最后一个不能同时偷，所以考虑第一个的时候不考虑最后一个，考虑最后一个的时候不考虑第一个
 */
public class No213 {


    public int rob(int[] nums) {
        if (nums.length == 1)   return nums[0];
        int stole = nums[0], keep = 0;
        int max;
        for (int i = 1; i < nums.length - 1; i++) {
            int t = keep;
            keep = Math.max(stole, keep);
            stole = nums[i] + t;
        }
        max = Math.max(keep, stole);
        keep = 0; stole = nums[1];
        for (int i = 2; i < nums.length; i++) {
            int t = keep;
            keep = Math.max(stole, keep);
            stole = nums[i] + t;
        }
        max = Math.max(Math.max(keep, stole), max);
        return max;
    }
}
