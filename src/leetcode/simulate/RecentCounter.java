package leetcode.simulate;

import java.util.LinkedList;
import java.util.Queue;

public class RecentCounter {
//    Queue<Integer> queue = new LinkedList<>();
//    int count;
//    public RecentCounter() {
//
//    }
//
//    public int ping(int t) {
//        queue.offer(t);
//        while (queue.peek() < t - 3000) {
//            queue.poll();
//        }
//        return queue.size();
//    }

    int[] queue = new int[10000];
    int start = 0;
    int end = 0;
    public RecentCounter() {

    }

    public int ping(int t) {
        queue[end++] = t;
        while (queue[start] < t - 3000) {
            start++;
        }
        return end - start;
    }
}
