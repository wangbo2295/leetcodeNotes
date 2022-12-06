package leetcode.breathFirstSearch;

import org.w3c.dom.Node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 */
public class No662 {
    /**
     * 思路：参照堆的思想，给每个节点编号
     * 再按照层序遍历，每次将最后一个节点和第一个节点的编号相减再加1就很容易得到这一层的长度了
     *
     * 看到评论区还有一种做法是直接将val改成编号，牛了
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)   return 0;
        Deque<NodeNum> queue = new LinkedList<>();
        queue.offer(new NodeNum(root, 1));
        int maxWidth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, queue.getLast().num - queue.getFirst().num + 1);
            for (int i = 0; i < size; i++) {
                NodeNum nodeNum = queue.poll();
                if (nodeNum.node.left != null) {
                    queue.offer(new NodeNum(nodeNum.node.left, nodeNum.num * 2));
                }
                if (nodeNum.node.right != null) {
                    queue.offer(new NodeNum(nodeNum.node.right, nodeNum.num * 2 + 1));
                }
            }
        }
        return maxWidth;
    }

    class NodeNum{
        TreeNode node;
        int num;
        public NodeNum(TreeNode node, int num) {this.node = node; this.num = num;}
    }
}
