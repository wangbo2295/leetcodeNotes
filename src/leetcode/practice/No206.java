package leetcode.practice;

public class No206 {
    //递归
    public ListNode reverseList(ListNode head) {
        ListNode headZero = new ListNode(0);
        headZero.next = head;
        reverse(head,headZero).next = null;
        return headZero.next;
    }
    public ListNode reverse(ListNode head,ListNode headZero) {
        if(head==null)  return headZero;
        reverse(head.next,headZero).next = head;
        return head;
    }

    //迭代，双指针
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = null;
        while(head!=null){
            cur = head.next;    //保存断开的节点
            head.next = pre;    //反转指针
            pre = head;         //向后移动
            head = cur;
        }
        return pre; //移动到head==null时所有节点完成反转
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
