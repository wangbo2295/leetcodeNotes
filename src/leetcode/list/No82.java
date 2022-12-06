package leetcode.list;

public class No82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode res = new ListNode(0, head);
        ListNode pre = res, cur = head;
        while (cur != null) {
            boolean flag = false;
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                flag = true;
            }
            cur = cur.next;
            if (flag) {
                pre.next = cur;
            } else {
                pre = pre.next;
            }
        }
        return res.next;
    }
}
