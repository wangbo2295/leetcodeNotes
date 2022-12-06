package leetcode.dynamicPrograming.subSequence;

/**
 * 子问题最优解：
 * if MSA(i-1) > 0
 *      MSA(i) <-- MSA(i-1) + nums[i]
 * else
 *      MSA(i) <-- nums[i]
 * 以动态规划的角度看，当子问题最优解无法推出问题最优解时，将当前问题最优解进行初始化
 * 本题中，初始化为nums[i]
 */
public class No53 {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] MSA = new int[len];
        MSA[0] = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < len; i++) {
            if (MSA[i - 1] > 0) {   //能由子问题最优解推出
                MSA[i] = MSA[i - 1] + nums[i];
            }else {     //不能由子问题最优解推出，初始化
                MSA[i] = nums[i];
            }
            maxSum = Math.max(maxSum, MSA[i]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        No53 no53 = new No53();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int i = no53.maxSubArray(nums);
        System.out.println(i);
    }
}
