package acwing.binarySearch;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个升序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组 {3,4,5,1,2}为 {1,2,3,4,5}的一个旋转，该数组的最小值为 1。
 * 数组可能包含重复项。
 * 注意：数组内所含元素非负，若数组大小为 0，请返回 −1。
 */
public class FindMin {
    /**
     * 由于数组两端可能会有相等的重复段，破坏了二分的单调性，因此想办法处理一下以满足单调性。
     * 删除数组右端的重复段，这样一来左半段一定大于等于nums[0]，右半段一定严格小于nums[0]。
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 0) return -1;
        int l = -1, r = n - 1;
        while (r >= 0 && nums[r] == nums[0]) --r;
        if (l >= r)  return nums[0];
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (nums[m] > nums[r]) {
                l = m;
            } else {
                r = m;
            }
        }
        return nums[r];
    }
}
