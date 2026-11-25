import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class BookAndBookmark {
    // The "book": an Iterable can hand out any number of iterators.
    static class Trail implements Iterable<String> {
        private final String[] stops;

        Trail(String... stops) {
            this.stops = stops;
        }

        @Override
        // a brand-new "bookmark" every time it is called
        public Iterator<String> iterator() {
            return new Iterator<>() {
                // each bookmark keeps its own place
                private int position = 0;

                @Override
                public boolean hasNext() {
                    return position < stops.length;
                }

                @Override
                public String next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    return stops[position++];
                }
            };
        }
    }

    public static void main(String[] args) {
        Trail trail = new Trail("Java 7", "Java 8", "Java 11", "Java 17");

        // 1. Anything Iterable works in a for-each loop.
        StringJoiner walk = new StringJoiner(" > ");
        for (String stop : trail) walk.add(stop);
        System.out.println(walk);

        // 2. Two iterators over one trail move independently.
        Iterator<String> asha = trail.iterator();
        Iterator<String> ravi = trail.iterator();
        asha.next();
        asha.next();
        System.out.println("Asha reaches " + asha.next()
                + "; Ravi is still at " + ravi.next());

        // 3. Removing from the list inside a for-each loop
        //    breaks the hidden iterator of the loop.
        List<String> releases =
                new ArrayList<>(List.of("8", "9", "10", "11"));
        try {
            for (String r : releases) {
                if (r.equals("9")) releases.remove(r);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("for-each + list.remove : "
                    + e.getClass().getSimpleName());
        }

        // 4. Let the iterator remove it...
        List<String> lts = new ArrayList<>(List.of("8", "9", "10", "11"));
        for (Iterator<String> it = lts.iterator(); it.hasNext(); ) {
            String r = it.next();
            if (r.equals("9") || r.equals("10")) it.remove();
        }
        System.out.println("iterator.remove        : " + lts);

        // 5. ...or let removeIf do the looping for you (Java 8).
        List<String> again = new ArrayList<>(List.of("8", "9", "10", "11"));
        again.removeIf(r -> r.equals("9") || r.equals("10"));
        System.out.println("removeIf               : " + again);
    }
}
