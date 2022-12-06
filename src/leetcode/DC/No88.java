package leetcode.DC;

public class No88 {

    int N = 100010;
    int[] t = new int[N];

    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
        }
        long ans = 0;
        for (int i : nums1) {
            int j = i + 10001;
            long cnt = get(j + diff);
            ans += cnt;
            add(j, 1);
        }
        return ans;
    }

    public void add(int x, int val) {
        for (; x < N; x += x & -x) {
            t[x] += val;
        }
    }

    public long get(int x) {
        long sum = 0;
        for (; x > 0; x -= x & -x) {
            sum += t[x];
        }
        return sum;
    }
}

class LUPrefix {

    boolean[] videos;
    int idx = 0;

    public LUPrefix(int n) {
        videos = new boolean[n + 10];
    }

    public void upload(int video) {
        videos[video] = true;
        while (videos[idx + 1]) ++idx;
    }

    public int longest() {
        return idx;
    }
}
