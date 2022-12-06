package leetcode.array;

import java.util.Arrays;

/**
 * 整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 */
public class No31 {
    /**
     * 思路：
     * 从数组结尾找到最长递减的连续子数组，和前面一个数组成起来重新排列
     * 要求是，前面一个数换成比其大的最小数，后面的按升序排列
     * 如果该最长递减子数组就是本身，则按升序排列
     */
    public void nextPermutation(int[] nums) {
        if (nums.length < 2)    return;
        int index = nums.length - 2;
        while (index >= 0 && nums[index] >= nums[1 + index]) index--;
        if (index < 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        rearrange(nums, index, nums.length - 1);
        reverse(nums, index + 1, nums.length - 1);
    }

    public void rearrange(int[] nums, int left, int right) {
        int index = right;
        while (nums[index] <= nums[left])   index--;
        swap(nums, left, index);
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;right--;
        }
    }
}
