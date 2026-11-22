# B. Migration Checklist: Java 7/8 → Java 25

A practical, ordered checklist for moving an older code base to the current LTS release. Each item links to the chapter that explains it.

> [!TIP]
> **Key points**
>
> 1. Upgrade your **build tools and plugins** first, then compile with `--release 25`.
> 2. Add back the **Java EE modules removed in Java 11** as normal dependencies (now Jakarta EE).
> 3. Find **JDK-internal API use** with `jdeps --jdk-internals` and replace it.
> 4. Remove **obsolete JVM flags** (PermGen, CMS, old GC logging).
> 5. Run your tests, then **modernize gradually**: records, switch expressions, text blocks, virtual threads.

## Phase 1: make it build

- [ ] **Upgrade the build.** Use a current Maven (with the compiler and Surefire plugins) or Gradle, and set `<maven.compiler.release>25</maven.compiler.release>` or `options.release = 25`. Old plugin versions are the most common first failure. (Chapter 1)
- [ ] **Upgrade libraries that use bytecode tricks**: Lombok, Mockito, ByteBuddy, ASM, Spring, Hibernate, Jackson. Old versions fail on newer class-file versions.
- [ ] **Replace removed Java EE modules (Java 11).** JAXB, JAX-WS, `javax.annotation`, JavaBeans Activation and CORBA left the JDK. Add their Jakarta EE artifacts as dependencies; most moved from `javax.*` to `jakarta.*` packages.
- [ ] **Replace Nashorn (removed in Java 15)** with GraalJS or another engine if you run JavaScript.
- [ ] **Remove applet and Java Web Start code.** Web Start went in Java 11; the Applet API was removed in Java 26.

## Phase 2: make it run

- [ ] **Find internal API use**: `jdeps --jdk-internals your-app.jar`. Since Java 17, `--illegal-access` no longer exists, so reflective access into JDK internals fails unless you `--add-opens` it. Prefer upgrading the library that does it. (Chapter 9)
- [ ] **Remove obsolete JVM flags.** `-XX:PermSize`/`MaxPermSize` (gone in 8), `-XX:+UseConcMarkSweepGC` (gone in 14), `-XX:+PrintGCDetails`-style logging (use `-Xlog:gc*`). The JVM refuses to start with some removed flags. (Chapter 12)
- [ ] **Check the default charset.** Since Java 18 it's UTF-8 everywhere. Code that read files without naming a charset on Windows may now decode differently. (Chapter 2)
- [ ] **Check date and number formatting.** Since Java 9 the JDK uses CLDR locale data; some formatted month names, separators and symbols changed. Pin a `Locale` in formats that must be exact. (Chapter 17)
- [ ] **Security Manager.** Deprecated in 17 and permanently disabled in 24. Code that calls `System.setSecurityManager` must change.
- [ ] **`Thread.stop`, `suspend` and `resume`** no longer work: depending on the release they throw `UnsupportedOperationException` or have been removed. Use interruption instead.
- [ ] **Watch for new warnings** about `sun.misc.Unsafe` memory access (deprecated for removal), JNI use and dynamically loaded agents. They're warnings today and errors in future releases.

## Phase 3: clean up deprecations

- [ ] `new Integer(…)` and other wrapper constructors → `valueOf` or autoboxing. (Chapter 15)
- [ ] `finalize()` → try-with-resources plus `Cleaner`. Test with `--finalization=disabled`. (Chapter 12)
- [ ] Hand-written `try`/`finally` closing → try-with-resources. (Chapter 13)
- [ ] `Date`/`Calendar`/`SimpleDateFormat` → `java.time`. (Chapter 17)
- [ ] Compile with `-Xlint:all` (or at least `-Xlint:deprecation,removal`) and work through the list.

## Phase 4: modernize where it pays

Do this gradually, as you touch code, not in one giant change.

| Replace | With | Since | Chapter |
|---|---|---|---|
| Data classes with hand-written `equals`/`hashCode` | records | 16 | 4, 19 |
| `switch` statements with `break` | arrow-form switch expressions | 14 | 3 |
| `instanceof` followed by a cast | type patterns | 16 | 19 |
| `if/else instanceof` ladders, visitors | sealed interface + pattern `switch` | 21 | 19 |
| Escaped multi-line strings | text blocks | 15 | 14 |
| Anonymous classes for callbacks | lambdas and method references | 8 | 10 |
| Loops that filter, map and collect | streams | 8 | 16 |
| `list.get(list.size() - 1)` | `list.getLast()` | 21 | 11 |
| `Collections.unmodifiableList(Arrays.asList(…))` | `List.of(…)` (no nulls!) | 9 | 11 |
| `HttpURLConnection` or third-party HTTP clients | `java.net.http.HttpClient` | 11 | 20 |
| Large thread pools for blocking I/O | virtual threads | 21 | 18 |
| `ThreadLocal` for request context | scoped values | 25 | 18 |
| Verbose local variable types | `var`, where it helps readability | 10 | 2 |

## Phase 5: ship it

- [ ] Run your test suite on the new JDK in CI, alongside the old one, until you switch.
- [ ] Re-measure memory and latency. G1 is the default collector, and since Java 27, compact object headers are on by default. Start from the defaults before adding tuning flags. (Chapter 12)
- [ ] Consider a `jlink`-built runtime for containers. (Chapter 20)
- [ ] Plan the next upgrade: LTS releases arrive every two years (21 → 25 → 29), and the steps get smaller when you keep up.
