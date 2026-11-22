# Samples for chapter 1

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## ClassVersion.java

Source: [ClassVersion.java](ClassVersion.java)

**Read the CAFEBABE magic number and class-file version from a compiled class.** (any Java)

```bash
for v in 8 11 17 21; do
  rm -rf out$v; mkdir out$v
  javac --release $v -d out$v Probe.java ClassVersion.java
  echo "--- compiled with --release $v"
  java -cp out$v ClassVersion
done
```

Expected output:

```text
--- compiled with --release 8
magic = 0xCAFEBABE
class file major version = 52  ->  built for Java 8
--- compiled with --release 11
magic = 0xCAFEBABE
class file major version = 55  ->  built for Java 11
--- compiled with --release 17
magic = 0xCAFEBABE
class file major version = 61  ->  built for Java 17
--- compiled with --release 21
magic = 0xCAFEBABE
class file major version = 65  ->  built for Java 21
```

## CompileError.java

Source: [CompileError.java](CompileError.java)

**A type error caught at compile time: no .class file is produced.** (any Java)

```bash
javac -d out CompileError.java
```

Expected output:

```text
CompileError.java:3: error: incompatible types: String cannot be converted to int
        int count = "hello";
                    ^
1 error
```

## Hello.java

Source: [Hello.java](Hello.java)

**Run a source file directly, with no separate compile step (Java 11+).** (Java 11+)

```bash
java Hello.java
```

Expected output:

```text
Hello from the JVM!
```

**The classic first program, compiled with javac and run with java.** (any Java)

```bash
javac -d out Hello.java && java -cp out Hello
```

Expected output:

```text
Hello from the JVM!
```

## HelloCompact.java

Source: [HelloCompact.java](HelloCompact.java)

**A complete program as a compact source file with IO.println (Java 25+).** (Java 25+)

```bash
java HelloCompact.java
```

Expected output:

```text
Hello from a compact source file!
```

## HelloCompact21.java

Source: [HelloCompact21.java](HelloCompact21.java)

**The same idea on Java 21, as a preview feature.** (Java 21 only)

```bash
javac --enable-preview --release 21 -d out HelloCompact21.java && java --enable-preview -cp out HelloCompact21
```

Expected output:

```text
Hello from a compact source file!
```

## MainOverload.java

Source: [MainOverload.java](MainOverload.java)

**main is an ordinary static method: it can be overloaded and called like any other.** (any Java)

```bash
javac -d out MainOverload.java && java -cp out MainOverload a b
```

Expected output:

```text
JVM called main(String[]) with 2 args
I am an overload, called by ordinary code: 42
```

## NonPublicMain.java

Source: [NonPublicMain.java](NonPublicMain.java)

**From Java 25, a non-public main is a valid entry point.** (Java 25+)

```bash
javac -d out NonPublicMain.java && java -cp out NonPublicMain
```

Expected output:

```text
A non-public main ran
```

**Before Java 25, a package-private main compiles but cannot be launched.** (Java 24 and earlier)

```bash
javac -d out NonPublicMain.java && java -cp out NonPublicMain
```

Expected output:

```text
Error: Main method not found in class NonPublicMain, please define the main method as:
```

## OrderA.java

Source: [OrderA.java](OrderA.java)

**The Java 21 preview picked differently: preview features can change.** (Java 21 only)

```bash
javac --enable-preview --release 21 -d out OrderA.java OrderB.java OrderC.java
for c in OrderA OrderB OrderC; do echo "$c -> $(java --enable-preview -cp out $c)"; done
```

Expected output:

```text
OrderA -> static main(String[])
OrderB -> static main()
OrderC -> instance main()
```

**Which main the Java 25 launcher picks when several are declared.** (Java 25+)

```bash
javac -d out OrderA.java OrderB.java OrderC.java
for c in OrderA OrderB OrderC; do echo "$c -> $(java -cp out $c)"; done
```

Expected output:

```text
OrderA -> static main(String[])
OrderB -> instance main(String[])
OrderC -> instance main()
```

## OrderB.java

Source: [OrderB.java](OrderB.java)

**The Java 21 preview picked differently: preview features can change.** (Java 21 only)

```bash
javac --enable-preview --release 21 -d out OrderA.java OrderB.java OrderC.java
for c in OrderA OrderB OrderC; do echo "$c -> $(java --enable-preview -cp out $c)"; done
```

Expected output:

```text
OrderA -> static main(String[])
OrderB -> static main()
OrderC -> instance main()
```

**Which main the Java 25 launcher picks when several are declared.** (Java 25+)

```bash
javac -d out OrderA.java OrderB.java OrderC.java
for c in OrderA OrderB OrderC; do echo "$c -> $(java -cp out $c)"; done
```

Expected output:

```text
OrderA -> static main(String[])
OrderB -> instance main(String[])
OrderC -> instance main()
```

## OrderC.java

Source: [OrderC.java](OrderC.java)

**The Java 21 preview picked differently: preview features can change.** (Java 21 only)

```bash
javac --enable-preview --release 21 -d out OrderA.java OrderB.java OrderC.java
for c in OrderA OrderB OrderC; do echo "$c -> $(java --enable-preview -cp out $c)"; done
```

Expected output:

```text
OrderA -> static main(String[])
OrderB -> static main()
OrderC -> instance main()
```

**Which main the Java 25 launcher picks when several are declared.** (Java 25+)

```bash
javac -d out OrderA.java OrderB.java OrderC.java
for c in OrderA OrderB OrderC; do echo "$c -> $(java -cp out $c)"; done
```

Expected output:

```text
OrderA -> static main(String[])
OrderB -> instance main(String[])
OrderC -> instance main()
```

## Probe.java

Source: [Probe.java](Probe.java)

**Read the CAFEBABE magic number and class-file version from a compiled class.** (any Java)

```bash
for v in 8 11 17 21; do
  rm -rf out$v; mkdir out$v
  javac --release $v -d out$v Probe.java ClassVersion.java
  echo "--- compiled with --release $v"
  java -cp out$v ClassVersion
done
```

Expected output:

```text
--- compiled with --release 8
magic = 0xCAFEBABE
class file major version = 52  ->  built for Java 8
--- compiled with --release 11
magic = 0xCAFEBABE
class file major version = 55  ->  built for Java 11
--- compiled with --release 17
magic = 0xCAFEBABE
class file major version = 61  ->  built for Java 17
--- compiled with --release 21
magic = 0xCAFEBABE
class file major version = 65  ->  built for Java 21
```

## RunTimeError.java

Source: [RunTimeError.java](RunTimeError.java)

**Code that compiles but fails at run time with ArithmeticException.** (any Java)

```bash
javac -d out RunTimeError.java && java -cp out RunTimeError
```

Expected output:

```text
Exception in thread "main" java.lang.ArithmeticException: / by zero
```
