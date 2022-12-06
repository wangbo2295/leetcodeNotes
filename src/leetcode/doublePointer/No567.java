package leetcode.doublePointer;

public class No567 {
    public boolean checkInclusion(String s1, String s2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        int diff = 0;
        for (char c : s1.toCharArray()) cnt1[c - 'a']++;
        for (int i = 0; i < 26; i++) if (cnt1[i] != cnt2[i]) ++diff;
        for (int i = 0, j = 0; j < s2.length(); j++) {
            int c = s2.charAt(j) - 'a';
            diff += check(cnt2[c], ++cnt2[c], cnt1[c]);
            if (j >= s1.length() - 1) {
                if (diff == 0)  return true;
                int v = s2.charAt(i++) - 'a';
                diff += check(cnt2[v], --cnt2[v], cnt1[v]);
            }
        }
        return false;
    }

    public int check(int pre, int cur, int compare) {
        if (cur == compare) return -1;
        if (pre == compare) return 1;
        return 0;
    }

    public static void main(String[] args) {
        No567 no567 = new No567();
        no567.checkInclusion("abcdxabcde", "abcdeabcdx");
    }
}
