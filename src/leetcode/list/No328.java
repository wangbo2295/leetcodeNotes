package leetcode.list;

/**
 * 给定单链表的头节点head，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为偶数 ，以此类推。
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 * 你必须在O(1)的额外空间复杂度和O(n)的时间复杂度下解决这个问题。
 */
public class No328 {
    /**
     * 两个指针依次指向已知的最后一个奇数节点 pre 和最后一个偶数节点 cur
     * 依次将偶数索引后的一个节点插入 pre 之后，此时 pre 之后新增一个奇数节点， cur 之后补上一个偶数节点
     * 将 pre 和 cur 均往后移，保持其指向已知的最后一个奇数节点和最后一个偶数节点
     * 最后链表被分割为一半奇数一半偶数
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null)   return null;
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy.next, cur = dummy.next.next, next;
        while (cur != null && cur.next != null) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            pre = pre.next;
            cur = cur.next;
        }
        return dummy.next;
    }
}
