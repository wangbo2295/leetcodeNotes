package leetcode.list;

public class No86 {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy, cur, next;
        while (pre.next != null && pre.next.val < x) {
            pre = pre.next;
        }
        cur = pre.next;
        while (cur != null && cur.next != null && cur.next.val >= x) {
            cur = cur.next;
        }
        while (cur != null && cur.next != null) {
            while (cur.next != null && cur.next.val < x) {
                next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
                pre = pre.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
