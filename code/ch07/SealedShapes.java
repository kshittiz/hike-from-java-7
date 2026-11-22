public class SealedShapes {
    sealed interface Shape permits Circle, Square, Polygon { }    // Java 17
    record Circle(double radius) implements Shape { }
    record Square(double side) implements Shape { }
    non-sealed static class Polygon implements Shape { }          // reopened for extension
    static class Hexagon extends Polygon { }

    static String name(Shape s) {
        return switch (s) {                                        // no default: the compiler
            case Circle c  -> "circle r=" + c.radius();            // knows every Shape subtype
            case Square sq -> "square side=" + sq.side();
            case Polygon p -> "polygon (" + p.getClass().getSimpleName() + ")";
        };
    }

    public static void main(String[] args) {
        Shape[] shapes = { new Circle(1.5), new Square(2), new Hexagon() };
        for (Shape s : shapes) System.out.println(name(s));
    }
}
