package acwing.堆;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 黑盒子
 * 给定一个输入序列 A ，一个查询序列 u
 * A[i] 表示第 i 次输入的数，u[i] 表示在前 u[i] 个数中查询第 i 大的数，i 从 1 开始
 * 按顺序输出所有查询结果，每个查询占一行
 */
public class BlackBox {
    /**
     * 思路：由于每次查询的是不同大小的数，用一个堆不好控制，因为只能输出最小的数
     * 可以这样做：用一个大顶堆维护前 i - 1 大的数，一个小顶堆维护之后的数
     * 那么小顶堆的堆顶就是第 i 大的数，我们只要在输入的过程中动态维护这两个对顶堆即可
     * 具体做法是：
     * 1、遍历查询数组，检查两个堆中的数是否达到所查询的前 k 个数的数量，如果不足则继续向堆中加入数
     * 2、如果新加入的数比大顶堆堆顶小，则将大顶堆堆顶的数加入小顶堆，并把该数加入大顶堆，重复该操作直到两个堆中总数等于 k。
     * 3、由于大顶堆的弹出和加入是成对的，所以大小不变，依旧等于 i - 1，此时小顶堆的堆顶就是所要查询的第 i 小的数
     * 4、然后将小顶堆的元素加入大顶堆，此时大顶堆个数变为 i + 1，为下个查询第 i + 1 小的数作准备
     * 5、依次处理每个查询即可。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt(), n = scanner.nextInt();
        int[] A = new int[m + 1], u = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            A[i] = scanner.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            u[i] = scanner.nextInt();
        }
        PriorityQueue<Integer> pre = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> after = new PriorityQueue<>();
        for (int i = 0, k = 1; i < n; i++) {
            while (pre.size() + after.size() < u[i + 1]) {
                if (!pre.isEmpty() && pre.peek() > A[k]) {
                    after.offer(pre.poll());
                    pre.offer(A[k++]);
                } else {
                    after.offer(A[k++]);
                }
            }
            System.out.println(after.peek());
            pre.offer(after.poll());
        }
    }
}
