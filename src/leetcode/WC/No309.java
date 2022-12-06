package leetcode.WC;

import java.util.*;

public class No309 {
    //T1 检查数对距离
    public boolean checkDistances(String s, int[] distance) {
        int[] hash = new int[26];
        for (int i = 0; i < 26; i++) hash[i] = -1;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (hash[c] >= 0 && i - hash[c] - 1 != distance[c]) return false;
            else hash[c] = i;
        }
        return true;
    }
    // T2 到达终点的方案个数
    public int numberOfWays(int startPos, int endPos, int k) {
        int[][] dp = new int[k + 1][3010];
        int MOD = 1_000_000_007;
        dp[0][startPos + 1005] = 1;
        for (int kk = 1; kk <= k; kk++) {
            for (int i = 1; i < 3005; i++) {
                dp[k][i] += dp[kk - 1][i - 1] + dp[kk - 1][i + 1];
                dp[k][i] %= MOD;
            }
        }
        return dp[k][endPos + 1005];
    }

    //T4 最长优雅子数组
    int[] hash = new int[37];
    public int longestNiceSubarray(int[] nums) {
        for (int i = 0; i < 31; i++) hash[(1 << i) % 37] = i;
        int[] cnt = new int[31];
        int ans = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            add(nums[j], cnt, false);
            while (!check(cnt)) {
                add(nums[i++], cnt, true);
            }
            if (check(cnt)) {
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }

    private void add(int num, int[] cnt, boolean negative) {
        int delta = negative ? -1 : 1;
        for (int i = num; i > 0; i -= i & -i) {
            cnt[hash[(i & -i) % 37]] += delta;
        }
    }

    private boolean check(int[] cnt) {
        for (int i : cnt) {
            if (i > 1)  return false;
        }
        return true;
    }

    //会议室II
    //1、每个空的会议室会优先服务会议开始时间最早的会议
    //2、有多个空闲会议室时，id 最小的会议室会优先被使用
    //思路：将会议按开始时间排序后，逐个处理每个会议
    //每次需要找到空闲会议室中id最小的，或者被占用会议室中结束时间最早且id更小的
    //可以用两个堆分别维护空闲和占用的会议室，处理每个会议之前，先将之前被占用但目前已经结束会议的会议室弹到空闲堆里
    //处理完所有空闲会议室后，如果空闲堆里有元素，取出堆顶元素就是当前会议被安排的会议室，修改其结束时间，压入被占用会议室堆中
    //处理每个会议过程中统计被使用次数最大且id最小的会议室
    public int mostBooked(int n, int[][] meetings) {
        int max = 0, id = n;
        PriorityQueue<Room> vac = new PriorityQueue<>(Comparator.comparingInt(o->o.id));
        PriorityQueue<Room> ocu = new PriorityQueue<>(Comparator.comparingLong((Room o)->o.vacancy).thenComparingInt((Room o)->o.id));
        Arrays.sort(meetings, Comparator.comparingInt(o->o[0]));
        for (int i = 0; i < n; i++) ocu.offer(new Room(i, 0, 0));
        for (int[] meeting : meetings) {
            int s = meeting[0], e = meeting[1];
            while (!ocu.isEmpty() && ocu.peek().vacancy <= s) {
                vac.offer(ocu.poll());
            }
            Room latest;
            if (!vac.isEmpty()) latest = vac.poll();
            else latest = ocu.poll();
            latest.vacancy = Math.max(latest.vacancy + e - s, e);
            latest.cnt++;
            if (latest.cnt > max || (latest.cnt == max && latest.id < id)) {
                max = latest.cnt;
                id = latest.id;
            }
            ocu.offer(latest);
        }
        return id;
    }

    class Room {
        int id;
        long vacancy;
        int cnt;
        public Room(int id, long vacancy, int cnt) {
            this.id = id;
            this.vacancy = vacancy;
            this.cnt = cnt;
        }
    }
}
