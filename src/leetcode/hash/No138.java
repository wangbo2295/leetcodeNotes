package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

public class No138 {

    Map<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) return null;
//         不能分开递归，对于某个节点应该同时递归
//        newNode.next = map.getOrDefault(head.next, copyRandomList(head.next));
//        newNode.random = map.getOrDefault(head.random, copyRandomList(head.random));
        if (!map.containsKey(head)) {
            Node newNode = new Node(head.val);
            map.put(head, newNode);
            newNode.next = copyRandomList(head.next);
            newNode.random = copyRandomList(head.random);
        }
        return map.get(head);
    }
}
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
