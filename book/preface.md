# Preface

Java was my first programming language. I met it in grade 7, in the BlueJ editor, writing small programs whose only job was to print something to the screen and make me feel like a magician when they did. A few years later, in grades 11 and 12, I learned C++, and with it pointers, manual memory management and a new respect for what Java had been quietly doing for me.

In the summer of 2013, during my bachelor's degree, I took a course on Java that went far deeper than anything before it. It didn't stop at syntax. It opened up the JVM: how a class is loaded and verified, why `main` must be `static`, what really happens when you cast 130 to a `byte`, why a `finally` block can override a `return`. I filled almost two hundred handwritten pages with rules, diagrams and interview questions. Java 7 was the current release.

Those notes went with me into my first job, where I built Java and JEE applications. Since then I've written production code in C, C++, C# and Go. But I kept coming back to Java, for personal projects and for the pleasure of it. Every language teaches you something; Java was the one I loved first, and the one I still enjoy most.

Years later I opened that old notebook again, and two things struck me. The fundamentals had aged remarkably well: the JVM still loads, verifies and JIT-compiles classes much as my notes described. But almost everything *around* those fundamentals had changed. Java now has lambdas and streams, `var`, records, sealed types, pattern matching and virtual threads, and a complete program can be a single line. Twenty releases had shipped since the one I learned.

This book is my tribute to the language, and my attempt to connect the Java I learned with the Java we write today.

## What makes this book different

- **It's a hike, not a catalogue.** Features appear in the story of the concept they changed, not in a list of release notes. Each chapter starts where a Java 7 developer stands, then walks forward release by release, asking at each step: *what problem did this change solve?* If you know why a feature exists, you'll know when to use it.
- **It corrects the classics.** Some things every Java 7 course taught were never quite right, like "the verifier removes viruses" or "a boolean takes one bit." Where my old notes were wrong, the book says so.
- **Every example runs.** Each example is a complete program, and every output shown comes from running it. An automated test suite checks them all against current Java releases.
- **It's free to read.**

## How to read it

Read it front to back if you're new to Java. If you already write Java, skim each chapter's **Key points** and stop wherever something surprises you. The [Quick Reference](fast-track.md) collects every chapter's key points on one page, and [Java 7 → 27 at a glance](at-a-glance.md) maps the whole trail.

## Conventions

| You'll see | It means |
|---|---|
| **Java 21** | The release that introduced or finalized a feature |
| **Key points** box | The chapter's main ideas, for skimmers |
| **What changed** box | How a release changed the concept |
| **Common mistake** box | A trap, or a correction to a classic misconception |
| **Migration tip** box | Advice for upgrading older code |
| **From the notebook** box | A rule or interview question from my 2013 course notes, checked against modern Java |
| **Rule** box | A short rule worth memorizing |
| `code/ch01/Hello.java` | The example's source file, runnable from the book's GitHub repository |

## Thank you

To the teachers who first put BlueJ in front of a curious twelve-year-old, to the instructor whose Java course filled that notebook, and to the OpenJDK community, whose careful work over twenty releases gave this book its story.

— Kshittiz Kumar, 2026
