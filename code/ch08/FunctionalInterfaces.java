import java.util.function.Predicate;

public class FunctionalInterfaces {
    @FunctionalInterface
    interface Discount {                         // exactly one abstract method
        double apply(double price);
    }

    public static void main(String[] args) {
        Discount java7Style = new Discount() {   // Java 7: anonymous class
            @Override public double apply(double price) { return price * 0.9; }
        };
        Discount java8Style = price -> price * 0.9;     // Java 8: lambda

        System.out.println(java7Style.apply(200) + " " + java8Style.apply(200));

        Predicate<String> longWord = w -> w.length() > 4;
        System.out.println(longWord.and(w -> w.startsWith("j")).test("javadoc"));
    }
}
