package leetcode.sort;
import datastructures.heap.MyHeap;

import java.util.*;
public class No347 {
    /**
     * 基于快排的解法
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer cnt = map.getOrDefault(nums[i], 0);
            map.put(nums[i], ++cnt);
        }
        Map.Entry<Integer, Integer>[] entries = map.entrySet().toArray(new Map.Entry[map.size()]);
        quickSelect(entries, 0, entries.length - 1, k);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = entries[i].getKey();
        }
        return res;
    }

    private void quickSelect(Map.Entry<Integer, Integer>[] entries, int l, int r, int k) {
        if (l > r) return;
        int pivot = entries[l].getValue(), i, j;
        for (i = l, j = l + 1; j <= r; j++) {
            if (entries[j].getValue() > pivot) {
                swap(entries, ++i, j);
            }
        }
        swap(entries, l, i);
        if (i + 1 < k) {
            quickSelect(entries, i + 1, r, k - i - 1);
        } else if (i + 1 > k) {
            quickSelect(entries, l, i - 1, k);
        }
    }

    private void swap(Map.Entry<Integer, Integer>[] entries, int i, int j) {
        Map.Entry<Integer, Integer> t = entries[i];
        entries[i] = entries[j];
        entries[j] = t;
    }

    /**
     * 基于优先队列的解法
     * 建立一个大小为k的小顶堆，遇到比堆顶元素大的就pop，最后堆里剩下的就是最大的k个元素
     */
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer cnt = map.getOrDefault(nums[i], 0);
            map.put(nums[i], ++cnt);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(o->o.getValue()));
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (queue.size() < k) {
                queue.offer(next);
            }else if (!queue.isEmpty() && queue.peek().getValue() < next.getValue()) {
                queue.poll();
                queue.offer(next);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll().getKey();
        }
        return res;
    }

    public int[] topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer cnt = map.getOrDefault(nums[i], 0);
            map.put(nums[i], ++cnt);
        }
        MyHeap<Map.Entry<Integer, Integer>> queue = new MyHeap<>(Comparator.comparingInt(o -> o.getValue()));
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (queue.size() < k) {
                queue.offer(next);
            }else if (!queue.isEmpty() && queue.top().getValue() < next.getValue()) {
                queue.poll();
                queue.offer(next);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll().getKey();
        }
        return res;
    }



    public static void main(String[] args) {

    }
}


