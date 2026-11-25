public class AbstractShape {
    static abstract class Shape {
        private final String name;
        // abstract classes can have constructors
        Shape(String name) { this.name = name; }
        // no body: subclasses must provide it
        abstract double area();
        String describe() {
            return name + " with area " + String.format("%.2f", area());
        }
    }
    static class Circle extends Shape {
        final double r;
        Circle(double r) { super("circle"); this.r = r; }
        @Override double area() { return Math.PI * r * r; }
    }

    public static void main(String[] args) {
        // reference of the abstract type, object of a concrete one
        Shape s = new Circle(1);
        System.out.println(s.describe());
    }
}
