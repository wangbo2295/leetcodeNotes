package leetcode.list;

import java.util.Comparator;
import java.util.PriorityQueue;

public class No23 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.val));
        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }
        ListNode ans = new ListNode(0);
        ListNode t = ans;
        while (!pq.isEmpty()) {
            t.next = pq.poll();
            t = t.next;
            if (t.next != null) {
                pq.offer(t.next);
            }
        }
        return ans.next;
    }
}
