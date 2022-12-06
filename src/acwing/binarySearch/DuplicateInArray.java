package acwing.binarySearch;

/**
 * 给定一个长度为 n+1 的数组nums，数组中所有的数均在 1∼n 的范围内，其中 n ≥ 1 。
 * 请找出数组中任意一个重复的数，但不能修改输入的数组。
 * 数据范围
 * 1≤n≤1000
 */
public class DuplicateInArray {
    /**
     * 注意到数组长度比值域大，也就是说数组内所有元素都可以映射到数组内
     * 将数组中的数看成指针，指向数组内的其他位置，由于有重复元素，所以会形成环
     * 问题转化为求环形链表入口
     * 实际上，由于数的值域在 1 - n，下标 0 不会被映射到，以链表的角度看就是一个dummy
     * 定义两个快慢指针，从下标 0 开始，慢指针每次走一步，快指针每次走两步
     * 快指针相对慢指针每次走一步，所以相遇时一定是慢指针第一次进入环
     * 定义起点到环入口距离为 a ，环入口到相遇点距离为 b ，环剩下长度为 c 。
     * 则相遇时慢指针走了a + b，快指针走了 a + n * (b + c) + b，由速度关系可得：
     * 2 * (a + b) = a + n * (b + c) + b，化简得，
     * a = n * (b + c) - b
     * 画出示意图，观察到左边是起点到环入口距离，右边是从相遇点开始在环中走 n 圈后停留在环入口的距离
     * 所以令一个指针从起点开始，一个指针从相遇点开始，两者相遇的点即为环入口。
     */
    public int duplicateInArray(int[] nums) {
        int s = 0, f = 0;
        do {
            s = nums[s];
            f = nums[nums[f]];
        } while (s != f);
        for (int i = 0; nums[i] != nums[s]; i = nums[i], s = nums[s]);
        return nums[s];
    }

    /**
     * 二分的通用写法
     * 需要注意的是，当题目一定有解时，初始区间要设定为定义域的闭区间
     * 当题目不一定有解时，初始区间要设定为定义域的开区间
     * 这样就不会因为无解而得到不存在的解或在一定有解的情况下得到无解的结果。
     */
    public int duplicateInArray2(int[] nums) {
        int l = 1, r = nums.length;
        while (l + 1 < r) {
            int m = l + r >> 1, cnt = 0;
            for (int i: nums) {
                if (i >= l && i < m)   ++cnt;
            }
            if (cnt > m - l) {
                r = m;
            } else {
                l = m;
            }
        }
        return l;
    }
}
