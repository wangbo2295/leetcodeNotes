package acwing.哈希;

import java.io.*;

public class LittleRabbit {
    public static long[] H;
    public static int p = 13331;
    public static long[] base;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        int n = s.length();
        H = new long[n + 1];
        base = new long[n + 1];
        base[0] = 1;
        for (int i = 1; i <= n; i++) {
            H[i] = H[i - 1] * p + s.charAt(i - 1) - 'a';
            base[i] = base[i - 1] * p;
        }
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String[] range = br.readLine().split(" ");
            int l1 = Integer.parseInt(range[0]), r1 = Integer.parseInt(range[1]), l2 = Integer.parseInt(range[2]), r2 = Integer.parseInt(range[3]);
            long h1 = H[r1] - H[l1 - 1] * base[r1 - l1 + 1];
            long h2 = H[r2] - H[l2 - 1] * base[r2 - l2 + 1];
            bw.write((h1 == h2 ? "Yes" : "No") + "\n");
            bw.flush();

        }
    }
}
