# C. Good to Know: Look-Alikes

Some names in Java sound so alike that nearly everyone mixes them up at first, and interviewers know it. This appendix takes them one group at a time, interfaces, classes and keywords alike: what each one is for, a small everyday picture to hang it on, and a tested program that shows the difference.

> [!TIP]
> **Key points**
>
> - **`Comparable`** is a class saying "here is my **one** natural order" (`compareTo`). **`Comparator`** is an outside rule for **any other** order (`compare`). You can have many comparators, but only one natural order.
> - Never compare numbers with `a - b`: it can overflow. Use `Integer.compare(a, b)` or `Comparator.comparingInt`.
> - **`Iterable`** is something you can loop over: the **book**. **`Iterator`** is one pass over it: a **bookmark** that remembers its place and only moves forward.
> - Removing from a collection inside a for-each loop throws `ConcurrentModificationException`. Use `iterator.remove()` or `removeIf` (**Java 8**).
> - **`==`** asks "same object?"; **`equals`** asks "same meaning?". Override `equals` and `hashCode` together, or let a record do it.
> - **`Collection`** is the interface; **`Collections`** is a toolbox of static helpers. An unmodifiable *view* still shows changes; `List.copyOf` (**Java 10**) doesn't.
> - **`final`**, **`finally`** and **`finalize`** are unrelated; `finalize` is deprecated for removal (**Java 18**).
> - **`throws`** is the label on a method; **`throw`** is the act. **`Runnable`** is a chore; **`Callable`** (**Java 5**) returns an answer.
> - **`AutoCloseable`** (**Java 7**) is anything try-with-resources can close; **`Closeable`** is its older I/O cousin, which must tolerate a second `close()`.
> - **`HashMap`**: no order. **`LinkedHashMap`**: insertion order. **`TreeMap`**: sorted keys.

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

## == vs equals (and hashCode)

### The picture: two copies of the same book

Two people each own a copy of the same novel, same edition, same pages. Are they holding *the same book*? In one sense no: there are two physical copies. In another sense yes: the content is identical. Java keeps these two questions apart:

- **`==`** asks "are these the very same object?", meaning *one* copy in memory. For objects it compares references (Chapter 4).
- **`equals`** asks "do these two objects mean the same thing?" What counts as "the same" is up to the class. The default version, inherited from [`Object`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Object.html), only checks `==`, so you must write your own when content matters.
- **`hashCode`** is `equals`'s partner. Hash-based collections (`HashSet`, `HashMap`) first use the hash code to pick a "shelf", and only then call `equals` on what's on that shelf. The rule: **objects that are equal must have the same hash code.** Write one without the other and lookups quietly fail.

