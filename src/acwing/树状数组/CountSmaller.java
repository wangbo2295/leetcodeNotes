package acwing.树状数组;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountSmaller {
    int N = 20010, M = 10001;
    int[] tree = new int[N];
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) nums[i] += M;
        List<Integer> list = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            list.add(sum(nums[i] - 1) - sum(0));
            add(nums[i], 1);
        }
        Collections.reverse(list);
        return list;
    }

    public int sum(int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) {
            sum += tree[i];
        }
        return sum;
    }

    public void add(int i, int x) {
        for (; i < N; i += i & -i) {
            tree[i] += x;
        }
    }
}
