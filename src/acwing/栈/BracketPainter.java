package acwing.栈;

import java.util.Scanner;
import java.util.Stack;

public class BracketPainter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] brackets = scanner.nextLine().split("");
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < brackets.length; i++) {
            String bracket = brackets[i];
            if (stack.empty() || bracket.equals("(") || bracket.equals("{") || bracket.equals("[")) stack.push(i);
            else {
                String last = brackets[stack.peek()];
                if ((last.equals("[") && bracket.equals("]")) || (last.equals("(") && bracket.equals(")")) || (last.equals("{") && bracket.equals("}"))) {
                    stack.pop();
                    max = Math.max(max, i - (stack.empty() ? -1 : stack.peek()));
                } else {
                    stack.push(i);
                }
            }
        }
        System.out.println(max);
    }
}
