import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRefs {
    public static void main(String[] args) {
        // static method
        Function<String, Integer> parse   = Integer::parseInt;
        // instance method of the argument
        Function<String, String>  upper   = String::toUpperCase;
        String prefix = "Java ";
        // instance method of a fixed object
        Function<String, String>  addTo   = prefix::concat;
        // constructor
        Supplier<StringBuilder>   fresh   = StringBuilder::new;
        BiFunction<Integer, Integer, Integer> max = Math::max;

        System.out.println(parse.apply("27") + 1);
        System.out.println(upper.apply("seven"));
        System.out.println(addTo.apply("27"));
        System.out.println(fresh.get().append("built").reverse());
        System.out.println(max.apply(7, 27));
    }
}
