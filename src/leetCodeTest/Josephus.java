package leetCodeTest;

/**
 * Josephus problem
 * O(n2)
 */
public class Josephus {

    public int solution(int m, int n) {
        int[] arr = new int[n];
        for (int i=0;i<arr.length;i++){
            arr[i] = i+1;
        }
        int size = n,hold = 0;
        while(size>1){
            hold = (hold+m)%size;
            for (int j=hold;j<size-1;j++){
                arr[j] = arr[j+1];
            }
            size--;
        }
        return arr[0];
    }

}


