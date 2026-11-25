public class Narrowing {
    public static void main(String[] args) {
        int big = Integer.MAX_VALUE;
        // silent wrap-around
        System.out.println("MAX_VALUE + 1  = " + (big + 1));
        // keeps only the low 8 bits
        System.out.println("(byte) 130     = " + (byte) 130);
        // truncates, never rounds
        System.out.println("(int) 3.99     = " + (int) 3.99);
        System.out.println("(int) -3.99    = " + (int) -3.99);
        // clamps for double -> int
        System.out.println("(int) 1e20     = " + (int) 1e20);
        System.out.println("Math.addExact  = " + safeAdd(big, 1));
    }

    static String safeAdd(int a, int b) {
        try {
            return String.valueOf(Math.addExact(a, b));  // Java 8+
        } catch (ArithmeticException e) {
            return "ArithmeticException: " + e.getMessage();
        }
    }
}
