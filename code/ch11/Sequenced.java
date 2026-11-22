import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedMap;

public class Sequenced {
    public static void main(String[] args) {
        List<Integer> releases = new ArrayList<>(List.of(17, 21, 25));
        releases.addFirst(11);                          // Java 21
        releases.addLast(27);
        System.out.println(releases + " first=" + releases.getFirst() + " last=" + releases.getLast());
        System.out.println("reversed view: " + releases.reversed());

        var visited = new LinkedHashSet<>(List.of("Java 7", "Java 8", "Java 11"));
        System.out.println("last visited: " + visited.getLast());

        SequencedMap<String, Integer> lts = new LinkedHashMap<>();
        lts.put("Java 8", 2014);
        lts.put("Java 21", 2023);
        lts.put("Java 25", 2025);
        System.out.println("oldest: " + lts.firstEntry() + ", newest: " + lts.lastEntry());

        // Java 7 equivalents, for comparison
        System.out.println(releases.get(releases.size() - 1) + " " + new ArrayList<>(visited).get(visited.size() - 1));
    }
}
