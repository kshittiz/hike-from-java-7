# C. Good to Know: Look-Alike Interfaces

Some pairs of names in Java sound so alike that nearly everyone mixes them up at first, and interviewers know it. This appendix takes them one pair at a time: what each one is for, a small everyday picture to hang it on, and a tested program that shows the difference.

> [!TIP]
> **Key points**
>
> - **`Comparable`** is a class saying "here is my **one** natural order" (`compareTo`). **`Comparator`** is an outside rule for **any other** order (`compare`). You can have many comparators, but only one natural order.
> - Never compare numbers with `a - b`: it can overflow. Use `Integer.compare(a, b)` or `Comparator.comparingInt`.
> - **`Iterable`** is something you can loop over: the **book**. **`Iterator`** is one pass over it: a **bookmark** that remembers its place and only moves forward.
> - Removing from a collection inside a for-each loop throws `ConcurrentModificationException`. Use `iterator.remove()` or `removeIf` (**Java 8**).

## Comparable vs Comparator

### The picture: a music playlist

Open any music app and you'll find a playlist that can be sorted several ways: by title, by length, by how often you've played each song. One of those orders is the "obvious" one, the order the songs would fall into if you didn't ask for anything special. All the others are choices you make from the outside, by tapping a sort button.

Java models exactly that with two interfaces:

- [`Comparable`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Comparable.html) is implemented **by the class itself**. It defines the class's **natural order** through one method, `compareTo`. A class can have only one natural order. `String` sorts alphabetically, `Integer` numerically, `LocalDate` by date.
- [`Comparator`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Comparator.html) is a **separate object** that knows how to compare two things through its `compare` method. Anyone can write one, without touching the class, and you can have as many as you like.

Both answer the same question with a number: **negative** means "the first one comes before the second", **zero** means "they're equal in this order", **positive** means "the first one comes after".

