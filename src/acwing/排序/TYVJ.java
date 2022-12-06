package acwing.排序;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 七夕节因牛郎织女的传说而被扣上了「情人节」的帽子。于是 TYVJ 今年举办了一次线下七夕祭。Vani 同学今年成功邀请到了 cl 同学陪他来共度七夕，于是他们决定去 TYVJ 七夕祭游玩。
 * TYVJ 七夕祭和 11 区的夏祭的形式很像。矩形的祭典会场由 N 排 M 列共计 N×M 个摊点组成。
 * 虽然摊点种类繁多，不过 cl 只对其中的一部分摊点感兴趣，比如章鱼烧、苹果糖、棉花糖、射的屋……什么的。
 * Vani 预先联系了七夕祭的负责人 zhq，希望能够通过恰当地布置会场，使得各行中 cl 感兴趣的摊点数一样多，并且各列中 cl 感兴趣的摊点数也一样多。
 * 不过 zhq 告诉 Vani，摊点已经随意布置完毕了，如果想满足 cl 的要求，唯一的调整方式就是交换两个相邻的摊点。
 * 两个摊点相邻，当且仅当他们处在同一行或者同一列的相邻位置上。
 * 由于 zhq 率领的 TYVJ 开发小组成功地扭曲了空间，每一行或每一列的第一个位置和最后一个位置也算作相邻。
 * 现在 Vani 想知道他的两个要求最多能满足多少个。
 * 在此前提下，至少需要交换多少次摊点。
 * 输入格式
 * 第一行包含三个整数 N 和 M 和 T ，T 表示 cl 对多少个摊点感兴趣。
 * 接下来 T 行，每行两个整数 x,y ，表示 cl 对处在第 x 行第 y 列的摊点感兴趣。
 * 输出格式
 * 首先输出一个字符串。
 * 如果能满足 Vani 的全部两个要求，输出 both；
 * 如果通过调整只能使得各行中 cl 感兴趣的摊点数一样多，输出 row；
 * 如果只能使各列中 cl 感兴趣的摊点数一样多，输出 column；
 * 如果均不能满足，输出 impossible。
 * 如果输出的字符串不是 impossible， 接下来输出最小交换次数，与字符串之间用一个空格隔开。
 * 数据范围
 * 1≤N,M≤100000,0≤T≤min(N∗M,100000),1≤x≤N,1≤y≤M
 */
public class TYVJ {
    /**
     * 本题考察：均分纸牌（前缀和）、仓库选址（中位数）
     * 思路：
     * 1、对于左右交换的摊位，不影响这一行的摊位数量，对于上下同理，故可以分别计算行和列的交换次数
     * 2、要使每行（每列）的摊位个数相等，首先要看总数能不能整除行（列）数，然后就转化为均分纸牌的问题
     * 3、因为只能相邻交换，所以可以按循序依次交换，如果多于平均数则交给下一个人，否则从下一个人那里拿。
     * 对于每次交换的个数，可以用前缀和来计算，对于某个前缀 i ，其应有的总数为 i * avg，实有与应有的差的绝对值就是这次交换的个数
     * 公式表示为 sum(|i * avg - G[i]|) ，G[i]为每行（列）的摊位个数的前缀和。
     * 4、如果事先就把摊位个数减去avg，那么上式转化为 sum(S[i]), S[i]为减去avg后（A[k]）的前缀和
     * 5、由于第一行和最后一行也算相邻，所以演变成一个环形的均分纸牌问题。通过普通的均分纸牌问题知道，第一个和最后一个没有交换
     * 如果均分可行，那么在环中会有两个相邻的摊位没有进行交换，因此可以枚举环的断点，取最优解
     * 6、假设从 k 处断开，那么起点为 A[k + 1],终点为 A[k]
     * 因为 S[k + 1] = S[k] + A[k + 1],应当修正为 0 + A[k + 1]，对于后续的前缀和同理，每个都要减去S[k]，最后S[k] = 0，表示最后均摊为0
     * 这样一来交换次数就变成 sum(S[i] - S[k]), 仔细观察发现，这就是之前的货仓选址问题。
     * 我们不需要枚举每个货仓位置（S[k]）只需要将 S 排序，选中位数即可
     *
     * 货仓选址证明：
     * 假设选址在 x 处，左边有 P 家商店，右边有 Q 家商店。
     * 如果 P > Q：
     *      货仓向左移动一步，总距离的增量为 -P + Q < 0
     * 如果 Q > P:
     *      货仓向右移动一步，总距离的增量为 Q - P < 0
     * 由此可见，当 P ！= Q 时，总能通过移动货仓位置来使得总距离更小，因此货仓的最佳位置就是中位数处。
     * 如果商店个数为奇数，货仓位置就在中位数商店所在位置，如果是偶数，则在中位数左右两个商店之间的任意位置都是最优位置。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt(), t = scanner.nextInt();
        int[] rows = new int[n];
        int[] cols = new int[m];
        for (int i = 0; i < t; i++) {
            int r = scanner.nextInt() - 1, c = scanner.nextInt() - 1;
            rows[r]++;
            cols[c]++;
        }
        long row = calc(rows, n, t);
        long col = calc(cols, m, t);
        if (row >= 0 && col >= 0) {
            System.out.println("both " + row + col);
        } else if (row >= 0) {
            System.out.println("row " + row);
        } else if (col >= 0) {
            System.out.println("column " + col);
        } else {
            System.out.println("impossible");
        }
    }

    public static long calc(int[] arr, int n, int t) {
        if (t % n != 0) {
            return -1;
        }
        //每个数减去平摊数avg，并原地计算前缀和
        arr[0] -= t / n;
        for (int i = 1; i < n; i++) {
            arr[i] += arr[i - 1] - t / n;
        }
        Arrays.sort(arr);//排序
        //累加结果
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(arr[n >> 1] - arr[i]);
        }
        return sum;
    }
}
