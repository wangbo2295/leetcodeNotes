package acwing.树;

import java.util.HashMap;
import java.util.Map;

public class BuilderTree {

    /**
     * 根据前序遍历和中序遍历构建二叉树
     * 对中序遍历和前序遍历进行分区，中序遍历中，根节点的左右半区间为左右子树的左右节点；前序遍历中，区间第一个元素为根节点
     * 对于前序遍历的每个根节点及对应的区间，在中序遍历中找到根节点，计算左右子树区间的长度，即可在前序遍历中确定左右子树的分界点
     * 递归地进行分区。对于前序遍历只需要记录根节点下标，因为区间左边界就是根节点下标（对于左区间）/根节点 + 左区间长度 + 1（对于右区间）
     * 而右边界就是根节点下标 + 左区间长度（对于左区间）/原右边界（对于右区间）
     * 对于中序遍历则要记录左右区间的边界下标，因为要在该区间内查找根节点位置
     * 递归结束条件为在中序遍历中左边界大于右边界，表明该子树为空。具体实现时可直接对根节点赋初值 null，在for循环中查找根节点，找到之后再赋值，否则默认返回null。
     *
     * 由于本题保证没有重复元素，因此可以用hash简化查找
     */
    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, 0, inorder.length - 1);
    }

    TreeNode buildTree(int[] preorder, int[] inorder, int root, int il, int ir) {
        if (il > ir)    return null;
        Integer i = map.get(preorder[root]);
        TreeNode node = new TreeNode(preorder[root]);
        node.left = buildTree(preorder, inorder, root + 1, il, i - 1);
        node.right = buildTree(preorder, inorder, root + i - il + 1, i + 1, ir);
        return node;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}