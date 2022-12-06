package leetcode.list;

public class No430 {
    public Node flatten(Node head) {
        getLast(head);
        return head;
    }

    private Node getLast(Node node) {
        Node end = node;
        while (node != null) {
            Node next = node.next;
            end = node;
            if (node.child != null) {
                Node last = getLast(node.child);
                node.next = node.child;
                node.child.prev = node;
                node.child = null;
                if (next != null) {
                    last.next = next;
                    next.prev = last;
                }
                end = last;
            }
            node = next;
        }
        return end;
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}
