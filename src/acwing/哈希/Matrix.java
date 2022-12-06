package acwing.哈希;

import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split(" ");
        int m = Integer.parseInt(split[0]), n = Integer.parseInt(split[1]), a = Integer.parseInt(split[2]), b = Integer.parseInt(split[3]);
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] ns = scanner.nextLine().split("");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(ns[j]);
            }
        }

    }
}
