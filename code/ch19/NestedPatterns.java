public class NestedPatterns {
    record Point(int x, int y) { }
    record Line(Point from, Point to) { }

    static String describe(Object o) {
        if (o instanceof Line(Point(var x1, var y1), Point(var x2, var y2)) && x1 == x2) {   // Java 21
            return "vertical line at x=" + x1 + " from y=" + y1 + " to y=" + y2;
        }
        if (o instanceof Line line) {                                                           // Java 16
            return "line " + line.from() + " -> " + line.to();
        }
        return "not a line";
    }

    public static void main(String[] args) {
        System.out.println(describe(new Line(new Point(3, 0), new Point(3, 9))));
        System.out.println(describe(new Line(new Point(0, 0), new Point(4, 4))));
        System.out.println(describe("7 to 27"));
    }
}
