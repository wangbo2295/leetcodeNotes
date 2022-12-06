package acwing.binarySearch;

import java.util.*;
import java.util.stream.Collectors;

public class CorralTheCows {
    public static int N = 1010, n, C;
    public static List<Integer> numbers;
    public static int[][] sum;
    public static int[][] points;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        C = scanner.nextInt();
        n = scanner.nextInt();
        sum = new int[N][N];
        points = new int[n][2];
        Set<Integer> set = new TreeSet<>();
//        numbers = new ArrayList<>();
//        numbers.add(0);
        set.add(0);
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt(), y = scanner.nextInt();
            set.add(x);
            set.add(y);
            points[i][0] = x;
            points[i][1] = y;
        }
        numbers = new ArrayList<>(set);
//        numbers = CorralTheCows.numbers.stream().distinct().collect(Collectors.toList());
        Collections.sort(numbers);
        for (int[] point : points) {
            int x = get(point[0]), y = get(point[1]);
            sum[x][y]++;
        }
        for (int i = 1; i < numbers.size(); i++) {
            for (int j = 1; j < numbers.size(); j++) {
                sum[i][j] += sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                System.out.print(sum[i][j]);
            }
            System.out.println();
        }
        int l = 0, r = 10001;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (check(m)) {
                r = m;
            } else {
                l = m;
            }
        }
        System.out.println(r);
    }

    public static int get(int x) {
        int l = -1, r = numbers.size();
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (numbers.get(m) <= x) {
                l = m;
            } else {
                r = m;
            }
        }
        return l;
    }

    public static boolean check(int m) {
        for (int x1 = 0, x2 = 1; x2 < numbers.size(); x2++) {
            //这里注意：要找到边长大于等于 m 的最小边长，就是当 x2 - (x1 + 1) 不满足 >= m 的第一个边长
            //也就是找到下一个 x2 - x1 > m 的第一个 x1
            while (numbers.get(x2) - numbers.get(x1 + 1) >= m) x1++;
            for (int y1 = 0, y2 = 1; y2 < numbers.size(); y2++) {
                while (numbers.get(y2) - numbers.get(y1 + 1) >= m) y1++;
                if (sum[x2][y2] - sum[x1][y2] - sum[x2][y1] + sum[x1][y1] >= C) return true;
            }
        }
        return false;
    }
}
