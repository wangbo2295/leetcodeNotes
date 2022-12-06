package leetcode.tree;

import java.util.*;

public class No508 {

    Map<Integer, Integer> map = new HashMap<>();
    int max;
    public int[] findFrequentTreeSum(TreeNode root) {
        search(root);
        List<Integer> res = new ArrayList<>();
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (next.getValue() == max) res.add(next.getKey());
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public int search(TreeNode root) {
        if (root == null)   return 0;
        int sum = search(root.left) + search(root.right) + root.val;
        int cnt = map.getOrDefault(sum, 0) + 1;
        max = Math.max(max, cnt);
        map.put(sum, cnt);
        return sum;
    }

}
