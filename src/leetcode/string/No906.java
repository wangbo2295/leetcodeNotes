package leetcode.string;

public class No906 {
    public int superpalindromesInRange(String left, String right) {
        long l = sqrt(Long.parseLong(left)), r = sqrt(Long.parseLong(right));
        String pali = nextPal("" + l), end = nextPal("" + r);
        int cnt = 0;
        while (!pali.equals(end)) {
            long p = Long.parseLong(pali);
            if (isPal("" + p * p)) {
                ++cnt;
            }
            pali = nextPal(pali);
        }
        return cnt;
    }

    public String nextPal(String num) {
        int len = num.length();
        if (len == 1)   return "" + (Integer.parseInt(num) + 1);
        String prefix = num.substring(0, (len - 1 >> 1) + 1);
        StringBuilder repre = new StringBuilder(prefix).reverse();
        StringBuilder oldsuffix = new StringBuilder(num.substring((len - 1 >> 1) + 1));
        if (repre.length() > oldsuffix.length())    repre.deleteCharAt(0);
        if (repre.compareTo(oldsuffix) > 0) return prefix + repre;
        long base = Long.parseLong(prefix);
        base++;
        StringBuilder newpre = new StringBuilder(String.valueOf(base));
        StringBuilder suffix = new StringBuilder(newpre).reverse();
        if ((len & 1) == 1) {
            suffix.deleteCharAt(0);
        }
        if (newpre.length() > prefix.length()) {
            newpre.deleteCharAt(newpre.length() - 1);
        }
        return "" + newpre + suffix;
    }

    public boolean isPal(String num) {
        String reverse = new StringBuilder(num).reverse().toString();
        return reverse.equals(num);
    }

    public long sqrt(long num) {
        long left = 0, right = num + 1;
        while (left + 1 < right) {
            long mid = left + (right - left >> 1);
            if (mid <= num / mid) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left * left == num ? left - 1 : left;
    }

    public static void main(String[] args) {
        No906 no906 = new No906();
//        System.out.println(no906.nextPal("191"));
//        System.out.println(no906.nextPal("999"));
//        System.out.println(no906.nextPal("1991"));
//        System.out.println(no906.nextPal("121"));
//        System.out.println(no906.nextPal("19"));
//        System.out.println(no906.nextPal("91"));
//        System.out.println(no906.nextPal("10"));
        no906.superpalindromesInRange("1", "1000");
    }
}
