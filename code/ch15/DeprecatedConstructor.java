public class DeprecatedConstructor {
    Integer old = new Integer(10);             // deprecated in Java 9, for removal since Java 16
    Integer modern = Integer.valueOf(10);
}
