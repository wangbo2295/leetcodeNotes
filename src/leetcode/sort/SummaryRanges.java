package leetcode.sort;

import java.util.Comparator;
import java.util.TreeSet;

public class SummaryRanges {
    TreeSet<int[]> treeSet;
    public SummaryRanges() {
        treeSet = new TreeSet<>(Comparator.comparingInt(o->o[0]));
    }

    public void addNum(int val) {
        int[] newrange = {val, val};
        int[] floor = treeSet.floor(newrange);
        int[] ceiling = treeSet.ceiling(newrange);
        if (floor != null && newrange[0] == floor[1] + 1) {
            newrange[0] = floor[0];
            treeSet.remove(floor);
        }
        if (ceiling != null && newrange[1] + 1 == ceiling[0]) {
            newrange[1] = ceiling[1];
            treeSet.remove(ceiling);
        }
        if (floor != null && val <= floor[1])   return;
        treeSet.add(newrange);
    }

    public int[][] getIntervals() {
        return treeSet.toArray(new int[treeSet.size()][]);
    }
}
