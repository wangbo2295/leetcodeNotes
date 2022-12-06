package leetCodeTest;

import java.util.*;


public class HashMapTest {
    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] test = {-1,0,1,2,-1,-4};
//        List<List<Integer>> lists = solution.threeSum(test);
//        System.out.println("result:");
//        for (List<Integer> list : lists) {
//            for (Integer integer : list) {
//                System.out.print(integer+"---");
//            }
//            System.out.println();
//        }
//        char[] s = {'a','b','c'};
//        Josephus josephus = new Josephus();
//        int res = josephus.solution(2,10);
//        System.out.println(res);
//        Solution2 solution2 = new Solution2();
//        solution2.generateTrees(3);
        //[1,2,2,3,4,4,3]
        //[1,2,2,2,null,2]
//        TreeNode treeNode1 = new TreeNode(1);
//        treeNode1.left = new TreeNode(2);
//        treeNode1.right = new TreeNode(2);
//        treeNode1.left.left = new TreeNode(2);
//        treeNode1.left.right = new TreeNode(4);
//        treeNode1.right.left = new TreeNode(2);
//        treeNode1.right.right = new TreeNode(3);
//        treeNode1.right.right = new TreeNode(399);
//        Solution3 solution3 = new Solution3();
//        solution3.recoverTree(treeNode1);
//        Solution4 solution4 = new Solution4();
//        solution4.levelOrder(treeNode1);
//        Solution5 solution5 = new Solution5();
//        boolean res = solution5.isSymmetric(treeNode1);
//        System.out.println("isSymmetric"+res);

