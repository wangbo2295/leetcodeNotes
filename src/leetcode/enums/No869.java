package leetcode.enums;

public class No869 {
    public boolean reorderedPowerOf2(int n) {
        int[] map = new int[10];
        int k = 0;
        while (n != 0) {
            map[n % 10]++;
            n /= 10;
            ++k;
        }
        tk = k;
        return dfs(map, 0, k);
    }
    int tk;
    public boolean dfs(int[] map, int num, int k) {
        if (k == 0 && (num ^ num & -num) == 0) return true;
        boolean res = false;
        for (int j = 0; j < map.length; j++) {
            if (map[j] != 0) {
                if (k == tk && j == 0) continue;
                map[j]--;
                res |= dfs(map, num * 10 + j, k - 1);
                map[j]++;
            }
        }
        return res;
    }
}
