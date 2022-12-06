package leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No559 {
    public int maxDepth(Node root) {
        int ans = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.children == null)  continue;
                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
            ans++;
        }
        return ans;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
