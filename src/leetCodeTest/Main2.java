package leetCodeTest;

import java.util.*;

public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();
        while (scanner.hasNext()) {
            String direct = scanner.nextLine();
            String fileName = direct.substring(direct.lastIndexOf('\\') + 1, direct.lastIndexOf(' '));
            String line = direct.substring(direct.lastIndexOf(' ') + 1);
            if (map.containsKey(fileName + line)) {
                String fn = map.get(direct);
                int index = fn.lastIndexOf(' ');
                int num = Integer.parseInt(fn.substring(index)) + 1;
                fn = fn.substring(0, index + 1) + num;
                map.put(direct, fn);
            }else {
                String limit = fileName;
                if (fileName.length() > 16) {
                    limit = fileName.substring(fileName.length() - 16);
                }
                map.put(fileName + " " + line, limit + " " + line + " 1");
            }
        }
        Set<Map.Entry<String, String>> set = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
