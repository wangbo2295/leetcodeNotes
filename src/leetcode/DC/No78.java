package leetcode.DC;

import java.util.Arrays;
import java.util.Comparator;

public class No78 {
    public int divisorSubstrings(int num, int k) {
        String n = String.valueOf(num);
        int ans = 0;
        for (int i = 0; i < n.length() - k + 1; i++) {
            int j = Integer.parseInt(n.substring(i, i + k));
            if (num != 0 && num % j == 0) {
                ans++;
            }
        }
        return ans;
    }

    public int waysToSplitArray(int[] nums) {
        long sum = 0; int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        long left = 0, right = sum;
        for (int i = 0; i < nums.length - 1; i++) {
            left += nums[i];
            right -= nums[i];
            if (left >= right) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 给你一个二维整数数组tiles，其中tiles[i] = [li, ri]，表示所有在li <= j <= ri之间的每个瓷砖位置 j都被涂成了白色。
     * 同时给你一个整数carpetLen，表示可以放在任何位置的一块毯子。
     * 请你返回使用这块毯子，最多可以盖住多少块瓷砖。
     *
     * 思路：贪心 + 滑动窗口
     * 毯子左端点要和某个瓷砖左端点重合，一定是局部最优
     * 如果左端点在瓷砖中间，那么左移一定会增加一个瓷砖，右边可能会失去一个瓷砖（如果右端点覆盖了瓷砖），
     * 也有可能不会失去瓷砖（右端点没覆盖瓷砖），所以总的结果不会变小。
     * 如果左端点在瓷砖左端点，那么左移左端点一定变小，右端点可能变小可能不变，总体是不优的。
     * 所以左端点跟某个瓷砖左端点重合就是局部最优。
     *
     * 滑动窗口：窗口大小就是毯子大小，维护最右边瓷砖的编号和区间内瓷砖总数sum（不包括最右边覆盖部分瓷砖的那个区间，另外计算），
     * 遍历最左边瓷砖的编号，初始化都为0，遍历过程中判断最右边瓷砖右断点是否超过毯子，如果没超过，就直接整个加入sum, 并将右端点右移
     * 如果超过右边界了，计算部分覆盖的长度并加上sum和max进行比较，然后将左端点右移，sum中减去左边第一个瓷砖区间的长度。
     */
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, Comparator.comparingInt(o -> o[0]));
        int l = 0, r = 0, n = tiles.length;
        int sum = 0, max = 0;
        while (r < n) {
            int rightBound = tiles[l][0] + carpetLen - 1;
            if (tiles[r][1] <= rightBound) {
                sum += tiles[r][1] - tiles[r][0] + 1;
                rightBound++;
                max = Math.max(max, sum);
            }else {
                if (rightBound >= tiles[r][0]) {
                    max = Math.max(max, sum + rightBound - tiles[r][0] + 1);
                }
                sum -= tiles[l][1] - tiles[l][0] + 1;
                l++;
            }
        }
        return max;
    }

    public int largestVariance(String s) {
        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        int minc = -1, maxc = -1, min = Integer.MAX_VALUE, max = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] > max) {
                max = map[i];
                maxc = i;
            }
            if (map[i] != 0 && map[i] < min) {
                min = map[i];
                minc = i;
            }
        }
        int[] nums = new int[s.length()];
        for (int i = 0; i < nums.length; i++) {
            if (s.charAt(i) == minc + 'a') {
                nums[i] = -1;
            }
            if (s.charAt(i) == maxc + 'a') {
                nums[i] = 1;
            }
        }
        int sum = 0, left = 0, lastMin = -1, ans = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == -1)  lastMin = right;
            sum += nums[right];
            if (sum < 0) {
                left = right + 1;
                sum = 0;
            }
            if (sum > ans && lastMin >= left) {
                ans = sum;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        No78 no78 = new No78();
        no78.largestVariance("lripaa");
    }
}
