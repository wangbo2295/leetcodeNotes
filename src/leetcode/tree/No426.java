package leetcode.tree;

public class No426 {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    Node pre;
    public Node treeToDoublyList(Node root) {
        Node head = new Node(0);
        pre = head;
        inOrder(root);
        return head.right;
    }
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        pre.right = node;
        node.left = pre;
        pre = node;
        inOrder(node.right);
    }
}