//        String s = "s";
//        s = s.substring(1);
//
//        System.out.println(s);
//        System.out.println(-7%2);
//        int[][] arr = new int[3][4];
//        System.out.println(arr.length + "-" + arr[0].length);
//        Queue<int[]> queue = new LinkedList<>();
//        Solution6 solution6 = new Solution6();
//        solution6.maximumSubsequenceCount("abdcdbc","ac");
//        PriorityQueue<Double> priorityQueue = new PriorityQueue<>((o1,o2)->-o1.compareTo(o2));
//        priorityQueue.add((double)4);
//        Solution7 solution7 = new Solution7();
//        solution7.countCollisions("RLRSLL");
//        Solution8 solution8 = new Solution8();
//        int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
//        solution8.rotate(matrix);
//        System.out.println(-123*10);
//        Solution9 solution9 = new Solution9();
//        solution9.myAtoi("2147483648");
//        System.out.println(""+1+'a');
//        Solution10 solution10 = new Solution10();
//        solution10.countAndSay(2);
//        Solution11 solution11 = new Solution11();
//        ListNode treeNode1 = new ListNode(1);
//        treeNode1.next = new ListNode(2);
//        treeNode1.next.next = new ListNode(2);
//        treeNode1.next.next.next = new ListNode(1);
////        treeNode1.next = new ListNode(4);
//        solution11.isPalindrome(treeNode1);
//        Solution12 solution12 = new Solution12();
//        int[] q = {1,2,3,4,5,90};
//        long[] res = solution12.kthPalindrome(q,5);
//        for(long l: res){
//            System.out.println(l);
//        }
//        Solution14 solution14 = new Solution14();
//        solution14.hammingWeight(-3);
//        Solution13 solution13 = new Solution13();
//        int[][] arr = {{1,4,8,6,2,2,1,7},{4,7,3,1,4,5,5,1},{8,8,2,1,1,8,0,1},{8,9,2,9,8,0,8,9},{5,7,5,7,1,8,5,5},{7,0,9,4,5,6,5,6},{4,9,9,7,9,1,9,0}};
//        int i = solution13.minPathSum(arr);
//        System.out.println(i);
//        Solution15 solution15 = new Solution15();
//        solution15.isInterleave("aabcc","dbbca","aadbbcbcac");
//        Solution16 solution16 = new Solution16();
//        TreeNode treeNode1 = new TreeNode(3);
//        treeNode1.left = new TreeNode(4);
//        treeNode1.left.left = new TreeNode(1);
//        treeNode1.right = new TreeNode(5);
//        treeNode1.left.right = new TreeNode(3);
//        treeNode1.right.right = new TreeNode(1);
//        solution16.rob(treeNode1);
//        Solution17 solution17 = new Solution17();
//        int[] nums = {1,2,3};int target = 4;
//        solution17.combinationSum4(nums,target);
//        String s = "sdahusdo";
//        int sd = s.indexOf("sdo");
//        System.out.println(sd);
//        Solution18 solution18 = new Solution18();
//        solution18.isInterleave("a","","a");
//        Solution19 solution19 = new Solution19();
//        int[] height = {4,2,0,3,2,4,3,4};
//        solution19.trap(height);
//        Solution20 solution20 = new Solution20();
//        int[] a1 = {9,3,15,20,7};
//        int[] a2 = {9,15,7,20,3};
//        solution20.buildTree(a1,a2);
//        Solution21 solution21 = new Solution21();
//        int[] a3 = {3,2,1,6,0,5};
//        solution21.constructMaximumBinaryTree(a3);
//        List<Integer> list = new ArrayList<>();
//        List<Character> list1 = new LinkedList<>();
//        String s1 = "sda";
//        String s2 = "sfa";
//        int i = s1.compareTo(s2);
//        System.out.println(i);
//        Set<Integer> set = new HashSet<>();
//        List<Integer> list2 = new ArrayList<>(set);
//        String s = "Sad";
//        s.indexOf("s",0);
//        new String(new StringBuilder());
//        new StringBuilder().delete(0,0);
//        String s = String.copyValueOf(list1)
//        Integer[] res = new Integer[list.size()];
//        Integer[] integers = list.toArray(new Integer[10]);
//        Solution22 solution22 = new Solution22();
//        TreeNode treeNode1 = new TreeNode(3);
//        treeNode1.left = new TreeNode(5);
//        treeNode1.left.left = new TreeNode(6);
//        treeNode1.left.right = new TreeNode(2);
//        treeNode1.left.right.left = new TreeNode(7);
//        treeNode1.left.right.right = new TreeNode(4);
//        treeNode1.right = new TreeNode(1);
//        treeNode1.right.left = new TreeNode(0);
//        treeNode1.right.right = new TreeNode(8);
//        solution22.lowestCommonAncestor(treeNode1,treeNode1.left,treeNode1.right);
//        Solution23 solution23 = new Solution23();
//        solution23.restoreIpAddresses("25525511135");
//        new StringBuilder().append(2);
//        [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//        String[][] param = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
//        String[][] param = {{"JFK","ATL"},{"ORD","PHL"},{"JFK","ORD"},{"PHX","LAX"},{"LAX","JFK"},{"PHL","ATL"},{"ATL","PHX"}};
//        String[][] param = {{"EZE","AXA"},{"TIA","ANU"},{"ANU","JFK"},{"JFK","ANU"},{"ANU","EZE"},{"TIA","ANU"},{"AXA","TIA"},{"TIA","JFK"},{"ANU","TIA"},{"JFK","TIA"}};
//        List<List<String>> params = new ArrayList();
//        params.sort((o1,o2)->{
//            for(int i=0;i<o1.size();i++){
//                int j = o1.get(i).compareTo(o2.get(i));
//                if(j!=0)    return j;
//            }
//            return 0;
//        });
//        for(String[] ss: param){
//            List<String> list = new ArrayList<>();
//            for(String s: ss){
//                list.add(s);
//            }
//            params.add(list);
//        }
//        Solution24 solution24 = new Solution24();
//        solution24.findItinerary(params);
//        StringBuilder stringBuilder = new StringBuilder("ssas");
//        stringBuilder.replace(0,0,"c");
//        System.out.println(stringBuilder);
//        char[][] board = {
//                {'5','3','.','.','7','.','.','.','.'},
//                {'6','.','.','1','9','5','.','.','.'},
//                {'.','9','8','.','.','.','.','6','.'},
//                {'8','.','.','.','6','.','.','.','3'},
//                {'4','.','.','8','.','3','.','.','1'},
//                {'7','.','.','.','2','.','.','.','6'},
//                {'.','6','.','.','.','.','2','8','.'},
//                {'.','.','.','4','1','9','.','.','5'},
//                {'.','.','.','.','8','.','.','7','9'}
//        };
//        Solution25 solution25 = new Solution25();
//        solution25.solveSudoku(board);
//        Solution26 solution26 = new Solution26();
//        int[] arr = {-3,-1,-1};
//        solution26.maxProduct(arr);
//        int[] arr = {2,3};int[] arr1 = {2,3};
//        boolean equals = arr.equals(arr1);
//        System.out.println(equals);
//        Solution27 solution27 = new Solution27();
//        int[][] arr = new int[10][10];
//        Arrays.sort(arr, (o1, o2) -> {
//            if (o1[0]==o2[0]) return o1[1]-o2[1];
//            return o1[0]-o2[0];
//        });
//        List<int[]> queue = new ArrayList<>();
//        queue.add(0,arr[0]);
//        Solution28 solution28 = new Solution28();
//        int[][] input = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
//        solution28.reconstructQueue(input);
//        Solution29 solution29 = new Solution29();
//        solution29.partitionLabels("caedbdedda");
        Solution30 solution30 = new Solution30();
