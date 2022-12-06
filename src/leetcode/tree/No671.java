package leetcode.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class No671 {
    public int findSecondMinimumValue(TreeNode root) {
        int secondMin = -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left == null) {
                if (poll.val != root.val) {
                    secondMin = secondMin == -1 ? poll.val : Math.min(secondMin, poll.val);
                }
            } else {
              queue.offer(poll.left);
              queue.offer(poll.right);
            }
        }
        return secondMin;
    }
}
