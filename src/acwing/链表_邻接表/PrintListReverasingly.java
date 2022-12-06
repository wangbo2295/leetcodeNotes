package acwing.链表_邻接表;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintListReverasingly {
    public int[] printListReversingly(ListNode head) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        while (!stack.empty()) {
            res.add(stack.pop());
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
