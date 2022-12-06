package leetcode.WC;

import java.util.Arrays;
import java.util.Comparator;

public class No294 {
    //字符在字符串中的百分比，模拟
    public int percentageLetter(String s, char letter) {
        double n1 = 0.0, n2 = 0.0;
        for (char c : s.toCharArray()) {
            if (c == letter)    n1++;
            n2++;
        }
        double percentage = n1 / n2 * 100;
        return (int) percentage;
    }

    //最多装满箱子的个数
    //贪心，按容量排序即可
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int[] toLoad = new int[capacity.length];
        for (int i = 0; i < toLoad.length; i++) {
            toLoad[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(toLoad);
        int ans = 0;
        for (int i = 0; i < toLoad.length; i++) {
            if (toLoad[i] > additionalRocks)    break;
            additionalRocks -= toLoad[i];
            ans++;
        }
        return ans;
    }

    //折线图最少的线段数
    //判断当前线段跟上一个线段的斜率是否相等
    //关注到浮点数的精度问题，将斜率公式转化为乘法，即交叉相乘
    public int minimumLines(int[][] stockPrices) {
        Arrays.sort(stockPrices, Comparator.comparingInt(o->o[0]));
        int lines = 0;
        long x0 = 0, y0 = Integer.MAX_VALUE;
        for (int i = 1; i < stockPrices.length; i++) {
            long x1 = stockPrices[i][0] - stockPrices[i - 1][0];
            long y1 = stockPrices[i][1] - stockPrices[i - 1][1];
            if (x0 * y1 != y0 * x1) {
                x0 = x1;
                y0 = y1;
                lines++;
            }
        }
        return lines;
    }

    //巫师总力量和
    //我不到啊
    public int totalStrength(int[] strength) {
        return 0;
    }

    public static void main(String[] args) {
        No294 no294 = new No294();
        no294.percentageLetter("foobar", 'o');
        int[] strength = {1,3,1,2};
        no294.totalStrength(strength);
    }
}
