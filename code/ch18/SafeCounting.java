import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounting {
    static int unsafe = 0;                                   // shown only to explain the race
    static int locked = 0;
    static final Object LOCK = new Object();
    static final AtomicInteger atomic = new AtomicInteger(); // Java 5

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int t = 0; t < 4; t++) {
            Thread th = new Thread(() -> {
                for (int i = 0; i < 100_000; i++) {
                    unsafe++;                                  // read, add, write: not atomic
                    synchronized (LOCK) { locked++; }          // one thread at a time
                    atomic.incrementAndGet();                  // a single atomic CPU operation
                }
            });
            threads.add(th);
            th.start();
        }
        for (Thread th : threads) th.join();                   // wait for all four
        System.out.println("synchronized: " + locked);
        System.out.println("atomic:       " + atomic.get());
        System.out.println("unsafe <= 400000: " + (unsafe <= 400_000));   // usually less: lost updates
    }
}
