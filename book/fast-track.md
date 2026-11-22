# Quick Reference

The key points of every chapter on one page. Follow any heading to read the full chapter.

## [1. Why Java Exists](part1/ch01-why-java-exists.md)

- `javac` compiles `.java` into platform-neutral **bytecode** (`.class`). A platform-specific **JVM** runs it: load → verify → interpret → JIT-compile the hot parts.
- Objects live on the **heap**, method calls on per-thread **stacks**. Class metadata moved from PermGen to **Metaspace** in **Java 8**.
- **Java 11** dropped the separate JRE download; **Java 9**'s `jlink` builds trimmed runtimes instead.
- Every class file records its Java version: *major − 44*. Mismatch → `UnsupportedClassVersionError`. Compile with `--release N`.
- Running Java got lighter: `jshell` (**9**), `java Hello.java` (**11**), multi-file launch (**22**), and in **Java 25**, a whole program can be `void main() { IO.println("Hi"); }`.

## [2. Types, Variables and Conversions](part1/ch02-types-and-variables.md)

- **Eight primitives** with fixed sizes on every platform: `byte` 8 bits, `short` 16, `char` 16 (unsigned), `int` 32, `long` 64, `float` 32, `double` 64, plus `boolean`. Everything else is a **reference**.
- **Widening** (small → big) is automatic. **Narrowing** needs a cast and silently drops bits: `(byte) 130` is `-126`. Use `Math.addExact` (**Java 8**) when overflow must fail loudly.
- `byte + byte` is an `int`. Arithmetic on `byte`, `short` and `char` always happens in `int`.
- Fields get default values, local variables don't. Use `BigDecimal` for money, never `double`.
- `var` (**Java 10**) infers a local's type. `_` (**Java 22**) names a variable you never use. UTF-8 is the default charset since **Java 18**.

## [3. Operators and Control Flow](part1/ch03-operators-and-control-flow.md)

- Expressions evaluate **left to right**; precedence decides grouping. `i++ + ++i` with `i = 5` is `12`.
- Integer `/` **truncates toward zero** and `%` takes the **sign of the dividend**: `-7 % 3` is `-1`. Use `Math.floorMod` (**Java 8**) for a remainder that's never negative.
- `&&` and `||` **short-circuit**; `&` and `|` always evaluate both sides. `>>` keeps the sign, `>>>` fills with zeros.
- `switch` grew up: strings (**Java 7**), arrows and switch *expressions* with `yield` (**Java 14**), type patterns, guards and `case null` (**Java 21**).
- No new arithmetic or logical operators arrived between Java 7 and 27; Java 8's `->` and `::` are syntax for lambdas and method references. `instanceof` gained patterns (**Java 16**), which removed most casts.

## [4. Classes, Objects and References](part2/ch04-classes-objects-references.md)

- A **class** is a blueprint; an **object** is an instance created at run time with `new`, always on the **heap**.
- A **reference variable** holds a reference to an object, not the object. Copying a reference shares the object.
- Printing an object shows `ClassName@` plus its **hash code in hex**. That's not a memory address.
- `==` compares references; `equals()` compares meaning, but only if the class overrides it.
- A data class needed hand-written `equals`, `hashCode` and `toString` in Java 7. A **record** (**Java 16**) generates them: `record Money(String currency, long cents) {}`.

## [5. Constructors, static and Initializer Blocks](part2/ch05-constructors-static-init.md)

- A **constructor** initializes a new object. If you write none, the compiler adds a no-argument one.
- **Order:** static blocks run once when the class initializes; then, for each object, instance initializers run, then the constructor body.
- **`this(...)`** calls another constructor of the same class; **`super(...)`** calls the parent's. Until **Java 25** one of them had to be the very first statement.
- **`static`** members belong to the class, shared by all objects. Static methods have no `this`.
- Since **Java 7**, a class needs a `main` to be launched; its static block no longer runs first.
- **Java 25** (flexible constructor bodies): you can validate and prepare arguments *before* `super(...)`.

## [6. Methods and Polymorphism](part2/ch06-methods-and-polymorphism.md)

- Java is **always pass-by-value**. For objects, the value passed is a *reference*, so a method can change the object but can't make the caller's variable point elsewhere.
- **Overloading** = same name, different parameter lists. The return type alone doesn't count. The compiler picks the overload from **static types**.
- **Overriding** = a subclass replaces an inherited method. The JVM picks the override from the **object's runtime type** (dynamic binding).
- Static methods are **hidden**, not overridden.
- **Varargs** (**Java 5**) accept zero or more arguments. **Method references** (**Java 8**) such as `Integer::parseInt` turn a method into a value.

## [7. Inheritance and Composition](part2/ch07-inheritance-and-composition.md)

- `class Car extends Vehicle`: a class has **one** parent class. Every chain ends at `java.lang.Object`.
- Parent constructors always run first: each constructor begins with `this(...)` or `super(...)`, and the compiler inserts `super()` if you write neither.
- **Fields are hidden** (the reference type decides); **methods are overridden** (the object decides).
- `final` stops extension or overriding. **Sealed** classes and interfaces (**Java 17**) allow extension only by a listed set of types, which makes `switch` exhaustive (**Java 21**).
- Prefer **composition** (a class *has* another object) over inheritance unless the relationship is truly "is-a."

