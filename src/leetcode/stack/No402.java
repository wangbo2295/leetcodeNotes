package leetcode.stack;

/**
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 */
public class No402 {
    /**
     * 思路：对于一个要入栈的数，找到左边第一个比他小的数，之前的都丢弃
     * 如果丢弃的数字等于k，结束
     * 从左到右地移除可以保证使结果最小，因为总位数是一定的，从左到右移除保证了从高位到低位是递增的
     *
     * 有几个要注意的点：
     * 1、原数可能本来就是递增的，这时不会删除任何数，所以用完单调栈后要检查k是否为0，从数的尾部删除剩余的k个数
     * 2、处理后的数可能有多个前导0，删除时要用while
     */
    public String removeKdigits(String num, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            while (k > 0 && sb.length() > 0 && num.charAt(i) < sb.charAt(sb.length() - 1)) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(num.charAt(i));
        }
        while (k > 0) {
            sb.deleteCharAt(sb.length() - 1);
            k--;
        }
        while (sb.length() > 1 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        if (sb.length() == 0)   return "0";
        return sb.toString();
    }
}
