package acwing.贪心;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Supermarket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[][] items = new int[n][2];
            for (int i = 0; i < n; i++) {
                items[i][0] = scanner.nextInt();
                items[i][1] = scanner.nextInt();
//                int profit = maxProfit(items);
                int profit = maxProfit2(items);
                System.out.println(profit);
            }
        }
    }

    /**
     * 贪心 + 堆
     * 第一种贪心策略：在不卖出过期食品情况下，尽量卖出利润最大的商品
     * 具体的来说，因为每天只能卖一件商品，考虑第 i 天时利润最大的 i 件商品
     * 将商品按照过期时间排序，用一个小顶堆维护已安排卖出的商品利润
     * 遍历每个商品，如果当前商品过期时间 i 大于已安排商品数量 size，即 size + 1 <= i ，说明前 i 天能卖出之前安排的加上当前商品
     * 如果 i <= size, 说明不能将之前安排的加上当前的都卖出，需要剔除某个在 i 天已经过期的商品，这时将堆顶利润最小的商品价值和当前商品价值比较
     * 如果当前商品利润更大，则替换堆顶，否则啥也不做。这样一来就动态维护了前 i 天能卖出的商品最大利润
     */
    public static int maxProfit(int[][] items) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(items, Comparator.comparingInt(o->o[1]));
        for (int[] item : items) {
            if (!pq.isEmpty() && item[1] <= pq.size() && item[0] > pq.peek()) {
                pq.poll();
                pq.offer(item[0]);
            }
            if (item[1] > pq.size())
                pq.offer(item[0]);
        }
        int profit = 0;
        while (!pq.isEmpty()) profit += pq.poll();
        return profit;
    }

    /**
     * 贪心 + 并查集
     * 另一种贪心策略：
     * 对于每个物品，在其过期时间内尽量更晚地卖出
     * 将物品按照利润从大到小排序，依次安排卖出时间
     * 从过期时间开始往前找到第一个未被安排卖出物品的时间，就是该物品被卖出的时间
     * 具体的做法是，维护一个并查集，每个节点指向第一个能安排卖出的时间
     * 初始时，每个节点指向自己，然后将物品按照利润从大到小排序
     * 遍历物品，找到第一个能安排卖出的时间（树的根），如果大于 0 ，将其价值累加到结果中，并将当前树的根指向前一个节点的根。
     */
    public static int maxProfit2(int[][] items) {
        Arrays.sort(items, Comparator.comparingInt(o->o[0]));
        int n = items.length, profit = 0;
        init(10001);
        for (int i = n - 1; i >= 0; i--) {
            int cur = find(items[i][1]);
            if (cur > 0) {
                profit += items[i][0];
                union(cur, cur - 1);
            }
        }
        return profit;
    }

    static int[] forest;
    public static int[] init(int n) {
        forest = new int[n + 1];
        for (int i = 1; i < n; i++) forest[i] = i;
        return forest;
    }

    public static int find(int x) {
        if (forest[x] == x) return x;
        return forest[x] = find(forest[x]);
    }

    public static void union(int x, int y) {
        forest[find(x)] = find(y);
    }
}
