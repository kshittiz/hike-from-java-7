import java.util.List;

public class Unnamed {
    public static void main(String[] args) {
        int count = 0;
        for (var _ : List.of("a", "b", "c")) {   // we only need the count
            count++;
        }
        System.out.println("count = " + count);

        try {
            Integer.parseInt("forty-two");
        } catch (NumberFormatException _) {  // we never read the exception
            System.out.println("not a number");
        }
    }
}
