package leetcode.WC;

import java.util.*;

public class No310 {
    public int mostFrequentEven(int[] nums) {
        int[] cnt = new int[100010];
        Arrays.sort(nums);
        int mcnt = 0, max = -1;
        for (int i : nums) {
            if ((i & 1) == 0) {
                cnt[i]++;
                if (cnt[i] > mcnt) {
                    mcnt = cnt[i];
                    max = i;
                }
            }
        }
        return max;
    }

    public int partitionString(String s) {
        int cnt = 1;
        int[] hash = new int[26];
        for (char c : s.toCharArray()) {
            if (hash[c - 'a'] > 0) {
                for (int i = 0; i < 26; i++) hash[i] = 0;
                ++cnt;
            }
            hash[c - 'a']++;
        }
        return cnt;
    }

    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2)->o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int cnt = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o[1])));
        for (int[] ints : intervals) {
            if (pq.isEmpty() || pq.peek()[1] >= ints[0]) {
                pq.offer(ints);
                cnt++;
            } else {
                pq.poll();
                pq.offer(ints);
            }
        }
        return cnt;
    }

    public int lengthOfLIS(int[] nums, int k) {
        return -1;
    }
}
