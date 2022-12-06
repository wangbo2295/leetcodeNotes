package acwing.字符串;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 每天早上，农夫约翰的奶牛们被挤奶的时候，都会站成一个 R 行 C 列的方阵。
 * 现在在每个奶牛的身上标注表示其品种的大写字母，则所有奶牛共同构成了一个 R 行 C 列的字符矩阵。
 * 现在给定由所有奶牛构成的矩阵，求它的最小覆盖子矩阵的面积是多少。
 * 如果一个子矩阵无限复制扩张之后得到的矩阵能包含原来的矩阵，则称该子矩阵为覆盖子矩阵。
 */
public class MilkingGrid {
    /**
     * 错误做法！不是完美循环的字符串，其循环节不一定是最小循环节的倍数，所以求最小循环节的最小公倍数是错误的做法，因为最小覆盖矩阵的宽可能不是最小循环节的倍数！
     *
     * XXX 思路：观察到只要 next 数组最后一个下标的值的两倍大于等于字符串长度 n ，那么该字符串就可以由某个前缀循环拼接覆盖
     * 而最小循环体就是第一个开始匹配的字符的前面的字串，设 next[n] = k, 则最小循环体长度为 n - next[k]
     * 对每行、每列的字符串求出最小循环体，再分别求出最小公倍数，就是覆盖子矩阵的尺寸 XXX
     */
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String[] s = scanner.nextLine().split(" ");
//        int m = Integer.parseInt(s[0]), n = Integer.parseInt(s[1]);
//        int[][] rowNext = new int[m][n + 1];
//        int[][] colNext = new int[n][m + 1];
//        char[][] matrix = new char[m][n];
//        for (int i = 0; i < m; i++) {
//            matrix[i] = scanner.nextLine().toCharArray();
//        }
//        for (int i = 0; i < m; i++) {
//            for (int j = 2; j <= n; j++) {
//                int k = rowNext[i][j - 1];
//                while (k > 0 && matrix[i][k] != matrix[i][j - 1])   k = rowNext[i][k];
//                if (k > 0)  k++;
//                rowNext[i][j] = k;
//            }
//            rowNext[i][0] = n - rowNext[i][n];
//        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 2; j <= m; j++) {
//                int k = colNext[i][j - 1];
//                while (k > 0 && matrix[k][i] != matrix[j - 1][i])   k = colNext[i][k];
//                if (k > 0)  k++;
//                colNext[i][j] = k;
//            }
//            colNext[i][0] = n - colNext[i][n];
//        }
//    }

    /**
     * 正确做法：由于每行最多 75 个字符，应该暴力枚举所有行的最小公共循环节
     * 可以用一个数组维护循环节长度是否可行，每次枚举所有行，只有当前一行满足当前循环节长度才进行判断，一旦当前行不满足则当前循环节长度也不满足，跳出循环
     * 最坏情况是最小循环节长度为 n 。再次枚举循环节数组，找到第一个满足的长度即为最小循环节长度
     *
     * 对于列的最小循环节，可以以行最小循环节作为比较对象，对列求next数组，循环节相等等价于一般 next 数组中的字符相等
     */
    static int M = 10010, N = 80;
    static int m, n;
    static char[][] grid = new char[M][N];
    static boolean[] st = new boolean[N];
    static int[] next = new int[M];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        scanner.nextLine();
        Arrays.fill(st, true);
        for (int i = 1; i <= m; i++) {
            System.arraycopy(scanner.nextLine().toCharArray(), 0, grid[i], 1, n);
            for (int j = 1; j <= n; j++) {
                if (st[j]) {
                    for (int k = j + 1; k <= n; k += j) {
                        for (int u = 0; u < j && k + u <= n; u++) {
                            if (grid[i][1 + u] != grid[i][k + u]) {
                                st[j] = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        int width = 0;
        for (int i = 1; i <= n; i++) {
            if (st[i]) {
                width = i;
                break;
            }
        }
        for (int i = 2; i <= m; i++) {
            int j = next[i - 1];
            String cur = String.valueOf(grid[i], 1, width);
            while (j > 0 && !String.valueOf(grid[j + 1], 1, width).equals(cur))    j = next[j];
            if (String.valueOf(grid[j + 1], 1, width).equals(cur))  j++;
            next[i] = j;
        }
        System.out.println((m - next[m]) * width);
    }
}
