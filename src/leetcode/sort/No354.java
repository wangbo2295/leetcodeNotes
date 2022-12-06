package leetcode.sort;

import java.util.*;

public class No354 {

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, Comparator.comparingInt((int[] o)->o[0]).thenComparing((o1, o2)->o2[1] - o1[1]));
        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; i++) {
            if (envelopes[i][1] > f.get(f.size() - 1)) {
                f.add(envelopes[i][1]);
            } else {
                int l = -1, r = f.size();
                while (l + 1 < r) {
                    int m = l + r >> 1;
                    if (f.get(m) < envelopes[i][1]) {
                        l = m;
                    } else {
                        r = m;
                    }
                }
                f.set(r, envelopes[i][1]);
            }
        }
        return f.size();
    }
}
