# Samples for chapter 10

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## InnerWithMain.java

Source: [InnerWithMain.java](InnerWithMain.java)

**Inner classes may declare static members, even main (Java 16+).** (Java 16+)

```bash
javac -d out InnerWithMain.java && java -cp out 'InnerWithMain$Inner'
```

Expected output:

```text
main inside an inner class
```

## NestedKinds.java

Source: [NestedKinds.java](NestedKinds.java)

**Static nested, inner, local and anonymous classes, a lambda, and the .class files they produce.** (Java 8+)

```bash
javac -d out NestedKinds.java && java -cp out NestedKinds
echo "--- class files:"; ls out | LC_ALL=C sort
```

Expected output:

```text
static nested
inner sees counter = 7
local class: hello
anonymous class: hello
lambda: no class file of its own
--- class files:
NestedKinds$1.class
NestedKinds$1Local.class
NestedKinds$Inner.class
NestedKinds$StaticNested.class
NestedKinds.class
```

## NotEffectivelyFinal.java

Source: [NotEffectivelyFinal.java](NotEffectivelyFinal.java)

**Lambdas and inner classes may only capture effectively final locals (Java 8+).** (Java 8+)

```bash
javac -d out NotEffectivelyFinal.java
```

Expected output:

```text
NotEffectivelyFinal.java:4: error: local variables referenced from a lambda expression must be final or effectively final
        Runnable r = () -> System.out.println(count);
                                              ^
1 error
```

## SortingJourney.java

Source: [SortingJourney.java](SortingJourney.java)

**Sorting: anonymous class (Java 7), lambda, then comparator combinators (Java 8).** (Java 16+)

```bash
javac -d out SortingJourney.java && java -cp out SortingJourney
```

Expected output:

```text
Java 7  [Dev[name=Asha, years=9], Dev[name=Mia, years=9], Dev[name=Leo, years=5], Dev[name=Ravi, years=3]]
lambda  [Dev[name=Asha, years=9], Dev[name=Leo, years=5], Dev[name=Mia, years=9], Dev[name=Ravi, years=3]]
combin. [Dev[name=Asha, years=9], Dev[name=Mia, years=9], Dev[name=Leo, years=5], Dev[name=Ravi, years=3]]
```

## ThisMeaning.java

Source: [ThisMeaning.java](ThisMeaning.java)

**this means the anonymous object in an anonymous class, but the enclosing object in a lambda.** (Java 8+)

```bash
javac -d out ThisMeaning.java && java -cp out ThisMeaning
```

Expected output:

```text
anonymous this: ThisMeaning$1
lambda this:    ThisMeaning
```
