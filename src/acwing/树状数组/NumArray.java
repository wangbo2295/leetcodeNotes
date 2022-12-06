package acwing.树状数组;

public class NumArray {
    int N = 30010;
    int[] tree = new int[N];
    int[] arr;
    public NumArray(int[] nums) {
        int n = nums.length;
        arr = nums;
        //O(N) 的初始化，从小到大依次扫描每个节点，并将子节点的值累加到当前节点
        //如何得到每个节点的子节点？
        //观察到每个节点的左上节点（或者说兄弟节点）的下标为 i - (i & -i), 由此得知节点 i 的子节点值一定大于这个值
        //观察树的结构可知， 节点 i - 1 为一个子节点，不断减去 lowbit 可依次得到所有子节点。
        System.arraycopy(nums, 0, tree, 1, n);
        for (int i = 2; i < N; i += 2) {
            for (int x = i - 1; x > i - (i & -i); x -= x & -x) {
                tree[i] += tree[x];
            }
        }
    }

    public void update(int index, int val) {
        int diff = val - arr[index];
        arr[index] = val;
        add(index + 1, diff);
    }

    public int sumRange(int left, int right) {
        return sum(right + 1) - sum(left);
    }

    private void add(int index, int x) {
        for (; index < N; index += index & -index) {
            tree[index] += x;
        }
    }

    private int sum(int index) {
        int sum = 0;
        for (; index > 0; index -= index & -index) {
            sum += tree[index];
        }
        return sum;
    }
}