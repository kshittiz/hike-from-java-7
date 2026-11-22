public class DeprecatedConstructor {
    Integer old = new Integer(10);             // deprecated since Java 9
    Integer modern = Integer.valueOf(10);
}
