package leetcode.simulate;

import java.util.*;

public class No1823 {
    /**
     * ้ๅๆจกๆ
     * @param n
     * @param k
     * @return
     */
    public int findTheWinner(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.offer(i+1);
        }
        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.poll();
    }
}
