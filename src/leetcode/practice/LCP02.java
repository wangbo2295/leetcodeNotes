package leetcode.practice;

class LCP02 {
    public static void main(String[] args) {
        LCP02 lcp02 = new LCP02();
        int[] cont = {1, 5, 6, 6, 5, 7, 5, 5, 4, 7};
        lcp02.fraction(cont);
    }
    public int[] fraction(int[] cont) {
        int[] res = new int[2];
        res[0] = 1; res[1] = cont[cont.length-1];
        fraction(cont,res,cont.length-2);
        ojld(res);
        return res;
    }
    public void fraction(int[] cont, int[] res,int n){
        if(n<0){
            int temp=res[0];
            res[0] = res[1];
            res[1] = temp;
            return;
        }
        int up = res[0]; int down = res[1];
        res[1] = cont[n]*down + up;
        res[0] = down;
        fraction(cont,res,n-1);
    }
    public void ojld(int[] res){
        int max;int min;
        if(res[0]>res[1]){
            max = res[0];min = res[1];
        }else{
            max = res[1]; min = res[0];
        }
        while(max%min!=0){
            max = min;
            min = max%min;
        }
        res[0] /= min;
        res[1] /= min;
    }
}