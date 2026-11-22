public class ShortCircuit {
    static boolean check(String name, boolean result) {
        System.out.println("  evaluated " + name);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("&& (short-circuit):");
        if (check("left", false) && check("right", true)) { }

        System.out.println("&  (always both):");
        if (check("left", false) & check("right", true)) { }

        String s = null;
        System.out.println("safe: " + (s != null && s.length() > 3));
    }
}
