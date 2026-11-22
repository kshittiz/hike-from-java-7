# Samples for chapter 20

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## HttpClientDemo.java

Source: [HttpClientDemo.java](HttpClientDemo.java)

**The standard HTTP Client (Java 11) calling a local server.** (Java 11+)

```bash
javac -d out HttpClientDemo.java && java -cp out HttpClientDemo
```

Expected output:

```text
200 Java 27 shipped on 2026-09-15
```

**jdeps lists exactly the JDK modules a class needs, ready for jlink.** (Java 11+)

```bash
javac -d out HttpClientDemo.java && $JDEPS --print-module-deps out/HttpClientDemo.class
```

Expected output:

```text
java.base,java.net.http,jdk.httpserver
```

## MarkdownDocs.java

Source: [MarkdownDocs.java](MarkdownDocs.java)

**Markdown documentation comments with /// (rendered by javadoc from Java 23; plain comments to older compilers).** (any Java)

```bash
javac -d out MarkdownDocs.java && java -cp out MarkdownDocs
```

Expected output:

```text
Java 27 shipped in 2026
Java 21 shipped in 2023
```

## StaticSite.java

Source: [StaticSite.java](StaticSite.java)

**Serving files with SimpleFileServer, the API behind jwebserver (Java 18+).** (Java 18+)

```bash
javac -d out StaticSite.java && java -cp out StaticSite
```

Expected output:

```text
200 <h1>A Hike from Java 7</h1>
```
