package leetcode.list;

import java.util.ArrayList;
import java.util.List;

public class SwordOffer29 {
    public Node insert(Node head, int insertVal) {
        Node insert = new Node(insertVal);
        if (head == null) {
            insert.next = insert;
            return insert;
        }
        Node t = head;
        while (!((t.val <= insertVal && t.next.val >= insertVal) || t.val >= t.next.val) && t.next != head ) {
            t = t.next;
        }
        insert.next = t.next;
        t.next = insert;
        return head;
    }

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
}
