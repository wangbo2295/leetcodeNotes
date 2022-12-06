package acwing.binarySearch;

/**
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * 请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 */
public class SingleNonDuplicate {
    /**
     * 观察两个案例：
     * 1：[1,1,2,3,3,4,4,8,8]
     * 2：[3,3,7,7,10,11,11]
     * 第一个案例取中点时，左右区间长度为偶数，这时只要看中点跟哪边相等就取哪边，因为包含中点后为奇数个数，必定有一个单独的数
     * 第二个案例取中点时，左右区间长度为奇数，这时只要看中点跟哪边相等就舍弃哪边，因为舍弃掉的奇数个加上中点为偶数个，另一半则为奇数个，一定包含一个单独的数
     */
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int m = l + r >> 1;
            if (nums[m] == nums[m - 1]) {
                if ((m - l & 1) == 0) {
                    r = m;
                } else {
                    l = m + 1;
                }
            } else if(nums[m] == nums[m + 1]) {
                if ((m - l & 1) == 0) {
                    l = m;
                } else {
                    r = m - 1;
                }
            } else {
                return nums[m];
            }
        }
        return nums[l];
    }
}
