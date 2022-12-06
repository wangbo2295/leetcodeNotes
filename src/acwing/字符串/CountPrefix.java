package acwing.字符串;

import java.util.Scanner;

public class CountPrefix {

    public static TrieNode trie = new TrieNode();

    static class TrieNode {
        TrieNode[] chars = new TrieNode[26];
        int end;
    }

    public static void insert(String s) {
        TrieNode cur = trie;
        for (char c : s.toCharArray()) {
            if (cur.chars[c - 'a'] == null) {
                cur.chars[c - 'a'] = new TrieNode();
            }
            cur = cur.chars[c - 'a'];
        }
        cur.end++;
    }

    public static int countPrefix(String t) {
        TrieNode cur = trie;
        int cnt = 0;
        for (char c : t.toCharArray()) {
            if (cur.chars[c - 'a'] != null) {
                cur = cur.chars[c - 'a'];
                cnt += cur.end;
            } else {
                break;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]);
        for (int i = 0; i < n; i++) {
            insert(scanner.nextLine());
        }
        for (int i = 0; i < m; i++) {
            System.out.println(countPrefix(scanner.nextLine()));
        }
    }
}
