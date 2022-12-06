package acwing.倍增;

import java.io.*;

public class RMQ {
    public static int[] log2;
    public static int[][] f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]), m = Integer.parseInt(nm[1]);
        int[] nums = new int[n + 1];
        String[] s = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(s[i - 1]);
        }
        log2 = new int[n + 1];
        for (int i = 1; i <= n; i++)    log2[i] = log2(i);
        f = new int[n + 1][log2[n] + 1];
        for (int i = 1; i <= n; i++) f[i][0] = nums[i];
        for (int k = 1; k <= log2[n]; k++) {
            for (int i = 1; i + (1 << k) <= n + 1; i++) {
                f[i][k] = Math.max(f[i][k - 1], f[i + (1 << (k - 1))][k - 1]);
            }
        }
        for (int i = 0; i < m; i++) {
            String[] range = br.readLine().split(" ");
            int l = Integer.parseInt(range[0]), r = Integer.parseInt(range[1]);
            int k = log2[r - l + 1];
            bw.write("" + Math.max(f[l][k], f[r - (1 << k) + 1][k]) + "\n");
        }
        bw.flush();
    }
    public static int log2(int n)  {return (int) (Math.log(n) / Math.log(2));}
}
