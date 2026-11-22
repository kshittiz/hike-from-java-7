# A Hike from Java 7

### The Trail to Java 27: how and why Java grew up, one release at a time

**by Kshittiz Kumar** · [Read the website edition](https://hikefromjava7.com/)

This repository is the **complete edition**: a reference guide that follows each Java concept from the way it worked in Java 7, through every release that changed it, to how you'd write it today. Every code sample is a real file you can run, and an automated test suite checks every printed output against real JDKs.

| | Website edition | Complete edition (this repo) |
|---|---|---|
| Best for | Reading and learning | Reference, practice and deep dives |
| Length | Focused | Everything |
| Puzzles, exercises, interview questions | Key ones | All |
| Runnable samples | Linked | Inline, with run commands and expected outputs |

## Contents
- [Preface](book/preface.md)
- [Quick Reference](book/fast-track.md)
- [Contents](book/contents.md)

**Part I · Foundations**

- [1. Why Java Exists](book/part1/ch01-why-java-exists.md)
- [2. Types, Variables and Conversions](book/part1/ch02-types-and-variables.md)
- [3. Operators and Control Flow](book/part1/ch03-operators-and-control-flow.md)

**Part II · Objects**

- [4. Classes, Objects and References](book/part2/ch04-classes-objects-references.md)
- [5. Constructors, static and Initializer Blocks](book/part2/ch05-constructors-static-init.md)
- [6. Methods and Polymorphism](book/part2/ch06-methods-and-polymorphism.md)
- [7. Inheritance and Composition](book/part2/ch07-inheritance-and-composition.md)
- [8. Abstraction and Interfaces](book/part2/ch08-abstraction-and-interfaces.md)

**Part III · Organizing Code**

- [9. Packages, Access and Modules](book/part3/ch09-packages-access-modules.md)
- [10. Nested Classes → Lambdas](book/part3/ch10-nested-classes-to-lambdas.md)

**Part IV · Data and Memory**

- [11. Arrays → Collections](book/part4/ch11-arrays-to-collections.md)
- [12. Garbage Collection and Memory](book/part4/ch12-garbage-collection-and-memory.md)

**Part V · Errors and Text**

- [13. Exceptions](book/part5/ch13-exceptions.md)
- [14. Strings and Text](book/part5/ch14-strings-and-text.md)
- [15. Wrapper Classes and Boxing](book/part5/ch15-wrappers-and-boxing.md)

**Part VI · Modern Java**

- [16. Functional Java: Streams](book/part6/ch16-functional-java-streams.md)
- [17. Dates and Times](book/part6/ch17-dates-and-times.md)
- [18. Concurrency](book/part6/ch18-concurrency.md)
- [19. Data-Oriented Java](book/part6/ch19-data-oriented-java.md)
- [20. The Modern Toolchain](book/part6/ch20-modern-toolchain.md)

**Appendices**

- [A. Java 7 → 27 at a glance](book/at-a-glance.md)
- [B. Migration checklist](book/appendices/b-migration-checklist.md)
- [About the Author](book/about.md)
- [Copyright](book/copyright.md)

## Runnable samples

| Chapter | Tested samples |
|---|---|
| [Chapter 1](code/ch01/README.md) | 17 |
| [Chapter 2](code/ch02/README.md) | 16 |
| [Chapter 3](code/ch03/README.md) | 9 |
| [Chapter 4](code/ch04/README.md) | 7 |
| [Chapter 5](code/ch05/README.md) | 6 |
| [Chapter 6](code/ch06/README.md) | 5 |
| [Chapter 7](code/ch07/README.md) | 6 |
| [Chapter 8](code/ch08/README.md) | 6 |
| [Chapter 9](code/ch09/README.md) | 17 |
| [Chapter 10](code/ch10/README.md) | 5 |
| [Chapter 11](code/ch11/README.md) | 5 |
| [Chapter 12](code/ch12/README.md) | 3 |
| [Chapter 13](code/ch13/README.md) | 7 |
| [Chapter 14](code/ch14/README.md) | 6 |
| [Chapter 15](code/ch15/README.md) | 6 |
| [Chapter 16](code/ch16/README.md) | 6 |
| [Chapter 17](code/ch17/README.md) | 4 |
| [Chapter 18](code/ch18/README.md) | 6 |
| [Chapter 19](code/ch19/README.md) | 5 |
| [Chapter 20](code/ch20/README.md) | 4 |

Run the whole suite with any JDK 21 or newer:

```bash
bash scripts/verify.sh                          # uses `java` on your PATH
JAVA=/path/to/jdk-25/bin/java bash scripts/verify.sh
```

Samples for newer features declare the minimum Java version they need, and the suite skips them on older JDKs. Continuous integration runs the suite on Java 21, 25 and 27.

## Citing this book

Kshittiz Kumar (2026). *A Hike from Java 7: The Trail to Java 27.* https://hikefromjava7.com/ — see [`CITATION.cff`](CITATION.cff).

## License

The book's text and diagrams are © 2026 Kshittiz Kumar, all rights reserved; you may read and download them for personal, non-commercial use (see [`LICENSE`](LICENSE)). The code in [`code/`](code/) is MIT-licensed (see [`LICENSE-CODE`](LICENSE-CODE)).

Java and OpenJDK are trademarks or registered trademarks of Oracle and/or its affiliates. This book is an independent work and is not affiliated with, sponsored by, or endorsed by Oracle.
