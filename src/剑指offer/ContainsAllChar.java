package 剑指offer;

public class ContainsAllChar {
    public String minWindow(String s, String t) {
        int[] hashs = new int[128];
        int[] hasht = new int[128];
        int[] res = new int[2];
        int minlen;
        for (char c : t.toCharArray())  hasht[c]++;
        int i = 0, j = 0;
        for (char c : t.toCharArray()) {
            while (hashs[c] < hasht[c]) {
                if (j == s.length())    return "";
                hashs[s.charAt(j++)]++;
            }
            while (hashs[s.charAt(i)] > hasht[s.charAt(i)]) {
                hashs[s.charAt(i++)]--;
            }
        }
        minlen = j - i;
        res[0] = i;
        res[1] = j;
        System.out.println(i + "-" + j + "-");
        while (j < s.length()) {
            hashs[s.charAt(j++)]++;
            while (hashs[s.charAt(i)] > hasht[s.charAt(i)]) hashs[s.charAt(i++)]--;
            if (j - i < minlen) {
                System.out.println(i + "-" + j + "-");
                minlen = j - i;
                res[0] = i;
                res[1] = j;
            }
        }
        return s.substring(i, j);
    }
}
