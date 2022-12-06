package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 */
public class No94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)   return res;
        Stack<InNode> stack = new Stack<>();
        stack.push(new InNode(root));
        while (!stack.empty()) {
            InNode peek = stack.peek();
            if (peek.known) {
                res.add(peek.node.val);
                stack.pop();    //要先pop再push
                if (peek.node.right != null) {
                    stack.push(new InNode(peek.node.right));
                }
            } else {
                if (peek.node.left != null) {
                    stack.push(new InNode(peek.node.left));
                }
                peek.known = true;
            }
        }
        return res;
    }

    class InNode {
        TreeNode node;
        boolean known;
        public InNode(TreeNode node) {this.node = node;}
    }


    /**
     * 用指针实现遍历，将遍历的节点压栈，出栈时的顺序就是中序遍历的顺序
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)   return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            //对于一个根节点，先往左边寻找子节点
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }else {//如果左子节点为null，就找右子节点
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
}
