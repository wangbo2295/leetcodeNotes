package acwing.栈;

import java.util.Scanner;
import java.util.Stack;

public class CityGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nm = scanner.nextLine().split(" ");
        int n = Integer.parseInt(nm[0]), m = Integer.parseInt(nm[1]);
        int[][] matrix = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            String[] row = scanner.nextLine().split(" ");
            for (int j = 1; j <= m; j++) {
                if (row[j].equals("F")) {
                    matrix[i][j] = matrix[i][j - 1] + 1;
                }
            }
        }
        int S = 0;
        for (int i = 1; i <= m; i++) {
            Stack<int[]> stack = new Stack<>();
            for (int j = 0; j <= n; j++) {
                int width = 0;
                while (!stack.empty() && stack.peek()[1] >= matrix[j][i]) {
                    int[] pop = stack.pop();
                    width += pop[0];
                    S = Math.max(S, pop[1] * width);
                }
                stack.push(new int[]{width + 1, matrix[j][i]});
            }
        }
        System.out.println(3 * S);
    }
}
