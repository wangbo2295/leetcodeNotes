package leetcode.WC;

import java.util.*;

public class No308 {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] > queries[i]) {
                    ans[i] = j;
                    break;
                }
            }
            if (nums[nums.length - 1] <= queries[i])
                ans[i] = nums.length;
        }
        return ans;
    }

    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '*' && !stack.empty()) stack.pop();
            else stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty())  sb.append(stack.pop());
        return sb.reverse().toString();
    }

    public int garbageCollection(String[] garbage, int[] travel) {
        int[] last = {-1, -1, -1};
        for (int i = garbage.length - 1; i >= 0; i--) {
            if (garbage[i].indexOf('M') >= 0) last[0] = Math.max(last[0], i);
            if (garbage[i].indexOf('P') >= 0) last[1] = Math.max(last[1], i);
            if (garbage[i].indexOf('G') >= 0) last[2] = Math.max(last[2], i);
        }
        int time = garbage[0].length(), veil = 0;
        for (int i = 1; i < garbage.length; i++) {
            for (int j = 0; j < 3; j++) if (last[j] >= i)   veil++;
            time += garbage[i].length() + veil * travel[i - 1];
        }
        return time;
    }

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] ans = new int[k][k];
        Map<Integer, Set<Integer>> edgesR = new HashMap<>();
        Map<Integer, Set<Integer>> edgesC = new HashMap<>();
        for (int i = 1; i <= k; i++) {
            edgesR.put(i, new HashSet<>());
            edgesC.put(i, new HashSet<>());
        }
        int[] degreeR = new int[k + 1];
        int[] degreeC = new int[k + 1];
        addEdge(rowConditions, edgesR, degreeR);
        addEdge(colConditions, edgesC, degreeC);
        List<Integer> orderR = topSort(edgesR, degreeR);
        List<Integer> orderC = topSort(edgesC, degreeC);
        if (orderC == null || orderR == null)   return ans;
        int[][] cor = new int[k + 1][2];
        for (int i = 0; i < orderR.size(); i++) cor[orderR.get(i)][0] = i;
        for (int i = 0; i < orderC.size(); i++) cor[orderC.get(i)][1] = i;
        for (int i = 1; i <= k; i++) ans[cor[i][0]][cor[i][1]] = i;
        return ans;
    }

    public void addEdge(int[][] conditions, Map<Integer, Set<Integer>> edges, int[] degree) {
        for (int[] row : conditions) {
            Set<Integer> verts = edges.get(row[0]);
            if (verts.add(row[1]))  degree[row[1]]++;
            edges.put(row[0], verts);
        }
    }

    public List<Integer> topSort(Map<Integer, Set<Integer>> edges, int[] degree) {
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < degree.length; i++) if (degree[i] == 0) {
            queue.offer(i);
        }
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            list.add(poll);
            Set<Integer> set = edges.get(poll);
            for (int v : set) {
                if (--degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        for (int i = 1; i < degree.length; i++) {
            if (degree[i] != 0) return null;
        }
        return list;
    }
}
