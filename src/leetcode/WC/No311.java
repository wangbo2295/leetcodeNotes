package leetcode.WC;

import javax.management.QueryEval;
import java.util.*;

public class No311 {
    public int smallestEvenMultiple(int n) {
        return (n & 1) == 0 ? n : 2 * n;
    }

    public int longestContinuousSubstring(String s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) - s.charAt(i - 1) == 1) {
                ++cnt;
            } else {
                cnt = 1;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int layer = 1;
        for (; !queue.isEmpty(); layer++) {
            int size = queue.size();
            List<TreeNode> cur = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode fa = queue.poll();
                if (fa.left == null) break;
                cur.add(fa.left);
                cur.add(fa.right);
                queue.offer(fa.left);
                queue.offer(fa.right);
            }
            for (int i = 0, j = cur.size() - 1; (layer & 1) == 1 && i < j; i++, j++) {
                TreeNode nodei = cur.get(i);
                TreeNode nodej = cur.get(j);
                int t = nodei.val;
                nodei.val = nodej.val;
                nodej.val = t;
            }
        }
        return root;
    }

    public int[] sumPrefixScores(String[] words) {
        int n = words.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) addWord(words[i]);
        for (int i = 0; i < n; i++) {
            ans[i] = get(words[i]);
        }
        return ans;
    }

    Trie root = new Trie();

    private void addWord(String word) {
        Trie cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new Trie();
            }
            cur.children[c - 'a'].cnt++;
            cur = cur.children[c - 'a'];
        }
        cur.end = true;
    }

    private int get(String word) {
        Trie cur = root;
        int res = 0;
        for (char c : word.toCharArray()) {
            res += cur.children[c - 'a'].cnt;
            cur = cur.children[c - 'a'];
        }
        return res;
    }

    class Trie {
        Trie[] children;
        boolean end;
        int cnt;
        public Trie() {children = new Trie[26]; end = false;}
    }
}
