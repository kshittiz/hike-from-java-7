# Samples for chapter 17

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## Formatting.java

Source: [Formatting.java](Formatting.java)

**DateTimeFormatter parsing and formatting; invalid dates are rejected.** (Java 8+)

```bash
javac -d out Formatting.java && java -cp out Formatting
```

Expected output:

```text
01 Jul 2013
2026-11-21
2013-07-01
rejected: Text '2026-02-30' could not be parsed: Invalid date 'FEBRUARY 30'
```

## JavaTimeBasics.java

Source: [JavaTimeBasics.java](JavaTimeBasics.java)

**LocalDate, Period, ChronoUnit and TemporalAdjusters (Java 8+).** (Java 8+)

```bash
javac -d out JavaTimeBasics.java && java -cp out JavaTimeBasics
```

Expected output:

```text
2013-07-01 was a MONDAY
plus one month: 2013-08-01
course unchanged: 2013-07-01
gap: P13Y4M20D = 13 years, 4 months, 20 days
days: 4891
2024 leap? true
next Monday after release day: 2026-09-21
```

## OldDateTraps.java

Source: [OldDateTraps.java](OldDateTraps.java)

**Calendar months start at 0, Date is mutable, and getYear counts from 1900.** (any Java)

```bash
javac -d out OldDateTraps.java && java -cp out OldDateTraps
```

Expected output:

```text
asked for 2013-7-1, got 2013-08-01
original changed too: true
year field of new Date(0): 70 (years since 1900)
```

## ZonesAndDst.java

Source: [ZonesAndDst.java](ZonesAndDst.java)

**ZonedDateTime handles daylight saving time; Instant is a point on the UTC timeline.** (Java 8+)

```bash
javac -d out ZonesAndDst.java && java -cp out ZonesAndDst
```

Expected output:

```text
2026-03-08T01:30-08:00[America/Los_Angeles]
2026-03-08T03:30-07:00[America/Los_Angeles]
elapsed: PT1H
in Kolkata: 2026-09-15T22:30
in Seattle: 2026-09-15T10:00
```
