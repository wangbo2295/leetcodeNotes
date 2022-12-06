package leetcode.simulate;

public class No537 {
    public String complexNumberMultiply(String num1, String num2) {
        //这里是输入正则表达式， 不能直接+，要转义或加括号。    \\+     [+]
        String[] n1 = num1.substring(0, num1.length() - 1).split("[+]");
        int a = Integer.parseInt(n1[0]);
        int b = Integer.parseInt(n1[1]);
        String[] n2 = num1.substring(0, num2.length() - 1).split("[+]");
        int c = Integer.parseInt(n2[0]);
        int d = Integer.parseInt(n2[1]);
        int na = a * c - b * d;
        int nb = (a * d + b * c);
        String res =  "" + na + "+" + nb + "i";
        return res;
    }

    public String complexNumberMultiply2(String num1, String num2) {
        int[] n1 = parseNumber(num1);
        int[] n2 = parseNumber(num2);
        int na = n1[0] * n2[0] - n1[1] * n2[1];
        int nb = (n1[0] * n2[1] + n1[1] * n2[0]);
        String res =  "" + na + "+" + nb + "i";
        return res;
    }
    public int[] parseNumber(String num) {
        int[] res = new int[2];
        int i = 0, pos = 1;
        int sum = 0;
        while (num.charAt(i) != '+') {
            char c = num.charAt(i);
            if (c == '-') {
                pos = -1;
            }
            if (c >= '0' && c <= '9') {
                sum = sum * 10 + c - '0';
            }
        }
        if (pos == -1) {
            res[0] = -sum;
        }else {
            res[0] = sum;
        }
        pos = 1; sum = 0;
        while (num.charAt(i) != 'i') {
            char c = num.charAt(i);
            if (c == '-') {
                pos = -1;
            }
            if (c >= '0' && c <= '9') {
                sum = sum * 10 + c - '0';
            }
        }
        if (pos == -1) {
            res[1] = -sum;
        }else {
            res[1] = sum;
        }
        return res;
    }
}
