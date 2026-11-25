# Samples for Chapter 2

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## CharMath.java

Source: [CharMath.java](CharMath.java)

**A char is a number; emoji need two UTF-16 units.** (any Java)

```bash
javac -d out CharMath.java && java -cp out CharMath
```

Expected output:

```text
66
B
B
coffee length = 1
grin length   = 2
grin code points = 1
```

## DefaultCharset.java

Source: [DefaultCharset.java](DefaultCharset.java)

**UTF-8 is the default charset from Java 18.** (Java 18+)

```bash
javac -d out DefaultCharset.java && java -cp out DefaultCharset
```

Expected output:

```text
UTF-8
```

## Defaults.java

Source: [Defaults.java](Defaults.java)

**Fields get default values.** (any Java)

```bash
javac -d out Defaults.java && java -cp out Defaults
```

Expected output:

```text
0 false null 0.0
```

## FloatTrap.java

Source: [FloatTrap.java](FloatTrap.java)

**0.1 + 0.2, BigDecimal, Infinity and NaN.** (any Java)

```bash
javac -d out FloatTrap.java && java -cp out FloatTrap
```

Expected output:

```text
0.30000000000000004
false
0.3
Infinity -Infinity NaN
```

## Literals.java

Source: [Literals.java](Literals.java)

**Binary, hex and octal literals, plus underscores in numbers.** (any Java)

```bash
javac -d out Literals.java && java -cp out Literals
```

Expected output:

```text
10 31 15 1000000 6.02214076E23
```

## LocalNoInit.java

Source: [LocalNoInit.java](LocalNoInit.java)

**Local variables must be assigned before use.** (any Java)

```bash
javac -d out LocalNoInit.java
```

Expected output:

```text
LocalNoInit.java:4: error: variable total might not have been initialized
        System.out.println(total);
                           ^
1 error
```

## Narrowing.java

Source: [Narrowing.java](Narrowing.java)

**Overflow wrap-around, narrowing casts, truncation and Math.addExact.** (any Java)

```bash
javac -d out Narrowing.java && java -cp out Narrowing
```

Expected output:

```text
MAX_VALUE + 1  = -2147483648
(byte) 130     = -126
(int) 3.99     = 3
(int) -3.99    = -3
(int) 1e20     = 2147483647
Math.addExact  = ArithmeticException: integer overflow
```

## OverloadPick.java

Source: [OverloadPick.java](OverloadPick.java)

**How widening conversions decide which overload is called.** (any Java)

```bash
javac -d out OverloadPick.java && java -cp out OverloadPick
```

Expected output:

```text
show(long)
show(long)
show(double)
show(byte)
```

## Promotion.java

Source: [Promotion.java](Promotion.java)

**byte + byte is an int, so assigning it to a byte does not compile.** (any Java)

```bash
javac -d out Promotion.java
```

Expected output:

```text
Promotion.java:5: error: incompatible types: possible lossy conversion from int to byte
        byte sum = a + b;      // does not compile: a + b is an int
                     ^
1 error
```

## PromotionFixed.java

Source: [PromotionFixed.java](PromotionFixed.java)

**Three ways to handle numeric promotion.** (any Java)

```bash
javac -d out PromotionFixed.java && java -cp out PromotionFixed
```

Expected output:

```text
30 30 30
```

## Ranges.java

Source: [Ranges.java](Ranges.java)

**The minimum and maximum of every numeric primitive type.** (any Java)

```bash
javac -d out Ranges.java && java -cp out Ranges
```

Expected output:

```text
byte   -128 .. 127
short  -32768 .. 32767
int    -2147483648 .. 2147483647
long   -9223372036854775808 .. 9223372036854775807
char   0 .. 65535
float  max 3.4028235E38
double max 1.7976931348623157E308
```

## Unnamed.java

Source: [Unnamed.java](Unnamed.java)

**Unnamed variables as a Java 21 preview feature.** (Java 21 only)

```bash
javac --enable-preview --release 21 -d out Unnamed.java && java --enable-preview -cp out Unnamed
```

Expected output:

```text
count = 3
not a number
```

**The unnamed variable _ for values you never use (Java 22+).** (Java 22+)

```bash
javac -d out Unnamed.java && java -cp out Unnamed
```

Expected output:

```text
count = 3
not a number
```

## Unsigned.java

Source: [Unsigned.java](Unsigned.java)

**Unsigned helper methods added in Java 8.** (Java 8+)

```bash
javac -d out Unsigned.java && java -cp out Unsigned
```

Expected output:

```text
4294967295
4294967295
2147483647
255
true
```

## VarDemo.java

Source: [VarDemo.java](VarDemo.java)

**Local variable type inference with var (Java 10+).** (Java 10+)

```bash
javac -d out VarDemo.java && java -cp out VarDemo
```

Expected output:

```text
asha -> [90, 85]
int double long
true
```

## VarError.java

Source: [VarError.java](VarError.java)

**var needs an initializer to infer from.** (Java 10+)

```bash
javac -d out VarError.java
```

Expected output:

```text
VarError.java:3: error: cannot infer type for local variable later
        var later;          // no initializer, nothing to infer from
            ^
  (cannot use 'var' on variable without initializer)
1 error
```
