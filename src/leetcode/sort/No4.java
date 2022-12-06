package leetcode.sort;

import java.util.Arrays;

public class No4 {
    /**
     * 合并数组 + 快排，但不符合题目要求的时间复杂度。
     * 另外观察到两个数组是升序的，这个条件没用到
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merge = new int[nums1.length + nums2.length];
        int len = merge.length;
        System.arraycopy(nums1, 0, merge, 0, nums1.length);
        System.arraycopy(nums2, 0, merge, nums1.length, nums2.length);
        rank(merge, len / 2, 0, len - 1);
        double res;
        if (len % 2 == 0) {
            double m = merge[len/2], n = merge[len/2 - 1];
            res = (m + n) / 2;
        }else {
            res =  merge[len / 2];
        }
        return res;
    }

    public void rank(int[] nums, int rank, int start, int end) {
        if (start >= end) return;
        int pivot = nums[start];
        int i = start, j = start + 1;
        while (j <= end) {
            if (nums[j] < pivot) {
                swap(nums, ++i, j);
            }
            j++;
        }
        swap(nums, i, start);
        if (i < rank){
            rank(nums, rank, i + 1, end);
        }else if (i > rank){
            rank(nums, rank, start, i - 1);
        }else if ((nums.length & 1) == 0) {
            rank(nums, rank - 1, start, i - 1);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }

    /**
     * 双指针，类似于归并排序中的合并数组
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int p1 = 0, p2 = 0;
        int rank = 0;
        int cur = 0, pre = 0;
        double res;
        while (rank <= (nums1.length + nums2.length) / 2) {
            pre = cur;
            if (p1 == nums1.length) {
                cur = nums2[p2];
                p2++;
            }else if (p2 == nums2.length) {
                cur = nums1[p1];
                p1++;
            }else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1];
                p1++;
            }else {
                cur = nums2[p2];
                p2++;
            }
            rank++;
        }
        res = cur;
        if ((nums1.length + nums2.length) % 2 == 0) {
            res = ((double) pre + (double) cur) / 2;
        }
        return res;
    }

    /**
     * todo O(log(m+n))
     * @param args
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {

        return 0;
    }
    public static void main(String[] args) {
        No4 no4 = new No4();
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        no4.findMedianSortedArrays2(nums1,nums2);
    }
}
