package datastructures.stack;

import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack {

    Stack<Integer> stack;
    PriorityQueue<Integer> priorityQueue;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack();
        priorityQueue = new PriorityQueue<>();
    }

    public void push(int x) {
        stack.push(x);
        priorityQueue.offer(x);
    }

    public void pop() {
        if (stack.peek().equals(priorityQueue.peek())) {
            priorityQueue.poll();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return priorityQueue.peek();
    }
}

class MinStack2 {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack2() {
        stack = new Stack();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.empty()) {
            minStack.push(x);
        }else {
            minStack.push(Math.min(x, minStack.peek()));
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

