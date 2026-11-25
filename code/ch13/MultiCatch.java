public class MultiCatch {
    static Object parse(String kind, String text) throws Exception {
        try {
            if (kind.equals("int")) return Integer.parseInt(text);
            return Class.forName(text);
        // Java 7: one handler
        } catch (NumberFormatException | ClassNotFoundException e) {
            return "could not parse '" + text + "' ("
                    + e.getClass().getSimpleName() + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(parse("int", "27"));
        System.out.println(parse("int", "twenty-seven"));
        System.out.println(parse("class", "com.example.Missing"));
    }
}
