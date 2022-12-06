package 剑指offer;

import java.util.*;

public class AlienOrder {
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> verts = new HashMap<>();
        for (String s : words) for (char c : s.toCharArray()) {
            verts.putIfAbsent(c, new ArrayList<>());
        }
        int[] degree = new int[128];
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                char[] rel = compare(words[i], words[j]);
                if (rel != null) {
                    if (rel[0] == 0 && rel[1] == 0) return "";
                    List<Character> list = verts.get(rel[0]);
                    list.add(rel[1]);
                    degree[rel[1]]++;
                    verts.put(rel[0], list);
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (char i = 'a'; i <= 'z'; i++) {
            if (verts.containsKey(i) && degree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            sb.append(c);
            List<Character> list = verts.get(c);
            for (Character ch : list) {
                if (--degree[ch] == 0) {
                    queue.offer(ch);
                }
            }
        }
        for (int i : degree) if (degree[i] != 0) return "";
        return sb.toString();
    }

    public char[] compare(String a, String b) {
        int la = a.length(), lb = b.length();
        for (int i = 0; i < Math.min(la, lb); i++) {
            char aa = a.charAt(i), bb = b.charAt(i);
            if (aa != bb)    return new char[]{aa, bb};
        }
        return la < lb ? null : new char[]{0, 0};
    }
}
