package threadTest;

/**
 * 哲学家进餐问题
 * 同时拿到左右筷子的锁才能吃饭
 */
public class TDPP {
    public static Integer[] chopsticks = {1,1,1,1,1};

    public static void eat(Integer id) {
        synchronized (chopsticks[id]) {
            System.out.println("第" + id + "位哲学家拿起了" + id + "号筷子");
            synchronized (chopsticks[(id + 1) % 5]) {
                System.out.println("第" + id + "位哲学家拿起了" + (id + 1) % 5 + "号筷子");
                System.out.println("第" + id + "位哲学家开始进餐。。");
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            final Integer id = i;
            new Thread(()-> {
                while (true){
                    eat(id);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
