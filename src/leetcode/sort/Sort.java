package leetcode.sort;

import java.util.StringJoiner;

public class Sort {

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] arr = {2,3,2,5,4,2,9,8,0,1};
        sort.insertSort2(arr);
        printResult(arr);
        int[] nums = {1,4,6,8,3,5,2,6,9,12,43,5,7,6,56,79,10};
        sort.selectSort(nums);
        int[] nums2 = {1,4,6,8,3,5,2,6,9,12,43,5,7,6,56,79,10};
        sort.mergeSort(nums2);
        printResult(nums);
        printResult(nums2);
    }

    /**
     * 插入排序，写法一
     * 第一个元素为已排序状态，从第二个元素起，如果小于前面一个元素，就一直往前面找到大于前一个元素的位置插入
     * @param nums
     */
    public void insertSort(int[] nums){
        for(int i=1;i<nums.length;i++){
            if(nums[i]<nums[i-1]){
                int j = i;
                while(j>0&&nums[j]<nums[j-1]){
                    int t = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = t;
                    j--;
                }
            }
        }
    }
    /**
     * 插入排序，写法二
     * 写法一是从前往后依次交换，做了许多不必要的工作
     * 实际上只要先保存当前元素，依次将前面的往后移，直到小于当前元素，就插入（这才符合插入排序的思想，之前那种写法有点像冒泡）
     * @param nums
     */
    public void insertSort2(int[] nums){
        int j;  //用来保存插入的位置
        for(int i=1;i<nums.length;i++){
            int t = nums[i];    //要插入的元素
            for(j=i;j>0&&t<nums[j-1];j--){  //将前面的元素依次往后移，直到找到插入位置
                nums[j] = nums[j-1];
            }
            nums[j] = t;
        }
    }

    /**
     * 选择排序
     * @param nums
     */
    public void selectSort (int[] nums){
        for(int i=0;i<nums.length;i++){
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for(int j=i;j<nums.length;j++){
                if(nums[j]<min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            int t = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = t;
        }
    }

    /**
     * 归并排序，分治法
     * 归并排序用到的临时数组导致空间开销很大，需要在临时数组和原数组间拷贝来拷贝去，很麻烦
     * 可以通过在递归的过程中不断交换原数组和临时数组的角色来避免拷贝
     * 该排序的元素移动次数还是很大，而元素间的比较次数很少
     * 由于Java使用范型排序（comparator）的对象比较开销很大（因为不容易内嵌，要根据Comparator动态调度），所以Java通用排序就是采用的归并排序（TimSort）
     * 而在C++中，由于编译器有主动执行内嵌优化的能力，比较对象相对省时，因此采用的是移动次数更少的快速排序
     */
    public void mergeSort(int[] nums){
        mergeSort(nums,0,nums.length);
    }
    private void mergeSort(int[] nums,int left,int right){
        //区间缩小到只有一个元素，则为有序数组，返回
        if(left+1==right){
            return;
        }
        int mid = left + (right - left >>1);
        //采用左闭右开区间
        mergeSort(nums,left,mid);
        mergeSort(nums,mid,right);
        //分别排序完左右两个子集后，再合并两个子集
        int[] arr = new int[right-left];
        int index = 0, l = left, m = mid;   //这里要用临时变量表示下标，不能改变原来的几个点
        while(l<mid&&m<right){
            if(nums[l]<nums[m]){
                arr[index++] = nums[l++];
            }
            else{
                arr[index++] = nums[m++];
            }
        }
        //如果(l != mid)，说明右边的先用完，左边剩下的有序子集直接放在原数组结尾
        if (l != mid) {
            System.arraycopy(nums, l, nums, m - (mid - l), mid - l);
        }
        //无论左边先用完还是右边先用完，都将已排序的元素复制到原数组的前面
        System.arraycopy(arr,0,nums,left,index);
    }


    /**
     * 打印排序结果
     * @param nums
     */
    public static void printResult(int[] nums){
        StringJoiner stringJoiner = new StringJoiner(",","[","]");
        for(int i: nums){
            stringJoiner.add("" +i);
        }
        System.out.println(stringJoiner);
    }
}
