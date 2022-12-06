package leetcode.binarySearch;

import java.util.Arrays;

public class No611 {
    public int triangleNumber(int[] nums) {
        int cnt = 0, n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 1; j++) {
                int l = j, r = n;
                while (l + 1 < r) {
                    int m = l + r >> 1;
                    if (nums[i] + nums[j] <= nums[m]) {
                        l = m;
                    } else {
                        r = m;
                    }
                }
                cnt += l - j;
            }
        }
        return cnt;
    }
}
