package leetcode.bitSet;

import java.util.ArrayList;
import java.util.List;

public class No401 {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        if (turnedOn > 8)   return res;
        for (int i = 0; i <= turnedOn; i++) {
            for (int j = 0; j < 1 << 4; j++) {
                if (countBit(j) == i && j < 12) {
                    String prefix = "" + j;
                    for (int k = 0; k < 1 << 6; k++) {
                        if (countBit(k) == turnedOn - i && k < 60) {
                            res.add(prefix + ":" + (k < 10 ? "0" + k : "" + k));
                        }
                    }
                }
            }
        }
        return res;
    }

    public int countBit(int num) {
        int cnt = 0;
        while (num > 0) {
            num -= num & -num;
            ++cnt;
        }
        return cnt;
    }
}
