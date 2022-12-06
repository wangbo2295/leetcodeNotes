package javaSE;

import waterLadderPrice.WaterPrice;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestCollections {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(null);
        Dog i1 = dogs.get(0);
        dogs.add(new Dog(1,"erha",5));
        dogs.add(new Dog(3,"chaiquan",3));
        dogs.add(new Dog(2,"samoye",2));
        dogs.add(new Dog(4,"alasijia",4));
        Collections.sort(dogs,new DogSort1());
        for (Dog dog: dogs) {
            System.out.println(dog.name);
        }
        System.out.println("-------------");
        Collections.sort(dogs,new DogSort2());
        for (Dog dog: dogs) {
            System.out.println(dog.name);
        }
        System.out.println("-------------");
        //如果排序只用到一次，可以用匿名内部类的方式重写compare方法
        Collections.sort(dogs, new Comparator<Dog>(){
            public int compare(Dog o1,Dog o2){
                return o1.age.compareTo(o2.age);
            }
        });

        int[][] is = new int[3][4];
        int length = is[1].length;
        System.out.println(length);

        /**
         * 1.comparing方法接收一个Function类型的参数(似乎lambda表达式就可以看做Function参数)
         * 2.Function接口有一个apply方法（R apply(T t);）其作用就是应用传进来的这个lambda表达式
         * 3.o自动解析为Dog类，所以调用apply方法之后返回的是o.age
         * 4.最后comparing方法返回一个实现了(Comparator<T> & Serializable)并且实现了compare方法的Comparator
         *
         * 启发
         * 1、如果Function接口能构造函数的话，能否实现自定义函数？
         * 2、lambda表达式可以实现函数的返回值作为参数传进另一个方法
         * 3、lambda表达式不需要指定
         */
        Collections.sort(dogs, Comparator.comparing(Dog::getAge));//Coparator接口的静态类，用于简化lambda语句。
        dogs.sort(Comparator.comparing(Dog::getId));

        dogs.add(new Dog(10,"hhh",29));
        for (Dog dog: dogs) {
            System.out.println(dog.name);
        }

        Runnable runnable = ()-> System.out.println("test lambda..");
        new Thread(runnable).start();
        System.out.println("-------自己的函数接口--------");
        MyFunction<Dog> myFunction = (o1,o2) -> o1.age== o2.age;
        MyFunction<Dog> myFunction1 = MyFunction.equaling(Dog::getId);
        Supplier<Integer> supplier = ()->new Dog().getAge();
        Predicate<Dog> predicate = Dog::isDog;
        BiPredicate<Dog,Dog> biPredicate = (o1,o2)->o1.getId().equals(o2.getId());
        boolean equal = myFunction.isEqual(dogs.get(0), dogs.get(1));
        boolean equal1 = myFunction1.isEqual(dogs.get(0), dogs.get(1));
        System.out.println(equal+"----"+equal1);
        System.out.println("-------lambda生成comparator--------");
        Collections.sort(dogs,(o1,o2)->o1.id.compareTo(o2.id));     //竟然不让用，哼,要用Comparator.comparing(o -> o.age)

        //当继承两个接口且接口有同名方法时，只能实现一个方法，另一个要临时实现？
        TestInterface01 multi01 = new TestMultiImpl();
        TestInterface02 multi02 = new TestMultiImpl();
        //javaSE.TestMultiImpl@b4c966a---javaSE.TestMultiImpl@2f4d3709
        //false---false
        //说明有同名方法时，只需要实现一次，两个接口都是调的这个方法
        System.out.println(multi01+"---"+multi02);
        System.out.println(multi01.isEqual(3,4)+"---"+multi02.isEqual(3,4));
        //如果要将两个接口的实现分开呢？
        TestInterface02 multi03 = (o1,o2)->o1==o2?false:true;   //lambda表达式重写接口2的方法
        //打印结果：javaSE.TestMultiImpl@b4c966a---javaSE.TestCollections$$Lambda$6/1922154895@34a245ab
        //false---true  重写的方法生效了
        System.out.println(multi01+"---"+multi03);
        System.out.println(multi01.isEqual(3,4)+"---"+multi03.isEqual(3,4));

        Queue<Integer> queue = new LinkedList<>();
        queue.isEmpty();


        BigDecimal[] price = new BigDecimal[5];
        for(int i=0;i<5;i++){
            price[i] = new BigDecimal(i+1);
            System.out.print(price[i]);
        }
        System.out.println();
        BigDecimal[] endPoint = new BigDecimal[5];
        for(int i=0;i<5;i++){
            endPoint[i] = new BigDecimal(i*10);
            System.out.print(endPoint[i]);
        }
        System.out.println();
        BigDecimal money = WaterPrice.generate().calculate(price,endPoint,new BigDecimal(55.6));
        BigDecimal money2 = WaterPrice.generate().calculate(price,endPoint,new BigDecimal(40.7));
        System.out.println(money.subtract(money2));

        Cat[] cats = new  Cat[10];
        for (Cat cat:
             cats) {
            cat = new Cat(1);
        }
        cats[5] = null;
        System.out.println(cats.length);
        Dog[] dogs1 = new Dog[1];
        dogs1 = dogs.toArray(dogs1);
        for(Dog dog: dogs1){
            System.out.println(dog.id);
        }

        TestCollections testCollections = new TestCollections();
        testCollections.isPalindrome(121);
        Solution solution = new Solution();
        solution.divide(10,3);



    }
    public boolean isPalindrome(int x) {
        int temp = x;
        int len = 1;
        while(temp/10>0){
            len++;
            temp/=10;
        }
        if (len==1) return true;
        boolean single = len%2==0?false:true;
        int[] stack = new int[len/2];
        int index = 0;
        while(index<len/2){
            stack[index++] = x%10;
            x /= 10;
        }
        index--;
        if(single)  x/= 10;
        while(index>=0&&stack[index]==x%10){
            index--;
            x /= 10;
        }
        return index<0?true:false;
    }

}


