package datastructures.heap;

import java.util.Comparator;

/**
 * 手动实现优先队列（堆）
 */
public class MyHeap<T> {
    int size;
    public static final int DEFAULT_CAPACITY = 10;
    Object[] elements;
    Comparator<? super T> comparator;

    public MyHeap(Comparator<? super T> comparator) {
        this(0, new Object[DEFAULT_CAPACITY], comparator);
    }

    public MyHeap(T[] nums, Comparator<? super T> comparator) {
        this(nums.length, nums, comparator);
        build();
    }

    public MyHeap(int size, Object[] elements, Comparator<? super T> comparator) {
        this.size = size;
        this.elements = elements;
        this.comparator = comparator;
    }

    private void build() {
        for (int i = (size - 2 >> 1); i >= 0; i--) {
            percolateDown(i);
        }
    }

    public void offer(T e) {
        if (size == elements.length) {
            ensureCapacity(size * 2);
        }
        elements[size] = e;
        percolateUp(size++);
    }

    public T poll() {
        T top = (T) elements[0];
        elements[0] = elements[--size];
        percolateDown(0);
        return top;
    }

    public T top() {return (T) elements[0];}

    public boolean isEmpty() {return size == 0;}

    public int size()  {return this.size;}

    private void percolateDown(int idx) {
        T e = (T) elements[idx];
        int child;
        for (; (idx << 1) + 1 < size; idx = child) {
            child = (idx << 1) + 1;
            if (child != size - 1 && comparator.compare((T) elements[child + 1], (T) elements[child]) < 0) {
                child++;
            }
            if (comparator.compare(e, (T) elements[child]) < 0)    break;
            elements[idx] = elements[child];
        }
        elements[idx] = e;
    }

    private void percolateUp(int idx) {
        T t = (T) elements[idx];
        for (; (idx - 1 >> 1) >= 0 && comparator.compare((T)elements[idx], (T) elements[idx - 1 >> 1]) < 0; idx = idx - 1 >> 1) {
            elements[idx - 1 >> 1] = elements[idx];
        }
        elements[idx] = t;
    }

    private void ensureCapacity(int newSize) {
        Object[] newContainer = new Object[newSize];
        System.arraycopy(elements, 0, newContainer, 0, size);
        elements = newContainer;
    }
}