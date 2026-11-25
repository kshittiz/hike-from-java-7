import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingJourney {
    record Dev(String name, int years) { }

    public static void main(String[] args) {
        List<Dev> devs = new ArrayList<>(Arrays.asList(
                new Dev("Asha", 9), new Dev("Ravi", 3),
                new Dev("Mia", 9), new Dev("Leo", 5)));

        // Java 7: an anonymous Comparator
        Collections.sort(devs, new Comparator<Dev>() {
            @Override public int compare(Dev a, Dev b) {
                return Integer.compare(b.years(), a.years());
            }
        });
        System.out.println("Java 7  " + devs);

        // Java 8: a lambda
        devs.sort((a, b) -> a.name().compareTo(b.name()));
        System.out.println("lambda  " + devs);

        // Java 8: comparator combinators and method references
        devs.sort(Comparator.comparingInt(Dev::years).reversed()
                .thenComparing(Dev::name));
        System.out.println("combin. " + devs);
    }
}
