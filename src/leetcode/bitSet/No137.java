package leetcode.bitSet;

public class No137 {
    /**
     * 1、考虑每个二进制位，出现三次的数在该二进制位的1的个数之和一定是3的倍数或者是0
     * 2、而只出现一次的那个数，如果该位是1，那么总的1的个数对3取余一定是1，否则为0
     * 3、因此只要计算每位1的总数，对3取余后是多少那么答案在该位就是多少
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                cnt += (num >> i) & 1;  //判断该位是否为1
            }
            ans |= cnt % 3 << i;    //对该位进行赋值
        }
        return ans;
    }

    public static void main(String[] args) {
        No137 no137 = new No137();
        int[] nums = {2,3,2,2};
        no137.singleNumber(nums);
    }
}
