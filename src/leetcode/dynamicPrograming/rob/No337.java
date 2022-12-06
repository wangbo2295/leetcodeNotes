package leetcode.dynamicPrograming.rob;

/**
 * 树形打家劫舍
 * 同样的思路：每个屋子保存偷和不偷的最大收益
 */
public class No337 {
    public int rob(TreeNode root) {
        int[] ints = postOrder(root);
        return Math.max(ints[0], ints[1]);
    }

    public int[] postOrder(TreeNode root) {
        if (root == null) return new int[] {0,0};
        int[] left = postOrder(root.left);
        int[] right = postOrder(root.right);
        int stole = root.val + left[0] + right[0];
        int keep = Math.max(left[1], left[0]) + Math.max(right[0], right[1]);
        return new int[] {keep, stole};
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
