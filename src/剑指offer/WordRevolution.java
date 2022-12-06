package 剑指offer;

import java.util.*;

public class WordRevolution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        Map<String, Node> map = new HashMap<>();
        int n = wordList.size();
        for (int i = 0; i < n; i++) {
            String a = wordList.get(i);
            Node node = map.getOrDefault(a, new Node(a, null, new HashSet<>()));
            for (int j = i + 1; j < n; j++) {
                String b = wordList.get(j);
                Node ver = map.getOrDefault(b, new Node(b, null, new HashSet<>()));
                if (check(a, b)) {
                    node.verts.add(ver);
                    ver.verts.add(node);
                }
                map.put(b, ver);
            }
            map.put(a, node);
        }
        Queue<Node> queue = new LinkedList<>();
        Node start = map.get(beginWord);
        queue.offer(start);
        start.vis = true;
        int stp = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i <size; i++) {
                Node cur = queue.poll();
                cur.vis = true;
                if (cur.cur.equals(endWord))    return stp;
                for (Node ver : cur.verts) {
                    if (!ver.vis)   queue.offer(ver);
                }
            }
            ++stp;
        }
        return 0;
    }

    public boolean check(String a, String b) {
        int cnt = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) ++cnt;
        }
        return cnt == 1;
    }

    private class Node {
        String cur;
        String path;
        Set<Node> verts;
        boolean vis;
        public Node(){}
        public Node(String cur, String path, Set<Node> verts) {
            this.cur = cur;
            this.path = path;
            this.verts = verts;
        }
    }
}