**Example:** [`code/appc/Playlist.java`](../../code/appc/Playlist.java) · [how to run it](../../code/appc/README.md#playlistjava) · any Java

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Playlist {
    // The class itself defines ONE natural order for songs:
    // alphabetical by title.
    record Song(String title, int seconds, int plays)
            implements Comparable<Song> {
        @Override
        public int compareTo(Song other) {
            return title.compareTo(other.title);
        }
    }

    public static void main(String[] args) {
        List<Song> songs = new ArrayList<>(List.of(
                new Song("Summit", 307, 98),
                new Song("Base Camp", 266, 41),
                new Song("River Crossing", 295, 98),
                new Song("Night Hike", 185, 12)));

        // null means "use the natural order" (compareTo)
        songs.sort(null);
        System.out.println("by title    : " + titles(songs));

        // an order supplied from outside: shortest first
        songs.sort(Comparator.comparingInt(Song::seconds));
        System.out.println("by length   : " + titles(songs));

        // most played first; ties broken by title
        songs.sort(Comparator.comparingInt(Song::plays)
                             .reversed()
                             .thenComparing(Song::title));
        System.out.println("most played : " + titles(songs));
    }

    static List<String> titles(List<Song> songs) {
        return songs.stream().map(Song::title).toList();
    }
}
```

```text
by title    : [Base Camp, Night Hike, River Crossing, Summit]
by length   : [Night Hike, Base Camp, River Crossing, Summit]
most played : [River Crossing, Summit, Base Camp, Night Hike]
```

- `songs.sort(null)` means "no comparator, use the natural order", so Java calls the songs' own `compareTo` and sorts them by title.
- `Comparator.comparingInt(Song::seconds)` builds a comparator from a getter: "compare songs by their length".
- `.reversed()` flips an order, and `.thenComparing(...)` breaks ties. Two songs have 98 plays, so the title decides between them.

Collections that keep themselves sorted, such as `TreeSet` and `TreeMap`, use the natural order unless you hand them a `Comparator` when you create them. If a class has neither, adding its objects to a `TreeSet` fails at run time with a `ClassCastException`.

### What changed

- **Java 1.2** introduced both interfaces, along with the Collections Framework.
- **Java 5** made them generic: `Comparable<Song>` instead of comparing plain `Object`s and casting.
- **Java 8** turned `Comparator` from something you wrote as a whole anonymous class into something you *assemble*: `comparing`, `comparingInt`, `thenComparing`, `reversed`, `nullsFirst` and `nullsLast`. **Java 8** also added `List.sort`. In Java 7 you called `Collections.sort(list, comparator)` with a hand-written `compare` method.

> [!WARNING]
> **Comparing by subtraction**
>
> A shortcut you'll see in old code is `(a, b) -> a - b`. It looks right, and it usually works, until the numbers are far apart and the subtraction overflows:
>
> **Example:** [`code/appc/CompareBySubtraction.java`](../../code/appc/CompareBySubtraction.java) · [how to run it](../../code/appc/README.md#comparebysubtractionjava) · any Java
>
> ```java
> import java.util.ArrayList;
> import java.util.Comparator;
> import java.util.List;
>
> public class CompareBySubtraction {
>     public static void main(String[] args) {
>         List<Integer> numbers =
>                 new ArrayList<>(List.of(5, Integer.MIN_VALUE, -3));
>
>         // looks clever, but a - b can overflow
>         Comparator<Integer> bySubtraction = (a, b) -> a - b;
>         numbers.sort(bySubtraction);
>         System.out.println("a - b          : " + numbers);
>
>         // always correct
>         numbers.sort(Integer::compare);
>         System.out.println("Integer.compare: " + numbers);
>     }
> }
> ```
>
> ```text
> a - b          : [5, -2147483648, -3]
> Integer.compare: [-2147483648, -3, 5]
> ```
>
> `5 - Integer.MIN_VALUE` is too big for an `int`, wraps around to a negative number, and tells the sort that 5 is the *smallest* value. `Integer.compare` (Java 7) never overflows.

> [!IMPORTANT]
> **Keep compareTo consistent with equals**
>
> The `Comparable` documentation strongly recommends that `compareTo` returns 0 exactly when `equals` returns `true`. If it doesn't, sorted collections behave surprisingly. The famous exception is `BigDecimal`: `4.0` and `4.00` compare as equal, but `equals` says they're different, so a `TreeSet` keeps one of them while a `HashSet` keeps both.

## Iterable vs Iterator

### The picture: a book and a bookmark

A **book** is something you *can* read from start to finish. A **bookmark** is one particular reading of it: it remembers which page you're on, it only moves forward, and when it reaches the end, it's done. Two people can read the same book at the same time, each with their own bookmark, and neither disturbs the other.

- [`Iterable`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Iterable.html) is the book. It has one job: hand out a fresh iterator whenever someone asks, through its `iterator()` method. Anything `Iterable` can be used in a for-each loop.
- [`Iterator`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Iterator.html) is the bookmark. `hasNext()` asks "is there another page?", `next()` turns to it, and the optional `remove()` tears out the page you just read.

**Example:** [`code/appc/BookAndBookmark.java`](../../code/appc/BookAndBookmark.java) · [how to run it](../../code/appc/README.md#bookandbookmarkjava) · any Java

```java
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
```

```text
Java 7 > Java 8 > Java 11 > Java 17
Asha reaches Java 11; Ravi is still at Java 7
for-each + list.remove : ConcurrentModificationException
iterator.remove        : [8, 11]
removeIf               : [8, 11]
```

1. `Trail` implements only `Iterable`, and that alone makes `for (String stop : trail)` work. Behind the scenes, the compiler calls `trail.iterator()` and then `hasNext()`/`next()` until the end.
2. Asha and Ravi each ask the same trail for an iterator. Each one keeps its own position, so Asha can be three stops ahead without moving Ravi's bookmark.
3. A for-each loop hides its iterator. Removing from the list directly changes the list *behind the bookmark's back*, and `ArrayList`'s iterator notices on its next step and throws `ConcurrentModificationException` rather than risk skipping or repeating an element.
4. The safe way is to ask the bookmark itself to do the removing: `it.remove()`.
5. Since **Java 8**, `removeIf` does the whole loop for you in one line.

### What changed

- **Java 1.2** introduced `Iterator` with the Collections Framework.
- **Java 5** added `Iterable` and the enhanced for loop that uses it.
- **Java 8** added default methods to both: `Iterable.forEach`, which takes a lambda, and `Iterator.forEachRemaining`. It also added `Collection.removeIf`.
- Streams (Chapter 16) are the modern relative of iterators: a stream is also consumed once and only moves forward, but it describes *what* to do with the items instead of stepping through them one call at a time.

## Summary

- `Comparable`: the one natural order, written by the class. `Comparator`: any other order, written by anyone. Since Java 8, build comparators from `comparing`, `thenComparing` and `reversed`.
- Compare numbers with `Integer.compare`, never with subtraction.
- `Iterable`: the thing you can loop over. `Iterator`: one forward-only pass with its own position.
- To remove while looping, use `iterator.remove()` or `removeIf`, not `list.remove` inside a for-each.
