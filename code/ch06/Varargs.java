public class Varargs {
    static String sum(int a, int b)   { return "sum(int, int) = " + (a + b); }
    static String sum(int... values) {                          // Java 5
        int total = 0;
        for (int v : values) total += v;
        return "sum(int...) over " + values.length + " values = " + total;
    }

    public static void main(String[] args) {
        System.out.println(sum(1, 2));        // fixed-arity wins when it fits
        System.out.println(sum(1, 2, 3));
        System.out.println(sum());            // zero arguments is fine
        // Java 8
        System.out.println(String.join(", ", "varargs", "in", "the", "JDK"));
    }
}
