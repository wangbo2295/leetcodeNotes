package leetcode.WC;

import java.util.*;

public class No296 {

    public int minMaxGame(int[] nums) {
        if (nums.length == 1)   return nums[0];
        int negative = 1;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length - 1; i += 2) {
            int num = Math.min(negative * nums[i], negative * nums[i + 1]);
            queue.offer(negative * num);
            System.out.println(queue.peek());
            negative = -negative;
        }
        while (queue.size() > 1) {
            int first = queue.poll();
            int second = queue.poll();
            int num = Math.min(negative * first, negative * second);
            queue.offer(negative * num);
            negative = -negative;
        }
        return queue.poll();
    }

    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int min = nums[0], ans = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > min + k) {
                ans++;
                min = nums[i];
            }
        }
        return ans;
    }

    /**
     * 给你一个下标从 0开始的数组nums，它包含 n个 互不相同的正整数。
     * 请你对这个数组执行 m个操作，在第 i个操作中，你需要将数字operations[i][0] 替换成operations[i][1]。
     * 题目保证在第 i个操作中：
     * operations[i][0]在nums中存在。
     * operations[i][1]在nums中不存在。
     * 请你返回执行完所有操作后的数组。
     *
     * 关键：用map存储该值在原数组中的位置，而不是存储该值应该改变成什么值
     */
    public int[] arrayChange(int[] nums, int[][] operations) {
        int[] map = new int[1000001];
        Arrays.fill(map, -1);
        for (int i = 0; i < nums.length; i++) {
            map[nums[i]] = i;
        }
        for (int i = 0; i < operations.length; i++) {
            map[operations[i][1]] = map[operations[i][0]];
            map[operations[i][0]] = -1;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] >= 0) {
                nums[map[i]] = i;
            }
        }
        return nums;
    }

    public static void main(String[] args) {

    }
}

class TextEditor {

    int textPosition;
    StringBuilder text;

    public TextEditor() {
        textPosition = 0;
        text = new StringBuilder();
    }

    public void addText(String text) {
        this.text.insert(textPosition, text);
        textPosition += text.length();
    }

    public int deleteText(int k) {
        int start = Math.max(textPosition - k, 0);
        this.text.delete(start, textPosition);
        int len = textPosition - start;
        textPosition = start;
        return len;
    }

    public String cursorLeft(int k) {
        textPosition -= Math.min(textPosition, k);
        int len = Math.min(10, textPosition);
        return text.substring(textPosition - len, textPosition);
    }

    public String cursorRight(int k) {
        textPosition += Math.min(text.length() - textPosition, k);
        int len = Math.min(10, textPosition);
        return text.substring(textPosition - len, textPosition);
    }
}

/**
 * 使用双向链表实现编辑器
 * 链表实现的关键：由于所有操作都是在光标附近进行的，所以可以维护一个光标节点，插入、删除都是在光标左右进行
 * 插入、删除方法写在节点类内部，因为只用操作一个光标节点
 * 考虑到操作越界的问题，可以维护文本的开头和结尾节点，有没有一种技巧可以简化该操作？
 * 创建一个哨兵节点，并将链表组织成一个环形链表，在文本内移动时，向左边最多移动到哨兵节点（意味着左边没有字符了），向右边最多移动到哨兵节点的前一个节点（意味着这是最后一个左边有字符的节点了）
 * 因此，光标移动的方法写在实现类，因为要从文本整体把握光标位置
 * 至于返回前min(k, len)个字符的方法，可以用一个临时变量向前探寻边界，k或len满足一个就跳出循环，期间用StringBuilder追加字符，最后再反转字符串即可。
 */
class TextEditor2 {

    Node root, cur;

    public TextEditor2() {
        root = cur = new Node();
        root.pre = root;
        root.next = root;
    }

    public void addText(String text) {
        for (char c : text.toCharArray()) {
            cur = cur.insert(new Node(c));
        }
    }

    public int deleteText(int k) {
        int k0 = k;
        for (; k > 0 && cur != root; k--) {
            cur = cur.pre;
            cur.next.delete();
        }
        return k0 - k;
    }

    public String cursorLeft(int k) {
        for (; k > 0 && cur != root; k--) {
            cur = cur.pre;
        }
        return substring(10);
    }

    public String cursorRight(int k) {
        //注意：这里是cur.next != root，因为Node表示的是光标左边的字符，如果向右移动到root，相当于到文本开头了，不可行。
        for (; k > 0 && cur.next != root; k--) {
            cur = cur.next;
        }
        return substring(10);
    }

    private String substring(int k) {
        StringBuilder sb = new StringBuilder();
        //这种写法好，可以学习一下，不要老是在while外面定义临时变量
        for (Node t = cur; t != root && k > 0; k--) {
            sb.append(t.c);
            t = t.pre;
        }
        return sb.reverse().toString();
    }

    class Node {
        char c; //光标左边的字符
        Node pre;
        Node next;
        public Node() {this('|');}
        public Node(char c) {this.c = c;}

        public Node insert(Node node) {
            node.pre = this;
            node.next = this.next;
            node.pre.next = node;
            node.next.pre = node;
            return node;
        }

        public void delete() {
            this.pre.next = this.next;
            this.next.pre = this.pre;
        }
    }
}

/**
 * 对顶栈
 */
class TextEditor3 {

    Stack<Character> pre;
    Stack<Character> next;

    public TextEditor3() {
       pre = new Stack<>();
       next = new Stack<>();
    }

    public void addText(String text) {
        for (char c : text.toCharArray()) {
            pre.push(c);
        }
    }

    public int deleteText(int k) {
        int k0 = k;
        for (; k > 0 && !pre.empty(); k--) {
            pre.pop();
        }
        return k0 - k;
    }

    public String cursorLeft(int k) {
        for (; k > 0 && !pre.empty(); k--) {
            next.push(pre.pop());
        }
        return leftK(10);
    }

    public String cursorRight(int k) {
        for (; k > 0 && !next.empty(); k--) {
            pre.push(next.pop());
        }
        return leftK(10);
    }

    public String leftK(int k) {
        StringBuilder sb = new StringBuilder();
        int k0 = k;
        for (; k > 0 && !pre.empty(); k--) {
            sb.append(pre.peek());
            next.push(pre.pop());
        }
        for (int i = 0; i < k0 - k; i++) {
            pre.push(next.pop());
        }
        return sb.reverse().toString();
    }
}
