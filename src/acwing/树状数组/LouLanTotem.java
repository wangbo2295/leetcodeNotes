package acwing.树状数组;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 利用树状数组统计逆序对的思想
 * 以数据范围建立树状数组，下标存储的为出现次数，那么前缀和相减则为某区间范围内数的总个数
 * 将坐标按 x 排序（本题输入数据默认有序），然后正序 + 倒序遍历坐标，分别查询大于和小于当前数的个数，并记录在数组中
 * 遍历每个下标，将左边大的个数和右边大的个数相乘累加到 V 型图腾中，^ 型图腾则相反
 */
public class LouLanTotem {
    static int N = 200010;
    static int n;
    static int[] tree = new int[N];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[] nums = new int[n + 1];
        int[] left_larger = new int[n + 1], right_larger = new int[n + 1];
        int[] left_less = new int[n + 1], right_less = new int[n + 1];
        long v = 0, u = 0;
        for (int i = 1; i <= n; i++)    nums[i] = scanner.nextInt();
        for (int i = n; i > 0; i--) {
            right_larger[i] = sum(N - 1) - sum(nums[i]);
            right_less[i] = sum(nums[i] - 1) - sum(0);
            add(nums[i], 1);
        }
        Arrays.fill(tree, 0);
        for (int i = 1; i <= n; i++) {
            left_larger[i] = sum(N - 1) - sum(nums[i]);
            left_less[i] = sum(nums[i] - 1) - sum(0);
            add(nums[i], 1);
        }
        for (int i = 1; i <= n; i++) {
            v += (long) left_larger[i] * right_larger[i];
            u += (long) left_less[i] * right_less[i];
        }
        System.out.println(v + " " + u);
    }

    public static void add(int i, int x) {
        for (; i < N; i += i & -i) {
            tree[i] += x;
        }
    }

    public static int sum(int i) {
        int sum = 0;
        for (; i > 0 ; i -= i & -i) {
            sum += tree[i];
        }
        return sum;
    }
}
