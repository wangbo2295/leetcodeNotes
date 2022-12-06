package leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class No145 {
    /**
     * 前序遍历是中左右，调整顺序为中右左，再将结果反转就是左右中，即后续遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
            if (pop.left != null)   stack.push(pop.left);
            if (pop.right != null)  stack.push(pop.right);
        }
        Collections.reverse(res);
        return res;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)   return res;
        Stack<PostNode> stack = new Stack<>();
        stack.push(new PostNode(root));
        while (!stack.empty()) {
            PostNode peek = stack.peek();
            if (peek.known) {
                res.add(stack.pop().node.val);
                continue;
            }
            if (peek.node.right != null) stack.push(new PostNode(peek.node.right));
            if (peek.node.left != null)    stack.push(new PostNode(peek.node.left));
            peek.known = true;
        }
        return res;
    }

    class PostNode {
        TreeNode node;
        boolean known;
        public PostNode(TreeNode node) {this.node = node;}
    }
}
