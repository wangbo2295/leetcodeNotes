package threadTest;

/**
 * 1、筷子为共享资源，相邻两个哲学家争夺一根筷子
 * 2、对筷子加锁，只有当某个哲学家同时拿起左右两根筷子时才进餐
 * 3、如果所有哲学家同时拿起左边的筷子，会导致死锁
 *      解决办法：
 *      a.只有当哲学家左右筷子都可用时，才拿起。
 *      b.最多允许四位哲学家拿起左边的筷子，这样至少能保证有一位哲学家能进餐并且释放筷子
 *      c.奇数号哲学家和偶数号哲学家拿筷子循序相反，两两竞争同一支筷子。
 */
////解决方案一
//public class Philosopher implements Runnable{
//    int id;
//    volatile Chopsticks left;
//    volatile Chopsticks right;
//    public Philosopher(int id,Chopsticks left,Chopsticks right){
//        this.id=id;
//        this.left = left;
//        this.right = right;
//    }
//
//    public void eat(){
//        if(left.status&&right.status) {     //只有当哲学家左右两边筷子均可用时才拿筷子吃饭
//            synchronized (left){
//                left.status = false;
//                System.out.println("第" + id + "位哲学家拿起了" + left.id + "号筷子");
//                synchronized (right){
//                    right.status = false;
//                    System.out.println("第" + id + "位哲学家拿起了" + right.id + "号筷子");
//                    System.out.println("第" + id + "位哲学家开始进餐。。。");
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    right.status = true;
//                    System.out.println("第" + id + "位哲学家放下了" + right.id + "号筷子");
//                }
//                left.status = true;
//                System.out.println("第" + id + "位哲学家放下了" + left.id + "号筷子");
//                System.out.println("第" + id + "位哲学家开始了思考。。。");
//
//            }
//        }
//    }
//
//    @Override
//    public void run() {
//        while(true){
//            eat();
//        }
//    }
//}

class Chopsticks{
    int id;
    boolean status;
    public Chopsticks(int id){this.id = id;status=true;}
}


//解决方案二
public class Philosopher implements Runnable{
    int id;
    volatile Chopsticks left;
    volatile Chopsticks right;
    public Philosopher(int id,Chopsticks left,Chopsticks right){
        this.id=id;
        this.left = left;
        this.right = right;
    }

    public void eat(){
        if(left.status&&right.status) {     //只有当哲学家左右两边筷子均可用时才拿筷子吃饭
            synchronized (left){
                left.status = false;
                System.out.println("第" + id + "位哲学家拿起了" + left.id + "号筷子");
                synchronized (right){
                    right.status = false;
                    System.out.println("第" + id + "位哲学家拿起了" + right.id + "号筷子");
                    System.out.println("第" + id + "位哲学家开始进餐。。。");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    right.status = true;
                    System.out.println("第" + id + "位哲学家放下了" + right.id + "号筷子");
                }
                left.status = true;
                System.out.println("第" + id + "位哲学家放下了" + left.id + "号筷子");
                System.out.println("第" + id + "位哲学家开始了思考。。。");

            }
        }
    }

    @Override
    public void run() {
        while(true){
            eat();
        }
    }
}
