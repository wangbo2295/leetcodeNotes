package leetcode.enums;

public class No1620 {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int[] ans = new int[2];
        int max = 0;
        for (int x = 0; x <= 50; x++) {
            for (int y = 0; y <= 50; y++) {
                int total = 0;
                for (int[] tower: towers) {
                    int distance = distance(x, y, tower[0], tower[1]);
                    if (!inRadius(radius, distance))  continue;
                    total += signal(tower[2], distance);
                }
                if (total > max) {
                    max = total;
                    ans[0] = x;
                    ans[1] = y;
                }
            }
        }
        return ans;
    }

    public int distance(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
    public boolean inRadius(int radius, int distance) {
        return radius * radius >= distance;
    }
    public int signal(double q, int distance) {
        double d = Math.sqrt(distance);
        return (int) (q / (1 + d));
    }
}
