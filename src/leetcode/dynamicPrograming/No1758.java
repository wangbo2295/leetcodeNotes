package leetcode.dynamicPrograming;

public class No1758 {
    public int minOperations(String s) {
        int pre0 = s.charAt(0) - '0';
        int pre1 = pre0 == 0 ? 1 : 0;
        int cur0, cur1;
        for (int i = 1; i < s.toCharArray().length; i++) {
            if (s.charAt(i) - '0' == 0) {
                cur0 = pre1;
                cur1 = pre0 + 1;
            }else {
                cur0 = pre1 + 1;
                cur1 = pre0;
            }
            pre0 = cur0;
            pre1 = cur1;
        }
        return Math.min(pre0, pre1);
    }
}
