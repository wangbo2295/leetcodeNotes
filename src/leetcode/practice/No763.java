package leetcode.practice;

import java.util.ArrayList;
import java.util.List;
/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 返回一个表示每个字符串片段的长度的列表。
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 */

/**
 * 思路：刚开始我想的是把每个字符出现的区间范围统计出来，然后在遍历的过程中判断当前字符的左边界是否大于之前所有字符的最远边界
 * 只要不大于之前字符的最远边界，就更新最远边界，也就是取上一个字符和当前字符最远边界的最大值
 * 只要大于之前字符的最远边界，就计算一次长度，也就是分割了。
 * 思路是正确的，但是在代码实现上还有优化空间
 * 观察：判断当前字符左边界大于之前字符最远右边界，这时相当于到达之前字符的最远边界的下一个下标了
 * 也就是说，可以维护一个最远边界的变量，变量过程中不断更新，直到遍历的下标等于最远边界对应的下标，这就到切割点了。
 * 优化：统计每个字符出现的最远边界，维护一个最远边界变量，遍历过程中不断更新，直到当前下标与其相等则进行一次分割。
 */
class No763 {
    public List<Integer> partitionLabels(String s) {

        int[] map = new int[26];    //统计字符出现的最远下标
        char[] str = s.toCharArray();
        for(int i=0;i<str.length;i++){
            map[str[i]-'a'] = Math.max(i,map[str[i]-'a']);
        }
        int maxIndex = 0;
        int preIndex = -1;
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<str.length;i++){
            maxIndex = Math.max(maxIndex,map[str[i]-'a']);
            if(i==maxIndex){
                res.add(maxIndex-preIndex);
                preIndex = maxIndex;
            }
        }
        return res;
    }
}