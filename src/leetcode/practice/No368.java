package leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class No368 {
    public static void main(String[] args) {
        No368 no368 = new No368();
        int[] nums = {3,4,16,8};
        no368.largestDivisibleSubset(nums);
        String s = "Sdgy";
        boolean gy = s.startsWith("gy", 2);
        System.out.println(gy);
    }
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        //dp[i]： 以nums[i]结尾的最长整除子集
        int[][] dp = new int[len][2];
        for(int i=0;i<len;i++){
            dp[i][1] = 1;
            dp[i][0] = i;
        }
        int index = 0;
        int max = 1;
        for(int i=0;i<len;i++){
            for(int j=0;j<i;j++){
                if(nums[i]%nums[j]==0){
                    if(dp[i][1]<dp[j][1]+1){
                        dp[i][0] = dp[j][0];
                        dp[i][1] = dp[j][1]+1;
                    }
                }
            }
            if(dp[i][1]>max){
                max = dp[i][1];
                index = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int i=dp[index][0];i<index;i++){
            if(nums[i]%nums[dp[index][0]]==0){
                res.add(nums[i]);
            }
        }
        res.add(nums[index]);
        return res;
    }
}