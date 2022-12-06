package leetcode.doublePointer;

/**
 * 环形链表II
 */
public class No142 {
    /**
     * 如何判断有环？
     * 快慢指针，慢指针一次移动一步，快指针移动两步，快指针相对慢指针移动一步，如果有环，则必定相遇
     * 如何寻找环的入口？
     * 设头节点到入口长度为a，入口到相遇节点长度为b，相遇节点到入口为c，则有
     * 2 * (a + b) = a + k(b + c) + b
     * 化简得，a + b = k(b + c) => a = (k - 1)(b + c) + c
     * 由上式看出，左边为头节点到入口的距离，右边为(k - 1)个环的距离加上相遇节点到入口的距离
     * 那么两个指针同时分别从头节点和相遇节点出发，再次相遇时的节点就一定是入口节点。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)   return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)   break;
        }
        if (fast == null || fast.next == null)   return null;
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return slow;
    }
}
