# Samples for chapter 11

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## ArrayBasics.java

Source: [ArrayBasics.java](ArrayBasics.java)

**Default values, length, an array of arrays, an anonymous array, and an out-of-bounds index.** (any Java)

```bash
javac -d out ArrayBasics.java && java -cp out ArrayBasics
```

Expected output:

```text
[0, 0, 0] [null, null]
length = 4
[[1], [2, 2], [3, 3, 3]]
60
Index 4 out of bounds for length 4
```

## ArrayCovariance.java

Source: [ArrayCovariance.java](ArrayCovariance.java)

**Arrays are covariant and check element types at run time.** (any Java)

```bash
javac -d out ArrayCovariance.java && java -cp out ArrayCovariance
```

Expected output:

```text
ArrayStoreException: java.lang.Integer
```

## FixedAndImmutable.java

Source: [FixedAndImmutable.java](FixedAndImmutable.java)

**Arrays.asList is fixed-size; List.of (Java 9) is unmodifiable and rejects null.** (Java 9+)

```bash
javac -d out FixedAndImmutable.java && java -cp out FixedAndImmutable
```

Expected output:

```text
Arrays.asList set: ok
Arrays.asList add: UnsupportedOperationException
List.of set     : UnsupportedOperationException
List.of null    : NullPointerException
ArrayList add   : ok
[z, b] [a, b] [a, b, c]
```

## GenericsDiamond.java

Source: [GenericsDiamond.java](GenericsDiamond.java)

**From explicit type arguments to the diamond (Java 7), Map defaults (Java 8), Map.of (Java 9) and var (Java 10).** (Java 10+)

```bash
javac -d out GenericsDiamond.java && java -cp out GenericsDiamond
```

Expected output:

```text
[90, 85] true true
12 0
```

## Sequenced.java

Source: [Sequenced.java](Sequenced.java)

**Sequenced collections: getFirst, getLast, addFirst, reversed, firstEntry (Java 21+).** (Java 21+)

```bash
javac -d out Sequenced.java && java -cp out Sequenced
```

Expected output:

```text
[11, 17, 21, 25, 27] first=11 last=27
reversed view: [27, 25, 21, 17, 11]
last visited: Java 11
oldest: Java 8=2014, newest: Java 25=2025
27 Java 11
```
