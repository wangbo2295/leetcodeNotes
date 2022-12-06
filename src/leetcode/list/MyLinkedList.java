package leetcode.list;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 循环双链表实现
 * 1、初始化时建立一个 dummy，其 prev 和 next 都为自身，这样形成一个循环链表，避免了空指针异常
 * 2、剩下的就是常规的双链表操作了。
 */
public class MyLinkedList {

    ListNode dummy;
    int size;

    public MyLinkedList() {
        dummy = new ListNode(-1, null, null);
        dummy.prev = dummy;
        dummy.next = dummy;
    }

    public int get(int index) {
        return find(index).val;
    }

    public void addAtHead(int val) {
        dummy.addAtNext(val);
        size++;
    }

    public void addAtTail(int val) {
        dummy.addAtPre(val);
        ++size;
    }

    /**
     * 1、index 大于 size 则不插入
     * 2、index 等于 size 则在尾部插入
     * 3、index 小于 0 则在头部插入
     * 4、否则在 index 节点的前面插入
     */
    public void addAtIndex(int index, int val) {
        ListNode node = find(index);
        if (index > size)   return;
        else if (index == size) addAtTail(val);
        else if (index < 0) addAtHead(val);
        else node.addAtPre(val);
        ++size;
    }

    public void deleteAtIndex(int index) {
        ListNode node = find(index);
        if (node == dummy)   return;
        node.remove();
        --size;
    }

    /**
     * 查找对应索引的节点
     * 如果 index 小于 0 或大于等于 size，则会返回 dummy。
     */
    private ListNode find(int index) {
        ListNode t = dummy;
        for (int i = 0; i <= Math.min(index, size); i++) {
            t = t.next;
        }
        return t;
    }

    static class ListNode {
        int val;
        ListNode prev;
        ListNode next;
        public ListNode(int val) {this(val, null, null);}
        public ListNode(int val, ListNode prev, ListNode next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

        private void addAtPre(int val) {
            ListNode pre = new ListNode(val, prev, this);
            prev.next = pre;
            prev = pre;
        }

        private void addAtNext(int val) {
            ListNode nxt = new ListNode(val, this, next);
            next.prev = nxt;
            next = nxt;
        }

        private void remove() {
            prev.next = next;
            next.prev = prev;
        }
    }
}
