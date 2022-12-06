package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

public class No929 {
    public int numUniqueEmails(String[] emails) {
        Map<String, Integer> map = new HashMap<>();
        for (String email : emails) {
            StringBuilder sb = new StringBuilder();
            String substring = email.substring(email.indexOf('@') + 1);
            sb.append(substring + "+");
            for (char c : email.toCharArray()) {
                if (c == '+')   break;
                if (c == '.')   continue;
                sb.append(c);
            }
            map.putIfAbsent(sb.toString(), 1);
        }
        return map.size();
    }
}
