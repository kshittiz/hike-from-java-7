# 8. Abstraction and Interfaces

> [!TIP]
> You're reading the **complete edition**, the reference guide with every deep dive and runnable sample. For a focused read, see this chapter on the [website edition](https://hikefromjava7.com/part2/ch08-abstraction-and-interfaces/).

> **In this chapter:** abstract classes versus interfaces, why interfaces could only hold abstract methods and constants in Java 7, and how Java 8 and 9 turned them into a tool for evolving APIs without breaking anyone.

> [!TIP]
> **Key points**
>
> - An **abstract class** can't be instantiated. It can have constructors, fields, concrete methods and abstract methods.
> - An **interface** is a contract. Its fields are always `public static final` constants.
> - **Java 8** added `default` and `static` methods to interfaces; **Java 9** added `private` ones. That's how `List.sort` and `Collection.stream` were added without breaking existing implementations.
> - If two interfaces give a class the same [default method](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.4), the class must override it and can pick one with `Walker.super.move()`.
> - An interface with exactly one abstract method is a **[functional interface](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.8)**, and a lambda can implement it (**Java 8**).

## Abstract classes

**Example:** [`code/ch08/AbstractShape.java`](../../code/ch08/AbstractShape.java) · [how to run it](../../code/ch08/README.md#abstractshapejava) · any Java

```java
public class AbstractShape {
    static abstract class Shape {
        private final String name;
        // abstract classes can have constructors
        Shape(String name) { this.name = name; }
        // no body: subclasses must provide it
        abstract double area();
        String describe() {
            return name + " with area " + String.format("%.2f", area());
        }
    }
    static class Circle extends Shape {
        final double r;
        Circle(double r) { super("circle"); this.r = r; }
        @Override double area() { return Math.PI * r * r; }
    }

    public static void main(String[] args) {
        // reference of the abstract type, object of a concrete one
        Shape s = new Circle(1);
        System.out.println(s.describe());
    }
}
```

```text
circle with area 3.14
```

An abstract class captures what related classes **share** (here, a name and a `describe` method) while leaving some behavior (`area`) for each subclass to define.

**Example:** [`code/ch08/NewAbstract.java`](../../code/ch08/NewAbstract.java) · [how to run it](../../code/ch08/README.md#newabstractjava) · any Java

```java
public class NewAbstract {
    static abstract class Shape { }
    public static void main(String[] args) {
        Shape s = new Shape();
    }
}
```

```text
NewAbstract.java:4: error: Shape is abstract; cannot be instantiated
        Shape s = new Shape();
                  ^
1 error
```

> [!NOTE]
> **The classic rule**
>
> *"Rule → Abstract class can not be instantiated. It is not mandatory to have an abstract method in an abstract class. A child class has to override all the abstract methods of the parent, otherwise it has to make itself abstract."*
>
> All three rules still hold.

## Interfaces: the contract

> [!NOTE]
> **The classic rule**
>
> *"Defn → Interfaces are the contract between the programming language and the programmer. GOLDEN Rule → Interfaces are never made to represent an object."*

An interface says *what* a type can do, not *what it is*. A class can implement any number of interfaces, which is how Java gets the benefits of multiple inheritance without the diamond problem for state.

In Java 7, an interface could contain only:

- abstract methods (implicitly `public abstract`),
- constants (implicitly `public static final`), and
- nested types.

**Example:** [`code/ch08/InterfaceConstants.java`](../../code/ch08/InterfaceConstants.java) · [how to run it](../../code/ch08/README.md#interfaceconstantsjava) · any Java

```java
public class InterfaceConstants {
    interface Limits {
        int MAX_USERS = 100;               // implicitly public static final
    }
    public static void main(String[] args) {
        System.out.println(Limits.MAX_USERS);
        Limits.MAX_USERS = 200;
    }
}
```

```text
InterfaceConstants.java:7: error: cannot assign a value to static final variable MAX_USERS
        Limits.MAX_USERS = 200;
              ^
1 error
```

> [!WARNING]
> **The constant-interface anti-pattern**
>
> Implementing an interface just to inherit its constants (a common Java 1.4 habit) pollutes your class's public API. Since Java 5, use a `final` class with `static final` fields, plus `import static` where you need them (Chapter 9).

## Abstract class or interface?

| | Abstract class | Interface |
|---|---|---|
| Instances | No | No |
| Constructors | Yes | No |
| Instance fields (state) | Yes | No, only constants |
| Concrete methods | Yes | Since Java 8 (`default`, `static`), Java 9 (`private`) |
| How many can a class use? | One (`extends`) | Many (`implements`) |
| Best for | Sharing state and code among closely related classes | Defining a capability that unrelated classes can have |

The modern rule of thumb: **start with an interface.** Reach for an abstract class when subclasses genuinely share state or constructor logic.

## How interfaces evolved: Java 8 and 9

Java 7 had a serious API problem. Adding a method to a published interface like `List` would break every class anyone had ever written that implemented it. So Java 8's designers, who wanted `stream()` and `forEach()` on every collection, gave interfaces method bodies:

**Example:** [`code/ch08/InterfaceEvolution.java`](../../code/ch08/InterfaceEvolution.java) · [how to run it](../../code/ch08/README.md#interfaceevolutionjava) · Java 9+

```java
import java.util.List;

public class InterfaceEvolution {
    interface Greeter {
        String name();  // abstract: implementers must provide

        // Java 8: default method
        default String greet() { return decorate("Hello, " + name()); }
        default String shout() {
            return decorate(("Hello, " + name() + "!").toUpperCase());
        }

        // Java 8: static method
        static Greeter of(String name) { return () -> name; }

        // Java 9: private method
        private String decorate(String s) { return "<< " + s + " >>"; }
    }

    public static void main(String[] args) {
        Greeter g = Greeter.of("Duke");
        System.out.println(g.greet());
        System.out.println(g.shout());

        List<String> names =
                new java.util.ArrayList<>(List.of("b", "c", "a"));
        names.sort(null);  // List.sort: a default method added in Java 8
        System.out.println(names);
    }
}
```

```text
<< Hello, Duke >>
<< HELLO, DUKE! >>
[a, b, c]
```

- **`default` methods (Java 8)** have a body that implementers inherit and may override. This is how `List.sort`, `Collection.stream` and `Iterable.forEach` were added.
- **`static` methods (Java 8)** belong to the interface. Factories like [`List.of`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/List.html) (Java 9) and `Comparator.comparing` live here.
- **`private` methods (Java 9)** share code between default methods without exposing it.

Interfaces still can't hold instance state, so the difference from abstract classes remains real.

### The diamond, resolved

**Example:** [`code/ch08/DiamondDefault.java`](../../code/ch08/DiamondDefault.java) · [how to run it](../../code/ch08/README.md#diamonddefaultjava) · Java 8+

```java
public class DiamondDefault {
    interface Walker  { default String move() { return "walk"; } }
    interface Swimmer { default String move() { return "swim"; } }

    static class Duck implements Walker, Swimmer {
        @Override public String move() {  // required: the defaults conflict
            return Walker.super.move() + " and " + Swimmer.super.move();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Duck().move());
    }
}
```

```text
walk and swim
```

When two interfaces offer the same default method, Java refuses to guess. The class must override it, and `Interface.super.method()` calls a specific parent's version. The rules for which default wins are:

1. A method from a **class** (including inherited ones) beats any default.
2. A **more specific** interface (a sub-interface) beats a less specific one.
3. Otherwise, the class must override and choose explicitly.

## Functional interfaces and lambdas

An interface with exactly **one abstract method** is a *functional interface*. Java 8 lets a lambda implement it:

**Example:** [`code/ch08/FunctionalInterfaces.java`](../../code/ch08/FunctionalInterfaces.java) · [how to run it](../../code/ch08/README.md#functionalinterfacesjava) · Java 8+

```java
import java.util.function.Predicate;

public class FunctionalInterfaces {
    @FunctionalInterface
    interface Discount {  // exactly one abstract method
        double apply(double price);
    }

    public static void main(String[] args) {
        Discount java7Style = new Discount() {   // Java 7: anonymous class
            @Override public double apply(double price) {
                return price * 0.9;
            }
        };
        Discount java8Style = price -> price * 0.9;     // Java 8: lambda

        System.out.println(java7Style.apply(200) + " "
                + java8Style.apply(200));

        Predicate<String> longWord = w -> w.length() > 4;
        System.out.println(
                longWord.and(w -> w.startsWith("j")).test("javadoc"));
    }
}
```

```text
180.0 180.0
true
```

`@FunctionalInterface` is optional but makes the compiler check the one-method rule. Java 8 also added a standard set in `java.util.function`: `Predicate<T>` (test), `Function<T,R>` (transform), `Consumer<T>` (accept), `Supplier<T>` (produce), and primitive versions like `IntPredicate`. Their default methods, such as `Predicate.and`, show how default methods and lambdas work together.

## Sealed interfaces (Java 17)

Interfaces can be `sealed` too, and that's often the better choice than a [sealed class](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.1.1.2). Chapter 7 used a sealed `Shape` interface with record implementations, and Chapter 19 builds on this pattern: a sealed interface defines a closed set of alternatives, records hold each alternative's data, and an exhaustive `switch` handles them all.

## Exercises

1. Turn `Shape` in `AbstractShape` into an interface with a `default` `describe()`. What do you lose? (Hint: the `name` field.)
2. Remove the override from `Duck`. Read the compiler error.
3. Write a `@FunctionalInterface` `Validator<T>` with `boolean validate(T t)` and a `default` `and(Validator<T> other)`.
4. Find three default methods in `java.util.Map` added after Java 7 (try `getOrDefault`, `computeIfAbsent`, `merge`). What did code look like without them?

## IQ corner

1. **Can an abstract class have a constructor?** Yes; subclasses call it with `super(...)`.
2. **Can an interface have a constructor?** No.
3. **Are interface methods always `public`?** Abstract and default ones are. Since Java 9, `private` methods are allowed.
4. **Why were default methods added?** To add methods to existing interfaces, like `Collection.stream()`, without breaking implementations.
5. **What makes an interface functional?** Exactly one abstract method. Defaults and statics don't count.

## Summary

- Abstract classes share state and code; interfaces define capabilities, and a class can have many.
- Interface fields are constants. Since Java 8/9, interfaces can have default, static and private methods.
- Conflicting defaults must be resolved explicitly with `X.super.m()`.
- Functional interfaces plus lambdas (Java 8) replaced most one-method anonymous classes.

**Next:** Part III organizes code: packages, access modifiers, and the module system that arrived in Java 9.
