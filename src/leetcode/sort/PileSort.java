package leetcode.sort;


/**
 * 堆排序，盗版
 * 原理：构建一个大顶堆，然后依次将堆顶元素pop到结尾，就得到一个升序数组
 * 时间复杂度：构建大顶堆（O（NlogN))+pop（O（NlogN)） = O（NlogN)
 * **注意**   这种方法采用自顶向下的建堆方法，而且用了额外的数组空间，空间复杂度为O（N）
 * 真正的堆排序应该采用自底向上的建堆方法，并在原地修改数组完成排序
 * todo 完成真正的堆排序
 */
public class PileSort {

    private int[] pile;
    private int size;


    public static void main(String[] args) {
        PileSort pileSort = new PileSort();
        int[] nums = {1,4,6,8,3,5,2,6,9,12,43,5,7,6,56,79,10};
        pileSort.pileSort(nums);
        Sort.printResult(nums);
    }

    public void pileSort(int[] nums){
        pile = new int[nums.length+1];
        pile[0] = Integer.MAX_VALUE;
        generatePile(nums);
        for(int i=nums.length-1;i>=0;i--){
            nums[i] = pop();
        }
    }

    void generatePile(int[] nums){
        for(int i: nums){
            push(i);
        }
    }

    public void push(int val){
        size++;
        int index = size;
        while(val>pile[index>>1]){
            pile[index] = pile[index>>1];
            index >>= 1;
        }
        pile[index] = val;
    }
    public int pop(){
        int res = pile[1];
        pile[1] = pile[size--];
        percolateDown(1);
        return res;
    }

    /**
     * 下滤比上滤复杂
     * 1、上滤不用考虑边界，只需将0节点设为要插入的节点，最终最多上滤到根节点（1）
     * 2、下滤需要判断是否到达最后一层
     * 3、下滤需要同时判断两个子节点的值，以及子节点和下滤节点的值
     * @param hole
     */
    private void percolateDown(int hole){
        int child;  //保存要下滤的子节点，下一轮循环将空洞设为子节点
        int temp = pile[hole];  //保存下滤节点的值
        for( ; hole*2<=size; hole = child){
            child = hole*2;
            //先判断是否存在右子节点以及右子节点是否大于左子节点，如果child==size说明到最后一层了且没有右子节点
            if(child!=size&&pile[child+1]>pile[child]){
                child++;      //若是，则将子节点设为右子节点
            }
            //判断子节点是否大于下滤节点的值，若下滤节点的值以及大于子节点的值，下滤结束，break
            if(pile[child]>temp) {
                pile[hole] = pile[child];
            }else{
                break;
            }
        }
        //将下滤节点的值放在其应当的位置
        pile[hole] = temp;
    }


}
