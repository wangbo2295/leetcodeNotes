package leetcode.math;

/**
 * 概率论我不会啊。。。
 * 这个解法是错的
 */
public class No478 {
    double radius;
    double x_center;
    double y_center;
    public No478(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
    }

    public double[] randPoint() {
        double p = Math.random() * radius;
        double theta = Math.random() * 2 * Math.PI;
        double x = x_center + p * Math.cos(theta);
        double y = y_center + p * Math.sin(theta);
        return new double[] {x, y};
    }
}
