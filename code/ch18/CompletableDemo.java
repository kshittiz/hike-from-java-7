import java.util.concurrent.CompletableFuture;

public class CompletableDemo {
    // pretend these call remote services
    static int fetchPrice()    { return 4500; }
    static double fetchTaxRate() { return 0.10; }

    public static void main(String[] args) {
        // Java 8
        CompletableFuture<Integer> price =
                CompletableFuture.supplyAsync(CompletableDemo::fetchPrice);
        CompletableFuture<Double> tax =
                CompletableFuture.supplyAsync(CompletableDemo::fetchTaxRate);

        String total = price
                .thenCombine(tax, (p, r) -> p + Math.round(p * r))
                .thenApply(c -> String.format("$%d.%02d", c / 100, c % 100))
                .exceptionally(e -> "unavailable")
                .join();
        System.out.println(total);
    }
}
