package leetcode.binarySearch;

public class No744 {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = - 1, right = letters.length;
        while (left + 1 < right) {
            int mid = left + (right - left >> 1);
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right == letters.length ? letters[0] : letters[right];
    }
}
