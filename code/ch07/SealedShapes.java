public class SealedShapes {
    sealed interface Shape permits Circle, Square, Polygon { }    // Java 17
    record Circle(double radius) implements Shape { }
    record Square(double side) implements Shape { }
    // reopened for extension
    non-sealed static class Polygon implements Shape { }
    static class Hexagon extends Polygon { }

    static String name(Shape s) {
        // no default: the compiler
        return switch (s) {
            // knows every Shape subtype
            case Circle c  -> "circle r=" + c.radius();
            case Square sq -> "square side=" + sq.side();
            case Polygon p ->
                    "polygon (" + p.getClass().getSimpleName() + ")";
        };
    }

    public static void main(String[] args) {
        Shape[] shapes = { new Circle(1.5), new Square(2), new Hexagon() };
        for (Shape s : shapes) System.out.println(name(s));
    }
}
