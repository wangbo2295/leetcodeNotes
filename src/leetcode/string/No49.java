package leetcode.string;

import java.util.*;

public class No49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strs) {
            char[] ss = s.toCharArray();
            Arrays.sort(ss);
            String s1 = String.valueOf(ss);
            List<String> list;
            if (map.containsKey(s1)) {
                list = map.get(s1);
            }else {
                list = new ArrayList<>();
            }
            list.add(s);
            map.put(s1, list);
        }
        List<List<String>> res = new ArrayList<>();
        Iterator<Map.Entry<String, List<String>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            res.add(iterator.next().getValue());
        }
        return res;
    }

    public static void main(String[] args) {
        No49 no49 = new No49();
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        no49.groupAnagrams(strs);
    }
}
