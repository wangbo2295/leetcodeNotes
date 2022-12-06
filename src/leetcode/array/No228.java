package leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class No228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        if (n == 0)   return ans;
        StringBuilder sb = new StringBuilder(nums[0] + "->");
        for (int i = 1; i <= n; i++) {
            if (i == n || nums[i] != nums[i - 1] + 1) {
                System.out.println(Integer.parseInt(sb.substring(0, sb.length() - 2)));
                if (nums[i - 1] != Integer.parseInt(sb.substring(0, sb.length() - 2))) {
                    sb.append(nums[i - 1]);
                } else {
                    sb.delete(sb.length() - 2, sb.length());
                }
                ans.add(sb.toString());
                if (i < nums.length)
                    sb = new StringBuilder(nums[i] + "->");
            }
        }
        return ans;
    }
}
