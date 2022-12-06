package leetcode.deepFirstSearch;

public class No942 {
    public int[] diStringMatch(String s) {
        int i = 0;
        int[] res = new int [s.length() + 1];
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == 'I') {
                res[i] = i++;
            }
            int cnt = 0;
            while (i < s.length() && s.charAt(i) == 'D') {
                i++; cnt++;
            }
            int start = i - cnt;
//            System.out.println(start);
            for (int j = 0; j <= cnt; j++) {
                res[start + j] = i--;
            }
            i += cnt + 2;
        }
        return res;
    }

    public static void main(String[] args) {
        No942 no942 = new No942();
        no942.diStringMatch("DDI");
    }
}
