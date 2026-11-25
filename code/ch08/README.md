# Samples for Chapter 8

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## AbstractShape.java

Source: [AbstractShape.java](AbstractShape.java)

**An abstract class with a constructor, an abstract method and a concrete method.** (any Java)

```bash
javac -d out AbstractShape.java && java -cp out AbstractShape
```

Expected output:

```text
circle with area 3.14
```

## DiamondDefault.java

Source: [DiamondDefault.java](DiamondDefault.java)

**Resolving two conflicting default methods with X.super.method().** (Java 8+)

```bash
javac -d out DiamondDefault.java && java -cp out DiamondDefault
```

Expected output:

```text
walk and swim
```

## FunctionalInterfaces.java

Source: [FunctionalInterfaces.java](FunctionalInterfaces.java)

**A functional interface implemented by an anonymous class and by a lambda.** (Java 8+)

```bash
javac -d out FunctionalInterfaces.java && java -cp out FunctionalInterfaces
```

Expected output:

```text
180.0 180.0
true
```

## InterfaceConstants.java

Source: [InterfaceConstants.java](InterfaceConstants.java)

**Interface fields are implicitly public static final.** (any Java)

```bash
javac -d out InterfaceConstants.java
```

Expected output:

```text
InterfaceConstants.java:7: error: cannot assign a value to static final variable MAX_USERS
        Limits.MAX_USERS = 200;
              ^
1 error
```

## InterfaceEvolution.java

Source: [InterfaceEvolution.java](InterfaceEvolution.java)

**Default and static methods (Java 8) and private methods (Java 9) in interfaces.** (Java 9+)

```bash
javac -d out InterfaceEvolution.java && java -cp out InterfaceEvolution
```

Expected output:

```text
<< Hello, Duke >>
<< HELLO, DUKE! >>
[a, b, c]
```

## NewAbstract.java

Source: [NewAbstract.java](NewAbstract.java)

**Abstract classes cannot be instantiated.** (any Java)

```bash
javac -d out NewAbstract.java
```

Expected output:

```text
NewAbstract.java:4: error: Shape is abstract; cannot be instantiated
        Shape s = new Shape();
                  ^
1 error
```
