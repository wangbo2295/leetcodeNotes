package leetcode.hash;

import java.util.*;

public class No146 {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.remove(1);
    }
}

/**
 * get和put均要求O（1）
 * 考虑用hashMap实现
 * 需要控制map的大小，当超过capacity时，要删除最久未使用
 * 维护一个最近使用链表，当get或put某元素时，将其移到表头，当需要删除最久未使用时，将表尾元素删除，更新表尾元素
 */
class LRUCache {
    Map<Integer, Node> map = new HashMap<>();
    Node last;
    Node first;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null)   return -1;  //没找到直接返回-1
        if (node == first)  return node.val;    //如果是头节点，无需更新链表，直接返回值
        if (node == last)   last = last.pre;    //如果是尾节点，先将倒数第二个置为尾节点，再将原尾节点移到表头
        unlink(node);   //解绑
        insertAtFirst(node);    //在头部插入节点并更新头节点
        return node.val;
    }
    //在头部插入节点
    public void insertAtFirst(Node node) {
        if (first != null) {
            node.next = first;
            first.pre = node;
        }
        first = node;
    }
    //解绑
    public void unlink(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        node.pre = null;
        node.next = null;
        pre.next = next;
        if (next != null) next.pre = pre;
    }

    public void put(int key, int value) {
        //如果已经存在，则更新value
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            map.put(key, node);
            get(key);   //这里调一个get是为了将该节点移到表头，纯偷懒。。。
            return;
        }
        //如果容量满了，则先删除尾节点
        if (map.size() >= capacity) {
            map.remove(last.key);
            last = last.pre;
        }
        //要插入的节点
        Node node = new Node(key, value);
        if (map.size() == 0)    last = node;    //第一次put时，需要将尾节点初始化
        insertAtFirst(node);
        map.put(key, node);
    }

    class Node{
        int key;
        int val;
        Node pre;
        Node next;
        public Node(int key, int val){this.key = key;this.val = val;}
    }
}