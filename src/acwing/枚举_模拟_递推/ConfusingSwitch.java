package acwing.枚举_模拟_递推;

import java.util.Scanner;

/**
 * 费解的开关
 * 25盏灯排成一个 5×5的方形。
 * 每一个灯都有一个开关，游戏者可以改变它的状态。
 * 每一步，游戏者可以改变某一个灯的状态。
 * 游戏者改变一个灯的状态会产生连锁反应：和这个灯上下左右相邻的灯也要相应地改变其状态。
 * 我们用数字 1 表示一盏开着的灯，用数字 0 表示关着的灯。
 * 给定一些游戏的初始状态，编写程序判断游戏者是否可能在 6 步以内使所有的灯都变亮。
 */
public class ConfusingSwitch {
    public static int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}}; //方向单位向量
    public static int[] hash = new int[1 << 5];                 //hash简化bit位的计算（用来计算某个lowbit在第几位）
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < 5; i++) {
            hash[1 << i] = i;   //第i位的lowbit为1 << i，用hash记录便于快速计算
        }
        for (int k = 0; k < n; k++) {
            int[][] matrix = new int[5][5];
            for (int i = 0; i < 5; i++) {
                String[] split = scanner.next().split("");
                for (int j = 0; j < 5; j++) {
                    matrix[i][j] = Integer.parseInt(split[j]);
                }
            }
            int min = 7;
            //枚举第一行的所有做法，对于一种做法，其之后的解法（如果存在）是唯一的
            //证明：由于每个开关最多被按一次（按两次相当于没按），在第一行按法固定的情况下，其没点亮的开关只能由下一行同列的开关点亮
            //例如，按下第一行若干个开关后，第一行第二列为0，那么只能按下第二行第二列开关来点亮该开关，由此递推可知解法唯一
            //于是我们枚举第一行的所有按法（2^5 - 1），然后从第二行模拟按下开关直到最后一行，最后检查最后一行是否全亮即可。对于所有的解法取次数最小的。
            for (int i = 0; i < 1 << 5; i++) {  //用二进制保存是否按下某开关，枚举所有按法
                int[][] lights = new int[5][5];
                for (int j = 0; j < 5; j++) {
                    System.arraycopy(matrix[j], 0, lights[j], 0, 5);
                }
                int cnt = simulate(lights, 0, i);   //根据 i 按下对应的开关
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 5; col++) { //从第二行起逐行按下开关，是否按下取决于上一行对应列的灯是否为0
                        if (lights[row][col] == 0) {
                            lightOn(lights, row + 1, col);
                            ++cnt;
                        }
                    }
                }
                if (!isOn(lights, 4)) continue; //如果最后一行有不亮的开关，则说明该解法不可行
                min = Math.min(min, cnt);
            }
            System.out.println(min == 7 ? -1 : min);
        }
    }

    public static int simulate(int[][] lights, int row, int i) {
        int cnt = 0;
        //每次取出 i 的lowbit，根据hash函数得出该位在第几位，点亮相应开关
        //然后减去lowbit，此时lowbit转化为另一个更靠后的lowbit，直到 i 中所有 1 都被取出。
        while (i > 0) {
            int col = hash[i & -i];
            lightOn(lights, row, col);
            i -= i & -i;
            ++cnt;
        }
        return cnt;
    }

    //改变当前和四个方向上相邻的灯状态
    public static void lightOn(int[][] lights, int i, int j) {
        lights[i][j] ^= 1;
        for (int k = 0; k < 4; k++) {
            int ni = i + dir[0][k], nj = j + dir[1][k];
            if (ni >= 0 && ni < 5 && nj >= 0 && nj < 5) {
                lights[ni][nj] ^= 1;
            }
        }
    }

    //判断某行是否全部点亮
    public static boolean isOn(int[][] lights, int row) {
        for (int i = 0; i < 5; i++) {
            if (lights[row][i] == 0) return false;
        }
        return true;
    }
}
