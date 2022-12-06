package leetcode.doublePointer;

/**
 * 有一个只含有'Q', 'W', 'E','R'四种字符，且长度为 n的字符串。
 * 假如在该字符串中，这四个字符都恰好出现n/4次，那么它就是一个「平衡字符串」。
 * 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
 * 你可以用和「待替换子串」长度相同的任何 其他字符串来完成替换。
 * 请返回待替换子串的最小可能长度。
 * 如果原字符串自身就是一个平衡字符串，则返回 0。
 */
public class No1234 {
    /**
     * 滑动窗口
     * 对于不满足的字符串，一定是有部分字符个数大于 n / 4，另一部分小于 n / 4
     * 要替换一个字串使得每个字符都等于 n / 4，就是要先删除一个字串使得每个字符个数都小于等于 n / 4，再根据每个字符达到 n / 4 的个数进行补齐即可
     * 那么问题转化为寻找一个最小字串，使得删除该字串后每个字符个数不超过 n / 4
     * 我们可以枚举被删除字串的右端点，如果删除该字串能满足要求，就尝试少删除一个（右移左端点），直到不满足为止，过程中不断更新最小字串长度
     * 为了方便计算频次，可以先统计好每个字符的个数，在窗口新增元素时减去频次，减小元素时增加频次。
     */
    public int balancedString(String s) {
        int n = s.length(), ans = n;
        int[] hash = new int[128];
        hash['Q'] = 0; hash['W'] = 1; hash['E'] = 2; hash['R'] = 3;
        int[] cnt = new int[4];
        for (char c : s.toCharArray()) cnt[hash[c]]++;
        if (check(cnt, n))  return 0;
        for (int i = 0, j = 0; j < n; j++) {
            char c = s.charAt(j);
            cnt[hash[c]]--;
            while (check(cnt, n) && i < j) {
                ans = Math.min(ans, j - i + 1);
                char v = s.charAt(i);
                cnt[hash[v]]++;
                i++;
            }
        }
        return ans;
    }

    public boolean check(int[] cnt, int n) {
        for (int i = 0; i < 4; i++) {
            if (cnt[i] > n / 4) return false;
        }
        return true;
    }
}
