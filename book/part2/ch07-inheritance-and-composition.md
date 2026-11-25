# 7. Inheritance and Composition

> [!TIP]
> You're reading the **complete edition**, the reference guide with every deep dive and runnable sample. For a focused read, see this chapter on the [website edition](https://hikefromjava7.com/part2/ch07-inheritance-and-composition/).

> **In this chapter:** how one class builds on another, what is and isn't inherited, the rules for overriding, why "has-a" often beats "is-a", and how Java 17's [sealed classes](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.1.1.2) let you say exactly who may extend a type.

> [!TIP]
> **Key points**
>
> - `class Car extends Vehicle`: a class has **one** parent class. Every chain ends at `java.lang.Object`.
> - Parent constructors always run first: each constructor begins with `this(...)` or `super(...)`, and the compiler inserts `super()` if you write neither.
> - **Fields are hidden** (the reference type decides); **methods are overridden** (the object decides).
> - `final` stops extension or overriding. **Sealed** classes and interfaces (**Java 17**) allow extension only by a listed set of types, which makes `switch` exhaustive (**Java 21**).
> - Prefer **composition** (a class *has* another object) over inheritance unless the relationship is truly "is-a."

## What inheritance gives you

`class Car extends Vehicle` means a `Car` **is a** `Vehicle`: it inherits the parent's fields and methods and can be used wherever a `Vehicle` is expected.

What's inherited, and what isn't:

| Member | Inherited? |
|---|---|
| `public` and `protected` fields and methods | Yes |
| Package-private members | Only if the subclass is in the same package |
| `private` members | No. They exist inside the object but the subclass can't name them. |
| Constructors | No. Each class declares its own. |
| `static` members | Accessible through the subclass, but not overridden |

> [!NOTE]
> **The classic rule**
>
> *"Rule → In Java, Object is the top-most class. Every class which has no parent extends Object."*

That's why every object has `toString`, `equals`, `hashCode` and `getClass`.

### Why only one parent?

Java classes have **single inheritance**. Multiple inheritance of *implementation* causes the "diamond problem": if two parents define the same method, which one does the child get? Java avoided it for classes from day one. Interfaces can be multiply inherited, and when Java 8 gave interfaces method bodies, it added explicit rules for the same diamond (Chapter 8).

## Constructors in a hierarchy

