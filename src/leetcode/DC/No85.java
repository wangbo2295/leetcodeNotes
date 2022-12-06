package leetcode.DC;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class No85 {
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            presum[i] += presum[i - 1];
            if (blocks.charAt(i - 1) == 'B') {
                presum[i]++;
            }
        }
        int min = k;
        for (int i = k; i <= n; i++) {
            min = Math.min(min, k - (presum[i] - presum[i - k]));
        }
        return min;
    }

    public int secondsToRemoveOccurrences(String s) {
        int n = s.length(), i = 0;
        char[] str = s.toCharArray();
        for (; ; i++) {
            boolean end = true;
            for (int j = 1; j < n; j++) {
                if (str[j] == '1' && str[j - 1] == '0') {
                    end = false;
                    str[j] = '0';
                    str[j - 1] = '1';
                    j++;
                }
            }
            if (end)    break;
        }
        return i;
    }

    int N = 50010;
    int[] b = new int[N];
    int[] a = new int[N];
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        for (int i = 0; i < s.length(); i++) {
            a[i + 1] = s.charAt(i) - 'a';
        }
        for (int[] shift : shifts) {
            int x = shift[2] == 1 ? 1 : -1;
            set(shift[0] + 1, shift[1] + 1, x);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int c = query(i);
            while (c < 0)   c += 26;
            sb.append((char) ('a' + (c % 26)));
        }
        return sb.toString();
    }

    public int query(int i) {
        return a[i] + sum(i);
    }

    public void set(int l, int r, int x) {
        add(l, x);
        add(r + 1, -x);
    }

    private void add(int i, int x) {
        for (; i < N; i += i & -i) {
            b[i] += x;
        }
    }

    private int sum(int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) {
            sum += b[i];
        }
        return sum;
    }

    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        TreeSet<Integer> set0 = new TreeSet<>();
        TreeSet<Long> sets = new TreeSet<>();
        int n = nums.length;
        long[] presum = new long[n + 1];
        long[] ans = new long[n];
        for (int i = 1; i <= n; i++)    presum[i] = presum[i - 1] + nums[i - 1];
        set0.add(0);
        set0.add(n + 1);
        sets.add(presum[n]);
        for (int i = 0; i < n; i++) {
            int j = removeQueries[i] + 1;
            int l = set0.floor(j);
            int r = set0.ceiling(j);
            set0.add(j);
            long sum = presum[r - 1] - presum[l];
            long lsum = presum[j - 1] - presum[l];
            long rsum = presum[r - 1] - presum[j];
            sets.remove(sum);
            sets.add(lsum);
            sets.add(rsum);
            ans[i] = sets.floor(Long.MAX_VALUE);
        }
        return ans;
    }
}
