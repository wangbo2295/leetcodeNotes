package acwing.贪心;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class StallReservations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] cows = new int[n][2];
        Integer[] idxs = new Integer[n];
        for (int i = 0; i < n; i++) idxs[i] = i;
        for (int i = 0; i < n; i++) {
            cows[i][0] = scanner.nextInt();
            cows[i][1] = scanner.nextInt();
        }
        Arrays.sort(idxs, (o1, o2)->cows[o1][0] == cows[o2][0] ? cows[o1][1] - cows[o2][1] : cows[o1][0] - cows[o2][0]);
        PriorityQueue<Stall> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.endtime));
        int[] stalls = new int[n];
        int cnt = 0;
        for (int i = 0; i < cows.length; i++) {
            Stall last;
            if (!pq.isEmpty() && pq.peek().endtime < cows[idxs[i]][0]) {
                last = pq.poll();
                last.endtime = cows[idxs[i]][1];
            } else {
                last = new Stall(++cnt, cows[idxs[i]][1]);
            }
            pq.offer(last);
            stalls[idxs[i]] = last.id;
        }
        System.out.println(cnt);
        for (int stall : stalls) {
            System.out.println(stall);
        }
    }

    static class Stall {
        int id;
        int endtime;
        public Stall (int id, int endtime) {this.id = id; this.endtime = endtime;}
    }
}
