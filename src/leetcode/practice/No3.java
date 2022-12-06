package leetcode.practice;

import java.util.LinkedList;
import java.util.Queue;

public class No3 {
    /**
     * map + 队列实现（其实就是滑动窗口）
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[256];
        Queue<Character> queue = new LinkedList<>();
        int maxLen = 0, tLen = 0;
        for (char c: s.toCharArray()) {
            if (map[c] == 0) {
                queue.offer(c);
                map[c]++;
                tLen++;
            }else {
                while (queue.peek() != c) {
                    char b = queue.poll();
                    map[b]--;
                }
                queue.poll();
                queue.offer(c);
                tLen = queue.size();
            }
            maxLen = Math.max(maxLen, tLen);
        }
        return maxLen;
    }


    public static void main(String[] args) {
        No3 no3 = new No3();
        no3.lengthOfLongestSubstring("pww$%^&*($%^kew");
    }
}
