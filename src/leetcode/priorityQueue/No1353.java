package leetcode.priorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个数组events，其中events[i] = [startDayi, endDayi]，表示会议i开始于startDayi，结束于endDayi。
 * 你可以在满足startDayi<= d <= endDayi中的任意一天d参加会议i。注意，一天只能参加一个会议。
 * 请你返回你可以参加的最大会议数目。
 */
public class No1353 {
    /**
     * 贪心：每天在能参加的会议中参加结束时间最小的
     * 关键在于如何知道每天能参加哪些会议
     * 将会议按开始时间排序，用优先队列维护每天能参加的会议的结束时间
     * 枚举每一天，先将今天能参加的会议的结束时间入堆，由于从小到大排序了，每天只需将开始时间等于当天的会议入堆
     * 然后将已经结束的会议出堆，因为是小顶堆，只需将所有结束时间小于当天的会议出堆
     * 此时堆里剩下的就是所有当天能参加的会议，根据贪心思想我们直接参加结束时间最小的即堆顶的会议，弹出堆顶，并将会议数+1
     * 枚举下一天。。。重复上述过程
     * 直到每个会议都入过一次堆且堆已经空了，说明所有能参加的会议都参加完了，结束模拟，返回会议数
     */
    public int maxEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cnt = 0, n = events.length;
        for (int i = 1, j = 0; j < n || !pq.isEmpty(); i++) {
            while (j < n && events[j][0] == i) pq.offer(events[j++][1]);
            while (!pq.isEmpty() && pq.peek() < i) pq.poll();
            if (!pq.isEmpty()) {
                pq.poll();
                ++cnt;
            }
        }
        return cnt;
    }
}