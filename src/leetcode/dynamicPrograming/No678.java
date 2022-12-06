package leetcode.dynamicPrograming;

public class No678 {

    public boolean checkValidString(String s) {
        Boolean[][] dp = new Boolean[s.length() + 1][s.length() + 1];
        return checkValidString(s, 0, 0, dp);
    }

    /**
     * 备忘录：由于多个星号可能组成相同的括号组合，所以把这些记录下来
     * dp[i][j] = 前 i 个括号的val为j时是否能生成有效括号
     * @param s
     * @param index
     * @param val
     * @return
     */
    public boolean checkValidString(String s, int index, int val, Boolean[][] dp) {
        if (val < 0) {
            dp[index][val] = false;
            return false;
        }
        if (index >= s.length()) {
            dp[index][val] = val == 0;
            return val == 0;
        }
        if (dp[index][val] != null)  return dp[index][val];
        boolean res = false;
        if (s.charAt(index) == '*') {
            res |= checkValidString(s, index+1, val + 1, dp);
            res |= checkValidString(s, index+1, val, dp);
            res |= checkValidString(s, index+1, val - 1, dp);
        }else if (s.charAt(index) == '('){
            res |= checkValidString(s, index+1, val + 1, dp);
        }else {
            res |= checkValidString(s, index+1, val - 1, dp);
        }
        dp[index][val] = res;
        return res;
    }


    /**
     * 动态规划：
     * 如何判断有效括号：左右括号有效 + 子串括号有效 / 左右括号的中间存在一对相反的括号，这样组成左右两组有效括号
     * dp[i][j] = i == j ? dp[i + 1][j - 1] : false;
     * @param s
     * @return
     */
    public boolean checkValidString2(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = i; j < dp.length; j++) {
                if ((s.charAt(i) == '(' || s.charAt(i) == '*') && (s.charAt(j) == ')' || s.charAt(j) == '*')) {
                    if (i + 1 >= j) {
                        dp[i][j] = true;
                    }
                    else {
                        dp[i][j] = dp[i + 1][j - 1];
                        //这里很关键
                        for (int k = i; k < j; k++) {
                            dp[i][j] |= dp[i][k] && dp[k + 1][j];
                        }
                    }
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    /**
     * 贪心思想：
     * 用计数的方法判断是否有效，但是存在通配符*，所以要考虑三种情况
     * * 会引起左括号数量的不定变化，所以记录左括号取值的范围，记为min， max
     * 在遍历的过程中，遇到左括号则min + 1， max + 1
     * 遇到右括号则min - 1， max - 1
     * 遇到 * 则min - 1， max + 1
     * 一旦max < 0, 返回false。
     * 一旦min < 0, 置为0。因为min < 0 控制的是最佳情况，max < 0 控制的是最差情况
     */
    public boolean checkValidString3(String s) {
        int min = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                min++;max++;
            }else if (s.charAt(i) == ')') {
                min--;max--;
            }else {
                min--;max++;
            }
            if (max < 0)    return false;
            if (min < 0)    min = 0;
        }
        return min == 0;
    }
}
