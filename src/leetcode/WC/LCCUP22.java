package leetcode.WC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LCCUP22 {
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int ans = 0, n = temperatureA.length;
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            int a = Integer.compare(temperatureA[i], temperatureA[i - 1]);
            int b = Integer.compare(temperatureB[i], temperatureB[i - 1]);
            if (a == b) cnt++;
            else cnt = 0;
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    public int transportationHub(int[][] path) {
        Set<Integer> set = new HashSet<>();
        int[] inde = new int[1001];
        int[] outde = new int[1001];
        for (int[] rel : path) {
            inde[rel[1]]++;
            outde[rel[0]]++;
            set.add(rel[0]);
            set.add(rel[1]);
        }
        for (int i = 0; i < 1001; i++) {
            if (inde[i] == set.size() - 1 && outde[i] == 0) return i;
        }
        return -1;
    }

    List<int[]> ans = new ArrayList<>();
    int[][] dir = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
    int m, n;
    public int[][] ballGame(int num, String[] plate) {
        m = plate.length; n = plate[0].length();
        for (int i = 1; i < m - 1; i++) {
            if (plate[i].charAt(0) == '.') if (dfs(i, 0, plate, 1, num)) ans.add(new int[]{i, 0});
            if (plate[i].charAt(n - 1) == '.') if (dfs(i, n - 1, plate, 3, num)) ans.add(new int[]{i, n - 1});
        }
        for (int i = 1; i < n - 1; i++) {
            if (plate[0].charAt(i) == '.') if (dfs(0, i, plate, 2, num)) ans.add(new int[]{0, i});

            if (plate[m - 1].charAt(i) == '.') if (dfs(m - 1, i, plate, 0, num)) ans.add(new int[]{m - 1, i});
        }
        int[][] ints = ans.toArray(new int[ans.size()][]);
        return ints;
    }

    public boolean dfs(int x, int y, String[] plate, int d, int num) {
        if (num < 0) return false;
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (plate[x].charAt(y) == 'O') {
            return true;
        }
        else if (plate[x].charAt(y) == 'E') d = (d + 1) % 4;
        else if (plate[x].charAt(y) == 'W') d = (d + 3) % 4;
        return dfs(x + dir[0][d], y + dir[1][d], plate, d, num - 1);
    }


    public int closeLampInTree(TreeNode root) {
        Node no = dfs(root);
        return Math.min(no.allClose, Math.min(no.allOpen + 1, no.subClose + 1));
    }

    public Node dfs(TreeNode root) {
        if (root == null) return new Node(0);
        Node node = new Node(Integer.MAX_VALUE);
        Node left = dfs(root.left);
        Node right = dfs(root.right);
        if (root.val == 0) {
            node.allClose = left.allClose + right.allClose;
            node.allOpen = Math.min(Math.min(left.allClose + right.allClose, left.allOpen + right.allOpen), left.subOpen + right.subOpen) + 1;
            node.subOpen = left.allOpen + right.allOpen;
            node.subClose = Math.min(Math.min(left.allOpen + right.allOpen, left.subClose + right.subClose), left.allClose + right.allClose) + 1;
        } else {
            node.allClose = Math.min(left.allClose + right.allClose, Math.min(left.allOpen + right.allOpen, left.subClose + right.subClose)) + 1;
            node.allOpen = left.allOpen + right.allOpen;
            node.subOpen = Math.min(left.allOpen + right.allOpen, Math.min(left.allClose + right.allClose, left.subOpen + right.subOpen)) + 1;
            node.subClose = left.allClose + right.allClose;
        }
        return node;
    }

    class Node {
        int allOpen, allClose, subOpen, subClose;
        public Node(int val) {
            allOpen = allClose = subOpen = subClose = val;
        }
    }

    public static void main(String[] args) {
        LCCUP22 lccup22 = new LCCUP22();
        String[] plate = {"....",".EE.","O.E.","...."};
        lccup22.ballGame(6, plate);
    }
}
