package leetcode.deepFirstSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class No22 {
    /**
     * 今天周赛学到的判断有效括号的方法，豁然开朗
     * 根本不用栈
     */

    /**
     * 思路：
     * 左括号为+1，右括号为-1，只要总和不小于0，就是有效括号
     * @param n
     * @return
     */
    List<String> res = new ArrayList<>();
    StringBuilder str = new StringBuilder();
    int n;
    String[] parenthesis = {"(", ")"};
    public List<String> generateParenthesis(int n) {
        this.n = n;
        generateParenthesis(0, 0);
        return res;
    }
    public void generateParenthesis(int k, int num) {
        if (k == 2 * n || num > n) {
            if (num == 0)
                res.add(String.join("", str));//这个函数只能用String的表
            return;
        }
        if (num >= 0) {
            str.append(parenthesis[0]);
            generateParenthesis(k + 1, num + 1);
            str.deleteCharAt(str.length() - 1);

        }
        if (num - 1 >= 0) {
            str.append(parenthesis[1]);
            generateParenthesis(k + 1, num - 1);
            str.deleteCharAt(str.length() - 1);
        }
    }
}
