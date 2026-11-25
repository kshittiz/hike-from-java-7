public class PatternSwitch {
    static String describe(Object value) {
        return switch (value) {                                   // Java 21
            case null                  -> "nothing";
            // guard with 'when'
            case Integer i when i < 0  -> "a negative int " + i;
            case Integer i             -> "an int " + i;
            case String s              -> "a string of length " + s.length();
            case int[] arr             -> "an int array of " + arr.length;
            default                    -> "something else: "
                    + value.getClass().getSimpleName();
        };
    }

    public static void main(String[] args) {
        Object[] samples =
                { 42, -7, "Java", new int[] {1, 2, 3}, 3.14, null };
        for (Object o : samples) {
            System.out.println(describe(o));
        }
    }
}
