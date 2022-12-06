package acwing.字符串;

import java.util.Scanner;

public class PhoneList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();scanner.nextLine();
        while (T-- > 0) {
            int n = scanner.nextInt();scanner.nextLine();
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                if (!insert(scanner.nextLine())) {
                    valid = false;
                }
            }
            if (valid) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            root = new Trie();
        }
    }

    public static Trie root = new Trie();

    public static boolean insert(String num) {
        Trie cur = root;
        boolean flag = false;
        for (char c : num.toCharArray()) {
            if (cur.childern[c - '0'] == null) {
                cur.childern[c - '0'] = new Trie();
                flag = true;
            }
            if (cur.childern[c - '0'].end)   return false;
            cur = cur.childern[c - '0'];
        }
        cur.end = true;
        return flag;
    }
    static class Trie {
        Trie[] childern;
        boolean end;
        public Trie() {
            this.childern = new Trie[10];
            this.end = false;
        }
    }
}
