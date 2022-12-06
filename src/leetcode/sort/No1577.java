package leetcode.sort;

import java.util.Arrays;

public class No1577 {
    public int numTriplets(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int count = 0;
        count = find(nums2, nums1, count);
        count = find(nums1, nums2, count);
        return count;
    }

    private int find(int[] nums2, int[] num1, int count) {
        for (int i = 0; i < num1.length; i++) {
            int left = 0, right = nums2.length - 1;
            long x = (long)num1[i] * (long)num1[i];
            while (left < right) {
                if (x > (long)nums2[left] * (long)nums2[right]) {
                    left++;
                }else if (x < (long)nums2[left] * (long)nums2[right]) {
                    right--;
                }else {
                    if (nums2[left] == nums2[right]) {
                        int n = right - left + 1;
                        count += n * (n - 1) / 2;
                        break;
                    }else {
                        int n1 = 1, n2 = 1;
                        while (nums2[left] == nums2[left + 1]) {
                            left++;
                            n1++;
                        }
                        while (nums2[right] == nums2[right - 1]) {
                            right--;
                            n2++;
                        }
                        count += n1 * n2;
                        left++; right--;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        No1577 no1577 = new No1577();
        int[] nums1 = {7,4};
        int[] nums2 = {5,2,8,9};
        no1577.numTriplets(nums1, nums2);
    }
}
