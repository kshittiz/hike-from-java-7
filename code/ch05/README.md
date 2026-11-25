# Samples for Chapter 5

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## Chaining.java

Source: [Chaining.java](Chaining.java)

**Constructor chaining with this(...).** (any Java)

```bash
javac -d out Chaining.java && java -cp out Chaining
```

Expected output:

```text
  two-arg constructor
  one-arg constructor
  no-arg constructor
unknown, 0
```

## InitOrder.java

Source: [InitOrder.java](InitOrder.java)

**Static block, instance initializer and constructor: the order they run in.** (any Java)

```bash
javac -d out InitOrder.java && java -cp out InitOrder
```

Expected output:

```text
1. static block
main starts
2. instance initializer (object #1)
3. constructor
2. instance initializer (object #2)
3. constructor
objects created: 2
```

## NoMain.java

Source: [NoMain.java](NoMain.java)

**Since Java 7 a class without main is rejected before its static block runs.** (Java 24 and earlier)

```bash
javac -d out NoMain.java && java -cp out NoMain; echo "static block lines printed: $(java -cp out NoMain 2>&1 | grep -c "static work")"
```

Expected output:

```text
Error: Main method not found in class NoMain, please define the main method as:
static block lines printed: 0
```

## StaticRules.java

Source: [StaticRules.java](StaticRules.java)

**Static fields are shared; instance fields are per object.** (any Java)

```bash
javac -d out StaticRules.java && java -cp out StaticRules
```

Expected output:

```text
a.id=1 b.id=2
2 instances so far
```

## ValidateBeforeSuper25.java

Source: [ValidateBeforeSuper25.java](ValidateBeforeSuper25.java)

**Statements before super(...): flexible constructor bodies (Java 25+).** (Java 25+)

```bash
javac -d out ValidateBeforeSuper25.java && java -cp out ValidateBeforeSuper25
```

Expected output:

```text
Account SB-01
rejected: empty id
```

## ValidateBeforeSuper7.java

Source: [ValidateBeforeSuper7.java](ValidateBeforeSuper7.java)

**Validating constructor arguments before super(...) the old way, with a static helper.** (any Java)

```bash
javac -d out ValidateBeforeSuper7.java && java -cp out ValidateBeforeSuper7
```

Expected output:

```text
Account SB-01
rejected: empty id
```
