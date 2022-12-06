package leetcode.simulate;

public class No6 {
    public String convert(String s, int numRows) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int start = 0, delta = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            int t = start;
            while (t < n) {
                sb.append(s.charAt(t));
                int mid = t + delta - 2 * (start % delta);
                if (start != 0 && start != numRows - 1 && mid < n) {
                    sb.append(s.charAt(mid));
                }
                t += delta;
            }
            start++;
        }
        return sb.toString();
    }
}
