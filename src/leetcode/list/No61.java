package leetcode.list;

public class No61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)   return null;
        int n = 1;
        ListNode h = head;
        while (head.next != null) {
            head = head.next;
            ++n;
        }
        head.next = h;
        head = head.next;
        int K = n - k % n;
        while (K > 1) {
            head = head.next;
            --K;
        }
        h = head.next;
        head.next = null;
        return h;
    }
}
