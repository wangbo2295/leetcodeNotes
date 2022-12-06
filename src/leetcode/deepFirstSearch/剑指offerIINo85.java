package leetcode.deepFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class 剑指offerIINo85 {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    public List<String> generateParenthesis(int n) {
        generateParenthesis(0, 0, n);
        return res;
    }
    private void generateParenthesis(int val, int k, int n) {
        if (k == 2 * n) {
            if (val == 0)
                res.add(new String(sb));
            return;
        }
        if (val > 0) {
            sb.append(')');
            generateParenthesis(val - 1, k + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (val < n) {
            sb.append('(');
            generateParenthesis(val + 1, k + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
