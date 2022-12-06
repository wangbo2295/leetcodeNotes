package leetcode.WC;

import java.util.Arrays;

public class No282 {
    public int prefixCount(String[] words, String pref) {
        int cnt = 0;
        for (String word : words) {
            if (word.startsWith(pref)) ++cnt;
        }
        return cnt;
    }

    public int minSteps(String s, String t) {
        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            map[c - 'a']--;
        }
        int ans = 0;
        for (int i = 0; i < map.length; i++) {
            ans += Math.abs(map[i]);
        }
        return ans;
    }

    public long minimumTime(int[] time, int totalTrips) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, n = time.length;
        for (int i : time) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        long left = (long) ((totalTrips - 1) / n + 1) * min - 1;
        long right = (long) ((totalTrips - 1) / n + 1) * max + 1;
        while (left + 1 < right) {
            long mid = left + (right - left >> 1);
            long total = 0;
            for (int i = 0; i < time.length; i++) {
                total += mid / time[i];
            }
            if (total < totalTrips) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

}
