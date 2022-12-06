package acwing.排序;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RunningMedian {
    PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> min = new PriorityQueue<>();
    public void insert(Integer num) {
        if (min.size() == 0 || num >= min.peek())  min.offer(num);
        else max.offer(num);
        if (max.size() > min.size()) {
            min.offer(max.poll());
        } else if (min.size() - max.size() > 1) {
            max.offer(min.poll());
        }
    }

    public Double getMedian() {
        if (min.size() > max.size()) {
            return(double) min.peek();
        } else {
            return (double) (max.peek() + min.peek()) / 2;
        }
    }
}