//        int[] prices = {1,3,7,5,10,3};
        int[] prices = {9,8,7,1,2};
        int fee = 3;
        solution30.maxProfit(prices,fee);

    }
}
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer,Integer> map0 = new HashMap<>();
        int s = 0,len = nums.length;
        //对原数组进行去重
        for(int f=0;f<nums.length;f++){
            if(!map0.containsKey(nums[f])){
                map0.put(nums[f],f);
                nums[s++] = nums[f];
            }else{
                len--;
            }
        }



        List<List<Integer>> res = new ArrayList<>();
        Map<Integer,List<int[]>> map1 = new HashMap<>();
        //统计所有两数之和可能性
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                int[] arr = {nums[i],nums[j]};
                List<int[]> temp_list;
                if(!map1.containsKey(nums[i]+nums[j])){
                    temp_list = new ArrayList<>();
                }else{
                    temp_list = map1.get(nums[i]+nums[j]);
                }
                temp_list.add(arr);
                map1.put(nums[i]+nums[j],temp_list);
            }
        }
        for(int k=0;k<len;k++){
            List<Integer> ints = new ArrayList<>();
            if(map1.containsKey(-nums[k])){
                List<int[]> temp_list = map1.get(-nums[k]);
                for(int[] i: temp_list){
                    if(i[0]==nums[k]||i[1]==nums[k]){
                        continue;
                    }
                    ints.add(nums[k]);
                    ints.add(i[0]);
                    ints.add(i[1]);
                    res.add(ints);
                }
            }
        }
        return res;


    }
}

class Solution2 {
    public List<TreeNode> generateTrees(int n) {
        List<List<TreeNode>> f = new ArrayList<>();
        List<TreeNode> f_1 = new ArrayList<>();
        f_1.add(new TreeNode(1));
        f.add(f_1);
        for(int i=2;i<=n;i++){
            //开始计算f(i)
            List<TreeNode> f_i = new ArrayList<>();
            //从1-i，所有情况累加
            for(int j=1;j<=i;j++){
                TreeNode temp =  new TreeNode(j);//以j为根节点
                int size = j-2<0?1:f.get(j-2).size();
                //统计左子树情况
                for(int k=0;k<size;k++){
                    if (j-1!=0)
                        temp = addToTree(temp,f.get(j-2).get(k));
                    int size2 = i-j-1<0?1:f.get(i-j-1).size();
                    //统计右子树情况
                    for(int l=0;l<size2;l++){
                        if (i-j!=0)
                            temp =  addToTree(temp,f.get(i-j-1).get(l));
                        f_i.add(temp);//加入f(i)
                    }
                }
            }
            //保存f(i)
            f.add(f_i);
        }
        return f.get(n-1);
    }

    public TreeNode addToTree(TreeNode cur,TreeNode newNode){
        if(cur==null||newNode==null){
            return cur;
        }
        if(newNode.val>cur.val){
            cur.right = addToTree(cur.right,newNode);
        }else{
            cur.left = addToTree(cur.left,newNode);
        }
        return cur;
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

class Solution3 {
    List<Integer> ordered = new ArrayList<>();
    List<Integer> disOrders = new ArrayList<>();
    public void recoverTree(TreeNode root) {
        middleSearch(root);
        findDisOrdered(ordered);
        switchValue(root,disOrders.get(0),disOrders.get(1));
        System.out.println(root);

    }

    public void middleSearch(TreeNode node){
        if(node==null)  return;
        middleSearch(node.left);
        ordered.add(node.val);
        middleSearch(node.right);
    }
    public void findDisOrdered(List<Integer> ordered){
        for(int i=0;i<ordered.size();i++){
            if(i==0&&ordered.get(i)>ordered.get(i+1)||i==ordered.size()-1&&ordered.get(i)<ordered.get(i-1))
                disOrders.add(ordered.get(i));
            else if((i!=0&&i!=ordered.size()-1)
                    &&(ordered.get(i)>ordered.get(i+1)&&ordered.get(i)>ordered.get(i-1)
                    ||ordered.get(i)<ordered.get(i+1)&&ordered.get(i)<ordered.get(i-1)))
                disOrders.add(ordered.get(i));
        }
    }
    public void switchValue(TreeNode node,Integer i,Integer j){
        if(node==null) return;
        switchValue(node.left,i,j);
        if(node.val==i) node.val=j;
        else if(node.val==j) node.val = i;
        switchValue(node.right,i,j);
    }
}

class Solution4 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null)  return null;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        one.add(root.val);
        res.add(one);
        List<TreeNode> temp = new ArrayList<>();
        temp.add(root);
        int i=0;
        while(temp.size()>0){
            List<Integer> resi = new ArrayList<>();
            for(int j=0;i<temp.size();j++){
                TreeNode left=null,right=null;
                if(temp.get(i).left != null){
                    resi.add(temp.get(i).left.val);
                    left = temp.get(i).left;
                }
                if(temp.get(i).right != null){
                    resi.add(temp.get(i).right.val);
                    right = temp.get(i).right;
                }
                temp.clear();
                if(left!=null)  temp.add(left);
                if(right!=null) temp.add(right);
            }
            res.add(resi);
        }
        return res;
    }
}

