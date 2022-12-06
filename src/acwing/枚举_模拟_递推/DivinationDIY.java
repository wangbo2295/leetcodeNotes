package acwing.枚举_模拟_递推;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DivinationDIY {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = {"0","A","2","3","4","5","6","7","8","9","0","J","Q","K"};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 1; i < s.length; i++) {
            map.put(s[i], i);
        }
        int[][] cards = new int[14][4];
        int[] top = new int[14];
        for (int i = 1; i <= 13; i++) {
            String[] pile = scanner.nextLine().split(" ");
            int idx;
            if (i == 13) {
                idx = 0;
                for (String s1 : pile) {
                    cards[i][idx++] = map.get(s1);
                }
            } else {
                idx = 3;
                for (String s1 : pile) {
                    cards[i][idx--] = map.get(s1);
                }
            }
        }
        int[] chosen = new int[14];
        int cur = 13, k = 0;
        while (k < 4) {
            cur = cards[cur][top[cur]++];
            chosen[cur]++;
            if (cur == 13)  k++;
        }
        int ans = 0;
        for (int i = 0; i < chosen.length - 1; i++) {
            if (chosen[i] == 4) ans++;
        }
        System.out.println(ans);
    }
}
