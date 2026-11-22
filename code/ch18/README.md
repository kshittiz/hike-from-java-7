# Samples for chapter 18

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## CompletableDemo.java

Source: [CompletableDemo.java](CompletableDemo.java)

**Combining asynchronous results with CompletableFuture (Java 8).** (Java 8+)

```bash
javac -d out CompletableDemo.java && java -cp out CompletableDemo
```

Expected output:

```text
$49.50
```

## ExecutorsDemo.java

Source: [ExecutorsDemo.java](ExecutorsDemo.java)

**A fixed thread pool running Callables (Java 5).** (Java 8+)

```bash
javac -d out ExecutorsDemo.java && java -cp out ExecutorsDemo
```

Expected output:

```text
total pages: 2100
```

## SafeCounting.java

Source: [SafeCounting.java](SafeCounting.java)

**A race condition, and two fixes: synchronized and AtomicInteger.** (any Java)

```bash
javac -d out SafeCounting.java && java -cp out SafeCounting
```

Expected output:

```text
synchronized: 400000
atomic:       400000
unsafe <= 400000: true
```

## ScopedValues.java

Source: [ScopedValues.java](ScopedValues.java)

**Scoped values as a Java 21 preview feature.** (Java 21 only)

```bash
javac --enable-preview --release 21 -d out ScopedValues.java && java --enable-preview -cp out ScopedValues
```

Expected output:

```text
asha opened chapter 18
asha ran the samples
anonymous left
```

**Scoped values: per-call context without ThreadLocal (Java 25+).** (Java 25+)

```bash
javac -d out ScopedValues.java && java -cp out ScopedValues
```

Expected output:

```text
asha opened chapter 18
asha ran the samples
anonymous left
```

## VirtualThreads.java

Source: [VirtualThreads.java](VirtualThreads.java)

**Ten thousand blocking tasks, one virtual thread each (Java 21+).** (Java 21+)

```bash
javac -d out VirtualThreads.java && java -cp out VirtualThreads
```

Expected output:

```text
10000 tasks finished
virtual? true
```
