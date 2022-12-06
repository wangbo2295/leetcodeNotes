package leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
 */
public class No128 {
    /**
     * 思路：
     * 1、对于每个数x，尝试匹配x + 1， x + 2，。。。，x + y，所有y中最大的便是结果
     * 2、对于x + 1是否存在，可以用一个hash_table保存每个数，查找效率就变为O（1）了
     * 3、但是对于每个数还是要匹配n-1次，总的时间复杂度还是O（n2），如何进一步优化？
     * 4、实际上，只需要从一个连续序列的第一个数开始匹配，如果不是第一个数，就不进行匹配，那么如何判断是否为第一个数？
     * 5、对于数x，只需判断x-1是否存在，如果不存在，则为第一个数，如果存在则不为第一个数，跳过
     * 6、本题有重复元素，可以去重进一步优化，所以采用HashSet
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (hashSet.contains(num - 1)) continue;
            while (hashSet.contains(num)) {
                num++;
            }
            ans = Math.max(ans, num - nums[i]);
        }
        return ans;
    }
}

