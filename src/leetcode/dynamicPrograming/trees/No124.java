package leetcode.dynamicPrograming.trees;

/**
 * 二叉树中的最大路径和
 * 思路：
 * 树中的任何一条路径都可以看作由左分支、根节点、右分支组成的一颗子树
 * 对每个节点来说，只要计算出经过每个节点的所有路径中的最大路径和就能知道所有路径的最大和
 */
public class No124 {
    int max;
    public int maxPathSum(TreeNode root) {
        calculate(root);
        return max;
    }

    private int calculate(TreeNode root) {
        if (root == null)   return 0;
        int lt = calculate(root.left);
        int rt = calculate(root.right);
        //分三种情况，左右分支均包含、只包含一个分支、不包含分支
        int res = Math.max(Math.max(lt + root.val, rt + root.val), root.val);
        int cur = Math.max(lt + rt + root.val, res);
        max = Math.max(max, cur);
        return res;
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {}

        public TreeNode(int _val) {
            val = _val;
        }

        public TreeNode(int _val, TreeNode _left, TreeNode _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
