package acwing.队列;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class SlidingWindow {
    /**
     * 单调队列的做法，实际上更新最大值和最小值的时间复杂度为O(n)，可以用堆优化。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]), k = Integer.parseInt(s[1]);
        String[] s1 = br.readLine().split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(s1[i]);
        }
        Deque<Integer> minque = new LinkedList<>();
        Deque<Integer> maxque = new LinkedList<>();
        int[] minval = new int[n - k + 1];
        int[] maxval = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            while (!minque.isEmpty() && minque.getFirst() <= i - k) minque.removeFirst();
            while (!minque.isEmpty() && nums[minque.getLast()] >= nums[i])  minque.removeLast();
            while (!maxque.isEmpty() && maxque.getFirst() <= i - k) maxque.removeFirst();
            while (!maxque.isEmpty() && nums[maxque.getLast()] <= nums[i])  maxque.removeLast();
            minque.offer(i);
            maxque.offer(i);
            if (i - k + 1 >= 0) {
                minval[i - k + 1] = nums[minque.getFirst()];
                maxval[i - k + 1] = nums[maxque.getFirst()];
            }
        }
        for (int i = 0; i <= n - k; i++) bw.write(minval[i] + " ");
        bw.write("\n");
        for (int i = 0; i <= n - k; i++) bw.write(maxval[i] + " ");
        bw.flush();
    }
}
