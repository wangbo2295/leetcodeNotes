package javaSE;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysTest {

    public static void main(String[] args) {
//        int[] arr = new int[10];    //基本数据类型的数组无法使用自定义比较器
        //因为Arrays.sort(T[], Comparator); 这个方法的参数是T[]，而int[] 是基本数据类型的数组
        //自定义构造器需重写compare方法，而该方法需要两个对象参数，因为int[]成员为基本数据类型不是对象，所以自然无法自定义比较器
        //而Ineger[]数组就可以使用自定义比较器了。
        Integer[] arr = new Integer[10];
        for(int i=0;i<10;i++){
            arr[i] = i;
        }
        Arrays.sort(arr,(o1, o2)->Integer.compare(o2,o1));
        //二维数组为什么能自定义比较器呢？
        //因为二维数组的元素是一维数组，而一维数组本身是对象，所以能够自定义比较器。
        int[][] arr2 = new int[10][10];
        Arrays.sort(arr2, Comparator.comparingInt(o -> o[1]));
    }
}
