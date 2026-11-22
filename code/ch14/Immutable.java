public class Immutable {
    public static void main(String[] args) {
        String s = "notes";
        s.concat(" 2013");                   // returns a new String, which we discard
        s.toUpperCase();
        System.out.println(s);               // unchanged

        s = s.concat(" 2013").toUpperCase(); // keep the result
        System.out.println(s);

        StringBuilder sb = new StringBuilder("notes");   // mutable (Java 5)
        sb.append(" 2013").insert(0, "my ").reverse();
        System.out.println(sb);
    }
}
