package leetcode.binarySearch;

import java.util.Arrays;

public class No475 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int min = Integer.MIN_VALUE;
        for (int house : houses) {
            int left = -1, right = heaters.length;
            while (left + 1 < right) {
                int mid = left + (right - left >> 1);
                if (heaters[mid] <= house) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            if (left == -1) {
                min = Math.max(min, heaters[0] - house);
            } else if (right == heaters.length) {
                min = Math.max(min, house - heaters[heaters.length - 1]);
            } else {
                min = Math.max(min, Math.min(house - heaters[left], heaters[right] - house));
            }
        }
        return min;
    }
}
