package leetcode.sort;

public class No215 {
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length, nums.length + 1 - k);    //寻找第 n + 1 - k 小的数
    }

    public int quickSort(int[] nums, int l, int r, int k) {
        int pivot = nums[l], i, j;
        for (i = l, j = l + 1; j < r; j++) {
            if (nums[j] <= pivot) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, l, i);
        if (k > i - l + 1) {
            return quickSort(nums, i + 1, r, k - (i - l + 1));
        } else if (k < i - l + 1) {
            return quickSort(nums, l, i, k);
        } else {
            return nums[i];
        }
    }
    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
