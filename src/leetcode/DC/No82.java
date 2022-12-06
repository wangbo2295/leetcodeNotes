package leetcode.DC;

import java.util.Arrays;

public class No82 {

    public boolean evaluateTree(TreeNode root) {
        if (root == null) return true;
        return switch (root.val) {
            case 0 -> false;
            case 2 -> evaluateTree(root.left) | evaluateTree(root.right);
            case 3 -> evaluateTree(root.left) & evaluateTree(root.right);
            default -> true;
        };
    }

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int bus, pass, cnt;
        for (bus = pass = cnt = 0; pass < passengers.length; pass++) {
            if (passengers[pass] <= buses[bus]) {
                if (cnt == capacity)    return
                ++cnt;
                continue;
            }

        }
        return -1;
    }

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] nums = new long[n];
        for (int i = 0; i < nums1.length; i++) {
            nums[i] = Math.abs(nums1[i] - nums2[i]);
        }
        Arrays.sort(nums);
        int k = k1 + k2, kk = 1, i;
        for (i = n - 1; i > 0; i--) {
            if (kk >= k) break;
            if (nums[i] == nums[i - 1]) {
                kk++;
            } else {
                kk = kk * 2 + 1;
            }
        }
        long ans = 0;
        return ans;
    }

    public static void main(String[] args) {

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
