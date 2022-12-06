package leetcode.simulate;

public class No495 {
    /**
     * 提莫攻击
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int start = timeSeries[0];
        int end = timeSeries[0] + duration - 1;
        int total = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] <= end) {
                end = timeSeries[i] + duration - 1;
            }else {
                total += end - start + 1;
                end = timeSeries[i] + duration;
                start = timeSeries[i];
            }
        }
        return total;
    }
}
