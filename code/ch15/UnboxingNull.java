import java.util.HashMap;
import java.util.Map;

public class UnboxingNull {
    public static void main(String[] args) {
        Map<String, Integer> stock = new HashMap<>();
        stock.put("coffee", 12);
        try {
            // get returns null; unboxing it throws
            int tea = stock.get("tea");
            System.out.println(tea);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        int safe = stock.getOrDefault("tea", 0);          // Java 8
        System.out.println("tea = " + safe);
    }
}
