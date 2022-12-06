package leetcode.doublePointer;

/**
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 * 给你一个字符串text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 */
public class No1156 {
    /**
     * 解法一：二分 （因为没想出滑动窗口）
     * 对于每个字符，我们计算能满足只有一个不同字符的最右边界
     * 那么该字符可以作为多数元素或少数元素两种情况，计算出两种情况下的边界，取最大值即为当前字符能构成的最长重复字串
     * 为了方便计算，我们预处理每个字符的次数前缀和，要计算在某个区间出现多少次只需前缀和差分即可
     * 对于作为多数元素的情况，我们二分其最右边界，记 tot 为该元素在该区间出现的次数， len 为区间长度
     * 那么当 tot == len 或 tot + 1 == len && tot < cnt[c][n - 1] (这表示有一个不同元素且主元还有剩余可用来替换不同元素)
     * 时，向右扩展边界，否则向左扩展边界。
     * 这样二分的正确性在于：
     * 对于固定的左边界，右边界向右扩展的过程中，数组长度 - 主元频次 是单调递减的，因为每向右移一位，数组长度一定加一，而主元频次不一定加一
     * 对于不作为多数元素的情况，记 tot 为该元素在该区间出现的次数， len 为区间长度， totv 为该元素下一个元素的频次
     * 我们要满足一下条件：
     * tot == 1 && tot + totv == len && totv < cnt[v][n - 1]
     * 这表示非主元只能出现一次，且其余元素必须相同，我们任意取一个其他元素（当前元素的下一个元素），计算其频次，判断 tot + totv == len，
     * 另外还需要有 v 用来替换非主元，即 totv < cnt[v][n - 1]。
     * 作为非主元的情况，不需要考虑数组长度为 1 ，因为以及在主元时考虑了，因此只有 i < n - 1 时才作为非主元计算右边界。
     */
    public int maxRepOpt1(String text) {
        int n = text.length(), ans = 0;
        int[][] cnt = new int[26][n + 1];
        for (int i = 1; i <= n; i++) {
            char c = text.charAt(i - 1);
            for (int j = 0; j < 26; j++) {
                if (c == j + 'a') cnt[j][i] = cnt[j][i - 1] + 1;
                else cnt[j][i] = cnt[j][i - 1];
            }
        }
        for (int i = 0; i < n; i++) {
            int c = text.charAt(i) - 'a';
            int j1 = findAsPiviot(cnt, i + 1, c);
            int j2 = 0;
            if (i < n - 1) j2 = findNotAsPiviot(cnt, i + 1, c, (int) text.charAt(i + 1) - 'a');
            ans = Math.max(Math.max(j1 - i, j2 - i), ans);
        }
        return ans;
    }

    public int findAsPiviot(int[][] cnt, int piviot, int c) {
        int n = cnt[0].length;
        int l = piviot, r = n;
        while (l + 1 < r) {
            int m = l + r >> 1;
            int tot = cnt[c][m] - cnt[c][piviot - 1], len = m - piviot + 1;
            if (tot == len || tot + 1 == len && tot < cnt[c][n - 1]) l = m;
            else r = m;
        }
        return l;
    }

    public int findNotAsPiviot(int[][] cnt, int p, int c, int v) {
        int n = cnt[0].length;
        int l = p, r = n;
        while (l + 1 < r) {
            int m = l + r >> 1;
            int tot = cnt[c][m] - cnt[c][p - 1], len = m - p + 1;
            int totv = cnt[v][m] - cnt[v][p - 1];
            if (tot == 1 && tot + totv == len && totv < cnt[v][n - 1]) l = m;
            else r = m;
        }
        return l;
    }
}
