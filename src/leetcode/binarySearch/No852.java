package leetcode.binarySearch;

public class No852 {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int i = 1;
        while (i <= n - 1 && arr[i] > arr[i - 1]) {
            i++;
        }
        return i - 1;
    }
}
