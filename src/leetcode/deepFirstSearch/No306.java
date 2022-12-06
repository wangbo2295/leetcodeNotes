package leetcode.deepFirstSearch;

public class No306 {
    public boolean isAdditiveNumber(String num) {
        for (int i = 0; i < num.length() - 2; i++) {
            for (int j = i + 1; j < num.length() - 1; j++) {
                for (int k = j + 1; k < num.length(); k++) {
                    int[] first = {0, i}, second = {i + 1, j}, third = {j + 1, k};
                    int max = Math.max(first[1] - first[0], second[1] - second[0]);
                    if (max + 1 < third[1] - third[0] || max > third[1] - third[0]) continue;
                    String sum = getSUm(num, first, second);
                    if (!sum.equals("NaN") && sum.equals(num.substring(third[0], third[1] + 1)) && isAdditiveNumber(num, second, third)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isAdditiveNumber(String num, int[] first, int[] second) {
        if (second[1] >= num.length() - 1) {
            return second[1] == num.length() - 1;
        }
        String sum = getSUm(num, first, second);
        if (sum.equals("NaN"))  return false;
        int start = second[1] + 1, end = second[1] + Math.max(first[1] - first[0], second[1] - second[0]) + 1;
        return (end < num.length() && sum.equals(num.substring(start, end + 1)) && isAdditiveNumber(num, second, new int[]{start, end}))
                || (end + 1 < num.length() && sum.equals(num.substring(start, end + 2)) && isAdditiveNumber(num, second, new int[]{start, end + 1}));
    }
    public String getSUm(String num, int[] first, int[] second) {
        if ((first[1] - first[0] > 0 && num.charAt(first[0]) == '0') || (second[1] - second[0] > 0 && num.charAt(second[0]) == '0'))   return "NaN";
        int next = 0, i_f = first[1], i_s = second[1];
        StringBuilder sb = new StringBuilder();
        while (i_f >= first[0] || i_s >= second[0] || next != 0) {
            int f = i_f >= first[0] ? num.charAt(i_f) - '0' : 0;
            int s = i_s >= second[0] ? num.charAt(i_s) - '0' : 0;
            int sum = f + s;
            sb.append((sum + next) % 10);
            next = (sum + next) / 10;
            i_f--;
            i_s--;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        No306 no306 = new No306();
        no306.isAdditiveNumber("199100199");
    }
}
