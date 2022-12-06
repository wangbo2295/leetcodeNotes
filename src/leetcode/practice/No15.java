package leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class No15 {
    public List<List<Integer>> threeSum(int[] nums) {
        //思路：先将数组排序，然后下标i=0，left=i+1，right=nums.length-1;
        //如果三数之和大于0，说明要减小总和，right左移
        //如果三数之和小于0，说明要增大总和，left右移
        //i一直循环到len-3，把所有满足的结果记录下来
        //双指针法能有效的降低时间复杂度，通常都是在有序的情况下使用
        //本题直接爆搜的复杂度是O（n3），哈希法能达到O（n2）但是去重太麻烦
        //双指针法能将时间复杂度降到O（n2），且比较好去重
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
            if (nums[i] > 0) {
                return res;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int left = i+1,right = nums.length-1;
            while(left<right){
                if(nums[i]+nums[left]+nums[right]>0){
                    right--;
                }else if(nums[i]+nums[left]+nums[right]<0){
                    left++;
                }else{
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(++left<nums.length&&nums[left]==nums[left-1]);
                }
            }
        }
        return res;
    }
}