## [8. Abstraction and Interfaces](part2/ch08-abstraction-and-interfaces.md)

- An **abstract class** can't be instantiated. It can have constructors, fields, concrete methods and abstract methods.
- An **interface** is a contract. Its fields are always `public static final` constants.
- **Java 8** added `default` and `static` methods to interfaces; **Java 9** added `private` ones. That's how `List.sort` and `Collection.stream` were added without breaking existing implementations.
- If two interfaces give a class the same default method, the class must override it and can pick one with `Walker.super.move()`.
- An interface with exactly one abstract method is a **functional interface**, and a lambda can implement it (**Java 8**).

## [9. Packages, Access and Modules](part3/ch09-packages-access-modules.md)

- A **package** is a namespace and a folder: `package com.notes.util;` lives in `com/notes/util/`. Compile with `javac -d out` to build the folder tree.
- Four access levels: `private` < package-private (no keyword) < `protected` < `public`. `protected` from another package works **only through inheritance**.
- Before **Java 9**, `public` meant "anyone on the classpath." **Modules** (`module-info.java`) add `requires` and `exports`, so a public class in an unexported package is invisible outside its module.
- Since **Java 16/17**, JDK internals are strongly encapsulated; `--illegal-access` is gone.
- **Java 25** added `import module java.base;` to import every package a module exports in one line.

## [10. Nested Classes → Lambdas](part3/ch10-nested-classes-to-lambdas.md)

- **Static nested** class: a class inside a class, with no tie to an outer object.
- **Inner** class: tied to an outer object (`outer.new Inner()`), and sees its fields.
- **Local** and **anonymous** classes live inside a method. Since **Java 8** they (and lambdas) can use local variables that are **effectively final**; before, those variables had to be declared `final`.
- **Lambdas** (**Java 8**) replace one-method anonymous classes. Inside a lambda, `this` means the *enclosing* object.
- **Java 16** let inner classes declare static members, even `main`, and allowed local records, enums and interfaces.

## [11. Arrays → Collections](part4/ch11-arrays-to-collections.md)

- An **array** has a fixed length, set at creation. Elements get default values. `arr.length` is a field.
- A 2-D array is an **array of arrays**; rows can have different lengths.
- Arrays are **covariant**: a `String[]` is an `Object[]`, and storing the wrong type fails at run time with `ArrayStoreException`. Generic collections catch it at compile time.
- `Arrays.asList` is fixed-size; `List.of` (**Java 9**) is unmodifiable and rejects `null`.
- **Java 21** sequenced collections added `getFirst()`, `getLast()`, `addFirst()` and `reversed()` to lists, ordered sets and ordered maps.

## [12. Garbage Collection and Memory](part4/ch12-garbage-collection-and-memory.md)

- An object becomes **eligible for collection** when no chain of references from a *GC root* (live stack variables, static fields, active threads) reaches it. Cycles don't keep objects alive.
- `System.gc()` is only a **request**.
- `finalize()` was deprecated in **Java 9** and marked for removal in **Java 18**. Use `try`-with-resources, and `Cleaner` (**Java 9**) as a safety net.
- **Collectors:** Parallel was the server default in Java 7; **G1** became the default in **Java 9**; **ZGC** and **Shenandoah** became production-ready in **Java 15**; CMS was removed in **Java 14**.
- **Java 27** makes G1 the default everywhere and turns on **compact object headers** by default.

## [13. Exceptions](part5/ch13-exceptions.md)

