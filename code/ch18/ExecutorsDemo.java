import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(4);  // Java 5
        try {
            List<Callable<Integer>> jobs = new ArrayList<>();
            for (int chapter = 1; chapter <= 20; chapter++) {
                final int c = chapter;
                jobs.add(() -> c * 10);  // pretend: pages in chapter c
            }
            int total = 0;
            for (Future<Integer> f : pool.invokeAll(jobs)) total += f.get();
            System.out.println("total pages: " + total);
        } finally {
            pool.shutdown();
        }
    }
}
