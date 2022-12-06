package leetcode.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class No786 {
    /**
     * 大顶堆保留前 k 个最小的元素，时间复杂度 O（n ^ 2 * logK）
     */
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        Comparator<int[]> comparator = (o1, o2)-> o2[0] * o1[1] - o1[0] * o2[1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] f1 = {arr[i], arr[j]};
                if (pq.size() < k) {
                    pq.offer(f1);
                    //这个坑注意，因为采用的是大顶堆，所以是逆序，比较器返回的符号也要跟着反过来，所以小于返回正数
                } else if (comparator.compare(f1, pq.peek()) > 0) {
                    pq.poll();
                    pq.offer(f1);
                }
            }
        }
        return pq.poll();
    }

    /**
     * 观察一个规律：
     * 对于固定的分母，分子越大分数越大
     * 如果枚举每个分母，那么可以组成 n - 1 个包含该分母的所有分数的数组
     * 由于每个数组是递增的，所以先将每个数组的第一个元素放入一个小顶堆中,那么最小的元素一定在其中
     * 然后取出堆顶元素[arr[i],arr[j]],这时只要 i + 1 < j 则将[arr[i + 1],arr[j]]放入堆中
     * 这样就能保证堆顶是未取出元素中的最小元素，那么第 k 次取出的就是第 k 小的元素。
     */
    public int[] kthSmallestPrimeFraction2(int[] arr, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->arr[o1[0]] * arr[o2[1]] - arr[o2[0]] * arr[o1[1]]);
        int n = arr.length;
        for (int j = 1; j < n; j++) {
            pq.offer(new int[] {0, j});
        }
        int[] min = null;
        for (int i = 1; i <= k; i++) {
            min = pq.poll();
            if (min[0] + 1 < min[1]) {
                pq.offer(new int[] {min[0] + 1, min[1]});
            }
        }
        return new int[] {arr[min[0]], arr[min[1]]};
    }

    public static void main(String[] args) {
        No786 no786 = new No786();
        int[] arr = {1,2,3,5};
        no786.kthSmallestPrimeFraction(arr, 3);
    }
}
