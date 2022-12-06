package leetcode.math;

/**
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 */
public class No812 {
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0.0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    maxArea = Math.max(calculateArea(points[i], points[j], points[k]), maxArea);
                }
            }
        }
        return maxArea;
    }

    public double calculateArea(int[] point1, int[] point2, int[] point3) {
        double tri1 = Math.sqrt(Math.pow(Math.abs(point1[0] - point2[0]), 2) + Math.pow(Math.abs(point1[1] - point2[1]), 2));
        double tri2 = Math.sqrt(Math.pow(Math.abs(point2[0] - point3[0]), 2) + Math.pow(Math.abs(point2[1] - point3[1]), 2));
        double tri3 = Math.sqrt(Math.pow(Math.abs(point3[0] - point1[0]), 2) + Math.pow(Math.abs(point3[1] - point1[1]), 2));
        double p = (tri1 + tri2 + tri3) / 2;
        double area = Math.sqrt(p * (p - tri1) * (p - tri2) * (p - tri3));
        return area;
    }

    public static void main(String[] args) {
        No812 no812 = new No812();
        int[][] nums = {{1, 0}, {0,0}, {0, 1}};
        no812.largestTriangleArea(nums);
    }
}