class Solution5 {
    public boolean isSymmetric(TreeNode root) {
        if(root.left==null&&root.right==null)   return true;
        if(!(root.left!=null&&root.right!=null))    return false;
        Queue<Integer> queue = new LinkedList<>();
        readLeft(root.left,queue);
        return readRight(root.right,queue);
    }

    public void readLeft(TreeNode node,Queue queue){
        if(node==null){
            queue.offer(-101);
            return;
        }
        readLeft(node.left,queue);
        queue.offer(node.val);
        readLeft(node.right,queue);
    }

    public boolean readRight(TreeNode node,Queue queue){
        if(node==null){
            return queue.poll().equals(-101);
        }
        boolean left = readRight(node.right,queue);
        boolean cur = queue.poll().equals(node.val);
        boolean right = readRight(node.left,queue);
        return left&&cur&&right;
    }
}

class Solution6 {
    public long maximumSubsequenceCount(String text, String pattern) {
        String s = "";long res = 0;
        for(char c: text.toCharArray()){
            if(c==pattern.charAt(0)||c==pattern.charAt(1)){
                s += c;
            }
        }
        if(pattern.charAt(0)==pattern.charAt(1)){
            int n = s.length()+1;
            res = n*(n-1)/2;
        }else{
            int num_a = 0,num_b = 0;
            long sum = 0;
            String s1 = "" + pattern.charAt(0) + s;
            for(char c: s1.toCharArray()){
                if(c==pattern.charAt(0))    num_a++;
                if(c==pattern.charAt(1)){
                    // num_b++;
                    sum += num_a;
                }
            }
            long sum2 = 0;
            num_a = 0;num_b = 0;
            String s2 = s + pattern.charAt(1);
            for(char c: s2.toCharArray()){
                if(c==pattern.charAt(0))    num_a++;
                if(c==pattern.charAt(1)){
                    // num_b++;
                    sum2 += num_a;
                }
            }
            res = Math.max(sum,sum2);
        }
        return res;
    }
}

class Solution7 {
    public int countCollisions(String directions) {
        char[] chars = directions.toCharArray();
        int count = 0;
        for(int i=0;i<chars.length-1;i++){
            if((chars[i]=='R'&&chars[i+1]=='L')||chars[i]=='S'){
                if(chars[i]=='R'&&chars[i+1]=='L'){
                    count += 2;
                }
                int l = i-1;int r = i+1;
                while(l>=0&&chars[l--]=='R'){
                    count++;
                }
                while(r<chars.length&&chars[r++]=='L'){
                    count++;
                }
            }
        }
        return count;
    }
}

class Solution8 {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int start = 0;
        while(len-1-start*2>0){
            int[] buff = new int[len-1-start*2];
            for(int i=start;i<len-start-1;i++){
                buff[i-start] = matrix[start][i];
            }
            for(int i=start;i<len-start-1;i++){
                matrix[start][len-2-i] = matrix[i+1][start];
            }
            for(int i=start;i<len-start-1;i++){
                matrix[i+1][start] = matrix[len-start-1][i+1];
            }
            for(int i=start;i<len-start-1;i++){
                matrix[len-start-1][i+1] = matrix[len-2-i][len-start-1];
            }
            for(int i=start;i<len-start-1;i++){
                if(len%2==0&&start+1==len/2){
                    matrix[start+1][start+1] = buff[0];
                    return;
                }else{
                    matrix[len-2-i][len-start-1] = buff[len-2-start-i];
                }
            }
            start++;
        }

    }
}

