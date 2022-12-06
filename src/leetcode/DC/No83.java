package leetcode.DC;

import java.util.*;

public class No83 {

    public String bestHand(int[] ranks, char[] suits) {
        int[] rankmap = new int[14];
        int[] suitmap = new int[4];
        for (int rank : ranks) {
            rankmap[rank]++;
        }
        for (char suit : suits) {
            suitmap[suit - 'a']++;
        }
        for (int i : suitmap) {
            if (i == 5) return "Flush";
        }
        for (int i : rankmap) {
            if (i >= 3) return "Three of a Kind";
            if (i >= 2) return "Pair";
        }
        return "High Card";
    }

    public long zeroFilledSubarray(int[] nums) {
        int cur = 0;
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                cur++;
            } else {
                cur = 0;
            }
            ans += cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        NumberContainers numberContainers = new NumberContainers();
        numberContainers.change(1, 10);
        numberContainers.find(10);
        numberContainers.change(1, 20);
        numberContainers.find(10);
        numberContainers.find(20);
        numberContainers.find(30);
//        numberContainers.change(3, 10);
//        numberContainers.change(5, 10);
//        numberContainers.find(10);
//        numberContainers.change(1, 20);
//        numberContainers.find(10);
    }
}

class NumberContainers {

    Map<Integer, TreeSet<Integer>> numMap;
    Map<Integer, Integer> indexMap;

    public NumberContainers() {
        numMap = new HashMap<>();
        indexMap = new HashMap<>();
    }

    public void change(int index, int number) {
        if (indexMap.containsKey(index)) {
            Integer num = indexMap.get(index);
            Set<Integer> set = numMap.get(num);
            set.remove(index);
        }
        indexMap.put(index, number);
        TreeSet<Integer> tree = numMap.getOrDefault(number, new TreeSet<>());
        tree.add(index);
        numMap.put(number, tree);
    }

    public int find(int number) {
        TreeSet<Integer> treeSet = numMap.get(number);
        if (treeSet == null || treeSet.isEmpty())    return -1;
        return treeSet.first();
    }
}
