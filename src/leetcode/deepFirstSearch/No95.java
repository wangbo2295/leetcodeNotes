package leetcode.deepFirstSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成1 ～ n 的所有可能的二叉搜索树
 * 思路：对左右子树进行递归地查找
 */
public class No95 {

    public List<TreeNode> generateTrees(int n) {
        return postOrder(1, n);
    }

    public List<TreeNode> postOrder(int min, int max) {
        List<TreeNode> res = new ArrayList<>();
        //如果范围错位，返回null，由于需要保证双层for循环的遍历，所以要使res的size大于0，加一个null进去
        if (min > max)  {
            res.add(null);
            return res;
        }
        for (int i = min; i <= max; i++) {
            List<TreeNode> LSTS = postOrder(min, i - 1);
            List<TreeNode> RSTS = postOrder(i + 1, max);
            //对左右子树的所有可能进行组合，生成 l * r 种树
            for (int j = 0; j < LSTS.size(); j++) {
                for (int k = 0; k < RSTS.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = LSTS.get(j);
                    root.right = RSTS.get(k);
                    res.add(root);
                }
            }
        }
        return res;
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {}

        public TreeNode(int _val) {
            val = _val;
        }

        public TreeNode(int _val, TreeNode _left, TreeNode _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
