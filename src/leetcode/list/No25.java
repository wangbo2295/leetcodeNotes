package leetcode.list;

import java.util.List;

public class No25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
//        reverseTwoRound(dummy, k);    //两次遍历
        reverseK(dummy, k, true);      //一次遍历
        return dummy.next;
    }

    /**
     * 一次遍历
     * 将要反转的链表段切开，并保存切点左右的节点
     * 迭代地将节点next指针指向前一个节点
     * 最后返回反转后的最后一个节点，递归地进行下一次反转直到链表结尾
     */
    ListNode reverseTwoRound(ListNode dummy, int k) {
        if (dummy.next == null)  return null;   //如果到链表结尾了，结束
        ListNode left = dummy.next; //反转段左端点
        ListNode right = left;      //反转段右端点
        int i;
        for (i = 1; i < k && right.next != null; i++) { //有可能最后一段不满k个节点，这时不进行反转，直接返回
            right = right.next;
        }
        if (i != k) return right;
        ListNode after = right.next;    //反转段右端点的下一个节点
        right.next = null;              //切断右侧节点，否则迭代会超出反转段
        // 为什么不切断左端点？因为迭代是往右进行，不影响左端点，并且最后会将左端点的指向调整
        //迭代过程：保存当前节点的下一个节点，因为反转的过程会切断该节点。
        //然后将当前节点指向前一个节点，并将cur置为next，pre置为cur，进行下一次迭代，注意next是在每次反转之前的cur.next，永远是当前节点的下一个节点
        ListNode prev = null, cur = left, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        //调整链表方向
        dummy.next = right;
        left.next = after;
        return reverseTwoRound(left, k);
    }

    /**
     * 一次遍历
     */
    ListNode reverseK(ListNode dummy, int k, boolean flag) {
        if (dummy.next == null)     return null;
        ListNode cur = dummy.next, next;
        int i;
        for (i = 1; i < k && cur.next != null; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = dummy.next;
            dummy.next = next;
        }
        //如果没反转足 k 个节点，则再次反转将其恢复
        if (i != k && flag) {   //用一个flag标记是否是恢复反转链表
            return reverseK(dummy, k, false);
        } else {
            return reverseK(cur, k, true); //返回反转后的尾节点以供下轮反转
        }
    }
}
