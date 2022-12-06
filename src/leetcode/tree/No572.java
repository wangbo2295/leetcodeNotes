package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class No572 {
    List<TreeNode> subs = new ArrayList<>();
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        findSubRoot(root, subRoot);
        boolean res = false;
        for (TreeNode sub : subs) {
            System.out.println(sub.val);
            res |= compare(root, sub);
        }
        return res;
    }

    public void findSubRoot(TreeNode root, TreeNode subRoot) {
        if (root == null) return;
        if (root.val == subRoot.val) subs.add(root);
        findSubRoot(root.left, subRoot);
        findSubRoot(root.right, subRoot);
    }

    public boolean compare(TreeNode root, TreeNode subRoot) {
        if (subRoot == null && root == null)    return true;
        if (root == null || subRoot == null)    return false;
        if (root.val != subRoot.val) return false;
        System.out.println(root.val + "-" + subRoot.val);
        return compare(root.left, subRoot.left) && compare(root.right, subRoot.right);
    }

    public static void main(String[] args) {
        No572 no572 = new No572();

    }
}

