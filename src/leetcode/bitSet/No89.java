package leetcode.bitSet;
import java.util.*;
public class No89 {
    public List<Integer> grayCode(int n) {
        boolean[] map = new boolean[1 << 16];
        List<Integer> res = new ArrayList<>();
        int pre = 0;
        res.add(pre);
        map[0] = true;
        while (res.size() < (1 << n)) {
            for (int i = 0; i < n; i++) {
                if (!map[pre ^ (1 << i)]) {
                    map[pre ^ (1 << i)] = true;
                    pre ^= 1 << i;
                    break;
                }
            }
        }
        return res;
    }
}
