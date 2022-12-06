package leetcode.sort;

import java.util.Arrays;

public class No719 {
    public int smallestDistancePair(int[] nums, int k) {
        int[] bottle = new int[1_000_001];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                bottle[Math.abs(nums[i] - nums[j])]++;
            }
        }
        int ans = -1;
        for (int i = 0; k > 0; i++) {
            if (bottle[i] > 0) {
                k -= bottle[i];
                ans = i;
            }
        }
        return ans;
    }

    /**
     * 排序 + 二分
     * 数对差值的绝对值一定在(0, max - min) 之间，可以先将数组排序
     * 然后对差值进行二分，left = 0， right = max - min
     * 计算有多少个数对差值小于等于mid，如果cnt <= k, 则left = mid，否则right = mid
     * 那么如何计算数组中数对差小于等于mid的个数呢？ 二分
     * 对于一个以下标j结尾的子数组，数对差小于等于mid的个数为j - i，其中i为第一个满足nums[j] - nums[i] > mid的下标
     * 枚举每个下标j，计算总和即可得到整个数组中数对差小于等于mid的个数
     */
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = -1, right = nums[n - 1] - nums[0] + 1;
        while (left + 1 < right) {
            int mid = left + (right - left >> 1);
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                cnt += j - binarySearch(nums, j + 1, nums[j] - mid);
            }
            if (cnt <= k) {
                left = cnt;
            } else {
                right = cnt;
            }
        }
        return left;
    }

    int binarySearch(int[] nums, int right, int target) {
        int left = -1;
        while (left + 1 < right) {
            int mid = left + (right - left >> 1);
            if (nums[mid] > target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
