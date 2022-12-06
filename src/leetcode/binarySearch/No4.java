package leetcode.binarySearch;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 */
public class No4 {
    /**
     * 二分查找
     * 对于两个有序数组，要找出第k大的数，可以先分别找出k/2大的数
     * 即nums1[k/2 - 1]和nums2[k/2-1]
     * 假设nums1[k/2-1] < nums2[k/2-1]
     * 那么nums1的前k/2-1个数比nums1[k/2-1]小、nums2的前k/2-1个数都比nums2[k/2-1]小
     * 最多有k - 2个数比nums1[k/2-1]小（nums2的前k/2-1个数不一定比nums1[k/2-1]小）
     * 至此，nums1[k/2-1]不可能是第k小的数，最多为第k-1小的数，所以排除。同时可以排除nums1[k/2-1]之前的所有数
     * 由于排除了k/2个数，k /= 2
     * 反之，排除nums2的前k-1个数
     * 对于相等的情况，可以归到上述两种情况之一处理。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int p1 = 0, p2 = 0;
        int m = nums1.length, n = nums2.length;
        int k = m + n >> 1;
        while (k > 1) {
            if (p1 >= m) {

            }
            if (nums1[p1 + k / 2 - 1] <= nums2[p2 + k / 2 - 1]) {
                p1 += k / 2;
            } else {
                p2 += k / 2;
            }
            k /= 2;
        }
        return Math.min(nums1[p1], nums2[p2]);
    }
}
