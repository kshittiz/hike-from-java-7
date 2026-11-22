import java.util.concurrent.CompletableFuture;

public class CompletableDemo {
    static int fetchPrice()    { return 4500; }   // pretend these call remote services
    static double fetchTaxRate() { return 0.10; }

    public static void main(String[] args) {
        CompletableFuture<Integer> price = CompletableFuture.supplyAsync(CompletableDemo::fetchPrice);   // Java 8
        CompletableFuture<Double>  tax   = CompletableFuture.supplyAsync(CompletableDemo::fetchTaxRate);

        String total = price.thenCombine(tax, (p, r) -> p + Math.round(p * r))
                            .thenApply(cents -> String.format("$%d.%02d", cents / 100, cents % 100))
                            .exceptionally(e -> "unavailable")
                            .join();
        System.out.println(total);
    }
}
