# Samples for chapter 3

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## DivMod.java

Source: [DivMod.java](DivMod.java)

**Integer division truncates; the remainder takes the dividend's sign; Math.floorMod.** (any Java)

```bash
javac -d out DivMod.java && java -cp out DivMod
```

Expected output:

```text
 7 / 2  = 3
-7 / 2  = -3
 7 % 3  = 1
-7 % 3  = -1
 7 % -3 = 1
floorMod(-7, 3) = 2
isOdd(-3) with % 2 == 1 ? false
```

## Labeled.java

Source: [Labeled.java](Labeled.java)

**Labeled break out of nested loops, and the enhanced for loop.** (any Java)

```bash
javac -d out Labeled.java && java -cp out Labeled
```

Expected output:

```text
first negative at [1][1]
7 8 9 
```

## PatternSwitch.java

Source: [PatternSwitch.java](PatternSwitch.java)

**Pattern matching for switch with guards and case null (Java 21+).** (Java 21+)

```bash
javac -d out PatternSwitch.java && java -cp out PatternSwitch
```

Expected output:

```text
an int 42
a negative int -7
a string of length 4
an int array of 3
something else: Double
nothing
```

## Precedence.java

Source: [Precedence.java](Precedence.java)

**Postfix vs. prefix increment, precedence and right-associative assignment.** (any Java)

```bash
javac -d out Precedence.java && java -cp out Precedence
```

Expected output:

```text
r = 12, i = 7
x = 200
42 42 42
```

## Shifts.java

Source: [Shifts.java](Shifts.java)

**Signed and unsigned shifts, and why 1 << 33 is 2.** (any Java)

```bash
javac -d out Shifts.java && java -cp out Shifts
```

Expected output:

```text
  8 << 2   = 32
-16 >> 2   = -4
-16 >>> 28 = 15
  1 << 33  = 2
  1L << 33 = 8589934592
6 & 3 = 2, 6 | 3 = 7, 6 ^ 3 = 5, ~6 = -7
```

## ShortCircuit.java

Source: [ShortCircuit.java](ShortCircuit.java)

**&& skips the right side when the left decides the result; & never does.** (any Java)

```bash
javac -d out ShortCircuit.java && java -cp out ShortCircuit
```

Expected output:

```text
&& (short-circuit):
  evaluated left
&  (always both):
  evaluated left
  evaluated right
safe: false
```

## SwitchExpression.java

Source: [SwitchExpression.java](SwitchExpression.java)

**Switch expressions with arrows and yield (Java 14+).** (Java 14+)

```bash
javac -d out SwitchExpression.java && java -cp out SwitchExpression
```

Expected output:

```text
JUN 30
FEB 28
FEB 29 (leap)
JAN 31
```

## SwitchJava7.java

Source: [SwitchJava7.java](SwitchJava7.java)

**Strings in switch (Java 7) and the classic fall-through bug.** (any Java)

```bash
javac -d out SwitchJava7.java && java -cp out SwitchJava7
```

Expected output:

```text
JUN 30
FEB 31   <- fall-through bug
JAN 31
```

## TernaryTrap.java

Source: [TernaryTrap.java](TernaryTrap.java)

**The conditional operator promotes both branches to a common type.** (any Java)

```bash
javac -d out TernaryTrap.java && java -cp out TernaryTrap
```

Expected output:

```text
1.0
1
A
```
