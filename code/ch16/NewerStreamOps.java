import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewerStreamOps {
    public static void main(String[] args) {
        List<Integer> sorted = List.of(2, 4, 6, 7, 8, 10);
        // Java 9
        System.out.println(
                sorted.stream().takeWhile(n -> n % 2 == 0).toList());
        // Java 9
        System.out.println(
                sorted.stream().dropWhile(n -> n % 2 == 0).toList());

        // Java 9
        System.out.println(
                Stream.iterate(7, v -> v <= 27, v -> v + 10).toList());

        // Java 12
        double avg = Stream.of(7, 17, 27).collect(Collectors.teeing(
                Collectors.summingInt(n -> n),
                Collectors.counting(),
                (sum, count) -> (double) sum / count));
        System.out.println(avg);

        // Java 16
        List<String> words = Stream.of("java", "seven")
                .<String>mapMulti((w, sink) -> {
                    sink.accept(w);
                    sink.accept(w.toUpperCase());
                })
                .toList();
        System.out.println(words);
    }
}
