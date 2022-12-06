package leetcode.sort;

/**
 * 快速排序
 * 选取pivot： 左、中、右先进行排序，然后将中间元素放到最后
 * i指向大堆第一个元素，j指向小堆第一个元素，中间为未分区元素
 * i一直向右直到大堆第一个元素，j一直向左直到小堆最后一个元素，如果中间还有未排序元素，交换i、j的值
 * 重复上个步骤直到i>j，这时完成分区，将pivot和i交换
 * 遇到相等元素：i、j都停止 ，虽然会在相等元素间交换，但可以使相等元素在两个分区间均匀分布
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = {1,4,6,8,3,5,2,6,9,12,43,5,7,6,56,79,10};
        quickSort.quickSort(nums);
        Sort.printResult(nums);
    }

    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length-1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if(left == right)   return;
        int pivot = median3(nums, left, right); //将左中右三个元素的相对位置排序，并将pivot放在倒数第二个位置
        if(left + 1 == right)   return;
        int i = left, j = right - 1;
        while(true){
            while(nums[++i] < pivot);//先自增是因为left以及比较过了，另外可以避免有相同元素时进入死循环
            while(nums[--j] > pivot);
            if(i < j)   swap(nums, i, j);
            else break;
        }
        swap(nums, i, right-1);//将大堆第一个元素与pivot交换
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    private int median3(int[] nums, int left, int right) {
        int mid = left + (right - left >> 1);
        //先将最小的放到左边，再看另外两个
        if(nums[left] > nums[mid]) {
            swap(nums, left, mid);
        }
        if(nums[left] > nums[right]) {
            swap(nums, left, right);
        }
        if(nums[mid] > nums[right]) {
            swap(nums, mid, right);
        }

        //左中右经过排序后，将pivot放在倒数第二个，因为左右都已经在正确的位置了（pivot的左右）
        swap(nums, mid, right-1);
        return nums[right-1];
    }
    private void swap(int[] nums, int i ,int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
