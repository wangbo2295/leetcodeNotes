package acwing.binarySearch;


public class SpecialSort {
    public int[] specialSort(int N) {
        int[] pre = new int[1];
        int[] cur = null;
        for (int i = 1; i <= N; i++) {
            cur = new int[i];
            System.arraycopy(pre, 0, cur, 0, i - 1);
            int l = -1, r = cur.length - 1;
            while (l + 1 < r) {
                int m = l + r >> 1;
                if (compare(cur[m], i)) {
                    l = m;
                } else {
                    r = m;
                }
            }
            for (int j = cur.length - 1; j > Math.max(0, l); j--) {
                cur[j] = cur[j - 1];
            }
            cur[r] = i;
            pre = cur;
        }
        return cur;
    }

    boolean compare(int a, int b) {
        return true;
    }
}

