package leetcode.sort;

public class No75 {
    public void sortColors(int[] nums) {
        int i = sort(nums, 0, 0);
        sort(nums, i, 1);
    }
    void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    int sort(int[] nums, int start, int target) {
        int i, j, n = nums.length;
        for (i = j = start; j < n; j++) {
            if (nums[j] == target) {
                swap(nums, i, j);
                ++i;
            }
        }
        return i;
    }
}
