# Samples for Chapter 12

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## CleanerDemo.java

Source: [CleanerDemo.java](CleanerDemo.java)

**Cleaner (Java 9) and try-with-resources as the replacement for finalize().** (Java 9+)

```bash
javac -d out CleanerDemo.java && java -cp out CleanerDemo
```

Expected output:

```text
using the file
deleted report.tmp
done
```

## Eligibility.java

Source: [Eligibility.java](Eligibility.java)

**Unreachable objects, including cycles, are collected; weak references watch it happen.** (any Java)

```bash
javac -d out Eligibility.java && java -XX:+UseSerialGC -cp out Eligibility
```

Expected output:

```text
plain object collected: true
island collected:       true
```

## FinalizeDemo.java

Source: [FinalizeDemo.java](FinalizeDemo.java)

**Overriding finalize() is deprecated for removal (Java 18+).** (Java 18+)

```bash
javac -Xlint:removal -d out FinalizeDemo.java
```

Expected output:

```text
FinalizeDemo.java:3: warning: [removal] finalize() in Object has been deprecated and marked for removal
    protected void finalize() {                   // deprecated (Java 9), for removal (Java 18)
                   ^
1 warning
```
