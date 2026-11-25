public class StringPool {
    public static void main(String[] args) {
        // literal: from the string pool
        String a = "java";
        // same pooled object
        String b = "java";
        // a new object on the heap, every time
        String c = new String("java");
        // compile-time constant: folded to "java"
        String d = "ja" + "va";
        String part = "ja";
        String e = part + "va";  // computed at run time: a new object

        System.out.println("a == b          " + (a == b));
        System.out.println("a == c          " + (a == c));
        System.out.println("a == d          " + (a == d));
        System.out.println("a == e          " + (a == e));
        System.out.println("a == e.intern() " + (a == e.intern()));
        System.out.println("a.equals(c)     " + a.equals(c));
    }
}
