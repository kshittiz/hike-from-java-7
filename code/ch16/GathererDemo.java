import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class GathererDemo {
    public static void main(String[] args) {
        List<List<Integer>> windows = Stream.of(1, 2, 3, 4, 5, 6, 7)
                .gather(Gatherers.windowFixed(3))            // Java 24
                .toList();
        System.out.println(windows);

        List<List<Integer>> sliding = Stream.of(1, 2, 3, 4)
                .gather(Gatherers.windowSliding(2))
                .toList();
        System.out.println(sliding);
    }
}
