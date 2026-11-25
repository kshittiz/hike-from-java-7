# Samples for Appendix C

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## BookAndBookmark.java

Source: [BookAndBookmark.java](BookAndBookmark.java)

**An Iterable hands out independent Iterators; how to remove elements safely while looping.** (any Java)

```bash
javac -d out BookAndBookmark.java && java -cp out BookAndBookmark
```

Expected output:

```text
Java 7 > Java 8 > Java 11 > Java 17
Asha reaches Java 11; Ravi is still at Java 7
for-each + list.remove : ConcurrentModificationException
iterator.remove        : [8, 11]
removeIf               : [8, 11]
```

## CloseableOrAutoCloseable.java

Source: [CloseableOrAutoCloseable.java](CloseableOrAutoCloseable.java)

**Both work with try-with-resources; Closeable is the I/O kind and must tolerate a second close().** (any Java)

```bash
javac -d out CloseableOrAutoCloseable.java && java -cp out CloseableOrAutoCloseable
```

Expected output:

```text
walking in the dark
logbook closed
lantern off
Closeable is an AutoCloseable: true
```

## CompareBySubtraction.java

Source: [CompareBySubtraction.java](CompareBySubtraction.java)

**Comparing with a - b overflows and sorts wrongly; Integer.compare is always correct.** (any Java)

```bash
javac -d out CompareBySubtraction.java && java -cp out CompareBySubtraction
```

Expected output:

```text
a - b          : [5, -2147483648, -3]
Integer.compare: [-2147483648, -3, 5]
```

## FailFastOrSnapshot.java

Source: [FailFastOrSnapshot.java](FailFastOrSnapshot.java)

**ArrayList's iterator fails fast; CopyOnWriteArrayList's iterator walks a snapshot.** (any Java)

```bash
javac -d out FailFastOrSnapshot.java && java -cp out FailFastOrSnapshot
```

Expected output:

```text
ArrayList      : ConcurrentModificationException
loop saw       : 7 8 11
list now holds : [7, 8, 11, 17]
```

## Playlist.java

Source: [Playlist.java](Playlist.java)

**Comparable gives songs one natural order; Comparators supply any other order from outside.** (any Java)

```bash
javac -d out Playlist.java && java -cp out Playlist
```

Expected output:

```text
by title    : [Base Camp, Night Hike, River Crossing, Summit]
by length   : [Night Hike, Base Camp, River Crossing, Summit]
most played : [River Crossing, Summit, Base Camp, Night Hike]
```

## RunnableOrCallable.java

Source: [RunnableOrCallable.java](RunnableOrCallable.java)

**Runnable returns nothing; Callable returns a result through a Future.** (Java 19+)

```bash
javac -d out RunnableOrCallable.java && java -cp out RunnableOrCallable
```

Expected output:

```text
waving from the summit
steps counted: 870
```

## SameOrEqual.java

Source: [SameOrEqual.java](SameOrEqual.java)

**== compares identity; equals compares content; HashSet needs equals and hashCode together.** (any Java)

```bash
javac -d out SameOrEqual.java && java -cp out SameOrEqual
```

Expected output:

```text
a == b          : false
a.equals(b)     : false
V1 set has b?   : false
V2 set has it?  : true
record set has? : true
```

## ThreeMaps.java

Source: [ThreeMaps.java](ThreeMaps.java)

**LinkedHashMap keeps insertion order, TreeMap keeps keys sorted and answers nearest-key questions.** (any Java)

```bash
javac -d out ThreeMaps.java && java -cp out ThreeMaps
```

Expected output:

```text
LinkedHashMap : {Ravi=95, Asha=88, Mia=102, Leo=91}
TreeMap       : {Asha=88, Leo=91, Mia=102, Ravi=95}
fastest       : 88=Asha
first >= 90   : 91=Leo
```

## ThrowOrThrows.java

Source: [ThrowOrThrows.java](ThrowOrThrows.java)

**throws labels a method; throw is the act of throwing.** (any Java)

```bash
javac -d out ThrowOrThrows.java && java -cp out ThrowOrThrows
```

Expected output:

```text
permit for Summit
caught: no permit for an unnamed trail
```

## ViewOrCopy.java

Source: [ViewOrCopy.java](ViewOrCopy.java)

**Collection is the interface, Collections the toolbox; an unmodifiable view sees changes, a copy does not.** (any Java)

```bash
javac -d out ViewOrCopy.java && java -cp out ViewOrCopy
```

Expected output:

```text
size            : 4
water bottles   : 2
last A-Z        : water
view            : [boots, tent]
copy            : [boots]
view.add        : refused
```
