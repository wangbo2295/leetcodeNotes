package acwing.栈;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;

public class TrainsInAndOut {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        BigDecimal[] S = new BigDecimal[n + 1];
        Arrays.fill(S, BigDecimal.ZERO);
        S[0] = BigDecimal.ONE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                S[i] = S[i].add(S[i - j].multiply(S[j - 1]));
            }
        }
        bw.write(String.valueOf(S[n]));
        bw.flush();
    }
}