class Solution9 {
    public int myAtoi(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        int index = 0;
        boolean flag = true;
        while(index<cs.length&&cs[index]==' ')   index++;
        if(index<cs.length&&(cs[index]=='+'||cs[index]=='-')){
            if(cs[index]=='-'){
                flag = false;
            }
            index++;
        }
        int num = 0;
        while(index<cs.length&&
                cs[index]>='0'&&
                cs[index]<='9'){
            int t = cs[index]-'0';
            int newNum = num*10 + t;
            if((newNum-t)/10!=num) return flag?Integer.MAX_VALUE:Integer.MIN_VALUE;
            num = newNum;
            index++;
        }
        return flag?num:-num;
    }
}

class Solution10 {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String num = countAndSay(n - 1);
        char[] chars = num.toCharArray();
        String res = "";
        int count = 1;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                count++;
            } else {
                res += count;
                res += chars[i];
                count = 1;
            }
        }
        res += count;
        res += chars[chars.length-1];
        return res;
    }
}


class Solution11 {
    public boolean isPalindrome(ListNode head) {
        int size = countSize(head);
        int half = size>>1;
        boolean flag = size%2==1;
        Stack<Integer> stack = new Stack<>();
        while(size>0){
            if(flag&&size==half+1){
                head = head.next;
                size--;
                continue;
            }
            if(size>half){
                stack.push(head.val);
            }else if(stack.peek().equals(head.val)){
                stack.pop();
            }else{
                return false;
            }
            head = head.next;
            size--;
        }
        return stack.empty();
    }
    public int countSize(ListNode node){
        if(node==null){
            return 0;
        }

        return countSize(node.next)+1;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution12 {
    public long[] kthPalindrome(int[] queries, int intLength) {
        List<List<Long>> dp = new ArrayList<>();
        List<Long> dp1 = new ArrayList<>();
        List<Long> dp2 = new ArrayList<>();
        for(long i=1;i<=9;i++) {
            dp1.add(i);
            dp2.add(10 * i + i);
        }
        dp.add(dp1);
        dp.add(dp2);

        for(int i=2;i<intLength;i++){
            List<Long> dpi = new ArrayList<>();
            List<Long> dpi_2 = dp.get(i-2);
            int unit = dpi_2.size()+1;
            for(int j=0;j<unit*9;j++){
                long base = (j/unit+1)*(long)Math.pow(10,i)+j/unit+1;
                if(j%unit==0){
                    dpi.add(base);
                }else{
                    dpi.add(base+dpi_2.get(j%unit-1)*10);
                }
            }
            dp.add(dpi);
        }
        long[] res = new long[queries.length];
        int index = 0;
        List<Long> dpn = dp.get(intLength-1);
        for(int i: queries){
            res[index++] = dpn.get(i-1);
        }
        return res;
    }
}
class Solution14 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        boolean flag = false;
        if(n<0){
            n ^= 1;
            flag = true;
        }
        while(n>0){
            if(n%2==1){
                res++;
            }
            n >>= 1;
        }
        return flag ? 32-res : res;

    }
}

class Solution13 {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] min  = new int[rows][columns];
        min[0][0] = grid[0][0];
        for(int i=1;i<rows;i++){
            min[i][0] = min[i-1][0] + grid[i][0];
        }
        for(int i=1;i<columns;i++){
            min[0][i] = min[0][i-1] + grid[0][i];
        }
        for(int layer = 2;layer<rows+columns-1;layer++) {
            int c1 = (layer - 1)>=rows?rows-1:(layer - 1);
            while (c1 > 0) {
                if(layer - c1>=columns) break;
                int v1 = min[c1 - 1][layer - c1];
                int v2 = min[c1][layer - c1 - 1];
                min[c1][layer - c1] = Math.min(v1, v2) + grid[c1][layer - c1];
                c1--;
            }
        }
        return min[rows-1][columns-1];

    }
}

