package leetcode.stack;

/**
 * 删除重复字母，并使得结果的字典序最小
 */
public class No316 {
    /**
     * 思路：
     * 如果当前字母比栈顶元素小，就看栈顶元素在后面还会不会出现，如果出现则弹出，否则将当前元素压栈
     * 如果当前字母已经在栈中出现，则直接丢弃当前元素
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        StringBuilder sb = new StringBuilder();
        char[] str = s.toCharArray();
        boolean[] used = new boolean[26];   //标记字母是否在栈中存在
        int[] unused = new int[26];     //标记每个字母还剩多少个没用
        for (char c : str) {
            unused[c - 'a']++;      //初始化
        }
        for (int i = 0; i < str.length; i++) {
            //如果当前字母不在栈中
            if (!used[str[i] - 'a']) {
                //只要栈顶元素比当前元素大且之后还会出现，弹出
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > str[i] && unused[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                    used[sb.charAt(sb.length() - 1) - 'a'] = false; //弹出后标记为未出现
                    sb.deleteCharAt(sb.length() - 1);               //弹出
                }
                sb.append(str[i]);  //将当前元素入栈
                used[str[i] - 'a'] = true;  //当前元素标记为已使用
            }
            //无论当前字母在不在栈中，是丢弃还是入栈，未使用数量都减1
            unused[str[i] - 'a']--;
        }
        return sb.toString();
    }
}
