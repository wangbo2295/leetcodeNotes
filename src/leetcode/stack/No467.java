package leetcode.stack;

/**
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以s 看起来是这样的：
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 现在给定另一个字符串 p 。返回s 中唯一 的 p 的 非空子串的数量。
 */
public class No467 {
    /**
     * 刚开始思路对了，但是没有注意到"唯一"这个条件
     * 如何去重？
     * 已知如何计算子串在s中的个数，就是统计连续的在s中存在的子串并记录长度，当前子串的子串在s中的个数为n
     * 遍历过程中一直累加这个个数就好，遇到不满足的就把长度置为1
     * 去重的关键在于，每次计算的子串的所有子串都是以最后一个字母结尾的，长度不同则不同
     * 所以可以记录以某个字母结尾的最长连续子串长度，由于可能计算多次，所以取最大值
     * 最后把每个字母结尾的子串个数相加。
     * @param p
     * @return
     */
    public int findSubstringInWraproundString(String p) {
        int[] map = new int[26];
        char[] chars = p.toCharArray();
        map[chars[0] - 'a'] = 1;
        int n = 1, ans = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] - chars[i - 1] == 1 || chars[i - 1] - chars[i] == 25) {
                n++;
            }else {
                n = 1;
            }
            map[chars[i] - 'a'] = Math.max(map[chars[i] - 'a'], n);
        }
        for (int i : map) {
            ans += i;
        }
        return ans;
    }
}
