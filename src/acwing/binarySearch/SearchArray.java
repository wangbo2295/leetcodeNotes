package acwing.binarySearch;

public class SearchArray {
    public boolean searchArray(int[][] array, int target) {
        int m = array.length, n = array[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (array[i][j] > target) {
                --j;
            } else if (array[i][j] < target) {
                ++i;
            } else {
                return true;
            }
        }
        return false;
    }
}
