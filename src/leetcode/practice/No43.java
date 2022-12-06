package leetcode.practice;

import java.util.Arrays;

class No43 {

    public static void main(String[] args) {
        No43 no43 = new No43();
        String multiply = no43.multiply("123", "456");
        System.out.println(multiply);
    }
    public String multiply(String num1, String num2) {
        char[] res = new char[400];
        int len1 = num1.length();
        int len2 = num2.length();
        char[] nums1 = num1.toCharArray();
        char[] nums2 = num2.toCharArray();
        Arrays.fill(res,'0');
        for(int i=len1-1;i>=0;i--){
            for(int j=len2-1;j>=0;j--){
                int t = (nums1[i]-'0')*(nums2[j]-'0');
                int cur = (t + res[401-len1+i-len2+j]-'0')%10;
                int next = ((t + res[401-len1+i-len2+j]-'0')/10+res[400-len1+i-len2+j]-'0');
                res[401-len1+i-len2+j] = (char)(cur+'0');
                res[400-len1+i-len2+j] = (char)(next+'0');
            }
        }
        String str = String.valueOf(res);
        int index = 0;
        while(str.charAt(index)<'1'||str.charAt(index)>'9'){
            index++;
        }
        return str.substring(index);
    }
}
