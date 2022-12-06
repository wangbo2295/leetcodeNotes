package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。
 * 请你找出所有出现 两次 的整数，并以数组形式返回。
 *
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 */
public class No442 {
    /**
     * 该算法巧妙之处：原地修改数组，对于出现两次的数，其对应下标会被修改两次，以此为标识，下标对应的就是出现两次的数
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int num: nums) {
            int index = (num - 1) % n;  //这里一定要取余，因为是原地修改，加n后会数组越界
            nums[index] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 2 * n) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
