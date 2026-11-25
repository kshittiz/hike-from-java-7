import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ViewOrCopy {
    public static void main(String[] args) {
        // Collection: the interface every list and set shares
        Collection<String> packed = new ArrayList<>(
                List.of("map", "water", "snacks", "water"));
        System.out.println("size            : " + packed.size());

        // Collections: a toolbox of static helpers
        System.out.println("water bottles   : "
                + Collections.frequency(packed, "water"));
        System.out.println("last A-Z        : " + Collections.max(packed));

        // A read-only VIEW still shows later changes underneath...
        List<String> gear = new ArrayList<>(List.of("boots"));
        List<String> view = Collections.unmodifiableList(gear);
        List<String> copy = List.copyOf(gear);   // ...a copy doesn't (10)
        gear.add("tent");
        System.out.println("view            : " + view);
        System.out.println("copy            : " + copy);
        try {
            view.add("stove");
        } catch (UnsupportedOperationException e) {
            System.out.println("view.add        : refused");
        }
    }
}
