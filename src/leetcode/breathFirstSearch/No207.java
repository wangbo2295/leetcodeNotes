package leetcode.breathFirstSearch;

import java.util.*;

/**
 * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai 则 必须 先学习课程 bi 。
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
public class No207 {
    /**
     * 思路：这题类似于进程的前趋图，就是判断是否是一个有向无环图
     * 对课程表进行拓扑排序，具体做法如下：
     * 计算每个顶点的入度，然后将入度为0的顶点放入一个队列
     * 依次出队，将与出队节点相连的顶点的入度减1，如果入度减为0，则加入队列
     * 出队的顺序就是拓扑排序的顺序
     * 每次从队列弹出一个节点时numCourses减1，最后判断numCourses是否为0
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> list = map.getOrDefault(prerequisites[i][1], new ArrayList<>());
            list.add(prerequisites[i][0]);
            map.put(prerequisites[i][1], list);
            inDegree[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)   queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<Integer> orDefault = map.getOrDefault(queue.poll(), new ArrayList<>());
                for (Integer integer : orDefault) {
                    inDegree[integer]--;
                    if (inDegree[integer] == 0) queue.offer(integer);
                }
                numCourses--;
            }
        }
        return numCourses == 0;
    }

    public static void main(String[] args) {
        No207 no207 = new No207();
        int[][] courses = {{1,4},{2,4},{3,1},{3,2}};
        no207.canFinish(5, courses);
    }
}
