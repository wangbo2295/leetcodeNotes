package leetCodeTest;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int operateNum = scanner.nextInt();
            int[] score;
            score = new int[n];
            for (int i = 0; i < n; i++) {
                score[i] = scanner.nextInt();
            }
            for (int i = 0; i < operateNum; i++) {
                String O = scanner.next();
                int one = scanner.nextInt();
                int two = scanner.nextInt();
                if (O.equals("Q")) {
                    int min = Math.min(one, two);
                    int max = Math.max(one, two);
                    int m = Integer.MIN_VALUE;
                    for (int j = min - 1; j <= max - 1; j++) {
                        m = Math.max(m, score[j]);
                    }
                    System.out.println(m);
                } else {
                    score[one - 1] = two;
                }
            }
        }
    }
}
