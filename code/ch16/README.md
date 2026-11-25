# Samples for Chapter 16

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## Grouping.java

Source: [Grouping.java](Grouping.java)

**groupingBy, partitioningBy and joining collectors.** (Java 16+)

```bash
javac -d out Grouping.java && java -cp out Grouping
```

Expected output:

```text
{2014=1, 2018=1, 2021=1, 2023=1, 2024=2, 2025=1, 2026=2}
LTS [8, 11, 17, 21, 25]  non-LTS [22, 23, 26, 27]
[Java 8, Java 11, Java 17, Java 21, Java 25]
```

## Laziness.java

Source: [Laziness.java](Laziness.java)

**Streams are lazy and short-circuit: elements flow one at a time.** (Java 8+)

```bash
javac -d out Laziness.java && java -cp out Laziness
```

Expected output:

```text
  looking at seven
  looking at eleven
  looking at seventeen
  looking at twenty-one
found TWENTY-ONE
```

## LoopVsStream.java

Source: [LoopVsStream.java](LoopVsStream.java)

**The same query as a Java 7 loop and as a stream pipeline.** (Java 16+)

```bash
javac -d out LoopVsStream.java && java -cp out LoopVsStream
```

Expected output:

```text
[ASHA, LEO, MIA]
[ASHA, LEO, MIA]
total years: 25
```

## NewerStreamOps.java

Source: [NewerStreamOps.java](NewerStreamOps.java)

**takeWhile, dropWhile, iterate (Java 9), teeing (12), mapMulti and toList (16).** (Java 16+)

```bash
javac -d out NewerStreamOps.java && java -cp out NewerStreamOps
```

Expected output:

```text
[2, 4, 6]
[7, 8, 10]
[7, 17, 27]
17.0
[java, JAVA, seven, SEVEN]
```

## OptionalDemo.java

Source: [OptionalDemo.java](OptionalDemo.java)

**Optional (Java 8) with map, orElse, isEmpty (11) and ifPresentOrElse (9).** (Java 11+)

```bash
javac -d out OptionalDemo.java && java -cp out OptionalDemo
```

Expected output:

```text
ASHA@EXAMPLE.COM
no email
true
send to asha@example.com
```

## StepTracker.java

Source: [StepTracker.java](StepTracker.java)

**Gatherers on a walk: fixed windows, sliding windows, a running total and a custom gatherer that drops repeats.** (Java 24+)

```bash
javac -d out StepTracker.java && java -cp out StepTracker
```

Expected output:

```text
half-hour totals : [870, 650, 380]
rolling 30 min   : [870, 840, 500, 650, 940]
steps so far     : [120, 460, 870, 960, 960, 1520, 1900]
heart rate       : [72, 75, 80, 72]
```

## WindowsTheOldWay.java

Source: [WindowsTheOldWay.java](WindowsTheOldWay.java)

**Before gatherers: grouping into windows meant leaving the stream for a hand-written loop.** (any Java)

```bash
javac -d out WindowsTheOldWay.java && java -cp out WindowsTheOldWay
```

Expected output:

```text
half-hour totals : [870, 650, 380]
```