class Solution15 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int p1 = 0,p2 = 0,p3=0;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        while(p3<c3.length){
            if(c3[p3]!=c1[p1]&&c3[p3]!=c2[p2]){
                return false;
            }
            while(c3[p3]==c1[p1]&&(p3==c3.length||p1==c1.length)){
                p1++;p3++;
            }
            while(c3[p3]==c2[p2]&&(p3==c3.length||p2==c2.length)){
                p2++;p3++;
            }
        }
        return true;
    }
}
class Solution16 {
    int[] dp = new int[10000];
    int i = 0;
    public int rob(TreeNode root) {
        generate(root);
        return dp[i-1];
    }
    public int generate(TreeNode root){
        if(root==null){
            return 0;
        }
        int heightL = generate(root.left);
        int heightR = generate(root.right);
        if(heightL==0&&heightR==0){
            dp[i] = (i==0?0:dp[i-1]) + root.val;
        }else if(heightL<2&&heightR<2){
            dp[i] = Math.max(root.val,dp[i-1]);
        }else if(heightR==1){
            dp[i] = Math.max(dp[i-1],dp[i-3]+root.val);
        }else{
            dp[i] = Math.max(dp[i-1],dp[i-2]+root.val);
        }
        i++;
        return Math.max(heightL,heightR)+1;
    }
}

class Solution17 {
    public int combinationSum4(int[] nums, int target) {
        int len = nums.length;
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i: nums){
            for(int j=i;j<=target;j++){
                dp[j] += dp[j-i];
            }
        }

        return dp[target];
    }
}
class Solution18 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length())    return false;
        int len1 = s1.length(),len2 = s2.length();
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        for(int i=1;i<=len1;i++){
            dp[i][0] = s1.charAt(i-1)==s3.charAt(i-1)&&dp[i-1][0];
        }
        for(int i=1;i<=len2;i++){
            dp[0][i] = s2.charAt(i-1)==s3.charAt(i-1)&&dp[0][i-1];
        }
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if((s1.charAt(i-1)==s3.charAt(i+j-1)&&dp[i-1][j])
                        ||(s2.charAt(j-1)==s3.charAt(i+j-1)&&dp[i][j-1])){
                    dp[i][j] = true;
                }
            }
        }
        Stack<int[]> stack = new Stack<>();
        stack.isEmpty();
        return dp[len1][len2];
    }
}

class Solution19 {
    public int trap(int[] height) {
        int len = height.length;
        int left = 0,right = len-1;
        int maxIndex = -1,maxVal = -1;
        for(int i=0;i<len;i++){
            if(height[i]>maxVal){
                maxIndex = i;
                maxVal = height[i];
            }
        }
        int res = 0;
        while(left<=maxIndex&&right>=maxIndex){
            int t = left;
            while(t<maxIndex&&height[left]>height[t+1]){
                res += height[left]-height[t+1];
                t++;
            }
            left = t;
            t = right;
            while(t>maxIndex&&height[right]>height[t-1]){
                res += height[right]-height[t-1];
                t--;
            }
            right = t;
        }
        return res;
    }
}

class Solution20 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int[] postRange = {0,postorder.length-1};
        int[] inRange = {0,inorder.length-1};
        return buildTree(inorder,postorder,postRange,inRange);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder,int[] postRange,int[] inRange){
        if(postRange[0]>postRange[1]||inRange[0]>inRange[1]){
            return null;
        }
        int rootVal = postorder[postRange[1]];
        int index = findRootIndex(rootVal,inorder,inRange);
        int leftSize = index-inRange[0];
        int rightSize = inRange[1] - index;
        int[] postRangeL = {postRange[0],postRange[0]+leftSize-1};
        int[] postRangeR = {postRange[0]+leftSize,postRange[1]-1};
        int[] inRangeL = {inRange[0],index-1};
        int[] inRangeR = {index+1,inRange[1]};
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inorder,postorder,postRangeL,inRangeL);
        root.right = buildTree(inorder,postorder,postRangeR,inRangeR);
        return root;
    }

    public int findRootIndex(int rootVal, int[] inorder,int[] inRange){
        for(int i=inRange[0];i<=inRange[1];i++){
            if(inorder[i]==rootVal){
                return i;
            }
        }
        return -1;
    }
}

class Solution21 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return  constructMaximumBinaryTree(nums,0,nums.length-1);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums,int start,int end) {
        if(start>end) return null;
        int index = maxValeIndex(nums,start,end);
        TreeNode root = new TreeNode(nums[index]);
        root.left = constructMaximumBinaryTree(nums,start,index-1);
        root.right = constructMaximumBinaryTree(nums,index+1,end);
        return root;
    }

    public int maxValeIndex(int[] nums,int start,int end){
        int index = 0;
        int max = 0;
        for(int i=start;i<=end;i++){
            if(nums[i]>max){
                max = nums[i];
                index = i;
            }
        }
        Integer i = null;

        return index;
    }
}

