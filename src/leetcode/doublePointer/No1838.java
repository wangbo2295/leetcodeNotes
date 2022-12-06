package leetcode.doublePointer;

import java.util.Arrays;

/**
 * 元素的 频数 是该元素在一个数组中出现的次数。
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 */
public class No1838 {
    /**
     * 思路：由题意知，相当于使一些数增加一共不超过 k，使改变后的数组中能够出现最大频数
     * 换个角度想，给定一个数，通过增加其他数不超过 k 次能使得该数的最大频次是多少，所有可能数中的最大的最大频次就是答案
     * 由此想到一种解法：排序后枚举每个数为改变后的数，由于是排好序的，只需在之前的数中进行增加操作使得其变为当前枚举的数
     * 我们只要找到增加 k 步以内最多能通过改变多少个数使得其均变为当前枚举的数即可
     * 换句话说， 对于每个 j ,找到最小的 i 使得 sum(i, j) + k >= (j - i + 1) * nums[j]
     * 上式左边为更改后的子数组和，右边为使得所有数都变为 nums[j] 需要的总数
     * 分析到这已经是一个较为明显的双指针了，我们只需枚举 j 的过程中，不断右移 i 直到满足以上不等式，就找到一个可行的方案
     * 枚举完所有 j 后取所有长度的最大值即可。
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        int ans = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            sum += nums[j];
            while ((long) (j - i + 1) * nums[j] > sum + k) {
                sum -= nums[i++];
            }
            ans = Math.max(j - i + 1, ans);
        }
        return ans;
    }
}
