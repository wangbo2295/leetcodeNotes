package leetcode.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * 这题太折磨了，下次再做
 */
class LCP03 {
    public static void main(String[] args) {
        LCP03 lcp03 = new LCP03();
        String command = "URR";
        int[][] obstacles = {{2,2}};
        System.out.println(lcp03.robot(command,obstacles,3,2));
    }
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        //统计连续的U和R，对每个障碍点和终点进行判断在不在路径上
        int[][] steps = new int[1000][2];
        if(command.charAt(0)=='R'){
            steps[0][0] = 1;
        }else{
            steps[0][1] = 1;
        }
        int j=0;
        for(int i=1;i<command.length();i++){
            if(command.charAt(i)!=command.charAt(i-1)){
                j++;
            }
            if(command.charAt(i)=='R'){
                steps[j][0]++;
            }else{
                steps[j][1]++;
            }
        }
        j++;
        for(int[] e: obstacles){
            int x1 = 0,y1=0,index = 0;
            while(x1<e[0]&&y1<e[1]){
                x1 += steps[index][0];
                y1 += steps[index][1];
                index = (index+1)%j;
            }
            if((x1==e[0]&&y1+steps[index][1]>=e[1])
                    ||(y1==e[1]&&x1+steps[index][0]>=e[0])){
                return false;
            }
        }
        int X = 0,Y=0,in = 0;
        while(X<=x&&Y<=y){
            X += steps[in][0];
            Y += steps[in][1];
            in = (in+1)%j;
        }
        if((X==x&&Y+steps[in][1]>=y)
                ||(Y==y&&X+steps[in][0]>=x)){
            return true;
        }
        return false;
    }
}