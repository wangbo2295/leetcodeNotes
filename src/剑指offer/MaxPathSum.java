package 剑指offer;

public class MaxPathSum {
    /**
     * 考虑每颗子树的最大路径和
     * 一种是不经过根节点的最大和，取左右子树的最大路径和的最大值
     * 一种是经过根节点的最大和，记maxrsum为以根节点为起点的最大路径和，取 max(0, left.maxrsum, right.maxrsum, left.maxrsum + right.maxrsum) + root.val
     * 上式包含了四种情况：根节点本身、根节点向左子树出发、根节点向右子树出发、根节点作为中间节点
     * 对上述一共五种情况取最大值即为当前树的最大路径和
     * 对于以根节点为起点的最大路径和，取左右子树的该值和0的最大值加上根节点的值就是所求
     */
    public int maxPathSum(TreeNode root) {
        return maxSum(root).maxsum;
    }

    public Node maxSum(TreeNode root) {
        if (root == null)   return new Node(Integer.MIN_VALUE, 0);
        Node left, right;
        left = maxSum(root.left);
        right = maxSum(root.right);
        int maxsum = Math.max(left.maxsum, right.maxsum);
        int pass = Math.max(0, Math.max(Math.max(left.maxrsum, right.maxrsum), left.maxrsum + right.maxrsum)) + root.val;
        maxsum = Math.max(maxsum, pass);
        int maxrsum = Math.max(0, Math.max(left.maxrsum, right.maxrsum)) + root.val;
        return new Node(maxsum, maxrsum);
    }

    class Node {
        int maxsum;
        int maxrsum;
        public Node(int maxsum, int maxrsum) {this.maxsum = maxsum;this.maxrsum = maxrsum;}
    }
}
