package leetcode.sort;

import java.util.Comparator;
import java.util.TreeSet;

public class ClosestRoom {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        TreeSet<int[]> room = new TreeSet<>(Comparator.comparingInt(o->o[1]));
        TreeSet<int[]> ids = new TreeSet<>(Comparator.comparingInt(o->o[0]));
        for (int[] ints : rooms) {
            room.add(ints);
            ids.add(ints);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] ceiling = room.ceiling(queries[i]);
            if (ceiling == null) ans[i] = -1;
            else ans[i] = ids.floor(queries[i])[0];
        }
        return ans;
    }
}
