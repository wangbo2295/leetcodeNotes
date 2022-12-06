package leetcode.stack;

import java.util.Stack;

public class No394 {
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ']') {
                StringBuilder repeat = new StringBuilder();
                StringBuilder cnt = new StringBuilder();
                while (!stack.empty() && !stack.peek().equals("[")) {
                    repeat.insert(0, stack.pop());
                }
                stack.pop();
                while (!stack.empty() && stack.peek().compareTo("0") >= 0 && stack.peek().compareTo("9") <= 0) {
                    cnt.append(stack.pop());
                }
                cnt.reverse();
                stack.push(String.valueOf(repeat).repeat(Integer.parseInt(cnt.toString())));
            } else {
                stack.push("" + c);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.empty()) {
            ans.insert(0, stack.pop());
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        No394 no394 = new No394();
        no394.decodeString("3[a]2[bc]");
    }
}
