public class Parsing {
    public static void main(String[] args) {
        // String -> primitive
        int a = Integer.parseInt("27");
        // String -> wrapper (may be cached)
        Integer b = Integer.valueOf("27");
        // with a radix
        int bin = Integer.parseInt("11011", 2);
        System.out.println(a + b + bin);
        System.out.println(Integer.toBinaryString(27) + " "
                + Integer.toHexString(255) + " " + Integer.MAX_VALUE);
        System.out.println(Integer.compare(7, 27) + " "
                + Integer.sum(7, 20) + " " + Character.isDigit('7'));
        System.out.println(Double.parseDouble("2.7e1") + " "
                + Boolean.parseBoolean("TRUE"));
    }
}
