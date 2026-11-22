public class Unsigned {
    public static void main(String[] args) {
        int allOnes = -1;   // bit pattern 0xFFFFFFFF
        System.out.println(Integer.toUnsignedString(allOnes));          // Java 8
        System.out.println(Integer.toUnsignedLong(allOnes));
        System.out.println(Integer.divideUnsigned(allOnes, 2));
        System.out.println(Byte.toUnsignedInt((byte) 0xFF));            // reading raw bytes
        System.out.println(Integer.compareUnsigned(-1, 1) > 0);
    }
}
