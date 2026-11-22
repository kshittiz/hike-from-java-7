public class UnnamedPatterns {
    sealed interface Shape permits Circle, Square, Rect { }
    record Circle(double r) implements Shape { }
    record Square(double side) implements Shape { }
    record Rect(double w, double h) implements Shape { }

    static String corners(Shape s) {
        return switch (s) {
            case Circle _             -> "no corners";        // Java 22: _ for an unused binding
            case Square _, Rect _     -> "four corners";      // several patterns, one case
        };
    }

    static boolean isWide(Shape s) {
        return s instanceof Rect(var w, _) && w > 10;         // ignore the height component
    }

    public static void main(String[] args) {
        System.out.println(corners(new Circle(1)) + ", " + corners(new Rect(2, 3)));
        System.out.println(isWide(new Rect(12, 1)) + " " + isWide(new Square(20)));
    }
}