class Solution22 {
    TreeNode res = null;
    Map<Integer,Integer> map = new HashMap<>();
    boolean find = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ansestorForP(root,p);
        ansestorForQ(root,q);
        return res;
    }

    public boolean ansestorForP(TreeNode root, TreeNode p){
        if(root==null) return false;
        boolean l = ansestorForP(root.left,p);
        boolean r = ansestorForP(root.right,p);
        if(root.val == p.val || l || r){
            map.put(root.val,1);
            return true;
        }else{
            return false;
        }
    }

    public boolean ansestorForQ(TreeNode root, TreeNode q){
        if(root==null) return false;
        boolean l = ansestorForQ(root.left,q);
        boolean r = ansestorForQ(root.right,q);
        if(root.val == q.val || l || r){
            if(map.containsKey(root.val) && find == false){
                res = root;
                find = true;
            }
            return true;
        }else{
            return false;
        }
    }
}

class Solution23 {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    public List<String> restoreIpAddresses(String s) {
        if(s.length()>12)   return res;
        dfs(s,0,4);
        return res;
    }
    public void dfs(String s,int start,int k){
        if(k==0){
            if(start>=s.length()){
                StringBuilder sb3 = new StringBuilder(sb);
                sb3.deleteCharAt(sb3.length()-1);
                res.add(new String(sb3));
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int num = 0;
        for(int i=start;i<start+3&&i<s.length();i++){
            sb2.append(s.charAt(i));
            num = num*10 + s.charAt(i)-'0';
            if(num>255) break;
            sb.append(sb2+".");
            dfs(s,i+1,k-1);
            sb.delete(sb.length()-sb2.length()-1,sb.length());
            if(s.charAt(start)=='0'){
                break;
            }
        }

    }
}

class Solution24 {
    List<String> list = new ArrayList<>();
    boolean[] used;
    public List<String> findItinerary(List<List<String>> tickets) {
        used = new boolean[tickets.size()];
        dfs(tickets,"JFK",tickets.size());
        return list;
    }

    public boolean dfs(List<List<String>> tickets,String from,int k){
        if(k==0){
            return true;
        }
        Map<String,Integer> froms = findTicket(tickets, from);
        Set<Map.Entry<String,Integer>> set = froms.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
        if(!froms.isEmpty()){

            while(iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                list.add(from);
                if (k == 1) list.add(entry.getKey());
                used[entry.getValue()] = true;
                if(dfs(tickets, entry.getKey(), k - 1)){
                    return true;
                }
                used[entry.getValue()] = false;
                if (k == 1) list.remove(list.size() - 1);
                list.remove(list.size() - 1);
            }
        }
        return false;
    }

    public Map<String,Integer> findTicket(List<List<String>> tickets,String from){
        Map<String,Integer> froms = new TreeMap<>();
        for(int i=0;i<used.length;i++){
            if(!used[i]&&tickets.get(i).get(0).equals(from)){
                froms.put(tickets.get(i).get(1),i);
            }
        }
        return froms;
    }
}

class Solution25 {
    public void solveSudoku(char[][] board) {
        dfs(board,0,0);
    }
    public boolean dfs(char[][] board,int row,int col){
        if(row==9&&col==0){
            return true;
        }
        if(board[row][col]!='.'){
            return dfs(board,row+(col+1)/9,(col+1)%9);
        }else{
            for(int num: getValidNums(row,col,board)){
                board[row][col] = (char)(num+'0');
                if(dfs(board,row+(col+1)/9,(col+1)%9)){
                    return true;
                }
                board[row][col] = '.';
            }
        }
        return false;
    }

    public boolean checkRow(int num,int row,char[][] board){
        for(char c: board[row]){
            if(c-'0'==num)  return true;
        }
        return false;
    }
    public boolean checkCol(int num,int col,char[][] board){
        for(int i=0;i<9;i++){
            if(board[i][col]-'0'==num)  return true;
        }
        return false;
    }
    public boolean checkBlock(int num,int row,int col,char[][] board){
        int blockNum =  + col/3;
        for(int i=row/3*3;i<row/3*3 +3;i++){
            for(int j = col/3*3;j<col/3*3 +3;j++){
                if(board[i][j]-'0'==num)  return true;
            }
        }
        return false;
    }
    public List<Integer> getValidNums(int row,int col,char[][] board){
        List<Integer> validNums = new ArrayList<>();
        for(int i=1;i<=9;i++){
            if(!checkRow(i,row,board)
                    &&!checkCol(i,col,board)
                    &&!checkBlock(i,row,col,board)){
                validNums.add(i);
            }
        }
        return validNums;
    }
}

class Solution26 {
    public int maxProduct(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            List<Integer> list = new ArrayList<>();
            while(i<nums.length&&nums[i]!=0){
                list.add(nums[i++]);
            }
            if(list.size()>0){
                lists.add(list);
            }
        }
        int res = Integer.MIN_VALUE;
        for(List<Integer> list: lists){
            int max = 1;
            int pos1 = max;
            for(int i=0;i<list.size();i++){
                max *= list.get(i);
                if(max>0)   pos1 = max;
            }
            int pos2 = 1;
            max = 1;
            for(int i=list.size()-1;i>=0;i--){
                max *= list.get(i);
                if(max>0)   pos2 = max;
            }
            max = Math.max(pos1,pos2);
            res = Math.max(max,res);
        }

        if(lists.size()==0) return 0;
        return lists.size()>1?(res<0?0:res):res;
    }
}

class Solution27 {
    public boolean isUgly(int n) {
        boolean[] dp = new boolean[n+1];
        dp[1] = true;
        int[] ugly = {2,3,5};
        for(int i=0;i<3;i++){
            for(int j=2;j<=n;j++){
                dp[j] = dp[j] || (j%ugly[i]==0&&dp[j/ugly[i]]);
            }
        }
        return dp[n];
    }
}

class Solution28 {
    public int[][] reconstructQueue(int[][] people) {
        //思路：从高到低，按照前面人的个数，在以排好的队列中插入
        List<int[]> queue = new ArrayList<>();
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0]==o2[0]) return o1[1]-o2[1];
            return o2[0]-o1[0];
        });
        for(int[] arr: people){
            queue.add(arr[1],arr);
        }
        int[][] res = new int[people.length][2];
        for(int i=0;i<res.length;i++){
            res[i] = queue.get(i);
        }
        return res;
    }
}

