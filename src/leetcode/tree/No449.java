package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class No449 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> encode = new ArrayList<>();
        preOrder(encode, root);
        return String.join(",", encode);
    }

    //前序遍历编码
    public void preOrder(List<String> encode, TreeNode root) {
        if (root == null) return;
        encode.add("" + root.val);
        preOrder(encode, root.left);
        preOrder(encode, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] nodes = data.split(",");
        return generate(nodes, 0, nodes.length - 1);
    }

    //二分法生成BST
    public TreeNode generate(String[] nodes, int left, int right) {
        if (left > right)  return null;
        int cur = Integer.parseInt(nodes[left]);
        TreeNode root = new TreeNode(cur);
        int mid = left + 1;
        while (mid <= right && Integer.parseInt(nodes[mid]) < cur) {
            mid++;
        }
        root.left = generate(nodes, left + 1, mid - 1);
        root.right = generate(nodes, mid, right);
        return root;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
