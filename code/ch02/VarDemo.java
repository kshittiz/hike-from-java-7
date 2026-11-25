import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VarDemo {
    public static void main(String[] args) {
        // Java 7: the type is written twice
        // (the diamond <> saved us a little)
        Map<String, List<Integer>> scores7 = new HashMap<>();

        // Java 10: the compiler infers the type from the right-hand side
        var scores = new HashMap<String, List<Integer>>();
        scores.put("asha", new ArrayList<>(List.of(90, 85)));

        for (var entry : scores.entrySet()) {
            var name = entry.getKey();          // String
            var marks = entry.getValue();       // List<Integer>
            System.out.println(name + " -> " + marks);
        }

        var n = 10;        // int, not Integer and not long
        var d = 10.0;      // double
        var big = 10L;     // long
        System.out.println(kind(n) + " " + kind(d) + " " + kind(big));
        System.out.println(scores7.isEmpty());
    }

    static String kind(int x)    { return "int"; }
    static String kind(long x)   { return "long"; }
    static String kind(double x) { return "double"; }
}
