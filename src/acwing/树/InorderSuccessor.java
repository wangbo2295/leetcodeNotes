package acwing.树;

public class InorderSuccessor {
    public TreeNode inorderSuccessor(TreeNode p) {
        if (p.right != null) return findInChild(p.right);
        return findInFather(p.father, p);
    }

    TreeNode findInChild(TreeNode root) {
        if (root.left == null && root.right == null) return root;
        return findInChild(root.left);
    }

    TreeNode findInFather(TreeNode root, TreeNode p) {
        if (root == null)   return null;
        if (root.right == p) return findInFather(root.father, root);
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode father;
        TreeNode(int x) { val = x; }
    }
}


