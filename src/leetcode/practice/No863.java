package leetcode.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树（具有根结点root），一个目标结点target，和一个整数值 k 。
 * 返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。
 */
class No863 {
    List<Integer> res = new ArrayList<>();
    //思路，递归过程中找到target，然后返回target的深度d给其父节点，再对父节点进行递归查找深度为k-d的节点
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        depth(root,target,k);
        return res;
    }
    //查找k并返回其深度d给其所有父节点，对父节点的另一分支搜寻深度为k-d的节点
    //对于k的子节点，查找深度为k的节点即可
    public int depth(TreeNode root, TreeNode target, int k){
        if(root==null)  return 0;
        if(root==target){
            if(k==0)    res.add(root.val);  //k==0的特殊情况
            //对target的子树进行查找
            findNode(root.left,1,k);
            findNode(root.right,1,k);
            return 1;
        }
        int dL = depth(root.left,target,k);
        //如果target在左子树，就对右子树查找，同时判断一下当前节点是否符合题意
        if(dL>0){
            if(dL==k)   res.add(root.val);
            findNode(root.right,dL+1,k);
        }
        int dR = depth(root.right,target,k);
        //如果target在右子树，就对左子树查找
        if(dR>0){
            if(dR==k)   res.add(root.val);
            findNode(root.left,dR+1,k);
        }
        int r = Math.max(dL,dR);
        return r==0?0:r+1;  //如果target不在该分支，返回0，否则返回深度+1

    }
    //找到深度为k的节点
    public void findNode(TreeNode node,int d,int k){
        if(node==null)  return;
        if(d==k){
            res.add(node.val);
        }else{
            findNode(node.left,d+1,k);
            findNode(node.right,d+1,k);
        }
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}