**Example:** [`code/ch07/ConstructorChain.java`](../../code/ch07/ConstructorChain.java) · [how to run it](../../code/ch07/README.md#constructorchainjava) · any Java

```java
public class ConstructorChain {
    static class Vehicle {
        Vehicle() { System.out.println("1. Vehicle()"); }
    }
    static class Car extends Vehicle {
        Car(String model) {                       // compiler inserts super() here
            System.out.println("2. Car(" + model + ")");
        }
    }
    static class SportsCar extends Car {
        SportsCar() {
            super("GT");                          // explicit call to a parent constructor
            System.out.println("3. SportsCar()");
        }
    }

    public static void main(String[] args) {
        new SportsCar();
    }
}
```

```text
1. Vehicle()
2. Car(GT)
3. SportsCar()
```

An object is built from the top down: [`Object`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Object.html), then `Vehicle`, then `Car`, then `SportsCar`. Each constructor's first act is to call its parent's.

> [!NOTE]
> **The classic rule**
>
> *"Rule → Every constructor of every child class has either this() or super() as its first line. If nothing is there, by default it goes to super()."*
>
> Still true, with one Java 25 refinement (Chapter 5): statements that don't use `this` may now come before that call.

One consequence catches people out: if the parent has **no** no-argument constructor, every child constructor must call `super(...)` explicitly, or the class won't compile.

## Hiding vs. overriding

**Example:** [`code/ch07/HidingVsOverriding.java`](../../code/ch07/HidingVsOverriding.java) · [how to run it](../../code/ch07/README.md#hidingvsoverridingjava) · any Java

```java
public class HidingVsOverriding {
    static class Parent {
        String name = "parent field";
        String name() { return "parent method"; }
    }
    static class Child extends Parent {
        String name = "child field";                         // hides Parent.name
        @Override String name() { return "child method"; }   // overrides Parent.name()
        String both() { return name + " / " + super.name + " / " + super.name(); }
    }

    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.name);         // fields: chosen by the reference type
        System.out.println(p.name());       // methods: chosen by the object
        System.out.println(new Child().both());
    }
}
```

```text
parent field
child method
child field / parent field / parent method
```

- A field with the same name in the child **hides** the parent's field. Which one you get depends on the **reference type**: `p.name` reads `Parent.name` because `p` is declared `Parent`.
- A method with the same signature **overrides** the parent's. Which one runs depends on the **object**: `p.name()` runs `Child.name()`.
- `super.name` and `super.name()` reach the parent's versions from inside the child.

In the Java 7 era, the first was often called **data hiding** and the second **method overriding**. Hiding fields is legal but confusing, and it's almost always a mistake. Keep fields `private` and the problem disappears.

### Overriding rules

An override must keep the same signature, may return a subtype (covariant return), may not throw new or broader [checked exceptions](https://docs.oracle.com/javase/specs/jls/se25/html/jls-11.html#jls-11.1.1), and **may not reduce visibility**:

**Example:** [`code/ch07/WeakerAccess.java`](../../code/ch07/WeakerAccess.java) · [how to run it](../../code/ch07/README.md#weakeraccessjava) · any Java

```java
public class WeakerAccess {
    static class Parent { public void greet() { } }
    static class Child extends Parent {
        @Override void greet() { }          // package-private is weaker than public
    }
}
```

```text
WeakerAccess.java:4: error: greet() in Child cannot override greet() in Parent
        @Override void greet() { }          // package-private is weaker than public
                       ^
  attempting to assign weaker access privileges; was public
1 error
```

The reason is substitutability: code holding a `Parent` reference may call `greet()`, so every subclass must allow that call.

## `final`: closing the door

`final` means "can't change" in three places:

- A **`final` class** can't be extended. [`String`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/String.html) and all the wrapper classes are final.
- A **`final` method** can't be overridden.
- A **`final` variable** can be assigned only once. A `final` field must be assigned by the end of every constructor; a `static final` field is a constant.

> [!NOTE]
> **The classic rule**
>
> *"Rule → When you make any local variable final, its value can't be changed. When you make any class final, it can't be inherited."*

Java 8 added a softer cousin, **[effectively final](https://docs.oracle.com/javase/specs/jls/se25/html/jls-4.html#jls-4.12.4)**: a local variable that's never reassigned can be used by lambdas and [inner classes](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.1.3) without the keyword (Chapter 10).

## Sealed classes: a middle ground (Java 17)

For years a class was either open to everyone or `final` to everyone. **Sealed** types ([JEP 409](https://openjdk.org/jeps/409)) list exactly which types may extend them:

**Example:** [`code/ch07/SealedShapes.java`](../../code/ch07/SealedShapes.java) · [how to run it](../../code/ch07/README.md#sealedshapesjava) · Java 21+

```java
public class SealedShapes {
    sealed interface Shape permits Circle, Square, Polygon { }    // Java 17
    record Circle(double radius) implements Shape { }
    record Square(double side) implements Shape { }
    non-sealed static class Polygon implements Shape { }          // reopened for extension
    static class Hexagon extends Polygon { }

    static String name(Shape s) {
        return switch (s) {                                        // no default: the compiler
            case Circle c  -> "circle r=" + c.radius();            // knows every Shape subtype
            case Square sq -> "square side=" + sq.side();
            case Polygon p -> "polygon (" + p.getClass().getSimpleName() + ")";
        };
    }

    public static void main(String[] args) {
        Shape[] shapes = { new Circle(1.5), new Square(2), new Hexagon() };
        for (Shape s : shapes) System.out.println(name(s));
    }
}
```

```text
circle r=1.5
square side=2.0
polygon (Hexagon)
```

- `sealed … permits Circle, Square, Polygon` names the only allowed direct subtypes.
- Each permitted subtype must say how it continues: `final` (records are implicitly final), `sealed` with its own list, or `non-sealed` to reopen the hierarchy below it.
- Because the compiler knows every subtype, a `switch` over a `Shape` with a case for each one is **exhaustive**, and needs no `default`. Add a new shape and every such `switch` fails to compile until you handle it, which is exactly what you want.

Anything else is rejected:

**Example:** [`code/ch07/NotPermitted.java`](../../code/ch07/NotPermitted.java) · [how to run it](../../code/ch07/README.md#notpermittedjava) · Java 17+

```java
public class NotPermitted {
    sealed interface Shape permits Circle { }
    record Circle(double r) implements Shape { }
    record Triangle(double base) implements Shape { }     // not in the permits list
}
```

```text
NotPermitted.java:4: error: class is not allowed to extend sealed class: Shape (as it is not listed in its 'permits' clause)
    record Triangle(double base) implements Shape { }     // not in the permits list
    ^
1 error
```

## Composition over inheritance

Classic object-oriented design distinguishes **association**, **aggregation** and **composition**: an object that *uses* another, one that *has* another that can live on its own, and one that *owns* another that lives and dies with it. Modern Java style simplifies the advice: when a class just needs another class's abilities, hold an instance of it rather than extending it.

**Example:** [`code/ch07/Composition.java`](../../code/ch07/Composition.java) · [how to run it](../../code/ch07/README.md#compositionjava) · Java 10+

```java
import java.util.ArrayList;
import java.util.List;

public class Composition {
    // Inheritance would say "a Playlist IS-A ArrayList" and expose add/remove/clear to everyone.
    // Composition says "a Playlist HAS-A list" and exposes only what makes sense.
    static class Playlist {
        private final List<String> songs = new ArrayList<>();
        void add(String song) { if (!songs.contains(song)) songs.add(song); }
        int size() { return songs.size(); }
        List<String> songs() { return List.copyOf(songs); }      // Java 10: read-only copy
    }

    public static void main(String[] args) {
        Playlist p = new Playlist();
        p.add("Duke's Theme");
        p.add("Duke's Theme");      // duplicate ignored: the Playlist enforces its own rule
        p.add("Coffee Break");
        System.out.println(p.size() + " " + p.songs());
    }
}
```

```text
2 [Duke's Theme, Coffee Break]
```

Had `Playlist` extended [`ArrayList`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/ArrayList.html), every caller could `add` duplicates, `clear` it, or `set` any element, and `Playlist` could never enforce its own rules. Inheritance is right when the subclass truly *is* a specialized parent and every parent operation makes sense for it.

## Exercises

1. Remove `super("GT")` from `SportsCar`. What error do you get, and why?
2. Make `Vehicle` have only a `Vehicle(String id)` constructor. Fix both subclasses.
3. Add a `Triangle` to `SealedShapes` correctly: add it to `permits`, make it a record, and see which `switch` the compiler now flags.
4. Rewrite `Playlist` to extend `ArrayList<String>` and list three ways callers could break its "no duplicates" rule.

## IQ corner

1. **Does Java support multiple inheritance?** Not of classes. A class can implement many interfaces.
2. **Are constructors inherited?** No.
3. **Are private members inherited?** They're part of the object, but the subclass can't access them by name.
4. **Can you override a static method?** No; you hide it.
5. **What's the difference between `final` and `sealed`?** `final` allows no subclasses. `sealed` allows only the ones it lists.

## Summary

- One parent class per class, ending at `Object`. Constructors chain from the top down.
- Fields are hidden by reference type; methods are overridden by object type. Overrides can't narrow access.
- `final` closes a class, method or variable. Sealed types (Java 17) close a hierarchy to a known list, enabling exhaustive `switch` (Java 21).
- Prefer composition when "is-a" isn't really true.

**Next:** Chapter 8 covers abstraction: abstract classes and interfaces, and how interfaces gained default, static and private methods.
