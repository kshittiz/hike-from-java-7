# Samples for Chapter 14

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## ConcatOperator.java

Source: [ConcatOperator.java](ConcatOperator.java)

**How + mixes numbers, chars and strings, evaluated left to right.** (any Java)

```bash
javac -d out ConcatOperator.java && java -cp out ConcatOperator
```

Expected output:

```text
33
123
195c
abc
value: null
```

## Immutable.java

Source: [Immutable.java](Immutable.java)

**Strings are immutable; StringBuilder is the mutable alternative.** (any Java)

```bash
javac -d out Immutable.java && java -cp out Immutable
```

Expected output:

```text
hike
HIKE 27
72 ekih ym
```

## ModernStringMethods.java

Source: [ModernStringMethods.java](ModernStringMethods.java)

**String methods added in Java 8, 11 and 15.** (Java 15+)

```bash
javac -d out ModernStringMethods.java && java -cp out ModernStringMethods
```

Expected output:

```text
trim()  length 8
strip() length 4
true
ababab
3
7 -> 17 -> 27
Java 27 is here
```

## SplitVsTokenizer.java

Source: [SplitVsTokenizer.java](SplitVsTokenizer.java)

**StringTokenizer versus String.split with a regular expression.** (any Java)

```bash
javac -d out SplitVsTokenizer.java && java -cp out SplitVsTokenizer
```

Expected output:

```text
StringTokenizer: java|seven|twenty-seven
split(","):    java|  seven ||twenty-seven
split regex:     java|seven|twenty-seven
```

## StringPool.java

Source: [StringPool.java](StringPool.java)

**Literals share pooled objects; new String and run-time concatenation create new ones.** (any Java)

```bash
javac -d out StringPool.java && java -cp out StringPool
```

Expected output:

```text
a == b          true
a == c          false
a == d          true
a == e          false
a == e.intern() true
a.equals(c)     true
```

## TextBlocks.java

Source: [TextBlocks.java](TextBlocks.java)

**Text blocks (Java 15) versus escaped string concatenation.** (Java 15+)

```bash
javac -d out TextBlocks.java && java -cp out TextBlocks
```

Expected output:

```text
{
  "title": "A Hike from Java 7",
  "chapters": 20
}
same text: true
SELECT name
  FROM releases
 WHERE lts = true ORDER BY year
```
