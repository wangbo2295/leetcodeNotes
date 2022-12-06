package leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class No18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //由三数之和启发，n数之和可以通过双指针法将时间复杂度从O（Nn）降为O（N（n-1））
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<nums.length-3;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<nums.length-2;j++){
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                int left = j+1,right = nums.length-1;
                while(left<right){
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum>target){
                        right--;
                    }else if(sum<target){
                        left++;
                    }else{
                        res.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        while(++left<nums.length&&nums[left]==nums[left-1]);
                        while(--right>=0&&nums[right]==nums[right+1]);
                    }
                }
            }
        }
        return res;
    }
}