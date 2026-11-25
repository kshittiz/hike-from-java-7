import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableOrCallable {
    public static void main(String[] args) throws Exception {
        // Runnable: a job with no answer (and no checked exceptions)
        Runnable wave = () -> System.out.println("waving from the summit");

        // Callable: a job that hands back an answer, and may throw
        Callable<Integer> countSteps = () -> 120 + 340 + 410;

        try (ExecutorService pool = Executors.newFixedThreadPool(2)) {
            Future<?> waved = pool.submit(wave);
            Future<Integer> steps = pool.submit(countSteps);
            waved.get();                     // nothing to return: null
            System.out.println("steps counted: " + steps.get());
        }
    }
}
