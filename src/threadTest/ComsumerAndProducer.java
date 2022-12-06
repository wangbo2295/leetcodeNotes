package threadTest;

public class ComsumerAndProducer {
    public static Integer count = 0;
    public static final Integer MAX_AMOUNT = 10;
    public static int[] buffer = new int[MAX_AMOUNT];
    class Producer implements Runnable{
        int in = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (count) {
                    if (count < 10) {
                        buffer[in]++;
                        System.out.println("Produced product: ->" + in);
                        in = (in + 1) % MAX_AMOUNT;
                        count++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    class Comsumer implements Runnable{
        int out = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (count) {
                    if (count > 0) {
                        buffer[out]--;
                        System.out.println("Consumed product: <-" + out);
                        out = (out + 1) % MAX_AMOUNT;
                        count--;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void simulate() {
        new Thread(new Producer()).start();
        new Thread(new Comsumer()).start();
    }

    public static void main(String[] args) {
        ComsumerAndProducer comsumerAndProducer = new ComsumerAndProducer();
        comsumerAndProducer.simulate();
    }
}


