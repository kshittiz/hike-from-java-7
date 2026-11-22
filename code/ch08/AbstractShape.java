public class AbstractShape {
    static abstract class Shape {
        private final String name;
        Shape(String name) { this.name = name; }      // abstract classes can have constructors
        abstract double area();                       // no body: subclasses must provide it
        String describe() { return name + " with area " + String.format("%.2f", area()); }
    }
    static class Circle extends Shape {
        final double r;
        Circle(double r) { super("circle"); this.r = r; }
        @Override double area() { return Math.PI * r * r; }
    }

    public static void main(String[] args) {
        Shape s = new Circle(1);          // reference of the abstract type, object of a concrete one
        System.out.println(s.describe());
    }
}
