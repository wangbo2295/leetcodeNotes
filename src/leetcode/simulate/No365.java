package leetcode.simulate;

public class No365 {
    /**
     * 用一个数组存储可能产生的水量
     * 想象成两桶水一直叠加，哪个多就计算一次差值，然后给另一桶叠加
     * 这期间产生的所有差值就是其中一桶为空时另一桶的水量，直到两个桶中的水相等跳出循环
     * 最后还要计算另一桶装满的情况，不过要从后往前遍历，避免重复计算
     */
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        int[] diff = new int[1000001];
        diff[jug1Capacity] = 1;diff[jug2Capacity] = 1;diff[jug1Capacity + jug2Capacity] = 1;
        int dif = jug1Capacity - jug2Capacity;
        diff[Math.abs(dif)] = 1;
        while (dif != 0) {
            while (dif > 0) {
                dif -= jug2Capacity;
                diff[Math.abs(dif)] = 1;
            }
            while (dif < 0) {
                dif += jug1Capacity;
                diff[Math.abs(dif)] = 1;
            }
        }
        for (int i = diff.length - 1; i >= 0; i--) {
            if (diff[i] == 1) {
                if (i + jug1Capacity < 1000001) {
                    diff[i + jug1Capacity] = 1;;
                }
                if (i + jug2Capacity < 1000001) {
                    diff[i + jug2Capacity] = 1;
                }
            }
        }
        return diff[targetCapacity] == 1;
    }
}
