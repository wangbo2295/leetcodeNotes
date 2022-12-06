package leetcode.WC;

import java.util.*;

public class No307 {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int totEn = 1;
        for (int en : energy) totEn += en;
        int initEx = 0, curex = 0;
        for (int ex : experience) {
            if (initEx + curex <= ex) {
                initEx = ex + 1 - curex;
            }
            curex += ex;
        }
        return totEn - initialEnergy + initEx - initialExperience;
    }

    public String largestPalindromic(String num) {
        int[] hash = new int[10];
        for (char c : num.toCharArray()) {
            hash[c - '0']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            while (hash[i] > 1 && !(i == 0 && sb.length() == 0)) {
                sb.append(i);
                hash[i] -= 2;
            }
        }
        StringBuilder reverse = new StringBuilder(sb).reverse();
        for (int i = 9; i >= 0; i--) {
            if (hash[i] > 0) {
                sb.append(i);
                break;
            }
        }
        return sb.append(reverse).toString();
    }


    Map<Integer, Set<Integer>> edges = new HashMap<>();
    boolean[] vis = new boolean[100001];
    public int amountOfTime(TreeNode root, int start) {
        addEdge(root);
        Queue<Integer> queue = new LinkedList<>();
        int sec = -1;
        queue.offer(start);
        vis[start] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer cur = queue.poll();
                Set<Integer> list = edges.get(cur);
                for (Integer next : list) {
                    if (!vis[next]) {
                        queue.offer(next);
                        vis[next] = true;
                    }
                }
            }
            sec++;
        }
        return sec;
    }

    private void addEdge(TreeNode root) {
        if (root == null)   return;
        Set<Integer> neigh = edges.getOrDefault(root.val, new HashSet<>());
        if (root.left != null) {
            neigh.add(root.left.val);
            Set<Integer> left = edges.getOrDefault(root.left.val, new HashSet<>());
            left.add(root.val);
            edges.put(root.left.val, left);
        }
        if (root.right != null) {
            neigh.add(root.right.val);
            Set<Integer> right = edges.getOrDefault(root.right.val, new HashSet<>());
            right.add(root.val);
            edges.put(root.right.val, right);
        }
        edges.put(root.val, neigh);
        addEdge(root.left);
        addEdge(root.right);
    }
}
