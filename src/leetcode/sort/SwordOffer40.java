package leetcode.sort;

public class SwordOffer40 {
    public int[] getLeastNumbers(int[] arr, int k) {
        frontK(arr, 0, arr.length, k - 1);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public void frontK(int[] arr, int left, int right, int index) {
        if (left >= right)  return;
        int pivot = arr[left];
        int i = left;
        for (int j = left + 1; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i, left);
        if (i < index) {
            frontK(arr, i + 1, right, index);
        }else if (i > index) {
            frontK(arr, left, i, index);
        }else {
            //必须确切的找到第k大的元素
            //假如找到比第k大的元素更大的元素就返回，那么在下标k - 1之前就可能包含比第k大的元素更大的元素
        }
    }

    public void swap(int[] arr, int first, int second) {
        int t = arr[first];
        arr[first] = arr[second];
        arr[second] = t;
    }
}
