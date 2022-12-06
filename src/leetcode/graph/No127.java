package leetcode.graph;

import java.util.*;
/**
 * 字典wordList 中从单词 beginWord和 endWord 的 转换序列 是一个按下述规格形成的序列beginWord -> s1-> s2-> ... -> sk：
 * 每一对相邻的单词只差一个字母。
 * 对于1 <= i <= k时，每个si都在wordList中。注意， beginWord不需要在wordList中。
 * sk== endWord
 * 给你两个单词 beginWord和 endWord 和一个字典 wordList ，返回 从beginWord 到endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 */
public class No127 {
    /**
     * 先建立邻接关系，只相差一个字母的两个单词判定为邻接
     * 得到一个无向图，计算beginWord到endWord的最短路径
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> beginList = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            String s = wordList.get(i);
            if (isNeighbor(beginWord, s)) {
                beginList.add(s);
            }
            for (int j = 0; j < wordList.size(); j++) {
                if (j != i && isNeighbor(s, wordList.get(j))) {
                    List<String> orDefault = map.getOrDefault(s, new ArrayList<>());
                    orDefault.add(wordList.get(j));
                    map.put(s, orDefault);
                }
            }
        }
        map.put(beginWord, beginList);
        Set<String> used = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        used.add(beginWord);
        int steps = 1, ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (poll.equals(endWord)) {
                    ans = steps;
                    return ans;
                }
                List<String> list = map.get(poll);
                if (list == null) continue;
                for (String s : list) {
                    if (used.add(s)) {
                        queue.offer(s);
                    }
                }
            }
            steps++;
        }
        return ans;
    }

    /**
     * 不用先建立邻接关系，而是在遍历的时候判断是否存在邻接关系
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(beginWord);
        set.add(beginWord);
        int steps = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                if (s.equals(endWord))  return steps;
                for (String str : wordList) {
                    if (isNeighbor(s, str) && set.add(str)) {
                        queue.add(str);
                    }
                }
            }
            steps++;
        }
        return 0;
    }

    private boolean isNeighbor(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                cnt++;
                if (cnt > 1)    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        No127 no127 = new No127();
        List<String> wordList = List.of("lest","leet","lose","code","lode","robe","lost");
        no127.ladderLength("leet", "code", wordList);
    }
}
