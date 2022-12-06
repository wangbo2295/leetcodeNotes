package leetcode.WC;

import java.util.*;

public class No301 {

    public int fillCups(int[] amount) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i : amount) {
            if (i > 0) {
                pq.offer(i);
            }
        }
        int cnt = 0;
        while (!pq.isEmpty()) {
            Integer first = pq.poll();
            if (!pq.isEmpty()) {
                Integer second = pq.poll();
                second--;
                if (second > 0) pq.offer(second);
            }
            first--;
            if (first > 0) {
                pq.offer(first);
            }
            ++cnt;
        }
        return cnt;
    }

    public boolean canChange(String start, String target) {
        if (start.length() != target.length())  return false;
        int n = start.length();
        List<int[]> L = new ArrayList<>();
        List<int[]> R = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.empty() && start.charAt(i) == 'L') {
                R.add(new int[] {stack.pop(), i - 1});
            }
            if (start.charAt(i) == 'R') stack.push(i);
        }
        while (!stack.empty()) R.add(new int[] {stack.pop(), n - 1});
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.empty() && start.charAt(i) == 'R') {
                L.add(new int[] {i + 1, stack.pop()});
            }
            if (start.charAt(i) == 'L') stack.push(i);
        }
        while (!stack.empty()) L.add(new int[] {0, stack.pop()});
        int li = 0, ri = 0;
        for (int i = 0; i < n; i++) {
            if (target.charAt(i) == 'L') {
                if (L.size() < 1 || i < L.get(li)[0] || i > L.get(li)[1])   return false;
                ++li;
            }
            if (target.charAt(i) == 'R') {
                if (R.size() < 1 || i < R.get(ri)[0] || i > R.get(ri)[1])   return false;
                ++ri;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        No301 no301 = new No301();
        no301.canChange("_L__R__R_", "L______RR");
//        no301.canChange("R_L_", "__LR");
    }
}

class SmallestInfiniteSet {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    public SmallestInfiniteSet() {
        for (int i = 1; i <= 1001; i++) pq.offer(i);
    }

    public int popSmallest() {
        Integer min = pq.poll();
        while (!pq.isEmpty() && min.equals(pq.peek())) {
            pq.poll();
        }
        return min;
    }

    public void addBack(int num) {
        pq.offer(num);
    }
}



