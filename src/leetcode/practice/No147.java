package leetcode.practice;

/**
 * 对链表进行插入排序
 * 虽然这道题递归很慢，但我还是喜欢用递归
 */
class No147 {
    boolean flag = false;
    public ListNode insertionSortList(ListNode head) {
        int k = 1;
        //每次从头找到第一个未排序的节点进行插入，k表示已排序的个数
        while(!flag){
            head = recursion(head, k++);
        }
        return head;
    }

    public ListNode recursion(ListNode node, int k){
        //如果到最后一个元素了说明排序完成，flag标记为true
        if(node.next == null) {
            flag = true;
            return node;
        }
        //找到第一个未排序的节点
        if(k == 0) {
            return node;
        }
        ListNode e = recursion(node.next, k - 1);
        //如果下个节点小于当前节点，则交换，否则返回当前节点
        if(e.val < node.val) {
            node.next = e.next;
            e.next = node;
            return e;
        }else {
            node.next = e;
            return node;
        }
    }

    /**
     * 真香
     * 维护一个已排序链表的最后节点，一个未排序的第一个节点，在表头安置一个虚拟头节点方便在头节点之前插入节点
     */
    public ListNode insertionSortList2(ListNode head) {
        ListNode beforeHead = new ListNode(0);
        beforeHead.next = head;
        ListNode lastSorted = head;
        ListNode firstUnsorted = head.next;
        while(firstUnsorted != null){
            if(firstUnsorted.val > lastSorted.val) {
                lastSorted = firstUnsorted;
                firstUnsorted = firstUnsorted.next;
            }else{
                ListNode tmp = beforeHead;
                while(tmp.next != null && tmp.next.val < firstUnsorted.val) {
                    tmp = tmp.next;
                }
                lastSorted.next = firstUnsorted.next;
                firstUnsorted.next = tmp.next;
                tmp.next = firstUnsorted;
                firstUnsorted = lastSorted.next;
            }
        }
        return beforeHead.next;
    }
}