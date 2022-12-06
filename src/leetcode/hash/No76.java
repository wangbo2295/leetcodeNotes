package leetcode.hash;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 */
public class No76 {
    /**
     * 思路：滑动窗口
     * 保持窗口内包含t，当左边界元素个数大于t中个数，右移并记录最小子串，右边界一直右移
     */
    public String minWindow(String s, String t) {
        //A-65, z-122 , len = 123 - 65 = 58
        int[] maps = new int[58], mapt = new int[58];
        //先统计t中各个字母个数
        for (char c : t.toCharArray()) {
            mapt[c - 'A']++;
        }
        //寻找第一个包含t的右边界
        int r;
        for (r = 0; r < s.length(); r++) {
            maps[s.charAt(r) - 'A']++;
            boolean fit = true;
            for (int i = 0; i < 58; i++) {
                if (maps[i] < mapt[i]) {
                    fit = false;
                    break;
                }
            }
            if (fit) break;
        }
        //如果到结尾还没找到，返回""
        if (r >= s.length())    return "";
        //找到左边界
        int l = 0;
        while (maps[s.charAt(l) - 'A'] > mapt[s.charAt(l) - 'A']) {
            maps[s.charAt(l) - 'A']--;
            l++;
        }
        //此时左边界为第一个在t中的字母对应的下标，右边界为使得该子串包含t的最小下标
        String minWindow = s.substring(l, r + 1);
        //使用滑动窗口
        for (++r; r < s.length(); r++) {
            maps[s.charAt(r) - 'A']++;
            //如果加入一个字母后左边界对应的字母超过t中的个数，那么就把左边界移动到下一个在t中的字母
            while (maps[s.charAt(l) - 'A'] > mapt[s.charAt(l) - 'A']) {
                maps[s.charAt(l) - 'A']--;
                l++;
            }
            //更新最小子串
            if (minWindow.length() > r - l + 1) {
                minWindow = s.substring(l, r + 1);
            }
        }
        return minWindow;
    }

    public static void main(String[] args) {
        No76 no76 = new No76();
        no76.minWindow("ADOBECODEBANC","ABC");
    }
}
