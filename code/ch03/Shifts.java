public class Shifts {
    public static void main(String[] args) {
        System.out.println("  8 << 2   = " + (8 << 2));
        System.out.println("-16 >> 2   = " + (-16 >> 2));     // keeps the sign
        System.out.println("-16 >>> 28 = " + (-16 >>> 28));   // fills with zeros
        System.out.println("  1 << 33  = " + (1 << 33));      // int shifts use only 5 bits: 33 % 32 = 1
        System.out.println("  1L << 33 = " + (1L << 33));
        System.out.println("6 & 3 = " + (6 & 3) + ", 6 | 3 = " + (6 | 3) + ", 6 ^ 3 = " + (6 ^ 3) + ", ~6 = " + (~6));
    }
}
