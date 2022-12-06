package leetcode.enums;

import java.util.Arrays;

public class No949 {
    public String largestTimeFromDigits(int[] arr) {
        int maxh = -1, maxm = -1;
        for (int i = 0; i < 4; i++) {
            if (arr[i] > 2) continue;
            for (int j = 0; j < 4; j++) {
                if (j == i) continue;
                int h = arr[i] * 10 + arr[j];
                if (h > 23) continue;
                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j || arr[k] > 5)   continue;
                    for (int l = 0; l < 4; l++) {
                        if (l == i || l == j || l == k) continue;
                        int m = arr[k] * 10 + arr[l];
                        if (h > maxh || (h == maxh && m > maxm)) {
                            maxh = h;
                            maxm = m;
                        }
                    }
                }
            }
        }
        if (maxh < 0 || maxm < 0)   return "";
        return "" + (maxh >= 10 ? maxh : "0" + maxh) + ":" + (maxm >= 10 ? maxm : "0" + maxm);
    }
}
