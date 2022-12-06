package leetcode.doublePointer;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组nums 和一个整数 k。如果某个连续子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 请返回这个数组中 「优美子数组」 的数目。
 */
public class No1248 {
    /**
     * 当有一个包含 k 个奇数的子数组时，如何计算有多少个子数组也满足？
     * 对于相同的连续 k 个奇数，我们取其最长的子数组
     * 令左边界为 l (开区间，即前一个奇数的下标)，第一个奇数下标为 i，最后一个奇数下标为 j，右边界为 r (开区间)
     * 那么这个子数组中一共有 (i - l) * (r - j) 个子数组满足要求
     * 如何得来？枚举每一个满足要求的子数组右边界，从 j 开始，有(l, j), (l + 1, j) ... (i, j) i - l 个子数组
     * 而对于 j ~ (r - 1), 有 r - j 个这样的右边界，所以一共是 (i - l) * (r - j) 个子数组
     * 为了方便计算，我们在奇数下标表中开头加上 -1，结尾加上 n，从 1 开始移动大小为 k 的窗口，不断累加答案即可
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        List<Integer> ids = new ArrayList<>();
        ids.add(-1);
        for (int i = 0; i < n; i++) if ((nums[i] & 1) == 1) ids.add(i);
        ids.add(n);
        int ans = 0;
        for (int i = 1, j = k; j < ids.size() - 1; i++, j++) {
            ans += (ids.get(i) - ids.get(i - 1)) * (ids.get(j + 1) - ids.get(j));
        }
        return ans;
    }
}
