import java.util.concurrent.atomic.AtomicInteger;
public class Lucky {
    private static final AtomicInteger x = new AtomicInteger(0);
    private static final AtomicInteger count = new AtomicInteger(0);

    private static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                int x = Lucky.x.getAndIncrement();
                if (x < 999999) {
                    if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                            % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                        System.out.println(x);
                        count.incrementAndGet();
                    }
                }
                else break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}