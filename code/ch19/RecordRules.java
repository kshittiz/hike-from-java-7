public class RecordRules {
    record Range(int low, int high) {
        Range {                                            // compact constructor: validate and normalize
            if (low > high) throw new IllegalArgumentException(low + " > " + high);
        }
        Range(int single) { this(single, single); }        // extra constructors must delegate
        int length() { return high - low; }                // ordinary methods are fine
        static Range of(int a, int b) { return new Range(Math.min(a, b), Math.max(a, b)); }
    }

    public static void main(String[] args) {
        System.out.println(new Range(7, 27) + " length " + new Range(7, 27).length());
        System.out.println(new Range(5));
        System.out.println(Range.of(27, 7));
        try {
            new Range(27, 7);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected: " + e.getMessage());
        }
    }
}
