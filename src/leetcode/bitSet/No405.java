package leetcode.bitSet;

public class No405 {
    public String toHex(int num) {
        int[] pattern = {0x78000000, 0x0f000000, 0x00f00000, 0x000f0000, 0x0000f000, 0x00000f00, 0x000000f0, 0x0000000f};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char s;
            if (i == 0) {
                s = toH((num >> 1 & pattern[i]) >> 27);
            } else {
                s = toH((num & pattern[i]) >> 4 * (7 - i));
            }
            if (sb.length() == 0 && s == '0')  continue;
            sb.append(s);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public char toH(int num) {
        if (num < 10)   return (char) (num + '0');
        return (char) (num - 10 + 'a');
    }
}
