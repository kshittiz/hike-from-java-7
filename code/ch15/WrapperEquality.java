import java.util.HashMap;
import java.util.Map;

public class WrapperEquality {
    public static void main(String[] args) {
        Long big = 1L;
        // 1 boxes to Integer
        System.out.println("Long(1).equals(1)  : " + big.equals(1));
        System.out.println("Long(1).equals(1L) : " + big.equals(1L));

        Map<Long, String> byId = new HashMap<>();
        byId.put(42L, "Asha");
        // Integer key: not found
        System.out.println("get(42)  : " + byId.get(42));
        System.out.println("get(42L) : " + byId.get(42L));
    }
}
