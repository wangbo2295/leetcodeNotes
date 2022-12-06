package leetcode.doublePointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class No904 {
    /**
     * map 实现计数
     */
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0, cnt = 0;
        for (int i = 0, j = 0; j < fruits.length; j++) {
            if (map.containsKey(fruits[j])) {
                map.put(fruits[j], map.get(fruits[j]) + 1);
            } else {
                map.put(fruits[j], 1);
                cnt++;
            }
            while (cnt > 2) {
                int t = map.get(fruits[i]);
                if (t == 1) {
                    map.remove(fruits[i]);
                    --cnt;
                } else {
                    map.put(fruits[i], t - 1);
                }
                i++;
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
}
