package leetcode.WC;

import java.util.*;

public class No305 {
    public int arithmeticTriplets(int[] nums, int diff) {
        int cnt = 0, n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[k] - nums[j] == nums[j] - nums[i] && nums[j] - nums[i] == diff) cnt++;
                }
            }
        }
        return cnt;
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] vis = new boolean[n];
        for (int i : restricted) {
            vis[i] = true;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            addEdge(map, edge[0], edge[1]);
            addEdge(map, edge[1], edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        vis[0] = true;
        int ans = 1;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            List<Integer> list = map.get(poll);
            for (Integer integer : list) {
                if (!vis[integer]) {
                    queue.offer(integer);
                    vis[integer] = true;
                    ans++;
                }
            }
        }
        return ans;
    }
    public void addEdge(Map<Integer, List<Integer>> map, int a, int b) {
        List<Integer> edge = map.getOrDefault(a, new ArrayList<>());
        edge.add(b);
        map.put(a, edge);
    }

    public boolean validPartition(int[] nums) {
        int equal = 0, asc = 0;
        if (nums[0] == nums[1]) equal = 2;
        else if (nums[1] - nums[0] == 1)    asc = 2;
        else return false;
        for (int i = 3; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (asc > 0) {
                    if (asc == 3)    equal = 1;
                    else return false;
                    asc = 0;
                } else {
                    equal++;
                }
            } else if (nums[i] - nums[i - 1] == 1) {
                if (equal > 0) {
                    if (equal > 2)  asc = 2;
                    else if (equal == 2)    asc = 1;
                    else return false;
                    equal = 0;
                } else {
                    asc++;
                }
            } else {
                if (equal < 2 && asc < 3)   return false;
                asc = 1;
                equal = 0;
            }
        }
        if (equal != 0) return equal >= 2;
        else  return asc >= 3;
    }

    public int longestIdealString(String s, int k) {
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (Math.abs(s.charAt(i) - s.charAt(j)) <= k) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return dp[n - 1];
    }
}
