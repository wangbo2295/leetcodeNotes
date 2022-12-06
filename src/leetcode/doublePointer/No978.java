package leetcode.doublePointer;

public class No978 {
    /**
     * diff 表示最后一个数减去倒数第二个数的符号，初始为 0
     * 如果下一个符号与当前相同（相等的情况另外处理），则放弃之前的结果，从当前两个元素重新开始统计
     * 相等为特殊情况，如果相等，要重置为一个元素
     * 剩下就是符号相反，只需更新当前符号并记录最大值即可
     */
    public int maxTurbulenceSize(int[] arr) {
        int max = 1, diff = 0;
        for (int i = 0, j = 1; j < arr.length; j++) {
            int cmp = Integer.compare(arr[j], arr[j - 1]);
            if (cmp != 0 && cmp == diff) i = j - 1;
            else if (cmp == 0)  i = j;
            else {
                max = Math.max(max, j - i + 1);
                diff = cmp;
            }
        }
        return max;
    }
}
