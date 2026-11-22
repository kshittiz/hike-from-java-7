import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRefs {
    public static void main(String[] args) {
        Function<String, Integer> parse   = Integer::parseInt;      // static method
        Function<String, String>  upper   = String::toUpperCase;    // instance method of the argument
        String prefix = "Java ";
        Function<String, String>  addTo   = prefix::concat;         // instance method of a fixed object
        Supplier<StringBuilder>   fresh   = StringBuilder::new;     // constructor
        BiFunction<Integer, Integer, Integer> max = Math::max;

        System.out.println(parse.apply("27") + 1);
        System.out.println(upper.apply("seven"));
        System.out.println(addTo.apply("27"));
        System.out.println(fresh.get().append("built").reverse());
        System.out.println(max.apply(7, 27));
    }
}
