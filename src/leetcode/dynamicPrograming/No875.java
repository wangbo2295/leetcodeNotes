package leetcode.dynamicPrograming;

import java.util.Arrays;

/**
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 */
public class No875 {

    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int low = 0, high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        ++high;
        while (low + 1 < high) {
            int k = low + (high - low >> 1);
            int time = getTime(piles, k);
            if (time <= h) {
                high = k;
            }else {
                low = k;
            }
        }
        return high;
    }

    public int getTime(int[] piles, int k) {
        int time = 0;
        for (int pile : piles) {
            time += (pile + k - 1) / k;
        }
        return time;
    }
}
