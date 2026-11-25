import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompareBySubtraction {
    public static void main(String[] args) {
        List<Integer> numbers =
                new ArrayList<>(List.of(5, Integer.MIN_VALUE, -3));

        // looks clever, but a - b can overflow
        Comparator<Integer> bySubtraction = (a, b) -> a - b;
        numbers.sort(bySubtraction);
        System.out.println("a - b          : " + numbers);

        // always correct
        numbers.sort(Integer::compare);
        System.out.println("Integer.compare: " + numbers);
    }
}
