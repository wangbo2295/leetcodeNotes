package leetcode.tree;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 */
public class No450 {

    /**
     * 递归法：思路清晰
     * 1、找到要删除的节点
     * 2、将该节点的值改为右子树中的最小值（或左子树中最大值）
     * 3、对右子树（左子树）删除该最小值节点
     * 要点：如果没有右子树（左子树），直接返回左子树根节点（右子树根节点）
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)   return null;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }else {
            if (root.right != null) {
                root.val = findMin(root.right).val;
                root.right = deleteNode(root.right, root.val);
            } else {
                root = root.left;
            }
        }
        return root;
    }

    public TreeNode findMin(TreeNode root) {
        if (root == null)   return null;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
