package leetcode.unionFind;

import java.util.*;

public class No721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        for (int i = 1; i < N; i++) fa[i] = i;
        Map<String, List<Integer>> map = new HashMap<>();
        for (int j = 0; j < accounts.size(); j++) {
            List<String> account = accounts.get(j);
            for (int i = 1; i < account.size(); i++) {
                List<Integer> orDefault = map.getOrDefault(account.get(i), new ArrayList<>());
                orDefault.add(j + 1);
                map.put(account.get(i), orDefault);
            }
        }
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            List<Integer> value = entry.getValue();
            for (int i = 1; i < value.size(); i++) {
                merge(value.get(i), value.get(i - 1));
            }
        }
        List<List<String>> list = new ArrayList<>();
        Map<Integer, TreeSet<String>> trees = new HashMap<>();
        for (int i = 1; i <= accounts.size(); i++) {
            if (fa[i] == i) trees.put(i, new TreeSet<>());
        }
        for (int i = 1; i <= accounts.size(); i++) {
            TreeSet<String> treeSet = trees.get(get(i));
            for (int j = 1; j < accounts.get(i).size(); j++) {
                treeSet.add(accounts.get(i).get(j));
            }
        }
        for (Map.Entry<Integer, TreeSet<String>> entry : trees.entrySet()) {
            Integer key = entry.getKey();
            TreeSet<String> value = entry.getValue();
            List<String> ls = new ArrayList<>();
            ls.add(accounts.get(key).get(0));
            ls.addAll(value);
            list.add(ls);
        }
        return list;
    }

    int N = 1010;
    int[] fa = new int[N];

    public int get(int x) {
        return fa[x] == x ? x : (fa[x] = get(fa[x]));
    }

    public void merge(int x, int y) {
        fa[get(x)] = get(y);
    }
}
