package acwing.并查集;

import java.util.Scanner;

public class GalaxyHeroStory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        init();
        while (t-- > 0) {
            String[] params = scanner.nextLine().split(" ");
            int x = Integer.parseInt(params[1]), y = Integer.parseInt(params[2]);
            if (params[0].equals("M")) {
                union(x, y);
            } else {
                if (get(x) == get(y)) {
                    System.out.println(Math.abs(d[x] - d[y]) - 1);
                } else {
                    System.out.println("-1");
                }
            }
        }
    }

    static int N = 30010;
    static int[] ships = new int[N];
    static int[] d = new int[N];
    static int[] size = new int[N];
    public static void init() {
        for (int i = 1; i < N; i++) {
            ships[i] = i;
            size[i] = 1;
        }
    }

    public static int get(int x) {
        if (ships[x] == x)  return x;
        int root = get(ships[x]);
        d[x] += d[ships[x]];
        return ships[x] = root;
    }

    public static void union(int x, int y) {
        x = get(x);
        y = get(y);
        if (x == y) return;
        ships[x] = y;
        d[x] = size[y];
        size[y] += size[x];
    }
}
