# Samples for Chapter 13

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## CatchOrder.java

Source: [CatchOrder.java](CatchOrder.java)

**A catch for a subclass after its parent is unreachable and does not compile.** (any Java)

```bash
javac -d out CatchOrder.java
```

Expected output:

```text
CatchOrder.java:11: error: exception FileNotFoundException has already been caught
        } catch (FileNotFoundException e) {   // ...makes this unreachable
          ^
1 error
```

## Checked.java

Source: [Checked.java](Checked.java)

**A custom checked exception, and an unchecked one.** (any Java)

```bash
javac -d out Checked.java && java -cp out Checked
```

Expected output:

```text
declined: short by 1000 cents, balance 3000
unchecked: ArrayIndexOutOfBoundsException
```

## FinallyExit.java

Source: [FinallyExit.java](FinallyExit.java)

**System.exit stops the JVM, so finally does not run.** (any Java)

```bash
javac -d out FinallyExit.java && java -cp out FinallyExit
```

Expected output:

```text
in try
```

## FinallyReturn.java

Source: [FinallyReturn.java](FinallyReturn.java)

**Classic IQ: a return in finally wins; finally cannot change an already-evaluated return value.** (any Java)

```bash
javac -d out FinallyReturn.java && java -cp out FinallyReturn
```

Expected output:

```text
show()    = 60
counter() = 1
```

## HelpfulNpe.java

Source: [HelpfulNpe.java](HelpfulNpe.java)

**Helpful NullPointerException messages (Java 14+, on by default since 15).** (Java 15+)

```bash
javac -g -d out HelpfulNpe.java && java -cp out HelpfulNpe
```

Expected output:

```text
Cannot read field "city" because "order.customer.address" is null
```

## MultiCatch.java

Source: [MultiCatch.java](MultiCatch.java)

**Multi-catch: one handler for several exception types (Java 7).** (any Java)

```bash
javac -d out MultiCatch.java && java -cp out MultiCatch
```

Expected output:

```text
27
could not parse 'twenty-seven' (NumberFormatException)
could not parse 'com.example.Missing' (ClassNotFoundException)
```

## TryWithResources.java

Source: [TryWithResources.java](TryWithResources.java)

**try-with-resources closes in reverse order and records suppressed exceptions (Java 7; Java 9 form).** (Java 9+)

```bash
javac -d out TryWithResources.java && java -cp out TryWithResources
```

Expected output:

```text
open  db
open  file
working
close file
close db
caught:     work failed
suppressed: close failed: db
open  shared
using shared
close shared
```
