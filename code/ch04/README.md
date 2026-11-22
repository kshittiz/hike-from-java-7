# Samples for chapter 4

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## Money7.java

Source: [Money7.java](Money7.java)

**A value class written by hand, Java 7 style, with java.util.Objects.** (any Java)

```bash
javac -d out Money7.java && java -cp out Money7
```

Expected output:

```text
Money7[currency=USD, cents=1250]  ==: false  equals: true
```

## MoneyRecord.java

Source: [MoneyRecord.java](MoneyRecord.java)

**The same value class as a record (Java 16+).** (Java 16+)

```bash
javac -d out MoneyRecord.java && java -cp out MoneyRecord
```

Expected output:

```text
Money[currency=USD, cents=1250]  ==: false  equals: true
same hashCode: true
accessor: 1250
```

## Point.java

Source: [Point.java](Point.java)

**Printing an object shows ClassName@hashCode in hex, not a memory address.** (any Java)

```bash
javac -d out Point.java PrintReference.java && java -cp out PrintReference
```

Expected output:

```text
looks like Point@<hex>: true
hex is the hash code:   true
```

**Two reference variables, one object; reassigning a reference.** (any Java)

```bash
javac -d out Point.java References.java && java -cp out References
```

Expected output:

```text
p.x = 10
p.x = 10, q.x = 99
p == q ? false
```

## PrintReference.java

Source: [PrintReference.java](PrintReference.java)

**Printing an object shows ClassName@hashCode in hex, not a memory address.** (any Java)

```bash
javac -d out Point.java PrintReference.java && java -cp out PrintReference
```

Expected output:

```text
looks like Point@<hex>: true
hex is the hash code:   true
```

## References.java

Source: [References.java](References.java)

**Two reference variables, one object; reassigning a reference.** (any Java)

```bash
javac -d out Point.java References.java && java -cp out References
```

Expected output:

```text
p.x = 10
p.x = 10, q.x = 99
p == q ? false
```

## Shadowing.java

Source: [Shadowing.java](Shadowing.java)

**Data shadowing and the this keyword.** (any Java)

```bash
javac -d out Shadowing.java && java -cp out Shadowing
```

Expected output:

```text
parameter = 10, field = 1
field now = 10
```
