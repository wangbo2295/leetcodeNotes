package leetcode.practice;

/**
 * 一个下标从 0开始的数组的 交替和定义为 偶数下标处元素之 和减去 奇数下标处元素之 和。
 *
 * 比方说，数组[4,2,5,3]的交替和为(4 + 5) - (2 + 3) = 4。
 * 给你一个数组nums，请你返回nums中任意子序列的最大交替和（子序列的下标 重新从 0 开始编号）。
 *
 * 一个数组的 子序列是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，[2,7,4]是[4,2,3,7,2,1,4]的一个子序列（加粗元素），但是[2,4,2] 不是。
 */
class No1191 {
    /**
     * 思路：贪心算法
     * 本质上是寻找一对一对的数，使其差值最大，那么可以从头到尾遍历寻找一对对的最大值和最小值，将其差值累加即可
     * 要注意的是最后一对最大最小值只取最大值即可
     * @param nums
     * @return
     */
    public long maxAlternatingSum(int[] nums) {
        long res = 0;
        int max; int min; int i=0;
        while(i<nums.length){
            while(i<nums.length-1&&nums[i]<=nums[i+1]){
                i++;
            }
            max = nums[i];
            while(i<nums.length-1&&nums[i]>=nums[i+1]){
                i++;
            }
            if(i==nums.length-1){
                return res+max;
            }else{
                min = nums[i];
            }
            res += max-min;
            i++;
        }
        return res;
    }
}
