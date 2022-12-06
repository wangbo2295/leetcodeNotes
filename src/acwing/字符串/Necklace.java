package acwing.字符串;

import java.util.Scanner;

/**
 * 最小表示法
 */
public class Necklace {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String neck1 = scanner.nextLine();
        String neck2 = scanner.nextLine();
        String s1 = minExpress(neck1);
        String s2 = minExpress(neck2);
        boolean match = s1.equals(s2);
        System.out.println(match ? "Yes" : "No");
        if (match) {
            System.out.println(s1);
        }
    }

    /**
     * 求一个字符串的最小表示法
     * 将原字符串复制一份贴在后面，定义两个指针 i ， j 表示从下标 i， j 开始的字串
     * 对 i， j 两个字串一一比对，当 c[i + k] != c[j + k] 时，比较两个字符大小
     * 如果 c[i + k] < c[j + k], 说明 s[j] 的字典序小于 s[i]，并且 s[j ~ j + k] 的字典序都不是最小的
     * 因为对每个 j + h <= j + k，都存在一个子串 s[i + h] 的字典序小于 s[j + h]，因为 c[i + k] < c[j + k]
     * 这样就可以将 j 指针移到 j + k + 1 处，为了避免和自身比较，如果此时 j == i 则 j ++
     * 对于c[i + k] > c[j + k] 的情况则相反。
     * 比对过程中如果 i 或者 j 超过 n 了说明比较完了，此时另外一个就是字典序最小的字串，只需对 i， j 取较小值即可得到最小表示法的起始下标
     * 另外在比对过程中如果没有出现字符不相等的情况，即 k == n，说明整个字符串由同一个字符构成，返回任意字串即可。
     */
    public static String minExpress(String s) {
        int n = s.length();
        s += s;
        int i = 0, j = 1, k;
        while (i < n && j < n) {
            for (k = 0; k < n && s.charAt(i + k) == s.charAt(j + k); k++);
            if (k == n) return s;
            if (s.charAt(i + k) < s.charAt(j + k)) {
                j += k + 1;
                if (j == i) j++;
            } else if (s.charAt(i + k) > s.charAt(j + k)) {
                i += k + 1;
                if (i == j) i++;
            }
        }
        int min = Math.min(i, j);
        return s.substring(min, min + n);
    }
}
