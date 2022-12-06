package leetcode.WC;

import java.util.Arrays;
import java.util.List;

public class No300 {
    public String decodeMessage(String key, String message) {
        int[] map = new int[26];
        Arrays.fill(map, -1);
        for (int i = 0, j = 0; j < key.length(); j++) {
            if (key.charAt(j) == ' ' || map[key.charAt(j) - 'a'] >= 0) {
                continue;
            }
            map[key.charAt(j) - 'a'] = i++;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                res.append(' ');
            } else {
                res.append((char) (map[message.charAt(i) - 'a'] + 'a'));
            }
        }
        return res.toString();
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i], -1);
        }
        int i = 0, j = 0;
        int[][] dir = {{0, 1, 0, -1}, {1, 0, -1, 0}};
        int k = 0;
        while (head != null) {
            matrix[i][j] = head.val;
            if (i + dir[0][k] < 0 || i + dir[0][k] >= m
                    || j + dir[1][k] < 0 || j + dir[1][k] >= n
                        || matrix[i + dir[0][k]][j + dir[1][k]] != -1) {
                ++k;
            }
            i += dir[0][k];
            j += dir[1][k];
            head = head.next;
        }
        return matrix;
    }
}
