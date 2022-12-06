package leetcode.doublePointer;

/**
 * 给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
 * 请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
 */
public class No1358 {
    /**
     * 思路：双指针
     * 找到子串左端点 i 对应的第一个满足条件的右端点 j ，为最小子串，以该左端点的子串个数为 n - j，
     * 然后依次收缩左端点直至不满足条件，期间的左端点对应的右端点都是 j ，累计 n - j 至答案中
     * 具体做法是，用 cnt 数组记录每个元素的出现次数，diff 变量记录不同元素的个数，如果 ++cnt[c] == 1 说明有一个新元素，diff + 1
     * 如果 --cnt[c] == 0 说明消除了一个元素，diff - 1。
     * 枚举窗口右端点，不断向窗口中累加元素并记录不同元素个数，当 diff == 3 时找到 i 对应的最小 j ，收缩 i 并且将 n - j 加入答案直至diff ！= 3
     */
    public int numberOfSubstrings(String s) {
        int n = s.length(), ans = 0, diff = 0;
        int[] cnt = new int[26];
        for (int i = 0, j = 0; j < n; j++) {
            int c = s.charAt(j) - 'a';
            if (++cnt[c] == 1) diff++;
            while (diff == 3) {
                int v = s.charAt(i++) - 'a';
                if (--cnt[v] == 0) diff--;
                ans += n - j;
            }
        }
        return ans;
    }
}
