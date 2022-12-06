package acwing.堆;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Sequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        while (T-- > 0) {
            String[] mn = scanner.nextLine().split(" ");
            int m = Integer.parseInt(mn[0]), n = Integer.parseInt(mn[1]);
            int[] ans = new int[n + 1];
            int[] cur = new int[n + 1];
            ans[n] = cur[n] = Integer.MAX_VALUE;
            String[] first = scanner.nextLine().split(" ");
            for (int i = 0; i < n; i++) ans[i] = Integer.parseInt(first[i]);
            Arrays.sort(ans);
            System.arraycopy(ans, 0, cur, 0, n);
            for (int i = 1; i < m; i++) {
                PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[0] + cur[o[1]]));
                String[] next = scanner.nextLine().split(" ");
                for (int j = 0; j < n; j++) {
                    pq.offer(new int[]{Integer.parseInt(next[j]), 0});
                }
                for (int j = 0; j < n; j++) {
                    int[] poll = pq.poll();
                    ans[j] = poll[0] + cur[poll[1]];
                    poll[1]++;
                    pq.offer(poll);
                }
                System.arraycopy(ans, 0, cur, 0, n);
            }
            for (int i = 0; i < n; i++) System.out.print(ans[i] + " ");
            System.out.println();
        }
    }
}
