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
