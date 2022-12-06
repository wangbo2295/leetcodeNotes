package leetcode.list;

public class No143 {
    public void reorderList(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy, fast = dummy;
        //快慢指针找到中点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //反转后半段链表
        ListNode pre = slow, cur = slow.next, next;
        while (cur != null && cur.next != null) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        //切断并合并
        ListNode reverse = slow.next;
        slow.next = null;
        while (head != null && reverse != null) {
            dummy.next = head;
            head = head.next;
            dummy = dummy.next;
            dummy.next = reverse;
            reverse = reverse.next;
            dummy = dummy.next;
        }
        if (head != null) {
            dummy.next = head;
        }
        if (reverse != null) {
            dummy.next = reverse;
        }
    }
}
