# Samples for Chapter 7

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## Composition.java

Source: [Composition.java](Composition.java)

**Favoring composition: a class that has a list instead of being one.** (Java 10+)

```bash
javac -d out Composition.java && java -cp out Composition
```

Expected output:

```text
2 [Duke's Theme, Coffee Break]
```

## ConstructorChain.java

Source: [ConstructorChain.java](ConstructorChain.java)

**Parent constructors always run first, via an explicit or implicit super().** (any Java)

```bash
javac -d out ConstructorChain.java && java -cp out ConstructorChain
```

Expected output:

```text
1. Vehicle()
2. Car(GT)
3. SportsCar()
```

## HidingVsOverriding.java

Source: [HidingVsOverriding.java](HidingVsOverriding.java)

**Fields are hidden (static type decides); methods are overridden (object decides).** (any Java)

```bash
javac -d out HidingVsOverriding.java && java -cp out HidingVsOverriding
```

Expected output:

```text
parent field
child method
child field / parent field / parent method
```

## NotPermitted.java

Source: [NotPermitted.java](NotPermitted.java)

**A class outside the permits list cannot extend a sealed type (Java 17+).** (Java 17+)

```bash
javac -d out NotPermitted.java
```

Expected output:

```text
NotPermitted.java:5: error: class is not allowed to extend sealed class: Shape (as it is not listed in its 'permits' clause)
    record Triangle(double base) implements Shape { }
    ^
1 error
```

## SealedShapes.java

Source: [SealedShapes.java](SealedShapes.java)

**A sealed hierarchy and an exhaustive switch with no default (Java 21+).** (Java 21+)

```bash
javac -d out SealedShapes.java && java -cp out SealedShapes
```

Expected output:

```text
circle r=1.5
square side=2.0
polygon (Hexagon)
```

## WeakerAccess.java

Source: [WeakerAccess.java](WeakerAccess.java)

**An override may not reduce visibility.** (any Java)

```bash
javac -d out WeakerAccess.java
```

Expected output:

```text
WeakerAccess.java:4: error: greet() in Child cannot override greet() in Parent
        @Override void greet() { }  // package-private is weaker than public
                       ^
  attempting to assign weaker access privileges; was public
1 error
```
