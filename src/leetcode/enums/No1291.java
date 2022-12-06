package leetcode.enums;

import java.util.ArrayList;
import java.util.List;

public class No1291 {
    public List<Integer> sequentialDigits(int low, int high) {
        int l = calculateDigits(low), h = calculateDigits(high);
        List<Integer> res = new ArrayList<>();
        for (int k = l; k <= h; k++) {
            int pattern = getKPattern(k);
            int lb = getLowBoundByK(k);
            if (lb >= low && lb <= high) res.add(lb);
            for (int i = 0; i < 9 - k; i++) {
                lb += pattern;
                if (lb >= low && lb <= high) res.add(lb);
            }
        }
        return res;
    }
    int calculateDigits(int num) {
        int cnt = 0;
        while (num > 0) {
            num /= 10;
            ++cnt;
        }
        return cnt;
    }
    int getKPattern(int k) {
        int pattern = 0;
        while (k > 0) {
            pattern = pattern * 10 + 1;
            --k;
        }
        return pattern;
    }
    int getLowBoundByK(int k) {
        int low = 0;
        for (int i = 1; i <= k; i++) {
            low = low * 10 + i;
        }
        return low;
    }
}
