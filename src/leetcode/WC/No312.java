package leetcode.WC;

import java.util.*;

public class No312 {
    public int longestSubarray(int[] nums) {
        int max = 0;
        for (int i : nums) max = Math.max(max, i);
        int cnt = 0, len = 0, pre = max;
        for (int num : nums) {
            if (num == max) ++cnt;
            else cnt = 0;
            len = Math.max(len, cnt);
        }
        return len;
    }

    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        int[] desc = new int[n];
        int[] incr = new int[n];
        int des = 1, inc = 1;
        desc[0] = 1;
        incr[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i - 1]) ++des;
            else des = 1;
            desc[i] = des;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] >= nums[i + 1]) ++inc;
            else inc = 1;
            incr[i] = inc;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = k; i < n - k; i++) {
            if (desc[i - 1] >= k && incr[i + 1] >= k) {
                ans.add(i);
            }
        }
        return ans;
    }


    boolean[] used;
    int cnt = 0;
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        used = new boolean[n];
        int[] c = new int[100010];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
            c[vals[i]]++;
        }
        for (int[] edge : edges) {
            int v1 = edge[0], v2 = edge[1];
            Set<Integer> m1 = map.get(v1);
            Set<Integer> m2 = map.get(v2);
            m1.add(v2);
            m2.add(v1);
            map.put(v1, m1);
            map.put(v2, m2);
        }
        for (int i = 0; i < n; i++) {
            if (c[vals[i]] > 1) {
                used[i] = true;
                dfs(vals, map, i, i);
                used[i] = false;
            }
        }
        return cnt / 2 + n;
    }

    public void dfs(int[] vals, Map<Integer, Set<Integer>> map, int cur, int target) {
        if (vals[cur] > vals[target]) return;
        else if (cur != target && vals[cur] == vals[target]) ++cnt;
        Set<Integer> set = map.get(cur);
        for (Integer v : set) {
            if (used[v]) continue;
            used[v] = true;
            dfs(vals, map, v, target);
            used[v] = false;
        }
    }

    public static void main(String[] args) {
        No312 no312 = new No312();
        int[] vals = {1,3,2,1,3};
        int[][] edges = {{0,1},{0,2},{2,3},{2,4}};
        no312.numberOfGoodPaths(vals, edges);
    }
}
