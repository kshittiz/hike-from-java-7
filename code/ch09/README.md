# Samples for Chapter 9

Every sample here is compiled and run by [`scripts/verify.sh`](../../scripts/verify.sh), and its output is compared with the expected output below.

Run one sample from this folder with the command shown, or run them all from the repository root with `bash scripts/verify.sh`.

## ModuleImport.java

Source: [ModuleImport.java](ModuleImport.java)

**import module java.base; (Java 25+).** (Java 25+)

```bash
javac -d out ModuleImport.java && java -cp out ModuleImport
```

Expected output:

```text
{17=2, 27=2, 7=1}
chapter09.md
```

## modules/com.trail.app/com/trail/app/App.java

Source: [modules/com.trail.app/com/trail/app/App.java](modules/com.trail.app/com/trail/app/App.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.trail.sneaky/com/trail/sneaky/Sneaky.java:4: error: package com.trail.greet.internal is not visible
import com.trail.greet.internal.Formatter;
                      ^
  (package com.trail.greet.internal is declared in module com.trail.greet, which does not export it)
1 error
```

**Two modules: one exports a package, the other requires it (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.app && java -p mods -m com.trail.app/com.trail.app.App
```

Expected output:

```text
[ Hello, modules ]
running in module: com.trail.app
```

## modules/com.trail.app/module-info.java

Source: [modules/com.trail.app/module-info.java](modules/com.trail.app/module-info.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.trail.sneaky/com/trail/sneaky/Sneaky.java:4: error: package com.trail.greet.internal is not visible
import com.trail.greet.internal.Formatter;
                      ^
  (package com.trail.greet.internal is declared in module com.trail.greet, which does not export it)
1 error
```

**Two modules: one exports a package, the other requires it (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.app && java -p mods -m com.trail.app/com.trail.app.App
```

Expected output:

```text
[ Hello, modules ]
running in module: com.trail.app
```

## modules/com.trail.greet/com/trail/greet/Greetings.java

Source: [modules/com.trail.greet/com/trail/greet/Greetings.java](modules/com.trail.greet/com/trail/greet/Greetings.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.trail.sneaky/com/trail/sneaky/Sneaky.java:4: error: package com.trail.greet.internal is not visible
import com.trail.greet.internal.Formatter;
                      ^
  (package com.trail.greet.internal is declared in module com.trail.greet, which does not export it)
1 error
```

**Two modules: one exports a package, the other requires it (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.app && java -p mods -m com.trail.app/com.trail.app.App
```

Expected output:

```text
[ Hello, modules ]
running in module: com.trail.app
```

## modules/com.trail.greet/com/trail/greet/internal/Formatter.java

Source: [modules/com.trail.greet/com/trail/greet/internal/Formatter.java](modules/com.trail.greet/com/trail/greet/internal/Formatter.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.trail.sneaky/com/trail/sneaky/Sneaky.java:4: error: package com.trail.greet.internal is not visible
import com.trail.greet.internal.Formatter;
                      ^
  (package com.trail.greet.internal is declared in module com.trail.greet, which does not export it)
1 error
```

**Two modules: one exports a package, the other requires it (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.app && java -p mods -m com.trail.app/com.trail.app.App
```

Expected output:

```text
[ Hello, modules ]
running in module: com.trail.app
```

## modules/com.trail.greet/module-info.java

Source: [modules/com.trail.greet/module-info.java](modules/com.trail.greet/module-info.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.trail.sneaky/com/trail/sneaky/Sneaky.java:4: error: package com.trail.greet.internal is not visible
import com.trail.greet.internal.Formatter;
                      ^
  (package com.trail.greet.internal is declared in module com.trail.greet, which does not export it)
1 error
```

**Two modules: one exports a package, the other requires it (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.app && java -p mods -m com.trail.app/com.trail.app.App
```

Expected output:

```text
[ Hello, modules ]
running in module: com.trail.app
```

## packages/com/trail/app/Main.java

Source: [packages/com/trail/app/Main.java](packages/com/trail/app/Main.java)

**Two packages compiled into a directory tree, plus a static import.** (any Java)

```bash
javac -d out packages/com/trail/util/Greeter.java packages/com/trail/app/Main.java && java -cp out com.trail.app.Main
```

Expected output:

```text
Hello, packages
max = 27
```

## packages/com/trail/util/Greeter.java

Source: [packages/com/trail/util/Greeter.java](packages/com/trail/util/Greeter.java)

**Two packages compiled into a directory tree, plus a static import.** (any Java)

```bash
javac -d out packages/com/trail/util/Greeter.java packages/com/trail/app/Main.java && java -cp out com.trail.app.Main
```

Expected output:

```text
Hello, packages
max = 27
```

## protectedaccess/sale/Discounted.java

Source: [protectedaccess/sale/Discounted.java](protectedaccess/sale/Discounted.java)

**protected members are reachable from another package only through inheritance.** (any Java)

```bash
javac -d out protectedaccess/shop/Product.java protectedaccess/sale/Discounted.java
```

Expected output:

```text
protectedaccess/sale/Discounted.java:10: error: price has protected access in Product
    double peek(Product other) { return other.price; }
                                             ^
1 error
```

## protectedaccess/shop/Product.java

Source: [protectedaccess/shop/Product.java](protectedaccess/shop/Product.java)

**protected members are reachable from another package only through inheritance.** (any Java)

```bash
javac -d out protectedaccess/shop/Product.java protectedaccess/sale/Discounted.java
```

Expected output:

```text
protectedaccess/sale/Discounted.java:10: error: price has protected access in Product
    double peek(Product other) { return other.price; }
                                             ^
1 error
```

## sneaky/com.trail.sneaky/com/trail/sneaky/Sneaky.java

Source: [sneaky/com.trail.sneaky/com/trail/sneaky/Sneaky.java](sneaky/com.trail.sneaky/com/trail/sneaky/Sneaky.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.trail.sneaky/com/trail/sneaky/Sneaky.java:4: error: package com.trail.greet.internal is not visible
import com.trail.greet.internal.Formatter;
                      ^
  (package com.trail.greet.internal is declared in module com.trail.greet, which does not export it)
1 error
```

## sneaky/com.trail.sneaky/module-info.java

Source: [sneaky/com.trail.sneaky/module-info.java](sneaky/com.trail.sneaky/module-info.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.trail.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.trail.sneaky/com/trail/sneaky/Sneaky.java:4: error: package com.trail.greet.internal is not visible
import com.trail.greet.internal.Formatter;
                      ^
  (package com.trail.greet.internal is declared in module com.trail.greet, which does not export it)
1 error
```
