import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FixedAndImmutable {
    static void attempt(String what, Runnable action) {
        try {
            action.run();
            System.out.println(what + ": ok");
        } catch (RuntimeException e) {
            System.out.println(what + ": " + e.getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
        List<String> asList = Arrays.asList("a", "b");          // Java 1.2: fixed-size view of an array
        List<String> listOf = List.of("a", "b");                // Java 9: truly unmodifiable
        List<String> growable = new ArrayList<>(listOf);        // diamond <>: Java 7

        attempt("Arrays.asList set", () -> asList.set(0, "z"));
        attempt("Arrays.asList add", () -> asList.add("c"));
        attempt("List.of set     ", () -> listOf.set(0, "z"));
        attempt("List.of null    ", () -> List.of("a", null));
        attempt("ArrayList add   ", () -> growable.add("c"));
        System.out.println(asList + " " + listOf + " " + growable);
    }
}
