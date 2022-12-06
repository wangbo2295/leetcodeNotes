package leetcode.stack;

import java.util.Stack;

/**
 * 单调栈
 * 栈中保存数组下标，保持栈的单调性，弹出时计算下标差值并保存起来
 */
public class No739 {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while( !stack.empty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer pop = stack.pop();
                res[pop] = i - pop;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        No739 no739 = new No739();
        int[] temperatures = {73,74,75,71,69,72,76,73};
        no739.dailyTemperatures(temperatures);
    }
}
