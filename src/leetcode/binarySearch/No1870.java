package leetcode.binarySearch;

/**
 * 实数域上的二分
 * l + 1 < r 的条件改为 l + eps < r，其中 eps 为精度，当精度不好确定的时候，可以采用固定次数的二分，例如循环 100 次
 */
public class No1870 {
    public int minSpeedOnTime(int[] dist, double hour) {
        double l = -1, r = Integer.MAX_VALUE, eps = 0.01;
//        for (int i = 0; i < 70; i++) {
//            double m = (l + r) / 2;
//            if (check(dist, m, hour)) r = m;
//            else l = m;
//        }
        while (l + eps < r) {
            double m = (l + r) / 2;
            if (check(dist, m, hour)) r = m;
            else l = m;
        }
        return r > 1e7 ? -1 : (int) Math.ceil(r);
    }

    public boolean check(int[] dist, double speed, double hour) {
        double time = 0;
        for (int j = 0; j < dist.length; j++) {
            int i = dist[j];
            if (j < dist.length - 1) time += (int) Math.ceil(i / speed);
            else time += i / speed;
        }
        return time <= hour;
    }
}
