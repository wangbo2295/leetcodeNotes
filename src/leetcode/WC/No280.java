package leetcode.WC;

import java.util.*;

public class No280 {
    public int minimumOperations(int[] nums) {
        Map<Integer, Integer> map0 = new TreeMap<>();
        Map<Integer, Integer> map1 = new TreeMap<>(Comparator.reverseOrder());
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                map0.put(nums[i], map0.getOrDefault(nums[i], 0) + 1);
            } else {
                map1.put(nums[i], map1.getOrDefault(nums[i], 0) + 1);
            }
        }
        Iterator<Map.Entry<Integer, Integer>> ite0 = map0.entrySet().iterator();
        Iterator<Map.Entry<Integer, Integer>> ite1 = map1.entrySet().iterator();
        Map.Entry<Integer, Integer> max0 = ite0.next();
        Map.Entry<Integer, Integer> second0 = ite0.hasNext() ? ite0.next() : null;
        Map.Entry<Integer, Integer> max1 = ite1.next();
        Map.Entry<Integer, Integer> second1 = ite1.hasNext() ? ite1.next() : null;
        int ans = 0;
        if (max0.getKey() != max1.getKey()) {
            ans = n - max0.getValue() - max1.getValue();
        } else {
            ans = n - Math.max(max0.getValue() + (second1 == null ? 0 : second1.getValue()),
                    max1.getValue() + (second0 == null ? 0 : second0.getValue()));
        }
        return ans;
    }

    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        long n = beans.length;
        long sum = 0;
        for (int bean : beans) {
            sum += bean;
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(sum - (long) beans[i] * (n - i), min);
        }
        return min;
    }


    int sum, n;
    public int maximumANDSum(int[] nums, int numSlots) {
        n = nums.length;
        dfs(nums, 0, numSlots, 0);
        return sum;
    }

    public void dfs(int[] nums, int used, int slot, int s) {
        if (used == (1 << n) - 1 && slot >= 0) {
            sum = Math.max(sum, s);
            return;
        }
        if (slot <= 0) return;
        for (int i = 0; i < n; i++) {
            if ((used & (1 << i)) == 0) {
                used |= 1 << i;
                dfs(nums, used, slot - 1, s + nums[i]);
                for (int j = 0; j < n; j++) {
                    if ((used & 1 << j) == 0) {
                        dfs(nums, used | (1 << j), slot - 1, s + (nums[i] & nums[j]));
                    }
                }
                used ^= 1 << i;
            }
        }
    }

    public static void main(String[] args) {
        No280 no280 = new No280();
        int[] nums = {1,2,3,4,5,6};
        no280.maximumANDSum(nums, 3);
    }
}
