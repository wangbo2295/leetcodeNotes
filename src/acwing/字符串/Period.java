package acwing.字符串;

import java.util.Scanner;

/**
 * 一个字符串的前缀是从第一个字符开始的连续若干个字符，例如 abaab 共有 5 个前缀，分别是 a，ab，aba，abaa，abaab。
 * 我们希望知道一个 N 位字符串 S 的前缀是否具有循环节。
 * 换言之，对于每一个从头开始的长度为 i（i>1）的前缀，是否由重复出现的子串 A 组成，即 AAA…A（A 重复出现 K 次,K>1）。
 * 如果存在，请找出最短的循环节对应的 K 值（也就是这个前缀串的所有可能重复节中，最大的 K 值）。
 */
public class Period {
    /**
     * 前言：kmp 匹配字符串
     * 如果我们得到一个 next 数组，表示以第 i 个字母结尾的字串中与原字符串的最长前缀匹配长度
     * 即 S[1 ~ j] = S[i - j + 1 ~ i]
     * 那么当下一个字母与主串对应字母不同时，可以不用从头开始匹配，由于已经知道前 j 个字母与当前后 j 个字母相同
     * 所以可以跳过前 j 个字母，从第 j + 1 个字母开始跟主串当前字母比对，这样比对的指针就一直往前走，时间复杂度 O(m + n)
     */

    /**
     * 回到本题，要在字符串中寻找最小循环元，也可以用next数组求解
     * 由 next 的定义知道，next[i] = j     <==>    S[1 ~ j] = S[i - j + 1 ~ i]
     * 对于字符串本身，如果 len = i % (i - j) == 0 就可以推导出S[1 ~ j]为最小循环元
     * 证明：
     * 因为 len = i % (i - j) == 0 ，所以 j % len == 0， 即前缀和后缀相同的长度也是 len 的倍数
     * 举个例子：
     * s = ABABABAB
     * next = [0, 0, 1, 2, 3, 4, 5, 6]
     * 对于 i = 8，len = i - next[i] = 2
     * 我们将 s 右移 len 个单位得到另一个 s'
     *      1 2  3 4 5 6 7 8    i
     * s :  A B [A B A B A B]
     *           1 2 3 4 5 6  7 8
     * s':      [A B A B A B] A B
     * 由 next数组的定义易知括号部分是相同的，观察到 s' 中的[1, 2] 等于 s 中的 [3,4]，也就是 s[1,2] = s[3,4]
     * 由于 next[i] % len == 0，所以可以以此类推得到所有的的s[i * len + 1, (i + 1) * len]是相等的，即以 i 结尾的前缀是循环串
     * 另外，因为 next[i] 为最大匹配前缀，所以 i - next[i] 即为最小循环元。如果要求其他循环元，可递归地计算 i - next[next[i]]
     */
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        int n, k = 1;
        while ((n = Integer.parseInt(scanner.nextLine())) != 0) {
            int[] next = new int[n + 1];
            String s = scanner.nextLine();
            for (int i = 2; i <= n; i++) {
                int j = next[i - 1];
                while (j > 0 && s.charAt(j) != s.charAt(i - 1)) j = next[j];
                if (s.charAt(j) == s.charAt(i - 1)) j++;
                next[i] = j;
            }
            System.out.println("Test case #" + k++);
            for (int i = 2; i <= n; i++) {
                int c = i / (i - next[i]);
                if (i % (i - next[i]) == 0 && c > 1) {
                    System.out.println(i + " " + c);
                }
            }
            System.out.println();
        }
    }
}
