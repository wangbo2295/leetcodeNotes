package 剑指offer;

public class ReBuildBinaryTree {
    int[] preorder, inorder, postorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        return build(0, 0, inorder.length - 1);
    }

    private TreeNode build(int pl, int il, int ir) {
        if (il > ir)    return null;
        int proot = preorder[pl];
        int iroot = il;
        for (; iroot <= ir; iroot++) {
            if (inorder[iroot] == proot)    break;
        }
        int lsize = iroot - il;
        TreeNode node = new TreeNode(proot);
        node.left = build(pl + 1, il, iroot - 1);
        node.right = build(pl + lsize + 1, iroot + 1, ir);
        return node;
    }
}
