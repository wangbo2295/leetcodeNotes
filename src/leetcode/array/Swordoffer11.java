package leetcode.array;

public class Swordoffer11 {
    /**
     * 解法一：遍历数组，找到第一个比前面小的元素
     * 时间复杂度：O（n）
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }

    /**
     * 解法二：二分查找
     * @param numbers
     * @return
     */
    public int minArray2(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (numbers[mid] < numbers[right]) {
                right = mid - 1;
            } else if (numbers[mid] > numbers[right]){
                left = mid + 1;
            }else {
                right--;
            }
        }
        return numbers[left];
    }
}
