package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No1200 {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i] - arr[i - 1]);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == min) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(arr[i - 1]);
                arrayList.add(arr[i]);
                res.add(arrayList);
            }
        }
        return res;
    }
}