class Solution29 {
    public List<Integer> partitionLabels(String s) {
        int[][] map = new int[26][2];
        for(int i=0;i<26;i++){
            map[i][0] = s.length();
            map[i][1] = -1;
        }
        for(int i=0;i<s.length();i++){
            if(i<map[s.charAt(i)-'a'][0]){
                map[s.charAt(i)-'a'][0] = i;
            }else if(i>map[s.charAt(i)-'a'][1]){
                map[s.charAt(i)-'a'][1] = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        int sum = 0;
        for(int i=1;i<s.length();i++){
            if(map[s.charAt(i)-'a'][0]>map[s.charAt(i-1)-'a'][1]){
                res.add(map[s.charAt(i-1)-'a'][1]-map[s.charAt(i-1)-'a'][0]+1);
                sum += map[s.charAt(i-1)-'a'][1]-map[s.charAt(i-1)-'a'][0]+1;
            }else{
                map[s.charAt(i)-'a'][0] = map[s.charAt(i-1)-'a'][0];
                map[s.charAt(i)-'a'][1] = Math.max(map[s.charAt(i-1)-'a'][1],map[s.charAt(i)-'a'][1]);
            }
        }
        res.add(s.length()-sum);
        return res;
    }
}

class Solution30 {
    public int maxProfit(int[] prices, int fee) {
        //思路：还是按照不加手续费的策略寻找山峰和山谷
        //不过多一个上笔的买入价格和利润
        //当前交易卖出的利润等于两笔分开卖出和按一笔卖出的更大值
        //更新上笔的买入价格和利润
        int lastBuy = Integer.MAX_VALUE;
        int lastProfit = 0;
        int buy = 0;
        int profit = 0;
        int i=0;
        int buyNotSell = Integer.MAX_VALUE;
        while(i<prices.length-1){
            while(i<prices.length-1&&prices[i]>=prices[i+1]){
                i++;
            }
            buy = Math.min(buyNotSell,prices[i]);
            while(i<prices.length-1&&prices[i]<=prices[i+1]){
                i++;
            }
            if(buy==prices[i])    break;
            if(prices[i]-buy-fee<0){
                buyNotSell = buy;
                continue;
            }
            if(lastProfit+prices[i]-buy>prices[i]-lastBuy){
                lastProfit = prices[i]-buy-fee;
                lastBuy = buy;
                profit += lastProfit;
            }else{
                profit = profit-lastProfit+prices[i]-lastBuy-fee;
                lastProfit = prices[i]-lastBuy-fee;
            }
        }
        return profit;
    }
}

