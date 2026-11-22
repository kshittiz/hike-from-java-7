# Samples for chapter 19

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## NestedPatterns.java

Source: [NestedPatterns.java](NestedPatterns.java)

**Nested record patterns with var (Java 21+).** (Java 21+)

```bash
javac -d out NestedPatterns.java && java -cp out NestedPatterns
```

Expected output:

```text
vertical line at x=3 from y=0 to y=9
line Point[x=0, y=0] -> Point[x=4, y=4]
not a line
```

## Payments.java

Source: [Payments.java](Payments.java)

**Sealed interface + records + record patterns with guards in an exhaustive switch (Java 21+).** (Java 21+)

```bash
javac -d out Payments.java && java -cp out Payments
```

Expected output:

```text
card *4242 2500
card *1881 (needs approval)
upi asha@bank 900
cash 100
```

## RecordRules.java

Source: [RecordRules.java](RecordRules.java)

**A record with a compact constructor, an extra constructor and methods (Java 16+).** (Java 16+)

```bash
javac -d out RecordRules.java && java -cp out RecordRules
```

Expected output:

```text
Range[low=7, high=27] length 20
Range[low=5, high=5]
Range[low=7, high=27]
rejected: 27 > 7
```

## UnnamedPatterns.java

Source: [UnnamedPatterns.java](UnnamedPatterns.java)

**Unnamed patterns as a Java 21 preview feature.** (Java 21 only)

```bash
javac --enable-preview --release 21 -d out UnnamedPatterns.java && java --enable-preview -cp out UnnamedPatterns
```

Expected output:

```text
no corners, four corners
true false
```

**Unnamed patterns with _ (Java 22+).** (Java 22+)

```bash
javac -d out UnnamedPatterns.java && java -cp out UnnamedPatterns
```

Expected output:

```text
no corners, four corners
true false
```
