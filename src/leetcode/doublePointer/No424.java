package leetcode.doublePointer;

/**
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
 */
public class No424 {
    /**
     * 解法一：二分
     * 如果 s 中存在长度为 m 的子串，满足替换不超过 k 个字符后全变为相同字符，那么对任意小于 m 的长度，也存在这样的子串
     * 这是因为，如果将子串长度减小，众数字符可能减1也可能不变，而数组长度一定减1，原来 cnt(c) + k >= m，最坏情况下两边同时减1，不等式不变
     * 因此可以二分长度，将答案转化为判定 s 中是否存在满足长度为 m 的子串经替换不超过 k 次后全部相同。
     * 具体地，可以预先处理每个字符的前缀和，检查时对所有长度为 m 的子串中的每一个字符，如果 cnt(c) + k >= m 即满足要求。
     */
    int n;
    int[][] cnt;
    public int characterReplacement2(String s, int k) {
        n = s.length();
        cnt = new int[26][n + 1];
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';
            cnt[c][i + 1] = 1;
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= n; j++)    cnt[i][j] += cnt[i][j - 1];
        }
        int l = 0, r = s.length() + 1;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (check(k, m)) l = m;
            else r = m;
        }
        return l;
    }

    public boolean check(int k, int m) {
        for (int i = m; i <= n; i++) {
            for (int j = 0; j < 26; j++) {
                if (cnt[j][i] - cnt[j][i - m] + k >= m) return true;
            }
        }
        return false;
    }

    /**
     * 解法二：双指针
     * 由于要求最大窗口，而窗口的大小跟窗口内多数元素是成正比的，那么我们可以从小到大不断扩大窗口
     * 从 0 开始右移右指针，我们维护窗口内每个元素的出现次数以及窗口元素最大的出现次数
     * 如果右移右指针后依然满足 max + k >= r - l + 1, 那么左指针无需移动，这时窗口扩大了
     * 如果右移右指针后不满足 max + k >= r - l + 1，说明当前多数元素已不能满足窗口特性，因为就算之后再补上该多数元素，窗口大小也一起增加了，依然不满足
     * 所以我们要左移左指针，更新元素出现次数，注意这里只需移动一次左指针，保持窗口大小不变，这就是目前找到的最大窗口
     * 在继续右移右指针的途中，如果窗口依然不满足，则左右指针是同时向右移动的，即窗口平移，直到找到窗口内某个多数元素比 max 更大，此时可以继续更新窗口大小，即右移右指针而不右移左指针
     */
    public int characterReplacement(String s, int k) {
        int[] cnt = new int[26];
        int max = 0, i, j;
        for (i = 0, j = 0; j < s.length(); j++) {
            int c = s.charAt(j) - 'A';
            max = Math.max(max, ++cnt[c]);
            if (max + k < j - i + 1) {
                cnt[s.charAt(i++)]--;
            }
        }
        return j - i;
    }
}
