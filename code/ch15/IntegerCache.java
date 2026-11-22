public class IntegerCache {
    public static void main(String[] args) {
        Integer a = 127, b = 127;          // autoboxing calls Integer.valueOf
        Integer c = 128, d = 128;
        System.out.println("127 == 127 : " + (a == b));      // cached object
        System.out.println("128 == 128 : " + (c == d));      // two different objects
        System.out.println("128 equals : " + c.equals(d));
        System.out.println("unboxed    : " + (c.intValue() == d));   // mixed: unboxes, compares values
    }
}
