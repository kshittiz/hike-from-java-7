# Samples for chapter 15

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## DeprecatedConstructor.java

Source: [DeprecatedConstructor.java](DeprecatedConstructor.java)

**Wrapper constructors are deprecated for removal (Java 16+).** (Java 16+)

```bash
javac -Xlint:removal -d out DeprecatedConstructor.java
```

Expected output:

```text
DeprecatedConstructor.java:2: warning: [removal] Integer(int) in Integer has been deprecated and marked for removal
    Integer old = new Integer(10);             // deprecated in Java 9, for removal since Java 16
                  ^
1 warning
```

## IntegerCache.java

Source: [IntegerCache.java](IntegerCache.java)

**The Integer cache makes == true for small values only; use equals.** (any Java)

```bash
javac -d out IntegerCache.java && java -cp out IntegerCache
```

Expected output:

```text
127 == 127 : true
128 == 128 : false
128 equals : true
unboxed    : true
```

## Parsing.java

Source: [Parsing.java](Parsing.java)

**Converting between strings, primitives and wrappers.** (any Java)

```bash
javac -d out Parsing.java && java -cp out Parsing
```

Expected output:

```text
81
11011 ff 2147483647
-1 27 true
27.0 true
```

## UnboxingNull.java

Source: [UnboxingNull.java](UnboxingNull.java)

**Unboxing null throws NullPointerException; the helpful message names the cause (Java 15+).** (Java 15+)

```bash
javac -g -d out UnboxingNull.java && java -cp out UnboxingNull
```

Expected output:

```text
Cannot invoke "java.lang.Integer.intValue()" because the return value of "java.util.Map.get(Object)" is null
tea = 0
```

## WrapperEquality.java

Source: [WrapperEquality.java](WrapperEquality.java)

**Long.equals(Integer) is false, which silently breaks map lookups.** (any Java)

```bash
javac -d out WrapperEquality.java && java -cp out WrapperEquality
```

Expected output:

```text
Long(1).equals(1)  : false
Long(1).equals(1L) : true
get(42)  : null
get(42L) : Asha
```
