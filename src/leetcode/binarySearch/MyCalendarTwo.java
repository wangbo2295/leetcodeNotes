package leetcode.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class MyCalendarTwo {

    List<int[]> booker = new ArrayList<>();

    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        int ll = -1, lr = booker.size();
        while (ll + 1 < lr) {
            int lm = ll + lr >> 1;
            if (booker.get(lm)[0] <= start) {
                ll = lm;
            } else {
                lr = lm;
            }
        }
        int rl = -1, rr = booker.size();
        while (rl + 1 < rr) {
            int rm = rl + rr >> 1;
            if (booker.get(rm)[1] >= end) {
                rr = rm;
            } else {
                rl = rm;
            }
        }
        for (int i = ll; i <= rr; i++) {
            int[] range = booker.get(i);
            if (range[0] < end && range[1] > start && range[2] > 1) return false;
        }
        for (int i = ll; i <= rr; i++) {
            int[] range = booker.get(i);
            if (range[1] <= start)  continue;
            if (range[0] >= end)    {}
        }
        return false;
    }
}
