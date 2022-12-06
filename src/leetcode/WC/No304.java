package leetcode.WC;

import java.util.Arrays;

public class No304 {
    public int minimumOperations(int[] nums) {
        int[] map = new int[101];
        for (int num : nums) {
            map[num]++;
        }
        int ans = 0;
        for (int i = 1; i < map.length; i++) {
            if (map[i] > 0) ++ans;
        }
        return ans;
    }

    /**
     * 两次dijisktra，求所有可达点的更大距离的最小值
     */
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        return -1;
    }

    public static void main(String[] args) {
        No304 no304 = new No304();
        int[] edges = {1,2,-1};
        no304.closestMeetingNode(edges, 0, 2);
    }
}
