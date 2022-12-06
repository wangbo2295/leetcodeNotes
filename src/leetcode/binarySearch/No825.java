package leetcode.binarySearch;

import java.util.Arrays;

public class No825 {
    /**
     * 二分查找
     * @param ages
     * @return
     */
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int ans = 0, cnt = 1;
        for (int i = 0; i < ages.length; i++) {
            if (ages[i] <= 14) continue;
            if (i != ages.length - 1 && ages[i] == ages[i + 1]) {
                cnt++;
                continue;
            } else {
                ans += cnt * binarySearch(ages, 0, i);
                cnt = 1;
            }
        }
        return ans;
    }

    public int binarySearch(int[] ages, int left, int right) {
        int start = left - 1, end = right + 1;
        while (start + 1 < end) {
            int mid = start + (end - start >> 1);
            if (ages[mid] <= ages[right] / 2 + 7) {
                start = mid;
            }else {
                end = mid;
            }
        }
        return right - end;
    }

    /**
     * 双指针
     * 排序后逐个遍历年龄，维护滑动窗口使得left指向最小的满足right条件的下标，每个right计算一次个数加到结果中
     * 年龄相等的情况需要单独处理，因为前面的可以向后面的发送好友请求。
     * 用一个cnt变量记录当前相等年龄个数，统计相等年龄中最后一个的请求个数再乘以cnt加入到结果集中
     * @param ages
     * @return
     */
    public int numFriendRequests2(int[] ages) {
        Arrays.sort(ages);
        int left = 0, ans = 0, cnt = 1;
        for (int right = 1; right < ages.length; right++) {
            if (ages[right] <= 14)  continue;
            while (ages[left] <= ages[right] / 2 + 7) {
                left++;
            }
            if (right < ages.length - 1 && ages[right] == ages[right + 1]) {
                cnt++;
            } else {
                ans += (right - left) * cnt;
                cnt = 1;
            }
        }
        return ans;
    }

    /**
     * 排序 + 前缀和
     * 注意到年龄最大为120，可以统计每个年龄以下的有多少个
     * 那么查询ages[i]时，presum[ages[i]] - presum[ages[i] / 2 + 7] - 1 即为请求个数，这样就无需单独处理重复年龄情况
     * @param ages
     * @return
     */
    public int numFriendRequests3(int[] ages) {
        Arrays.sort(ages);
        int[] cnt = new int[121];
        for (int age : ages) {
            cnt[age]++;
        }
        int[] presum = new int[121];
        for (int i = 1; i < presum.length; i++) {
            presum[i] = presum[i - 1] + cnt[i];
        }
        int ans = 0;
        for (int i = 0; i < ages.length; i++) {
            if (ages[i] <= 14)  continue;
            ans += presum[ages[i]] - presum[ages[i] / 2 + 7] - 1;
        }
        return ans;
    }
}
