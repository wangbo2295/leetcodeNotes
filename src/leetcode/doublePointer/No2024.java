package leetcode.doublePointer;

public class No2024 {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        int[] cnt = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cnt[i] = cnt[i - 1];
            if (answerKey.charAt(i - 1) == 'T') ++cnt[i];
        }
        int ans = 0;
        for (int i = 0, j = 1; j <= n; j++) {
            while (cnt[j] - cnt[i] + k < j - i + 1) i++;
            ans = Math.max(ans, j - i + 1);
        }
        for (int i = 0, j = 1; j <= n; j++) {
            while (cnt[j] - cnt[i] > k) i++;
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
