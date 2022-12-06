package leetcode.tree;

import java.util.*;

public class No652 {

    /**
     * 解法一：将一颗子树表示为 root.val(left)(right) 的字符串形式
     * 其中 root.val 为根节点的值，left 和 right 分别表示左子树和右子树序列化后的结果，如果为空则是空字符串
     * 这样就能唯一的表示一个树了，一定要记得序列化一棵树的关键在于要把 null 节点也算进去，否则同一个序列化结果可能对应不同树结构
     * 就像树的三种遍历方式只用任何一种都不能确定唯一树结构，因为这样的遍历结果是没有null节点的信息的。
     */
    Map<String, TreeNode> map = new HashMap<>();
    Set<TreeNode> set = new HashSet<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        preOrder(root);
        return new ArrayList<>(set);
    }

    public String preOrder(TreeNode root) {
        if (root == null)   return "";
        String hash = "" + root.val + "(" + preOrder(root.left) + ")(" + preOrder(root.right) + ")";
        if (map.containsKey(hash)) set.add(map.get(hash));
        else map.put(hash, root);
        return hash;
    }

    /**
     * 解法二：三元组优化
     * 解法一中生成字符串的形式当树中节点非常多时，字符串可能非常长
     * 由于本题只需要判断是否相同，而不需要具体的子树结构，我们可以将同样的子树唯一编号
     * 对于一颗子树，其三元组的表示为 (x, l, r)，其中 x 为根节点值， l 和 r 分别为左子树和右子树的编号
     * 易知两棵树相同的充要条件是三元组完全相同
     */
    Map<String, Pair<TreeNode, Integer>> seen = new HashMap<>();
    Set<TreeNode> repeat = new HashSet<>();
    int num;
    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        pre(root);
        return new ArrayList<>(repeat);
    }
    public int pre(TreeNode root) {
        if (root == null)   return 0;
        int[] hash = {root.val, pre(root.left), pre(root.right)};
        String serial = Arrays.toString(hash);
        if (seen.containsKey(serial)) {
            Pair<TreeNode, Integer> pair = seen.get(serial);
            repeat.add(pair.key);
            return pair.value;
        } else {
            seen.put(serial, new Pair<>(root, ++num));
            return num;
        }
    }

    class Pair<K, V> {
        K key;
        V value;
        public Pair(K a, V b) {this.key = a;this.value = b;}
    }
}
