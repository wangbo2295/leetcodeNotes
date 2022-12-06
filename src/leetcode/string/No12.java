package leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 *
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给你一个整数，将其转为罗马数字。
 */
public class No12 {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 1000) {
            sb.append("M".repeat(num / 1000));
            num %= 1000;
        }
        if (num >= 900) {
            sb.append("CM");
            num %= 100;
        }else if (num >= 500) {
            sb.append("D");
            sb.append("C".repeat((num - 500) / 100));
            num %= 100;
        }else if (num >= 400) {
            sb.append("CD");
            num %= 100;
        }else {
            sb.append("C".repeat(num / 100));
            num %= 100;
        }
        if (num >= 90) {
            sb.append("XC");
            num %= 10;
        }else if (num >= 50) {
            sb.append("L");
            sb.append("X".repeat((num - 50) / 10));
            num %= 10;
        }else if (num >= 40) {
            sb.append("XL");
            num %= 10;
        }else {
            sb.append("X".repeat(num / 10));
            num %= 10;
        }
        if (num >= 9) {
            sb.append("IX");
        }else if (num >= 5) {
            sb.append("V");
            sb.append("I".repeat(num - 5));
        }else if (num >= 4) {
            sb.append("IV");
        }else {
            sb.append("I".repeat(num));
        }
        return sb.toString();
    }
}
