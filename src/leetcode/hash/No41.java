package leetcode.hash;

public class No41 {
    public int firstMissingPositive(int[] nums) {
        //数组分为两个部分，1-n的和其他
        //对1-n之间的数进行映射，最后第一个没被映射的下标就是缺失的第一个正数
        //如何区分被映射和没被映射？
        //对被映射的下标取相反数
        //如何在一个数被映射后复现其原来的数？
        //将1-n以外的置为n+1，只对绝对值在1-n以内的映射，下标取绝对值-1即可
        //注意：有重复元素，如果当前已经为负数，则不进行映射
        //做到以上两点，就可以遍历数组进行映射然后再次遍历找到没被映射的下标

        //将1-n以外的数置为n + 1
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        //将下标0-n-1的数取相反数，对于1-n以外的数，取相反数之后绝对值大于n
        //对于1-n以内的数，取相反数后绝对值还是本身
        for (int i = 0; i < n; i++) {
            int abs = Math.abs(nums[i]);
            if (abs <= n && nums[abs - 1] > 0) {//由于有重复的数，所以只映射一次
                nums[abs - 1] *= -1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i + 1;
        }
        return n + 1;
    }

    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)   return i + 1;
        }
        return n + 1;
    }

    public void swap(int[] nums, int i, int j) {int t = nums[i]; nums[i] = nums[j]; nums[j] = t;}

    public static void main(String[] args) {
        int[] nums = {1,2,0};
        No41 no41 = new No41();
        no41.firstMissingPositive(nums);
    }
}
