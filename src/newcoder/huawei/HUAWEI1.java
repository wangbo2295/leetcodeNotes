package newcoder.huawei;

import java.util.Scanner;

public class HUAWEI1 {
    public static void main(String[] args) {
        No3();
    }
    public static void No1() {
        Scanner scanner = new Scanner(System.in);
        int empty;
        while ((empty = scanner.nextInt()) != 0) {
            int ans = 0;
            while (empty > 2) {
                ans += empty / 3;
                empty = empty % 3 + empty / 3;
            }
            if (empty == 2) ans++;
            System.out.println(ans);
        }
    }
    public static void No2() {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[501];
        scanner.next();
        while (scanner.hasNext()) {
            nums[scanner.nextInt()]++;
        }
        for (int i = 0; i < 501; i++) {
            if (nums[i] != 0) {
                System.out.println(i);
            }
        }
    }
    public static void No3() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String hex = scanner.next();
            int res = 0;
            for (int i = 2; i < hex.length(); i++) {
                int n = hex.charAt(i) >= '0' && hex.charAt(i) <= '9' ? hex.charAt(i) - '0' : hex.charAt(i) - 'A' + 10;
                res = res * 16 + n;
            }
            System.out.println(res);
        }
    }


}
