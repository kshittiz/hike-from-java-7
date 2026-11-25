import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastOrSnapshot {
    public static void main(String[] args) {
        // fail-fast: ArrayList's iterator stops at the first sign
        // that the list changed underneath it
        List<String> plain = new ArrayList<>(List.of("7", "8", "11"));
        try {
            for (String v : plain) {
                if (v.equals("7")) plain.add("17");
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ArrayList      : "
                    + e.getClass().getSimpleName());
        }

        // snapshot: CopyOnWriteArrayList's iterator walks a copy taken
        // when the loop started, so changes don't disturb it
        List<String> safe = new CopyOnWriteArrayList<>(
                List.of("7", "8", "11"));
        StringBuilder seen = new StringBuilder();
        for (String v : safe) {
            if (v.equals("7")) safe.add("17");
            seen.append(v).append(' ');
        }
        System.out.println("loop saw       : " + seen.toString().trim());
        System.out.println("list now holds : " + safe);
    }
}
