package leetcode.array;

public class No80 {
    public int removeDuplicates(int[] nums) {
        int i = 2, j = 2, cnt = 1, n = nums.length;
        for (; j < n; j++) {
            if (nums[j] > nums[i - 2])
                nums[i++] = nums[j];
        }
        return i;
    }
}
