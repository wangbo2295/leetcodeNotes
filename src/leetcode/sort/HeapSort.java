package leetcode.sort;

/**
 * 真正的堆排序
 * 自底向上建堆，原地修改数组
 * 采用大顶堆
 * 本类采用private static 修饰内部方法，private static修饰的方法只能被该类中的static方法调用，达到封装的目的
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] nums = {1,4,6,8,3,5,2,6,9,12,43,5,7,6,56,79,10,78};
        HeapSort.heapSort(nums);
        Sort.printResult(nums);
    }

    public static void heapSort(int[] nums){
        create(nums);
        sort(nums);
    }

    /**
     * 对输入数组建堆
     * 从末尾节点的父节点开始，自顶向上的进行下滤操作
     * @param nums
     */
    private static void create(int[] nums){
        for (int i=nums.length-2 >> 1;i>=0;i--){
            percolateDown(nums,i,nums.length-1);
        }
    }

    /**
     * 下滤操作
     * @param nums
     * @param hole
     */
    private static void percolateDown(int[] nums,int hole, int end){
        int t = nums[hole];
        int child;
        for( ; hole * 2 + 1 <= end; hole = child){
            child = hole * 2 + 1;
            if(child != end && nums[child+1] > nums[child]){
                child++;
            }
            if(t < nums[child]){
                nums[hole] = nums[child];
            }else{
                break;
            }
        }
        nums[hole] = t;
    }
    /**
     * 排序，依次将堆顶元素追加到数组末尾
     */
    private static void sort(int[] nums){
        for(int i=0;i<nums.length;i++){
            int t = nums[0];
            nums[0] = nums[nums.length-i-1];
            nums[nums.length-i-1] = t;
            percolateDown(nums,0, nums.length-2-i);
        }
    }
}
