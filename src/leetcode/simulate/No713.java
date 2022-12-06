package leetcode.simulate;

public class No713 {
    /**
     * 暴力解法
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int multi = 1;
            for (int j = i; j < nums.length; j++) {
                multi *= nums[j];
                if (multi >= k) {
                    break;
                }
                count++;
            }
        }
        return count;
    }

//    public int numSubarrayProductLessThanK2(int[] nums, int k) {
//
//    }
}
