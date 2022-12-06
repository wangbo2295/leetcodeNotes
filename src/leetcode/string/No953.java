package leetcode.string;

/**
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 */
public class No953 {
    /**
     * 自定义字典序
     * 用map保存每个字母的大小，在给定的order中，字符出现的顺序即为其大小，将字符的大小映射到大小为26的数组中
     * 从第二个单词开始跟前面一个单词依次比较字符大小
     * 如果前一个单词对应的字符比当前小，跳出循环，进行下一对单词的比较
     * 如果前一个单词对应的字符比当前大，返回false
     * 如果相等，则进行下一位的比较
     * 以前一个单词的长度为遍历次数，如果遍历完还没有返回false说明前一个单词小，继续下一对单词的比较
     * 如果前一个单词还没遍历完，当前单词就遍历完了，那前一个单词一定比当前单词大，返回false
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] orders = new int[26];
        for (int i = 0; i < order.toCharArray().length; i++) {
            orders[order.charAt(i) - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < words[i - 1].length(); j++) {
                if (j >= words[i].length()
                        ||orders[words[i - 1].charAt(j) - 'a'] > orders[words[i].charAt(j) - 'a']) {
                    return false;
                }
                if (orders[words[i - 1].charAt(j) - 'a'] < orders[words[i].charAt(j) - 'a']) break;
            }
        }
        return true;
    }
}
