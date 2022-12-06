package leetcode.doublePointer;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 这题和环形链表都需要好好斟酌！
 *
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 */
public class No160 {
    /**
     * 设A不相交的长度为a，B不相交的长度为b，两者相交的长度为c
     * 当A走过a + c + b，B走过b + c + a时两者相交
     * 如何做到？
     * A走到底后从B的头节点开始走，B走到底后从A的头节点开始走
     * 如果不相交，那就是c == 0的情况，同样适用，只是最后返回的是null
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pa = headA, pb = headB;
        while (pa != pb) {
            if (pa != null) {
                pa = pa.next;
            }else {
                pa = headB;
            }
            if (pb != null) {
                pb = pb.next;
            } else {
                pb = headA;
            }
        }
        return pa;
    }
}
