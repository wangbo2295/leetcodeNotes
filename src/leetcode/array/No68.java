package leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class No68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<int[]> intervals = new ArrayList<>();
        int start = 0, sum = 0;
        for (int i = 0; i < words.length; i++) {
            if (sum + words[i].length() + i - start <= maxWidth) {
                sum += words[i].length();
            } else {
                intervals.add(new int[]{start, i - 1, sum});
                start = i;
                sum = words[i].length();
            }
        }
        intervals.add(new int[]{start, words.length - 1, sum});
        List<String> res = new ArrayList<>();
        for (int i = 0; i < intervals.size() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            int[] interval = intervals.get(i);
            int tot = maxWidth - interval[2];
            int k = interval[1] - interval[0];
            if (k == 0) {
                sb.append(words[interval[0]]).append(" ".repeat(tot));
            } else {
                int floor = tot / k;
                int ceil = tot / k + 1;
                for (int j = interval[0]; j < interval[1]; j++) {
                    if (k * floor != tot) {
                        sb.append(words[j]).append(" ".repeat(ceil));
                        tot -= ceil;
                    } else {
                        sb.append(words[j]).append(" ".repeat(floor));
                        tot -= floor;
                    }
                    k--;
                }
                sb.append(words[interval[1]]);
            }
            res.add(sb.toString());
        }
        StringBuilder sb = new StringBuilder();
        int[] ints = intervals.get(intervals.size() - 1);
        for (int i = ints[0]; i < ints[1]; i++) sb.append(words[i]).append(" ");
        sb.append(words[ints[1]]);
        sb.append(" ".repeat(maxWidth - (ints[2] + ints[1] - ints[0])));
        res.add(sb.toString());
        return res;
    }
}
