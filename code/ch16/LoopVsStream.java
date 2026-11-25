import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoopVsStream {
    record Dev(String name, String team, int years) { }

    static final List<Dev> DEVS = List.of(
            new Dev("Asha", "platform", 9), new Dev("Ravi", "mobile", 3),
            new Dev("Mia", "platform", 6), new Dev("Leo", "web", 5),
            new Dev("Zoe", "platform", 2));

    public static void main(String[] args) {
        // Java 7: say how
        List<String> seniors7 = new ArrayList<>();
        for (Dev d : DEVS) {
            if (d.years() >= 5) seniors7.add(d.name().toUpperCase());
        }
        Collections.sort(seniors7);
        System.out.println(seniors7);

        // Java 8+: say what
        List<String> seniors = DEVS.stream()
                .filter(d -> d.years() >= 5)
                .map(d -> d.name().toUpperCase())
                .sorted()
                .toList();  // Java 16 (collect(Collectors.toList()) in 8)
        System.out.println(seniors);

        int totalYears = DEVS.stream().mapToInt(Dev::years).sum();
        System.out.println("total years: " + totalYears);
    }
}