**Example:** [`code/appc/SameOrEqual.java`](../../code/appc/SameOrEqual.java) · [how to run it](../../code/appc/README.md#sameorequaljava) · any Java

```java
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SameOrEqual {
    // A class that forgets equals and hashCode
    static class TicketV1 {
        final String trail;
        final int seat;

        TicketV1(String trail, int seat) {
            this.trail = trail;
            this.seat = seat;
        }
    }

    // The Java 7 way: write both, using java.util.Objects
    static class TicketV2 {
        final String trail;
        final int seat;

        TicketV2(String trail, int seat) {
            this.trail = trail;
            this.seat = seat;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof TicketV2)) return false;
            TicketV2 t = (TicketV2) o;
            return seat == t.seat && trail.equals(t.trail);
        }

        @Override
        public int hashCode() {
            return Objects.hash(trail, seat);
        }
    }

    // The Java 16 way: a record writes both for you
    record Ticket(String trail, int seat) { }

    public static void main(String[] args) {
        TicketV1 a = new TicketV1("Summit", 7);
        TicketV1 b = new TicketV1("Summit", 7);
        System.out.println("a == b          : " + (a == b));
        System.out.println("a.equals(b)     : " + a.equals(b));

        Set<TicketV1> v1 = new HashSet<>(Set.of(a));
        System.out.println("V1 set has b?   : " + v1.contains(b));

        Set<TicketV2> v2 = new HashSet<>();
        v2.add(new TicketV2("Summit", 7));
        System.out.println("V2 set has it?  : "
                + v2.contains(new TicketV2("Summit", 7)));

        Set<Ticket> v3 = new HashSet<>();
        v3.add(new Ticket("Summit", 7));
        System.out.println("record set has? : "
                + v3.contains(new Ticket("Summit", 7)));
    }
}
```

```text
a == b          : false
a.equals(b)     : false
V1 set has b?   : false
V2 set has it?  : true
record set has? : true
```

`TicketV1` has no `equals` or `hashCode`, so two tickets for the same seat are strangers, and the set can't find the second one. `TicketV2` writes both, using `Objects.hash` (**Java 7**) to combine the fields. The record `Ticket` (**Java 16**, Chapter 19) generates both from its components, which is the simplest fix today.

## Collection vs Collections

### The picture: a bag and a toolbox

A **bag** is something that holds things. A **toolbox** is full of tools for working on bags: count what's inside, find the biggest item, seal the bag shut. Java has one of each, with names one letter apart:

- [`Collection`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Collection.html) (no *s*) is the **interface** that lists and sets share: `add`, `remove`, `size`, `contains`, `stream`. You create objects that *are* collections.
- [`Collections`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Collections.html) (with an *s*) is a **utility class** of static helper methods that *work on* collections: `sort`, `max`, `frequency`, `shuffle`, `unmodifiableList` and many more. You never create one.

**Example:** [`code/appc/ViewOrCopy.java`](../../code/appc/ViewOrCopy.java) · [how to run it](../../code/appc/README.md#vieworcopyjava) · any Java

```java
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
```

```text
size            : 4
water bottles   : 2
last A-Z        : water
view            : [boots, tent]
copy            : [boots]
view.add        : refused
```

The second half shows a detail that catches many people. `Collections.unmodifiableList` returns a read-only **view**: you can't change the list through it, but it still looks at the original, so when `tent` is added to `gear`, the view shows it. `List.copyOf` (**Java 10**) makes an independent read-only **copy** that never changes. Use a view when you want to share live data without allowing edits; use a copy when you want a snapshot.

## final vs finally vs finalize

### The picture: three words, three unrelated jobs

These three share a root word and nothing else:

| Word | What it is | What it means |
|---|---|---|
| `final` | A **keyword** on variables, methods and classes | "This can't change": a variable can't be reassigned, a method can't be overridden, a class can't be extended (Chapter 7) |
| `finally` | A **block** after `try` | "Run this no matter what", whether the `try` finished normally or threw (Chapter 13) |
| `finalize` | A **method** on `Object` | Once meant "run this before the garbage collector reclaims the object". Deprecated in **Java 9** and marked for removal in **Java 18** ([JEP 421](https://openjdk.org/jeps/421)) |

If an interviewer asks about all three, the modern answer for `finalize` is: don't use it. It ran at an unpredictable time, or never. To release resources, use try-with-resources; for a safety net, use `Cleaner` (Chapter 12).

## throw vs throws

### The picture: a warning label and the act itself

A box of glassware carries a label: *Fragile, may break*. That label is a promise about what *could* happen. Dropping the box is the event itself.

- **`throws`** is the label. It sits in a method's **signature** and tells callers which checked exceptions the method might pass to them. It does nothing by itself.
- **`throw`** is the act. It's a **statement** inside the method body that creates the problem right now, with a specific exception object.

**Example:** [`code/appc/ThrowOrThrows.java`](../../code/appc/ThrowOrThrows.java) · [how to run it](../../code/appc/README.md#throworthrowsjava) · any Java

```java
import java.io.IOException;

public class ThrowOrThrows {
    // "throws" is the warning label on the method's signature
    static String readPermit(String trail) throws IOException {
        if (trail.isBlank()) {
            // "throw" is the act of throwing, at the moment it happens
            throw new IOException("no permit for an unnamed trail");
        }
        return "permit for " + trail;
    }

    public static void main(String[] args) {
        for (String trail : new String[] {"Summit", " "}) {
            try {
                System.out.println(readPermit(trail));
            } catch (IOException e) {
                System.out.println("caught: " + e.getMessage());
            }
        }
    }
}
```

```text
permit for Summit
caught: no permit for an unnamed trail
```

A method can say `throws IOException` and never actually throw, and the compiler insists that callers be ready anyway. Unchecked exceptions (Chapter 13) can be thrown without any label.

## Runnable vs Callable

### The picture: a chore and a question

"Please water the plants" is a **chore**: you do it, and there's nothing to report back. "How many steps did we walk?" is a **question**: someone has to come back with an answer.

- [`Runnable`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Runnable.html) (**Java 1.0**) is a chore: its `run()` method returns nothing and can't throw checked exceptions.
- [`Callable`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/Callable.html) (**Java 5**) is a question: its `call()` method returns a value and may throw any exception.

**Example:** [`code/appc/RunnableOrCallable.java`](../../code/appc/RunnableOrCallable.java) · [how to run it](../../code/appc/README.md#runnableorcallablejava) · Java 19+

```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableOrCallable {
    public static void main(String[] args) throws Exception {
        // Runnable: a job with no answer (and no checked exceptions)
        Runnable wave = () -> System.out.println("waving from the summit");

        // Callable: a job that hands back an answer, and may throw
        Callable<Integer> countSteps = () -> 120 + 340 + 410;

        try (ExecutorService pool = Executors.newFixedThreadPool(2)) {
            Future<?> waved = pool.submit(wave);
            Future<Integer> steps = pool.submit(countSteps);
            waved.get();                     // nothing to return: null
            System.out.println("steps counted: " + steps.get());
        }
    }
}
```

```text
waving from the summit
steps counted: 870
```

Hand either one to an `ExecutorService` (Chapter 18) and you get back a `Future`, a receipt you can exchange for the result later. `get()` waits until the job is done. For a `Runnable` the "result" is just `null`; for a `Callable` it's the answer. A plain `Thread` only accepts a `Runnable`. The `try (ExecutorService pool = …)` form needs **Java 19** or later, when `ExecutorService` became `AutoCloseable`; closing the pool waits for its tasks to finish.

## Closeable vs AutoCloseable

### The picture: a lantern and a logbook

At the end of a night hike you switch off your lantern and close your logbook. Both need closing, but the logbook comes with a rule of its own: closing it a second time must change nothing.

- [`AutoCloseable`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/AutoCloseable.html) (**Java 7**) is the general idea: *anything* with a `close()` method that try-with-resources can call for you. Its `close()` may throw any `Exception`.
- [`Closeable`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/io/Closeable.html) (**Java 5**, older) is the I/O version used by streams, readers and writers. Its `close()` throws only `IOException`, and it carries an extra promise: closing something that's already closed must have no effect.

When Java 7 introduced try-with-resources, it made `Closeable` extend the new `AutoCloseable`, so every existing stream and reader worked in the new syntax right away.

**Example:** [`code/appc/CloseableOrAutoCloseable.java`](../../code/appc/CloseableOrAutoCloseable.java) · [how to run it](../../code/appc/README.md#closeableorautocloseablejava) · any Java

```java
import java.io.Closeable;

public class CloseableOrAutoCloseable {
    // AutoCloseable (Java 7): anything try-with-resources can close
    static class Lantern implements AutoCloseable {
        @Override
        public void close() {              // may throw any Exception
            System.out.println("lantern off");
        }
    }

    // Closeable (Java 5): the I/O version; close() throws IOException
    // and calling it twice must be harmless
    static class Logbook implements Closeable {
        private boolean closed;

        @Override
        public void close() {
            if (closed) return;            // second call does nothing
            closed = true;
            System.out.println("logbook closed");
        }
    }

    public static void main(String[] args) {
        try (Lantern lantern = new Lantern();
             Logbook log = new Logbook()) {
            System.out.println("walking in the dark");
            log.close();                   // closing early is allowed
        }                                  // closed in reverse order
        System.out.println("Closeable is an AutoCloseable: "
                + AutoCloseable.class.isAssignableFrom(Closeable.class));
    }
}
```

```text
walking in the dark
logbook closed
lantern off
Closeable is an AutoCloseable: true
```

At the end of the block, try-with-resources closes the resources in **reverse** order of opening: the logbook first, then the lantern. The logbook was already closed by hand, so that automatic second `close()` does nothing, exactly as `Closeable` promises. Only `lantern off` appears. For your own classes, implement `AutoCloseable` and declare the narrowest exception you can, or none at all.

## HashMap vs LinkedHashMap vs TreeMap

### The picture: three ways to keep a guest list

At a trailhead, three rangers keep a list of hikers and their finishing times:

- The first scribbles names wherever there's space on the page. Finding a name is quick, but the page has no order. That's [`HashMap`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/HashMap.html), whose documentation states plainly that it makes **no guarantee about order**, and that the order can even change over time.
- The second writes names **in the order hikers arrive**. That's [`LinkedHashMap`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/LinkedHashMap.html) (**Java 1.4**).
- The third keeps names **sorted alphabetically** and can answer "who's nearest to…?" questions. That's [`TreeMap`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/TreeMap.html), which sorts by the keys' natural order or a `Comparator` you supply (see *Comparable vs Comparator* above).

**Example:** [`code/appc/ThreeMaps.java`](../../code/appc/ThreeMaps.java) · [how to run it](../../code/appc/README.md#threemapsjava) · any Java

```java
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class ThreeMaps {
    public static void main(String[] args) {
        // the order hikers reached the summit, with their times
        String[] names = {"Ravi", "Asha", "Mia", "Leo"};
        int[] minutes = {95, 88, 102, 91};

        Map<String, Integer> arrival = new LinkedHashMap<>();
        Map<String, Integer> alphabetical = new TreeMap<>();
        for (int i = 0; i < names.length; i++) {
            arrival.put(names[i], minutes[i]);
            alphabetical.put(names[i], minutes[i]);
        }
        System.out.println("LinkedHashMap : " + arrival);
        System.out.println("TreeMap       : " + alphabetical);

        // TreeMap can also answer "nearest" questions
        TreeMap<Integer, String> byTime = new TreeMap<>();
        arrival.forEach((name, time) -> byTime.put(time, name));
        System.out.println("fastest       : " + byTime.firstEntry());
        System.out.println("first >= 90   : " + byTime.ceilingEntry(90));
    }
}
```

```text
LinkedHashMap : {Ravi=95, Asha=88, Mia=102, Leo=91}
TreeMap       : {Asha=88, Leo=91, Mia=102, Ravi=95}
fastest       : 88=Asha
first >= 90   : 91=Leo
```

| | `HashMap` | `LinkedHashMap` | `TreeMap` |
|---|---|---|---|
| Iteration order | None promised | Insertion order | Sorted by key |
| Speed of `get`/`put` | Fastest on average | Nearly as fast | Slower (grows with log of size) |
| `null` key | Allowed (one) | Allowed (one) | Not with natural ordering |
| Extra powers | — | Can be an LRU cache (access order) | `firstEntry`, `ceilingEntry`, `headMap`… |

Choose `HashMap` by default, `LinkedHashMap` when the order things were added matters (or for a simple cache), and `TreeMap` when you need sorted keys or range questions.

## String vs StringBuilder vs StringBuffer

### The picture: a printed sign, a whiteboard, and a whiteboard with a queue

A **printed sign** can't be changed; to fix a typo you print a new one. A **whiteboard** can be edited freely. A **whiteboard with a queue** in front of it only lets one person write at a time.

| | `String` | `StringBuilder` | `StringBuffer` |
|---|---|---|---|
| Can it change? | No, immutable (Chapter 14) | Yes | Yes |
| Safe for several threads at once? | Yes, because it never changes | No | Yes, its methods are synchronized |
| Since | Java 1.0 | **Java 5** | Java 1.0 |
| Use it for | Almost everything | Building text step by step | Rarely, mostly older code |

The JDK documentation recommends `StringBuilder` over `StringBuffer` wherever only one thread uses it, because it's faster. In practice, text being built is almost always local to one method, so `StringBuffer` is now mostly seen in old code. Chapter 14 shows both in action.

## Fail-fast vs snapshot iterators

### The picture: a strict tour guide and a photograph

A **strict tour guide** counts the group before each stop; if anyone joined or left without telling her, she stops the tour. Someone working from a **photograph** of the group simply walks through who was in the picture, whatever happens afterwards.

- Most ordinary collections (`ArrayList`, `HashMap`…) have **fail-fast** iterators. If the collection changes during a loop, except through the iterator's own `remove`, the iterator throws `ConcurrentModificationException` at its next step. This is a bug detector, not a locking mechanism: it's documented as a best effort.
- [`CopyOnWriteArrayList`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/CopyOnWriteArrayList.html) (**Java 5**) gives **snapshot** iterators: the loop walks the list exactly as it was when the loop started, never throws `ConcurrentModificationException`, and doesn't see later changes. Other concurrent collections, such as `ConcurrentHashMap`, are *weakly consistent*: they never throw, and may or may not show changes made during the loop.

**Example:** [`code/appc/FailFastOrSnapshot.java`](../../code/appc/FailFastOrSnapshot.java) · [how to run it](../../code/appc/README.md#failfastorsnapshotjava) · any Java

```java
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
```

```text
ArrayList      : ConcurrentModificationException
loop saw       : 7 8 11
list now holds : [7, 8, 11, 17]
```

The snapshot loop sees only `7 8 11`, but the list afterwards holds `17` too. The price is that every write copies the whole array, so `CopyOnWriteArrayList` suits lists that are read far more often than they change, like a list of event listeners.

## Summary

- `Comparable`: the one natural order, written by the class. `Comparator`: any other order, written by anyone. Since Java 8, build comparators from `comparing`, `thenComparing` and `reversed`.
- Compare numbers with `Integer.compare`, never with subtraction.
- `Iterable`: the thing you can loop over. `Iterator`: one forward-only pass with its own position.
- To remove while looping, use `iterator.remove()` or `removeIf`, not `list.remove` inside a for-each.
- `==` checks identity, `equals` checks meaning; `equals` and `hashCode` always go together.
- `Collection` holds things; `Collections` works on them. Views read through; copies don't.
- `final` = can't change, `finally` = always runs, `finalize` = don't use.
- `throws` warns, `throw` acts. `Runnable` does, `Callable` answers.
- Prefer `AutoCloseable` for your own resources; `Closeable` is for I/O and must be safe to close twice.
- Pick a map by the order you need: none, insertion, or sorted.
- `StringBuilder` for building text; `StringBuffer` only for old or genuinely shared code.
- Ordinary iterators fail fast; `CopyOnWriteArrayList` iterates a snapshot.
