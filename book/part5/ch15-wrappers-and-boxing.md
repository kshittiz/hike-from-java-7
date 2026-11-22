# 15. Wrapper Classes and Boxing

> [!TIP]
> You're reading the **complete edition**, the reference guide with every deep dive and runnable sample. For a focused read, see this chapter on the [website edition](https://hikefromjava7.com/part5/ch15-wrappers-and-boxing/).

> **In this chapter:** why Java has both `int` and `Integer`, how autoboxing works, the integer cache behind one of Java's most famous puzzles, and the twisting story of the wrapper constructors you learned in Java 7: deprecated, marked for removal, then reprieved.

> [!TIP]
> **Key points**
>
> - Each primitive has a **wrapper class**: `Integer`, `Long`, `Double`, `Character`, `Boolean`… Collections and generics need objects, so they hold wrappers.
> - **Autoboxing** (**Java 5**) converts between them automatically: `Integer i = 10;` calls `Integer.valueOf(10)`.
> - `Integer.valueOf` **caches** -128 to 127, so `==` happens to work for small values and fails for larger ones. Always compare wrappers with `equals`.
> - Unboxing `null` throws `NullPointerException`. `Long.valueOf(1).equals(1)` is `false`.
> - `new Integer(10)` was deprecated in **Java 9**, marked **for removal** in **Java 16**, and reprieved in **Java 25**: still deprecated, but no longer scheduled for removal. Use `Integer.valueOf` or autoboxing either way.

## Why wrappers exist

Primitives are fast and compact, but they aren't objects: they can't be `null`, have no methods, and can't be type arguments (`List<int>` doesn't compile, even in Java 27). Wrapper classes bridge the gap:

| Primitive | Wrapper | Primitive | Wrapper |
|---|---|---|---|
| `byte` | `Byte` | `float` | `Float` |
| `short` | `Short` | `double` | `Double` |
| `int` | `Integer` | `char` | `Character` |
| `long` | `Long` | `boolean` | `Boolean` |

Wrappers are immutable and `final`, and they carry useful static helpers:

