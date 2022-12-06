package leetcode.DC;

public class DoubleWeek76 {

    public static void main(String[] args) {
        ATM atm = new ATM();
        int[] deposit = {0,0,1,2,1};
        atm.deposit(deposit);
        atm.withdraw(600);

    }
}

/**
 * 给你一个整数 total ，表示你拥有的总钱数。同时给你两个整数 cost1 和 cost2 ，分别表示一支钢笔和一支铅笔的价格。你可以花费你部分或者全部的钱，去买任意数目的两种笔。
 * 请你返回购买钢笔和铅笔的 不同方案数目 。
 * 输入：total = 20, cost1 = 10, cost2 = 5
 * 输出：9
 * 解释：一支钢笔的价格为 10 ，一支铅笔的价格为 5 。
 * - 如果你买 0 支钢笔，那么你可以买 0 ，1 ，2 ，3 或者 4 支铅笔。
 * - 如果你买 1 支钢笔，那么你可以买 0 ，1 或者 2 支铅笔。
 * - 如果你买 2 支钢笔，那么你没法买任何铅笔。
 * 所以买钢笔和铅笔的总方案数为 5 + 3 + 1 = 9 种。
 */
class BuyPenAndPencil {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long res = total/cost1+1;
        for(int i=0;i<=total/cost1;i++){
            res += (total-i*cost1)/cost2;
        }
        return res;
    }
}

class ATM {
    int[] storage;
    int[] money = {20,50,100,200,500};
    public ATM() {
        storage = new int[5];
    }

    public void deposit(int[] banknotesCount) {
        for(int i=0;i<5;i++){
            storage[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] res = new int[5];
        int i=4;
        int[] storageTemp = new int[5];
        System.arraycopy(storage,0,storageTemp,0,storage.length);
        while(i>=0){
            if(amount>=money[i]&&storage[i]>0){
                int n = amount/money[i];
                if(n<=storage[i]){
                    amount -= n*money[i];
                    storage[i]-=n;
                    res[i]+=n;
                }else{
                    amount -= storage[i]*money[i];
                    res[i] = storage[i];
                    storage[i] = 0;
                }
            }
            i--;
        }
        if(amount!=0){
            storage = storageTemp;
            return new int[] {-1};
        }
        return res;
    }
}
