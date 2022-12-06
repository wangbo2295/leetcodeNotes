package leetcode.DC;

import java.util.*;

public class No84 {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Map<Integer, Integer> map = new TreeMap<>();
        add(items1, map);
        add(items2, map);
        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getKey();
            Integer weight = entry.getValue();
            res.add(List.of(value, weight));
        }
        return res;
    }

    public void add(int[][] items, Map<Integer, Integer> map) {
        for (int[] item: items) {
            Integer orDefault = map.getOrDefault(item[0], 0);
            orDefault += item[1];
            map.put(item[0], orDefault);
        }
    }

    public long countBadPairs(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] -= i;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        long ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int cnt = entry.getValue();
            if (cnt > 1) {
                ans += (long) cnt * (cnt - 1) >> 1;
            }
        }
        return (long) n * (n - 1) >> 1 - ans;
    }

    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> map = new HashMap<>();
        long ans = 0;
        for (int id : tasks) {
            Long task = map.getOrDefault(id, ans + 1);
            ans = Math.max(task, ans + 1);
            map.put(id, ans + space + 1);
        }
        return ans;
    }

    long ans;
    public long minimumReplacement(int[] nums) {
        for (int j = nums.length - 2; j >= 0; j--) {
            if (nums[j] > nums[j + 1]) {
                int tear = (nums[j] + nums[j + 1] - 1) / nums[j + 1];
                ans += tear - 1;
                nums[j] /= tear;
            }
        }
        return ans;
    }
}