//狗狗实现Comparator接口，实现compare方法，按名字排名
class Dog extends Animal {
    public Integer id;
    public Dog(){}
    public Dog(int id,String name,Integer age){
        super(name,age);
        this.id = id;
    }

    public Integer getAge(){
        return this.age;
    }
    public Integer getId(){
        return this.id;
    }

    public boolean isDog(){return true;}



}
//创建Comparator类，按名字比较（String）
class DogSort1 implements Comparator<Dog>{
    @Override
    public int compare(Dog o1, Dog o2) {
        return o1.name.compareTo(o2.name);
    }
}
//创建Comparator类，按id比较（Integer）
class DogSort2 implements Comparator<Dog>{
    @Override
    public int compare(Dog o1, Dog o2) {
        return o1.id.compareTo(o2.id);
    }
}
class Cat extends Animal{
    public Cat(Integer age){
        this.age = age;
    }
}
class Animal{
    public String name;
    public Integer age;
    public Animal(){}
    public Animal(String name,Integer age){
        this.name = name;
        this.age = age;
    }
}




class MyStack {

    Queue<Integer> queue1;
    Queue<Integer> queue2;
    int size;

    public MyStack() {
        queue1 = new LinkedList();
        queue2 = new LinkedList();
    }

    public void push(int x) {
        queue1.offer(x);
        size++;
    }

    public int pop() {
        while(queue1.size()>1){
            queue2.offer(queue1.poll());
        }
        int res = queue1.poll();
        size--;
        swapQueue();
        return res;
    }

    public int top() {
        int res;
        while(queue1.size()>1){
            queue2.offer(queue1.poll());
        }
        res = queue1.peek();
        queue2.offer(queue1.poll());
        swapQueue();
        return res;
    }

    public boolean empty() {
        return size==0?true:false;
    }

    public void swapQueue(){
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
}


class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend==Integer.MIN_VALUE&&divisor==-1)    return Integer.MAX_VALUE;
        int left=0,right=dividend+1;
        int cur_value = 0,for_value=0;
        while(left<right){
            int mid = left+(right-left>>1);
            if(multiply(divisor,mid)>dividend){
                right = mid;
            }else if(multiply(divisor,mid)<dividend){
                left = mid+1;
            }else{
                return mid;
            }
        }
        return left;
    }

    public int multiply(int x,int i){
        int level=0,res=0;
        while(i>0){
            level = find_level(i);
            res += x<<level;
            i -= 1<<level;
        }
        return res;
    }
    public int find_level(int i) {
        if (i == 1) {
            return 0;
        } else{
            return find_level(i>>1) + 1;
        }
    }
}