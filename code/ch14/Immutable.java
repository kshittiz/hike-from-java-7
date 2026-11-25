public class Immutable {
    public static void main(String[] args) {
        String s = "hike";
        s.concat(" 27");  // returns a new String, which we discard
        s.toUpperCase();
        System.out.println(s);               // unchanged

        s = s.concat(" 27").toUpperCase(); // keep the result
        System.out.println(s);

        StringBuilder sb = new StringBuilder("hike");   // mutable (Java 5)
        sb.append(" 27").insert(0, "my ").reverse();
        System.out.println(sb);
    }
}
