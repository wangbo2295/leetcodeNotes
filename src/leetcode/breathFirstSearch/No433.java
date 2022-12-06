package leetcode.breathFirstSearch;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 *
 * 假设我们需要调查从基因序列start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 *
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 *
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 *
 * 注意：起始基因序列start 默认是有效的，但是它并不一定会出现在基因库中。
 */
public class No433 {
    /**
     * 思路：注意到数据范围很小，采用回溯算法(实际上bfs更合适，在后面有补充)
     * 从end 开始遍历，找到所有能变换的基因，再递归地寻找直到找到第一个能变换为start的基因
     * @param start
     * @param end
     * @param bank
     * @return
     */
    boolean[] used;
    String end;
    int min = Integer.MAX_VALUE;
    public int minMutation(String start, String end, String[] bank) {
        this.end = end;
        this.used = new boolean[bank.length];
        findMinPath(start, bank, 0);
        return min < 10 ? min : -1;
    }
    public void findMinPath(String from, String[] bank, int k) {
        if (from.equals(end)) {
            min = Math.min(min, k);
            return;
        }
        for (int i = 0; i < bank.length; i++) {
            if (!used[i] && valid(from, bank[i])) {
                used[i] = true;
                findMinPath(bank[i], bank, k + 1);
                used[i] = false;
            }
        }
    }

    public boolean valid(String from, String to) {
        int s = 0, e = 7;
        while (s < 8 && from.charAt(s) == to.charAt(s)) {
            s++;
        }
        while (e >= 0 && from.charAt(e) == to.charAt(e)) {
            e--;
        }
        if (s == e) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * bfs
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation2(String start, String end, String[] bank) {
        boolean[] used = new boolean[bank.length];
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int steps = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String next = queue.poll();
                for (int j = 0; j < bank.length; j++) {
                    if (!used[j] && valid(next, bank[j])) {
                        if (bank[j].equals(end)) return steps;
                        queue.offer(bank[j]);
                        used[j] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
