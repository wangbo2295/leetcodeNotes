package leetcode.DC;

import java.util.*;

public class DC79 {
    public boolean digitCount(String num) {
        int n = num.length();
        int[] map = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (num.charAt(i) - '0' <= n)
                map[num.charAt(i) - '0']++;
        }
        for (int i = 0; i < n; i++) {
            if (map[i] != num.charAt(i) - '0') {
                return false;
            }
        }
        return true;
    }

    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        int n = messages.length, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            Integer num = messages[i].split(" ").length;
            if (map.containsKey(senders[i])) {
                num += map.get(senders[i]);
            }
            max = Math.max(max, num);
            map.put(senders[i], num);
        }
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            if (next.getValue().equals(max)) {
                return next.getKey();
            }
        }
        return null;
    }

    public long maximumImportance(int n, int[][] roads) {
        long[] inDegrees = new long[n];
        for (int i = 0; i < roads.length; i++) {
            inDegrees[roads[i][0]]++;
            inDegrees[roads[i][1]]++;
        }
        long ans = 0;
        Arrays.sort(inDegrees);
        for (int i = 0; i < n; i++) {
            ans += inDegrees[i] * (i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}

class BookMyShow {

    Seats head;
    Seats vHead;
    Seats vTail;
    int n;
    int m;

    public BookMyShow(int n, int m) {
        this.n = n;
        this.m = m;
        this.head = new Seats(false, 0, (long)m * n, null, null);
        this.vHead = new Seats(true, Integer.MIN_VALUE, 0, null, head);
        this.vTail = new Seats(true, Integer.MAX_VALUE, 0, head, null);
    }

    public int[] gather(int k, int maxRow) {
        Seats tmp = head;
        while (tmp != null) {
            while (tmp != null && tmp.taken && tmp.len >= k) {
                tmp = tmp.next;
            }
            if (tmp == null)    break;
            int row = (int)tmp.num / m;
            if (row > maxRow)   break;
            else if ((tmp.num + k) / m > row) continue;
            else {
                Seats seats = new Seats(true, tmp.num, k, tmp.pre, tmp);
                tmp.num += k;
                tmp.len -= k;
                tmp.pre.next = seats;
                tmp.pre = seats;
                return new int[] {row, (int) seats.num % m};
            }
        }
        return new int[] {};
    }

    public boolean scatter(int k, int maxRow) {
        Seats tmp = head;
        while (tmp.taken)   tmp = tmp.next;
        return scatter(k, maxRow, head);
    }

    public boolean scatter(int k, int maxRow, Seats seats) {
        if (k <= 0) return true;
        if (seats.num / m > maxRow) return false;
        if (k >= seats.len) {
            seats.taken = true;
            Seats tmp = seats;
            while (tmp.taken)   tmp = tmp.next;
            boolean scatter = scatter(k - (int) seats.len, maxRow, tmp);
            if (!scatter) {
                seats.taken = false;
            }
            return scatter;
        }else {
            Seats seats1 = new Seats(true, seats.num, k, seats.pre, seats);
            seats.num += k;
            seats.len -= k;
            seats.pre = seats1;
            seats1.pre.next = seats1;
            return true;
        }
    }

    class Seats{
        boolean taken;
        long num;
        long len;
        Seats pre;
        Seats next;
        public Seats(boolean taken, long num, long len, Seats pre, Seats next) {
            this.taken = taken;
            this.num = num;
            this.len = len;
            this.pre = pre;
            this.next = next;
        }
    }
}


