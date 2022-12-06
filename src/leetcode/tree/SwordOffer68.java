package leetcode.tree;

public class SwordOffer68 {
    TreeNode LCA;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LCA(root, p, q);
        return LCA;
    }
    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)   return null;
        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);
        if ((left == p && right == q) || (left == q && right == p)
                || (root == p && (left == q || right == q) || (root == q && (left == p || right == p)))) {
            LCA = root;
        }
        if (left == p || left == q) return left;
        if (right == p || right == q)   return right;
        return root;
    }
}
