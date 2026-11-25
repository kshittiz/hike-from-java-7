import java.util.List;

public class InterfaceEvolution {
    interface Greeter {
        String name();  // abstract: implementers must provide

        // Java 8: default method
        default String greet() { return decorate("Hello, " + name()); }
        default String shout() {
            return decorate(("Hello, " + name() + "!").toUpperCase());
        }

        // Java 8: static method
        static Greeter of(String name) { return () -> name; }

        // Java 9: private method
        private String decorate(String s) { return "<< " + s + " >>"; }
    }

    public static void main(String[] args) {
        Greeter g = Greeter.of("Duke");
        System.out.println(g.greet());
        System.out.println(g.shout());

        List<String> names =
                new java.util.ArrayList<>(List.of("b", "c", "a"));
        names.sort(null);  // List.sort: a default method added in Java 8
        System.out.println(names);
    }
}
