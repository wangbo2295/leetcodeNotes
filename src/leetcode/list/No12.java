package leetcode.list;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class No12 {
    //生疏了
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)  return head;    //如果链表长度小于2，直接返回
        ListNode res = new ListNode(0); //虚拟头节点用来充当起始的pre和保存头节点
        ListNode pre = res;
        ListNode cur = head;
        ListNode next = head.next;
        while (cur != null && next != null) {
            cur.next = next.next;
            next.next = cur;
            pre.next = next;
            if (cur.next == null || cur.next.next == null)  break;   //如果后面少于两个元素，返回
            //移动三个节点
            pre = cur;
            cur = cur.next;
            next = cur.next;
        }
        return res.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
