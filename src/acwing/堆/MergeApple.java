package acwing.堆;

import java.util.PriorityQueue;
import java.util.Scanner;

public class MergeApple {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(scanner.nextInt());
        }
        int sum = 0;
        while (pq.size() > 1) {
            Integer first = pq.poll();
            Integer second = pq.poll();
            sum += first + second;
            pq.offer(first + second);
        }
        sum += pq.poll();
        System.out.println(sum);
    }
}
