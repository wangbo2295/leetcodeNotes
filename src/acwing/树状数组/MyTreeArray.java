package acwing.树状数组;

public class MyTreeArray {
    int[] tree;

    public MyTreeArray(int[] nums) {
        int n = nums.length;
        tree = new int[n + 1];
        for (int i = 1; i <= n; i++) add(i, nums[i - 1]);
    }

    public void add(int i, int x) {
        for (; i < tree.length; i += i & -i) {
            tree[i] += x;
        }
    }

    public int sum(int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) {
            sum += tree[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        MyTreeArray myTreeArray = new MyTreeArray(nums);
        System.out.println(myTreeArray.sum(5) - myTreeArray.sum(0));
        myTreeArray.add(3, 1);
        System.out.println(myTreeArray.sum(5) - myTreeArray.sum(0));
    }
}
