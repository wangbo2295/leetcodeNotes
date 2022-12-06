package leetcode.WC;

import java.util.*;

public class No281 {
    public int countEven(int num) {
        int cnt = 0;
        for (int i = 1; i <= num; i++) {
            int sum = 0, j = i;
            while (j > 0) {
                sum += j % 10;
                j /= 10;
            }
            if ((sum & 1) == 0) cnt++;
        }
        return cnt;
    }

    public ListNode mergeNodes(ListNode head) {
        head = head.next;
        ListNode dummy = new ListNode(0);
        ListNode t = dummy;
        while (head != null) {
            int sum = 0;
            while (head.val != 0) {
                sum += head.val;
                head = head.next;
            }
            head = head.next;
            t.next = new ListNode(sum);
            t = t.next;
        }
        return dummy.next;
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((o1, o2)->o2.getKey().compareTo(o1.getKey()));
        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {queue.offer(iterator.next());}
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> top = queue.poll();
            int append = Math.min(repeatLimit, top.getValue());
            sb.append(("" + top.getKey()).repeat(append));
            top.setValue(top.getValue() - append);
            if (!queue.isEmpty() && top.getValue() > 0) {
                Map.Entry<Character, Integer> second = queue.poll();
                sb.append(second.getKey());
                second.setValue(second.getValue() - 1);
                if (second.getValue() > 0)  queue.offer(second);
                queue.offer(top);
            }
        }
        return sb.toString();
    }

    /**
     * 求nums[i] 与k的最大公约数，然后令l = k / gcd(nums[i])，寻找nums[j]是l的倍数的个数
     */
    public long countPairs(int[] nums, int k) {
        long cnt = 0;
        return cnt;
    }


    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next;}
    }
}


