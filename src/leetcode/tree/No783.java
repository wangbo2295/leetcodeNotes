package leetcode.tree;

public class No783 {
    int pre = -100001;
    int min = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        getMin(root);
        return min;
    }
    public void getMin(TreeNode root) {
        if (root == null)   return;
        getMin(root.left);
        min = Math.min(min, root.val - pre);
        pre = root.val;
        getMin(root.right);
    }
}