- `Throwable` splits into `Error` (don't catch) and `Exception`. `RuntimeException` and its subclasses are **unchecked**; other exceptions are **checked** and must be caught or declared with `throws`.
- Catch blocks are tried top to bottom, so put subclasses **before** their parents.
- `finally` always runs, except when the JVM stops (`System.exit`, a crash). A `return` in `finally` overrides the `try`'s return.
- **Java 7:** try-with-resources, multi-catch and suppressed exceptions. **Java 9:** try-with-resources on an existing effectively final variable.
- **Java 14/15:** helpful `NullPointerException` messages name the exact `null` expression.

## [14. Strings and Text](part5/ch14-strings-and-text.md)

- `String` is **immutable**: methods like `concat` and `toUpperCase` return a new string. Use `StringBuilder` (**Java 5**) to build text in loops.
- String **literals** are pooled and shared. `new String("x")` always creates a new object. Compare with `equals()`, never `==`.
- **Java 7** moved the pool from PermGen to the heap. **Java 9** introduced compact strings and faster `+` concatenation.
- New methods: `String.join` (**8**), `isBlank`, `strip`, `repeat`, `lines` (**11**), `formatted` (**15**).
- **Text blocks** (**Java 15**) are multi-line literals with `"""`. String templates were previewed in Java 21–22 and **withdrawn**.

## [15. Wrapper Classes and Boxing](part5/ch15-wrappers-and-boxing.md)

- Each primitive has a **wrapper class**: `Integer`, `Long`, `Double`, `Character`, `Boolean`… Collections and generics need objects, so they hold wrappers.
- **Autoboxing** (**Java 5**) converts between them automatically: `Integer i = 10;` calls `Integer.valueOf(10)`.
- `Integer.valueOf` **caches** -128 to 127, so `==` happens to work for small values and fails for larger ones. Always compare wrappers with `equals`.
- Unboxing `null` throws `NullPointerException`. `Long.valueOf(1).equals(1)` is `false`.
- `new Integer(10)` was deprecated in **Java 9**, marked **for removal** in **Java 16**, and reprieved in **Java 25**: still deprecated, but no longer scheduled for removal. Use `Integer.valueOf` or autoboxing either way.

## [16. Functional Java: Streams](part6/ch16-functional-java-streams.md)

- A stream pipeline is **source → intermediate operations → one terminal operation**: `list.stream().filter(…).map(…).toList()` (**Java 8**; `toList()` **Java 16**).
- Streams are **lazy**: nothing runs until the terminal operation, and elements flow through one at a time, so `findFirst` can stop early.
- **Collectors** group, partition, count and join: `groupingBy`, `partitioningBy`, `joining`.
- `Optional` (**Java 8**) represents "maybe a value." Use it for return types, not fields or parameters.
- Later additions: `takeWhile`, `dropWhile`, bounded `iterate` (**9**), `teeing` (**12**), `mapMulti` (**16**), and **gatherers** (**24**) for custom intermediate operations.

## [17. Dates and Times](part6/ch17-dates-and-times.md)

- Java 7's `Date` is mutable, `Calendar` months start at **0**, `Date.getYear()` counts from **1900**, and `SimpleDateFormat` isn't thread-safe.
- **Java 8**'s `java.time` ([JSR 310](https://jcp.org/en/jsr/detail?id=310)) has immutable, thread-safe types: `LocalDate`, `LocalTime`, `LocalDateTime`, `ZonedDateTime`, `Instant`, `Duration`, `Period`.
- Pick by meaning: a birthday is a `LocalDate`, a meeting is a `ZonedDateTime`, a log timestamp is an `Instant`.
- `ZonedDateTime` handles daylight saving time correctly. `DateTimeFormatter` is immutable and safe to share.

## [18. Concurrency](part6/ch18-concurrency.md)

- Two threads updating the same variable can **lose updates**. Fix it with `synchronized`, locks, or atomic classes such as `AtomicInteger` (**Java 5**).
- Don't manage threads by hand: use an `ExecutorService` (**Java 5**). Combine async results with `CompletableFuture` (**Java 8**).
- **Virtual threads** (**Java 21**) are cheap threads managed by the JVM. Millions can block on I/O at once. Use one per task: `Executors.newVirtualThreadPerTaskExecutor()`.
- **Scoped values** (**Java 25**) share per-call context safely; they're a better `ThreadLocal`.
- **Structured concurrency** treats related tasks as one unit. It's still a **preview** in Java 27.

## [19. Data-Oriented Java](part6/ch19-data-oriented-java.md)

- **Records** (**Java 16**): immutable data carriers with generated constructor, accessors, `equals`, `hashCode` and `toString`. Validate in a compact constructor.
- **Sealed interfaces** (**Java 17**): a closed set of alternatives, such as `Card`, `Upi` or `Cash`.
- **Record patterns** (**Java 21**) deconstruct records in `switch` and `instanceof`: `case Card(String last4, long cents)`.
- Over a sealed type, `switch` is **exhaustive**: no `default`, and adding a new alternative becomes a compile error everywhere it's unhandled.
- `_` (**Java 22**) ignores parts you don't need. Primitive patterns are still in preview in Java 27.

## [20. The Modern Toolchain](part6/ch20-modern-toolchain.md)

- **HTTP Client** (**Java 11**) replaced `HttpURLConnection`; **Java 26** added HTTP/3.
- **`jwebserver`** and `SimpleFileServer` (**Java 18**) serve static files for testing.
- **`jdeps`** finds the modules your code needs; **`jlink`** (**Java 9**) builds a runtime with only those; **`jpackage`** (**Java 16**) makes native installers.
- **JDK Flight Recorder** became free and open source in **Java 11**: low-overhead production profiling.
- **Markdown doc comments** (`///`, **Java 23**), `@snippet` (**Java 18**), and ahead-of-time caches (**Java 24–25**) round out the toolbox.

## [B. Migration checklist](appendices/b-migration-checklist.md)

1. Upgrade your **build tools and plugins** first, then compile with `--release 25`.
2. Add back the **Java EE modules removed in Java 11** as normal dependencies (now Jakarta EE).
3. Find **JDK-internal API use** with `jdeps --jdk-internals` and replace it.
4. Remove **obsolete JVM flags** (PermGen, CMS, old GC logging).
5. Run your tests, then **modernize gradually**: records, switch expressions, text blocks, virtual threads.
