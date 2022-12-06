package leetcode.WC;

import java.util.*;

public class Week289 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.digitSum("11111222223",3);
    }
}
class Solution {
    public String digitSum(String s, int k) {
        while(s.length()>k){
            s = split(s.toCharArray(),k);
        }
        return s;
    }
    public String split(char[] s,int k){
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for(int i=0;i<s.length;i++){
            sum += s[i]-'0';
            if(i%k==k-1||i==s.length-1){
                sb.append(""+sum);
                sum = 0;
            }
        }
        return sb.toString();
    }
}

/**
 * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。在每一轮中，你可以完成 2 个或者 3 个 相同难度级别 的任务。
 * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
 * 输入：tasks = [2,2,3,3,2,4,4,4,4,4]
 * 输出：4
 * 解释：要想完成所有任务，一个可能的计划是：
 * - 第一轮，完成难度级别为 2 的 3 个任务。
 * - 第二轮，完成难度级别为 3 的 2 个任务。
 * - 第三轮，完成难度级别为 4 的 3 个任务。
 * - 第四轮，完成难度级别为 4 的 2 个任务。
 * 可以证明，无法在少于 4 轮的情况下完成所有任务，所以答案为 4 。
 *
 * 实际上，2和3已经可以组成大于1的所有整数了
 * 所以只要优先以3为单位完成任务，剩下的用2填补就行
 * 如果n%3==0，全部3个3个的完成
 * 如果n%3==1，留4个，最后两组2
 * 如果n%3==2，留2个，最后一组2
 * 如果n==1，返回-1
 */
class Solution2 {
    public int minimumRounds(int[] tasks) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<tasks.length;i++){
            if(map.containsKey(tasks[i])){
                Integer val = map.get(tasks[i]);
                map.put(tasks[i],val+1);
            }else{
                map.put(tasks[i],1);
            }
        }
        Set<Map.Entry<Integer,Integer>> set = map.entrySet();
        Iterator<Map.Entry<Integer,Integer>> entryIterator = set.iterator();
        int count = 0;
        while(entryIterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = entryIterator.next();
            Integer val = entry.getValue();
            if (val<2)  return -1;
            if(val%3==0){
                count += val/3;
            }else{
                count += val/3+1;
            }
        }
        return count;
    }
}

class Solution3 {
    int max = 0;
    int curMax = 0;
    int sum = 1;
    int[] dirI = {0,1,0,-1};
    int[] dirJ = {1,0,-1,0};
    public int maxTrailingZeros(int[][] grid) {
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                maxTrailingZeros(grid,i,j,true,0);
            }
        }
        return max;
    }
    public void maxTrailingZeros(int[][] grid,int i,int j,boolean turn,int dir){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length){
            return;
        }
        if(turn){
            for(int k=0;k<4;k++){
                sum *= grid[i][j];
                int t = 0;
                while(sum%10==0){
                    sum /= 10;
                    curMax+=t;
                }
                max = Math.max(max,curMax);
                maxTrailingZeros(grid,i+dirI[k],j+dirJ[k],k==dir,k);
                sum /= grid[i][j];
                curMax -= t;
            }
        }else{
            sum *= grid[i][j];
            int t = 0;
            while(sum%10==0){
                sum /= 10;
                curMax+=t;
            }
            max = Math.max(max,curMax);
            maxTrailingZeros(grid,i+dirI[dir],j+dirJ[dir],false,dir);
            sum /= grid[i][j];
            curMax -= t;
        }

    }
}
