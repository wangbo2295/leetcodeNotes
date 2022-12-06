package leetcode.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class No378 {
    //优先队列法，每次弹出最小元素，第 k 次即为第 k 小元素
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->matrix[o[0]][o[1]]));
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[] {i, 0});
        }
        int[] min;
        for (int i = 1; i < k; i++) {
            min = pq.poll();
            if (min[0] + 1 < n) {
                min[0]++;
                pq.offer(min);
            }
        }
        min = pq.poll();
        return matrix[min[0]][min[1]];
    }

    //二分法，二分target，计算矩阵中小于等于target的个数cnt，找到cnt < k 对应的最大target
    //关键点：对于同一个cnt可能对应多个target，关键是要找到cnt == k 对应的最小target的值
    //也就是说小于该target，cnt也会减小，而大于target，cnt不一定减小，那么第 k 小的数就一定是target
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0] - 1, r = matrix[n - 1][n - 1] + 1;
        while (l + 1 < r) {
            int m = l + (r - l >> 1);
            int cnt = 0;
//            for (int[] ints : matrix) {
//                cnt += findPosition(ints, m); //每行再用二分计算数量，没有用到矩阵列为有序的性质
//            }
            int si = n - 1, sj = 0;
            while (si >= 0 && si < n && sj >= 0 && sj < n) {
                if (matrix[si][sj] <= m) {
                    cnt += si;
                    sj++;
                } else {
                    si--;
                }
            }
            if (cnt < k) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }

    int findPosition(int[] arr, int target) {
        int l = -1, r = arr.length;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (arr[m] <= target) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }
}
