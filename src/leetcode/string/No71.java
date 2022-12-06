package leetcode.string;


/**
 * 这题用栈更直观
 */
public class No71 {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        int n = paths.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (paths[i].equals(".") || paths[i].length() < 1)   continue;
            if (paths[i].equals(".."))  {
                int index = sb.lastIndexOf("/");
                if (index >= 0)
                    sb.delete(index, sb.length());
            }
            else sb.append("/").append(paths[i]);
        }
        return sb.length() > 0 ? sb.toString() : "/";
    }
}
