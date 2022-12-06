package leetcode.binarySearch;

import java.util.Arrays;

public class MyCalendarOne {

    int[][] booked;
    int size;
    public MyCalendarOne() {
        booked = new int[1000][2];
        Arrays.fill(booked, new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE});
    }

    public boolean book(int start, int end) {
        int l = -1, r = size;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (booked[m][1] <= start) {
                l = m;
            } else {
                r = m;
            }
        }
        if (booked[r][0] < end) return false;
        for (int i = size; i > r; i--) {
            booked[i] = booked[i - 1];
        }
        booked[r] = new int[]{start, end};
        size++;
        return true;
    }
}
