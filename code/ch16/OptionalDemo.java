import java.util.Map;
import java.util.Optional;

public class OptionalDemo {
    static final Map<String, String> EMAILS = Map.of("asha", "asha@example.com");

    static Optional<String> email(String user) {           // Java 8: "maybe a value"
        return Optional.ofNullable(EMAILS.get(user));
    }

    public static void main(String[] args) {
        System.out.println(email("asha").map(String::toUpperCase).orElse("no email"));
        System.out.println(email("ravi").map(String::toUpperCase).orElse("no email"));
        System.out.println(email("ravi").isEmpty());                         // Java 11
        email("asha").ifPresentOrElse(e -> System.out.println("send to " + e),
                                      () -> System.out.println("skip"));      // Java 9
    }
}
