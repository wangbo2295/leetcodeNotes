package leetcode.binarySearch;

import java.util.*;

public class TopVotedCandidate {
    Map<Integer, Integer> map;
    int[] times;
    int[] votes;
    int[] cur = {-1, -1};
    public TopVotedCandidate(int[] persons, int[] times) {
        this.map = new HashMap<>();
        this.times = times;
        this.votes = new int[times.length];
        for (int i = 0; i < times.length; i++) {
            Integer vote = map.getOrDefault(persons[i], 0);
            if (vote + 1 >= cur[0]) {
                cur[0] = vote + 1;
                cur[1] = persons[i];
            }
            map.put(persons[i], vote + 1);
            votes[i] = cur[1];
        }
    }

    public int q(int t) {
        int l = -1, r = votes.length;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (times[m] <= t) {
                l = m;
            } else {
                r = m;
            }
        }
        return votes[l];
    }
}
