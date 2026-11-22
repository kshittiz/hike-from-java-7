import java.util.List;

public class Laziness {
    public static void main(String[] args) {
        String first = List.of("seven", "eleven", "seventeen", "twenty-one", "twenty-seven").stream()
                .peek(s -> System.out.println("  looking at " + s))
                .filter(s -> s.startsWith("twenty"))
                .map(String::toUpperCase)
                .findFirst()                           // short-circuits: stops at the first match
                .orElse("none");
        System.out.println("found " + first);
    }
}
