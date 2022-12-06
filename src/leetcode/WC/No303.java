package leetcode.WC;

import com.sun.source.doctree.SeeTree;

import java.util.*;

public class No303 {

    public char repeatedCharacter(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c))    return c;
        }
        return ' ';
    }

    public int equalPairs(int[][] grid) {
        int cnt = 0, n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean match = true;
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] != grid[k][j]) {
                        match = false;
                        break;
                    }
                }
                if (match)  ++cnt;
            }
        }
        return cnt;
    }

    public long countExcellentPairs(int[] nums, int k) {
        int[] cnt = new int[31];
        int[] ints = Arrays.stream(nums).distinct().toArray();
        for (int num : ints) {
            int t = 0;
            for (int i = 0; i < 31; i++) {
                if ((num >> i & 1) == 1)    ++t;
            }
            cnt[t]++;
        }
        long ans = 0;
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                if (i + j >= k) {
                    ans += (long) cnt[i] * cnt[j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] foods = {"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"};
        String[] cuisines = {"korean", "japanese", "japanese", "greek", "japanese", "korean"};
        int[] ratings = {9, 12, 8, 15, 14, 7};
        FoodRatings foodRatings = new FoodRatings(foods, cuisines, ratings);
        foodRatings.highestRated("korean");
        foodRatings.highestRated("japanese");
        foodRatings.changeRating("sushi", 16);
        foodRatings.highestRated("japanese");
        foodRatings.changeRating("ramen", 16);
        foodRatings.highestRated("japanese");
    }
}

/**
 * 本题为什么用PriorityQueue不行？
 * 因为更新元素后堆不会自动保持堆续性，需要重新入堆，而问题在于堆只能弹出顶部元素，无法弹出指定元素
 * 而TreeSet作为有序集合，可以以O（1）时间查询元素，所以修改时只要先删除元素再加入修改后的即可保持有序
 * 关键在于将food的三个属性封装起来便于查询。
 */
class FoodRatings {

    Map<String, Pair> rateMap = new HashMap<>();
    Map<String, TreeSet<Pair>> cuisineMap = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;
        for (int i = 0; i < n; i++) {
            Pair pair = new Pair(foods[i], ratings[i], cuisines[i]);
            rateMap.put(foods[i], pair);
            TreeSet<Pair> q = cuisineMap.getOrDefault(cuisines[i], new TreeSet<>((o1, o2) -> {
                int compare = o2.rate.compareTo(o1.rate);
                if (compare != 0) return compare;
                return o1.food.compareTo(o2.food);
            }));
            q.add(pair);
            cuisineMap.put(cuisines[i], q);
        }
    }

    public void changeRating(String food, int newRating) {
        Pair pair = rateMap.get(food);
        TreeSet<Pair> pairs = cuisineMap.get(pair.cuisine);
        pairs.remove(pair); //先把旧的删除，再重新添加修改后的pair
        pair.rate = newRating;
        rateMap.put(food, pair);
        pairs.add(pair);
        cuisineMap.put(pair.cuisine, pairs);
    }

    public String highestRated(String cuisine) {
        return Objects.requireNonNull(cuisineMap.get(cuisine).first()).food;
    }
}

class Pair {
    String food;
    String cuisine;
    Integer rate;
    public Pair (String food, Integer rate, String cuisine) {
        this.cuisine = cuisine;
        this.food = food;
        this.rate = rate;
    }
}
