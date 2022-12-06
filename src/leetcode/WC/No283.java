package leetcode.WC;

import java.util.*;

public class No283 {
    public List<String> cellsInRange(String s) {
        char r1 = s.charAt(0), r2 = s.charAt(3);
        int c1 = s.charAt(1) - '0', c2 = s.charAt(4) - '0';
        List<String> res = new ArrayList<>();
        for (char c = r1; c <= r2; c++) {
            for (int i = c1; i <= c2; i++) {
                res.add("" + c + i);
            }
        }
        return res;
    }

    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])    continue;
            int start;
            if (i == 0) start = 1;
            else start = nums[i - 1] + 1;
            for (; start < nums[i]; start++) {
                if (k == 0) return ans;
                ans += start;
                k--;
            }
        }
        for (int start = nums[nums.length - 1] + 1; k > 0; start++) {
            ans += start;
            k--;
        }
        return ans;
    }

    /**
     * 思路：计算每个节点的入度和左右子节点放入map，从入度为0的开始构造二叉树
     * @param descriptions
     * @return
     */
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] description : descriptions) {
            int[] ints = map.getOrDefault(description[0], new int[] {-1, -1, 0});
            ints[(description[2] + 1) & 1] = description[1];
            map.put(description[0], ints);
            int[] orDefault = map.getOrDefault(description[1], new int[]{-1, -1, 0});
            orDefault[2]++;
            map.put(description[1], orDefault);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Iterator<Map.Entry<Integer, int[]>> iterator = map.entrySet().iterator();
        TreeNode root = null;
        while (iterator.hasNext()) {
            Map.Entry<Integer, int[]> next = iterator.next();
            if ((next.getValue()[0] > 0 || next.getValue()[1] > 0) && next.getValue()[2] == 0) {
                root = new TreeNode(next.getKey());
                queue.offer(root);
            }
        }
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            int[] ints = map.get(poll.val);
            TreeNode left = ints[0] > 0 ? new TreeNode(ints[0]) : null;
            TreeNode right = ints[1] > 0 ? new TreeNode(ints[1]) : null;
            poll.left = left;
            poll.right = right;
            if (left != null)   queue.offer(left);
            if (right != null)  queue.offer(right);
        }
        return root;
    }

    public List<Integer> replaceNonCoprimes(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            Integer peek = stack.peek();
            int gcd = GCD(peek, nums[i]);
            int newNum;
            if (gcd > 1) {
                newNum = peek * nums[i] / gcd;
                stack.pop();
            } else {
                newNum = nums[i];
            }
            stack.push(newNum);
        }
        List<Integer> list = stack.stream().toList();
        return list;
    }

    private int GCD(int num1, int num2) {
        while(num2 > 0) {
            int t;
            t = num1 % num2;
            num1 = num2;
            num2 = t;
        }
        return num1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
