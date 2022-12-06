package leetcode.binarySearch;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

public class No315 {
    List<Integer> res = new ArrayList<>();
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] idxs = new int[n];    //保存原数组下标，排序时通过下标访问nums数组，实际修改的是该下标数组
        for (int i = 0; i < n; i++) res.add(-1);    //返回list就逆天，直接返回数组就好了，还得初始化
        for (int i = 0; i < idxs.length; i++)   idxs[i] = i;
        merge(nums, idxs, 0, n);
        return res;
    }

    public void merge(int[] nums, int[] idxs, int l, int r) {
        if (l + 1 == r) return; //左闭右开
        int m = l + r >> 1;
        merge(nums, idxs, l, m);
        merge(nums, idxs, m, r);
        int len = r - l;
        int[] t = new int[len];
        //此时已将左右两半区按从大到小排好序了，p1指向左半区的第一个未排序元素，p2指向右半区第一个未排序元素
        for (int i = 0, p1 = l, p2 = m; i < len; i++) {
            //如果 p1 指向的元素大于 p2 指向的元素，说明 p1 比 p2 及其之后的都大，个数为 r - p2
            //如果 p2 已指向 r ，则不再比较大小，直接将 p1 剩下的元素依次放入排序数组，另外比较大小时要满足 p1 < m
            if (p2 == r || p1 < m && nums[idxs[p1]] > nums[idxs[p2]]) {
                res.set(idxs[p1], res.get(idxs[p1]) + r - p2);
                t[i] = idxs[p1++];
            } else {    //否则说明 p2 指向的元素大于 p1 指向的元素，这时只需将该元素放入排序数组，因为 p1 之后的元素都不可能比其大
                t[i] = idxs[p2++];
            }
        }
        System.arraycopy(t, 0, idxs, l, len);
    }
}
