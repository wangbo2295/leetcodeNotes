package leetcode.practice;

import java.util.*;

public class No937 {
    /**
     * treemap有序
     * @param logs
     * @return
     */
    public String[] reorderLogFiles(String[] logs) {
        TreeMap<String, String> charMap = new TreeMap<>();
        List<String> numList = new ArrayList<>();
        for (String s : logs) {
            int index = 0;
            while (index < s.length() && s.charAt(index++) != ' ');
            String key = s.substring(index) + (char) 31 + s.substring(0, index);
            if (s.charAt(index) >= 'a' && s.charAt(index) <= 'z') {
                charMap.put(key, s);
            }else {
                numList.add(s);
            }
        }
        String[] res = new String[logs.length];
        int i = 0;
        Set<Map.Entry<String, String>> entrySet = charMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            res[i++] = iterator.next().getValue();
        }
        while (i < res.length) {
            res[i++] = numList.get(i - charMap.size());
        }
        return res;
    }
}
