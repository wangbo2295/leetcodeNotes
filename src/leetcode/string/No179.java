package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class No179 {
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add("" + num);
        }
        list.sort((o1, o2) -> {
            String o3 = o1 + o2;
            String o4 = o2 + o1;
            for (int i = 0; i < o3.length(); i++) {
                if (o3.charAt(i) != o4.charAt(i)) return o4.charAt(i) - o3.charAt(i);
            }
            return 0;
        });
        while (list.size() > 1 && list.get(0).equals("0")) {
            list.remove(0);
        }
        return String.join("", list);
    }
}
