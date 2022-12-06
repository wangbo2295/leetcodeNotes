package newcoder.tencent;

import java.util.*;
import java.util.logging.Handler;

public class Main {

    public static void main(String[] args) {
            NoKsubString();
    }

    //麻了，第一题都不会
    public static void No1 () {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n > 0) {
            int N = scanner.nextInt();
            int[] collection = new int[100001];
            Arrays.fill(collection, -1);
            int max = Integer.MIN_VALUE;
            while (N > 0) {
                int root1 = scanner.nextInt();
                int root2 = scanner.nextInt();
                int union = union(collection, root1, root2);
                max = Math.max(max, union);
                N--;
            }
            System.out.println(max);
            n--;
        }
    }

    public static int union(int[] col, int root1, int root2) {
        while (col[root1] > 0) {
            root1 = col[root1];
        }
        while (col[root2] > 0) {
            root2 = col[root2];
        }
        if (root1 == root2) return -col[root1];
        int t = col[root2];
        col[root2] = root1;
        col[root1] += t;
        return -col[root1];
    }

    public static void NoKsubString() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Boolean> map = new HashMap<>();
        String str = scanner.nextLine();
        int k = scanner.nextInt();
        PriorityQueue<String> queue = new PriorityQueue<>(String::compareTo);
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                String s = str.substring(i, j + 1);
                if (!map.containsKey(s)) {
                    queue.offer(s);
                    map.put(s, true);
                }
            }
        }
        for (int i = 1; i < k; i++) {
            queue.poll();
        }
        System.out.println(queue.poll());
    }
}
