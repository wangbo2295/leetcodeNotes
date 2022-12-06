package leetcode.bitSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 *
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 */
public class No784 {
    /**
     * 思路
     * 对于每个字母只有大写、小写两种状态，可以用二进制表示所有可能
     * 对于数字只有一种状态，可以预处理一个数字位置为 1 的pattern，将要枚举的二进制数与patter进行与运算，结果为0说明符合题意
     */
    public List<String> letterCasePermutation(String s) {
        int pattern = 0;
        int n = s.length();
        StringBuilder root = new StringBuilder(s.toLowerCase());    //原始状态全为小写
        //生成pattern，数字为全为 1 。
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                pattern |= 1 << i;
            }
        }
        //利用 hash 提高查找bit位效率
        int[] hash = new int[37];
        for (int i = 0; i < 31; i++) {
            hash[(1 << i) % 37] = i;
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            if ((i & pattern) != 0) continue;   //如果是数字位为 1 的状态，则跳过
            StringBuilder sb = new StringBuilder(root);
            int t = i;
            //利用lowbit将每位为 1 的字母置为大写
            while (t > 0) {
                int idx = hash[(t & -t) % 37];
                sb.setCharAt(idx, Character.toUpperCase(sb.charAt(idx)));
                t -= t & -t;
            }
            res.add(sb.toString());
        }
        return res;
    }
}
