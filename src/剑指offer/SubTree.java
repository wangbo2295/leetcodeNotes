package 剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SubTree {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(A);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == B.val && compare(node, B))  return true;
            if (node.left != null)  queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return false;
    }

    public boolean compare(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return compare(A.left, B.left) && compare(A.right, B.right);
    }
}
