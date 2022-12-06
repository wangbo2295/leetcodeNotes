package leetcode.enums;

import java.util.HashSet;
import java.util.Set;

public class No2018 {
    public boolean placeWordInCrossword(char[][] board, String word) {
        Set<String> set = new HashSet<>();
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '#') {
                    if (sb.length() > 0) {
                        set.add(new String(sb));
                    }
                    sb.delete(0, sb.length());
                } else {
                    sb.append(board[i][j]);
                }
            }
            if (sb.length() > 0)    set.add(new String(sb));
            sb.setLength(0);
        }
        for (int j = 0; j < n; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                if (board[i][j] == '#') {
                    if (sb.length() > 0) {
                        set.add(new String(sb));
                    }
                    sb.delete(0, sb.length());
                } else {
                    sb.append(board[i][j]);
                }
            }
            if (sb.length() > 0)  set.add(new String(sb));
            sb.setLength(0);
        }
        for (String s : set) {
            if (match(s, word) || match(new StringBuilder(s).reverse().toString(), word))     return true;
        }
        return false;
    }

    boolean match(String s, String word) {
        if (s.length() != word.length())   {
            return false;
        }
        // System.out.println(s + "," + word);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') continue;
            if (s.charAt(i) != word.charAt(i))  return false;
        }
        return true;
    }

    public static void main(String[] args) {
        No2018 no2018 = new No2018();
        char[][] board = {{' '},{'#'},{'o'},{' '},{'t'},{'m'},{'o'},{' '},{'#'},{' '}};
        no2018.placeWordInCrossword(board, "octmor");
    }
}
