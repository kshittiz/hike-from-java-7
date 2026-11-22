public class ModernStringMethods {
    public static void main(String[] args) {
        String padded = "\u2003 Java \u2003";             // em spaces: Unicode whitespace
        System.out.println("trim()  length " + padded.trim().length());    // trim removes only chars <= ' '
        System.out.println("strip() length " + padded.strip().length());   // Java 11: Unicode-aware
        System.out.println("   ".isBlank());                // Java 11
        System.out.println("ab".repeat(3));                 // Java 11
        System.out.println("one\ntwo\nthree".lines().count());   // Java 11
        System.out.println(String.join(" -> ", "7", "17", "27"));  // Java 8
        System.out.println("Java %d is %s".formatted(27, "here")); // Java 15
    }
}
