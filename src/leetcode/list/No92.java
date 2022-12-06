package leetcode.list;

public class No92 {
    /**
     * 切断 [left, right] 之间的链表，并保存切断点左右的四个节点 (pre, leftNode, rightNode, after)
     * 将中间段反转后，pre.next = rightNode, leftNode.next = after
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right)  return head;
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode leftNode = pre.next;
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        ListNode after = rightNode.next;
        //切断链表
        pre.next = null;
        rightNode.next = null;
        ListNode cur = leftNode, prev = null, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        pre.next = rightNode;
        leftNode.next = after;
        return dummy.next;
    }

    /**
     * 不断地将[left, right] 间的元素移到表头（切断后的）
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        ListNode cur = prev.next, next;
        for (int i = left; i < right; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }
}
