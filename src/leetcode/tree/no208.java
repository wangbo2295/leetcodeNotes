package leetcode.tree;

/**
 * 实现前缀树
 */
public class no208 {

}

/**
 * 实现思路：
 * 由于只考虑小写字母，所以每个节点有26个子节点
 * 26个子节点对应26个小写字母。
 * 插入：从第一个字母开始，将字母映射成数组下标，在根节点的子节点数组中创建子节点对象，表示加入该字母。然后将指
 * 针指向该子节点，依次执行直到结尾，然后将结尾节点的end设为true.
 * 查找/前缀：从根节点开始一次迭代如果某个字母不存在直接返回false，直到该前缀遍历完返回true。查找完整单词需要再判断一下end是否为true。
 * 查找和前缀可以整合成一个方法，可以写一个函数查找前缀最后一个字母对应的trie，如果存在则前缀存在，如果end为true则单词存在。
 */
class Trie {

    Trie[] children;
    boolean isEnd;

    public Trie() {
        children = new Trie[26];
    }

    public void insert(String word) {
        Trie tmp = this;
        for (char c: word.toCharArray()) {
            if (tmp.children[c - 'a'] == null) {
                tmp.children[c - 'a'] = new Trie();
            }
            tmp = tmp.children[c - 'a'];
        }
        tmp.isEnd = true;
    }

    public boolean search(String word) {
        Trie prefix = searchPrefix(word);
        return prefix != null && prefix.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) == null;
    }

    public Trie searchPrefix(String prefix) {
        Trie tmp = this;
        for (char c : prefix.toCharArray()) {
            if (tmp.children[c - 'a'] == null) return null;
            tmp = tmp.children[c - 'a'];
        }
        return tmp;
    }
}