import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericsDiamond {
    public static void main(String[] args) {
        // Java 5/6
        Map<String, List<Integer>> java6 =
                new HashMap<String, List<Integer>>();
        // Java 7: diamond
        Map<String, List<Integer>> java7 = new HashMap<>();
        var java10 = new HashMap<String, List<Integer>>();  // Java 10: var

        // Java 8: Map defaults
        java7.computeIfAbsent("asha", k -> new ArrayList<>()).add(90);
        java7.computeIfAbsent("asha", k -> new ArrayList<>()).add(85);
        java7.merge("count", List.of(1), (a, b) -> a);
        System.out.println(java7.get("asha") + " " + java6.isEmpty()
                + " " + java10.isEmpty());

        // Java 9
        Map<String, Integer> lengths = Map.of("seven", 5, "twenty-seven", 12);
        System.out.println(lengths.get("twenty-seven") + " "
                + lengths.getOrDefault("eight", 0));
    }
}
