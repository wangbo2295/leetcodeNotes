package leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class No140单词拆分II {

    public static void main(String[] args) {
        No140单词拆分II no140 = new No140单词拆分II();
        String s = "catsanddog";
        List<String> strings = Arrays.asList("cat", "cats", "and", "sand", "dog");
        no140.wordBreak(s,strings);
    }

    List<String> res = new ArrayList<>();
    List<String> phrase = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        dfs(s,0,wordDict);
        return res;
    }

    public void dfs(String s,int start, List<String> wordDict){
        if(start==s.length()){
            res.add(String.join(" ",phrase));
            return;
        }
        for(String s1: wordDict){
            if(s.startsWith(s1,start)){
                phrase.add(s.substring(start,start+s1.length()));
                dfs(s,start+s1.length(),wordDict);
                phrase.remove(phrase.size()-1);
            }
        }
    }
}