package leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 */
public class Interview17_11 {
    /**
     * 优化：如果要多次查找并且单词不同，那么就要将单词出现的位置保存起来以便重复利用，用到的hashmap
     * 思路：统计每个单词出现的下标，并按顺序放在list里面，再将键值对放入map
     * 拿到两个目标单词的list，双指针一一计算距离取最小值
     */
    public int findClosest(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> list;
            if (map.containsKey(words[i])) {
                list = map.get(words[i]);
            }else {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(words[i], list);
        }
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int minDistance = Integer.MAX_VALUE;
        int index1 = 0, index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            Integer i1 = list1.get(index1);
            Integer i2 = list2.get(index2);
            if (i1 > i2) {
                minDistance = Math.min(minDistance, i1 - i2);
                index2++;
            }else {
                minDistance = Math.min(minDistance, i2 - i1);
                index1++;
            }
        }
        return minDistance;
    }

    /**
     * 如果只要查找一次的话，可以只遍历一次就得出最小值
     * 两个指针记录两个单词出现的位置
     * 当找到其中一个单词时，就计算与另一个单词的距离，由于只用一个指针保存位置，所以每次都是和最近的相减
     * 遍历过程中在所有距离中取最小值
     */
    public int findClosest2(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1, minDist = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
                if (index2 != -1) minDist = Math.min(minDist, i - index2);
            }
            if (words[i].equals(word2)) {
                index2 = i;
                if (index1 != -1) minDist = Math.min(minDist, i - index1);
            }
        }
        return minDist;
    }
}
