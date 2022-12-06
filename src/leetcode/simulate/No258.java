package leetcode.simulate;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列模拟，不断将两数之和小于10的合并
 */
public class No258 {
    public int addDigits(int num) {
        if (num == 0) return 0;
        Queue<Integer> queue = new LinkedList<>();
        while (num > 0) {
            queue.offer(num % 10);
            num /= 10;
        }
        while (queue.size() > 1) {
            Integer first = queue.poll();
            Integer second = queue.poll();
            Integer sum = first + second;
            queue.offer(sum % 10);
            if (sum > 9) {
                queue.offer(sum / 10);
            }
        }
        return queue.poll();
    }

    /**
     * 思路： 在一个num大于9的循环内（保证有两位数），计算前两位的和并将和的各位数字贴到num后面
     * 简化队列， 直接将输入的数本身当作一个队列
     * @param num
     * @return
     */
    public int addDigits2(int num) {
        while (num > 9) {   //不断合并num的最后两位数，直到最后num小于10
            int first = num % 10;   //取个位数
            num /= 10;              //向右移一位
            int second = num % 10;  //取移位后的个位数
            num /= 10;              //向右移一位
            int sum = first + second;   //计算两数之和
            num = (num * 10) + (sum % 10);  //个位数贴到num后面
            if (sum > 9) {
                num = (num * 10) + (sum / 10);  //如果sum大于9则将sum的十位数贴到num后面
            }
        }
        return num;
    }
}
