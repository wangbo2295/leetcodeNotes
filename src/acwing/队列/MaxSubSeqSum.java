package acwing.队列;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class MaxSubSeqSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = nums[i - 1] + scanner.nextInt();
        }
        Deque<Integer> deque = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[deque.getLast()] >= nums[i]) deque.pollLast();
            while (!deque.isEmpty() && i - deque.getFirst() > m) deque.pollFirst();
            deque.addLast(i);
            max = Math.max(max, nums[i] - nums[deque.getFirst()]);
        }
        System.out.println(max);
    }
}
