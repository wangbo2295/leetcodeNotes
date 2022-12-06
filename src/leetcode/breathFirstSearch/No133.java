package leetcode.breathFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No133 {
    /**
     * 方法一，递归   自底向上
     * 因为是无向图，所以两个节点之间的连接是双向的，为了防止陷入无限循环，需要标记节点是否已被克隆
     * 用map保存原节点和克隆节点的映射，克隆之后放入map，下次再遍历到该节点就可以直接返回其拷贝了。
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        Node[] map = new Node[101];
        return clone(node, map);
    }
    public Node clone(Node node, Node[] map) {
        if (node == null) return node;
        if (map[node.val] != null) {
            return map[node.val];
        }
        Node copy = new Node(node.val);
        map[node.val] = copy;
        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(clone(neighbor, map));
        }
        return copy;
    }

    /**
     * 方法二，BFS      自顶向下
     */
    public Node cloneGraph2(Node node) {
        if (node == null)   return node;
        Node[] map = new Node[101];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        map[node.val] = new Node(node.val);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node src = queue.poll();
                for (Node neighbor : src.neighbors) {
                    if (map[neighbor.val] == null) {
                        map[neighbor.val] = new Node(neighbor.val);
                        queue.offer(neighbor);
                    }
                    map[src.val].neighbors.add(map[neighbor.val]);
                }
            }
        }
        return map[node.val];
    }
}
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