**Example:** [`code/ch15/Parsing.java`](../../code/ch15/Parsing.java) · [how to run it](../../code/ch15/README.md#parsingjava) · any Java

```java
public class Parsing {
    public static void main(String[] args) {
        int a = Integer.parseInt("27");             // String -> primitive
        Integer b = Integer.valueOf("27");          // String -> wrapper (may be cached)
        int bin = Integer.parseInt("11011", 2);     // with a radix
        System.out.println(a + b + bin);
        System.out.println(Integer.toBinaryString(27) + " " + Integer.toHexString(255) + " " + Integer.MAX_VALUE);
        System.out.println(Integer.compare(7, 27) + " " + Integer.sum(7, 20) + " " + Character.isDigit('7'));
        System.out.println(Double.parseDouble("2.7e1") + " " + Boolean.parseBoolean("TRUE"));
    }
}
```

```text
81
11011 ff 2147483647
-1 27 true
27.0 true
```

> [!NOTE]
> **From the notebook**
>
> *"parseXxx() → used to convert a String to a primitive."*
>
> And `valueOf` converts to the wrapper. Java 8 added more helpers, such as `Integer.sum`, `max`, `min` and the unsigned methods from Chapter 2, so wrappers can serve as method references (`Integer::sum`).

## Autoboxing (Java 5)

Before Java 5, you converted by hand: `list.add(new Integer(5))` and `int n = ((Integer) list.get(0)).intValue()`. Java 5 made the compiler do it:

- **Boxing**: `Integer i = 10;` becomes `Integer i = Integer.valueOf(10);`
- **Unboxing**: `int n = i;` becomes `int n = i.intValue();`
- In expressions, `i = i + 10` unboxes, adds, and boxes the result into a **new** object (wrappers are immutable).

Convenient, but it hides three traps.

## Trap 1: the integer cache

**Example:** [`code/ch15/IntegerCache.java`](../../code/ch15/IntegerCache.java) · [how to run it](../../code/ch15/README.md#integercachejava) · any Java

```java
public class IntegerCache {
    public static void main(String[] args) {
        Integer a = 127, b = 127;          // autoboxing calls Integer.valueOf
        Integer c = 128, d = 128;
        System.out.println("127 == 127 : " + (a == b));      // cached object
        System.out.println("128 == 128 : " + (c == d));      // two different objects
        System.out.println("128 equals : " + c.equals(d));
        System.out.println("unboxed    : " + (c.intValue() == d));   // mixed: unboxes, compares values
    }
}
```

```text
127 == 127 : true
128 == 128 : false
128 equals : true
unboxed    : true
```

> [!NOTE]
> **From the notebook**
>
> *"Java caches Integer objects in the range -128 to 127. Rule → Always use .equals() for comparing Wrapper objects, not ==."*
>
> Still the rule. The Java Language Specification requires boxing to reuse objects for small values (-128 to 127 for `int` and `short`, all `byte`s, `char` up to 127, and both `boolean`s), and the JDK's `Long.valueOf` caches the same range. On HotSpot the upper bound for `Integer` can be raised with `-XX:AutoBoxCacheMax`, which is exactly why code relying on `==` is fragile.

The last line shows the escape: when one side is a primitive, Java unboxes the other and compares values.

## Trap 2: unboxing `null`

**Example:** [`code/ch15/UnboxingNull.java`](../../code/ch15/UnboxingNull.java) · [how to run it](../../code/ch15/README.md#unboxingnulljava) · Java 15+

```java
import java.util.HashMap;
import java.util.Map;

public class UnboxingNull {
    public static void main(String[] args) {
        Map<String, Integer> stock = new HashMap<>();
        stock.put("coffee", 12);
        try {
            int tea = stock.get("tea");        // get returns null; unboxing it throws
            System.out.println(tea);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        int safe = stock.getOrDefault("tea", 0);          // Java 8
        System.out.println("tea = " + safe);
    }
}
```

```text
Cannot invoke "java.lang.Integer.intValue()" because the return value of "java.util.Map.get(Object)" is null
tea = 0
```

`Map.get` returns `null` for a missing key, and assigning that to an `int` calls `intValue()` on `null`. Java 14's helpful message (Chapter 13) at least explains it. Java 8's `getOrDefault` avoids it.

## Trap 3: equality across wrapper types

**Example:** [`code/ch15/WrapperEquality.java`](../../code/ch15/WrapperEquality.java) · [how to run it](../../code/ch15/README.md#wrapperequalityjava) · any Java

```java
import java.util.HashMap;
import java.util.Map;

public class WrapperEquality {
    public static void main(String[] args) {
        Long big = 1L;
        System.out.println("Long(1).equals(1)  : " + big.equals(1));     // 1 boxes to Integer
        System.out.println("Long(1).equals(1L) : " + big.equals(1L));

        Map<Long, String> byId = new HashMap<>();
        byId.put(42L, "Asha");
        System.out.println("get(42)  : " + byId.get(42));                  // Integer key: not found
        System.out.println("get(42L) : " + byId.get(42L));
    }
}
```

```text
Long(1).equals(1)  : false
Long(1).equals(1L) : true
get(42)  : null
get(42L) : Asha
```

`equals` on wrappers checks the **type** as well as the value, and `1` boxes to an `Integer`, not a `Long`. The same thing silently breaks a `Map<Long, …>` lookup with an `int` key: no error, just `null`. Code reviewers learn to look for missing `L` suffixes.

## The constructors: deprecated, then reprieved

**Example:** [`code/ch15/DeprecatedConstructor.java`](../../code/ch15/DeprecatedConstructor.java) · [how to run it](../../code/ch15/README.md#deprecatedconstructorjava) · Java 16–24, Java 25+

```java
public class DeprecatedConstructor {
    Integer old = new Integer(10);             // deprecated since Java 9
    Integer modern = Integer.valueOf(10);
}
```

Compiled with `javac -Xlint:removal` on Java 16 through 24:

```text
DeprecatedConstructor.java:2: warning: [removal] Integer(int) in Integer has been deprecated and marked for removal
    Integer old = new Integer(10);             // deprecated since Java 9
                  ^
1 warning
```

Compiled with `javac -Xlint:deprecation` on Java 25 or later, the warning is an ordinary deprecation:

```text
warning: [deprecation] Integer(int) in Integer has been deprecated
```

- **Java 9** deprecated `new Integer(...)`, `new Long(...)` and the other wrapper constructors: they always allocate, while `valueOf` can reuse cached objects.
- **Java 16** marked them **for removal** ([JEP 390](https://openjdk.org/jeps/390)) and declared the wrappers *value-based classes*: code shouldn't depend on their identity. `javac` now warns if you `synchronize` on a wrapper object, which is a bug because the cache can share that object with unrelated code.
- **Java 25** took back the "for removal" ([JDK-8354335](https://bugs.openjdk.org/browse/JDK-8354335)). The constructors are still deprecated, but there's no longer a plan to delete them.

Both decisions came from **Project Valhalla**, the long-running effort to let the JVM treat value-like objects without identity, so that a `List<Integer>` could someday be as compact as an `int[]`. The original plan expected that turning wrappers into value classes would break old code calling the constructors, so they had to go. Valhalla's design later changed so that this breakage no longer happens, and the removal was dropped. It's a good example of why "deprecated" and "deprecated for removal" mean different things. As of Java 27, Valhalla itself is still in progress.

> [!IMPORTANT]
> **Migration tip**
>
> Replace `new Integer(x)` with `Integer.valueOf(x)` (or just autoboxing), `new Boolean(b)` with `Boolean.valueOf(b)`, and `new Double(d)` with `Double.valueOf(d)`. Search the code base for `==` between wrapper types too, and for `synchronized` on wrappers.

## Exercises

1. Run `IntegerCache` with `-XX:AutoBoxCacheMax=1000`. What changes?
2. Sum a `List<Integer>` of one million numbers with a `for` loop into an `Integer` total and into an `int` total. Time both and explain the difference.
3. Write a `Map<Character, Integer>` letter counter with `merge(ch, 1, Integer::sum)`.
4. Find out what `Integer.valueOf("0x1F")` does, and how `Integer.decode` differs.

## IQ corner

1. **Why does `Integer a = 128, b = 128; a == b` give `false`?** Only -128 to 127 are cached; larger values are separate objects.
2. **What's autoboxing?** The compiler's automatic `valueOf`/`xxxValue()` conversion between primitives and wrappers (Java 5).
3. **Can unboxing throw?** Yes, `NullPointerException` when the wrapper is `null`.
4. **Why were wrapper constructors deprecated?** They always allocate and depend on object identity, while `valueOf` can cache. They were marked for removal in Java 16, but Java 25 dropped the removal plan; they remain deprecated.
5. **Is `List<int>` allowed?** No, not even in Java 27. Use `List<Integer>`, or `int[]` / `IntStream` for performance.

## Summary

- Wrappers let primitives live in collections and generics; autoboxing (Java 5) converts automatically.
- Compare wrappers with `equals`; the small-value cache makes `==` unreliable.
- Watch for unboxing `null` and for cross-type `equals` (`Long` vs. `Integer`).
- Wrapper constructors: deprecated (9), marked for removal (16), removal dropped (25). Use `valueOf`.

**Next:** Part VI turns to modern Java. Chapter 16 covers the biggest change in Java's history: lambdas and streams.
