import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreads {
    public static void main(String[] args) {
        AtomicInteger done = new AtomicInteger();
        try (ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {   // Java 21
            for (int i = 0; i < 10_000; i++) {
                exec.submit(() -> {
                    Thread.sleep(Duration.ofMillis(50));     // blocking is cheap on a virtual thread
                    done.incrementAndGet();
                    return null;
                });
            }
        }                                                    // close() waits for every task
        System.out.println(done.get() + " tasks finished");

        Thread vt = Thread.ofVirtual().unstarted(() -> {});
        System.out.println("virtual? " + vt.isVirtual());
    }
}
