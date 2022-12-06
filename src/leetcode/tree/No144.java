package leetcode.tree;

import com.sun.source.tree.Tree;

import java.util.*;

/**
 * 前序遍历，迭代实现
 */
public class No144 {
    /**
     * 用栈模拟递归的过程
     * 入栈的顺序为中-右-左，因为处理完根节点之后立马处理左右节点而不是像递归那样处理完左节点再处理右节点
     * 所以先将右节点入栈再入左节点，这样就能使下次迭代先处理左节点
     * 处理完根节点并将左右节点入栈后该节点就处理完了，可以pop
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
            if (pop.right != null)  stack.push(pop.right);
            if (pop.left != null)   stack.push(pop.left);
        }
        return res;
    }
}
