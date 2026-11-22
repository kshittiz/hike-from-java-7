# Samples for chapter 6

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## Binding.java

Source: [Binding.java](Binding.java)

**Overriding is decided at run time; overloading and static methods at compile time.** (any Java)

```bash
javac -d out Binding.java && java -cp out Binding
```

Expected output:

```text
Woof
animal / dog
describe(Animal)
```

## MethodRefs.java

Source: [MethodRefs.java](MethodRefs.java)

**The four kinds of method reference (Java 8+).** (Java 8+)

```bash
javac -d out MethodRefs.java && java -cp out MethodRefs
```

Expected output:

```text
28
SEVEN
Java 27
tliub
27
```

## PassByValue.java

Source: [PassByValue.java](PassByValue.java)

**Java always passes copies: of primitives, and of references.** (any Java)

```bash
javac -d out PassByValue.java && java -cp out PassByValue
```

Expected output:

```text
after swap:    x=1 y=2
after replace: 0
after mutate:  42
```

## ReturnTypeOverload.java

Source: [ReturnTypeOverload.java](ReturnTypeOverload.java)

**Methods cannot be overloaded on return type alone.** (any Java)

```bash
javac -d out ReturnTypeOverload.java
```

Expected output:

```text
ReturnTypeOverload.java:3: error: method show(int) is already defined in class ReturnTypeOverload
    String show(int n) { return "n"; }     // differs only by return type
           ^
1 error
```

## Varargs.java

Source: [Varargs.java](Varargs.java)

**Variable-arity methods and how they compete with fixed-arity overloads.** (any Java)

```bash
javac -d out Varargs.java && java -cp out Varargs
```

Expected output:

```text
sum(int, int) = 3
sum(int...) over 3 values = 6
sum(int...) over 0 values = 0
varargs, in, the, JDK
```
