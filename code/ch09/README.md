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

## modules/com.notes.app/com/notes/app/App.java

Source: [modules/com.notes.app/com/notes/app/App.java](modules/com.notes.app/com/notes/app/App.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.notes.sneaky/com/notes/sneaky/Sneaky.java:3: error: package com.notes.greet.internal is not visible
import com.notes.greet.internal.Formatter;     // a public class in a package that is not exported
                      ^
  (package com.notes.greet.internal is declared in module com.notes.greet, which does not export it)
1 error
```

**Two modules: one exports a package, the other requires it (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.app && java -p mods -m com.notes.app/com.notes.app.App
```

Expected output:

```text
[ Hello, modules ]
running in module: com.notes.app
```

## modules/com.notes.app/module-info.java

Source: [modules/com.notes.app/module-info.java](modules/com.notes.app/module-info.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.notes.sneaky/com/notes/sneaky/Sneaky.java:3: error: package com.notes.greet.internal is not visible
import com.notes.greet.internal.Formatter;     // a public class in a package that is not exported
                      ^
  (package com.notes.greet.internal is declared in module com.notes.greet, which does not export it)
1 error
```

**Two modules: one exports a package, the other requires it (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.app && java -p mods -m com.notes.app/com.notes.app.App
```

Expected output:

```text
[ Hello, modules ]
running in module: com.notes.app
```

## modules/com.notes.greet/com/notes/greet/Greetings.java

Source: [modules/com.notes.greet/com/notes/greet/Greetings.java](modules/com.notes.greet/com/notes/greet/Greetings.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.notes.sneaky/com/notes/sneaky/Sneaky.java:3: error: package com.notes.greet.internal is not visible
import com.notes.greet.internal.Formatter;     // a public class in a package that is not exported
                      ^
  (package com.notes.greet.internal is declared in module com.notes.greet, which does not export it)
1 error
```

**Two modules: one exports a package, the other requires it (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.app && java -p mods -m com.notes.app/com.notes.app.App
```

Expected output:

```text
[ Hello, modules ]
running in module: com.notes.app
```

## modules/com.notes.greet/com/notes/greet/internal/Formatter.java

Source: [modules/com.notes.greet/com/notes/greet/internal/Formatter.java](modules/com.notes.greet/com/notes/greet/internal/Formatter.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.notes.sneaky/com/notes/sneaky/Sneaky.java:3: error: package com.notes.greet.internal is not visible
import com.notes.greet.internal.Formatter;     // a public class in a package that is not exported
                      ^
  (package com.notes.greet.internal is declared in module com.notes.greet, which does not export it)
1 error
```

**Two modules: one exports a package, the other requires it (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.app && java -p mods -m com.notes.app/com.notes.app.App
```

Expected output:

```text
[ Hello, modules ]
running in module: com.notes.app
```

## modules/com.notes.greet/module-info.java

Source: [modules/com.notes.greet/module-info.java](modules/com.notes.greet/module-info.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.notes.sneaky/com/notes/sneaky/Sneaky.java:3: error: package com.notes.greet.internal is not visible
import com.notes.greet.internal.Formatter;     // a public class in a package that is not exported
                      ^
  (package com.notes.greet.internal is declared in module com.notes.greet, which does not export it)
1 error
```

**Two modules: one exports a package, the other requires it (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.app && java -p mods -m com.notes.app/com.notes.app.App
```

Expected output:

```text
[ Hello, modules ]
running in module: com.notes.app
```

## packages/com/notes/app/Main.java

Source: [packages/com/notes/app/Main.java](packages/com/notes/app/Main.java)

**Two packages compiled into a directory tree, plus a static import.** (any Java)

```bash
javac -d out packages/com/notes/util/Greeter.java packages/com/notes/app/Main.java && java -cp out com.notes.app.Main
```

Expected output:

```text
Hello, packages
max = 27
```

## packages/com/notes/util/Greeter.java

Source: [packages/com/notes/util/Greeter.java](packages/com/notes/util/Greeter.java)

**Two packages compiled into a directory tree, plus a static import.** (any Java)

```bash
javac -d out packages/com/notes/util/Greeter.java packages/com/notes/app/Main.java && java -cp out com.notes.app.Main
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
protectedaccess/sale/Discounted.java:8: error: price has protected access in Product
    double peek(Product other) { return other.price; }     // not OK: through another Product reference
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
protectedaccess/sale/Discounted.java:8: error: price has protected access in Product
    double peek(Product other) { return other.price; }     // not OK: through another Product reference
                                             ^
1 error
```

## sneaky/com.notes.sneaky/com/notes/sneaky/Sneaky.java

Source: [sneaky/com.notes.sneaky/com/notes/sneaky/Sneaky.java](sneaky/com.notes.sneaky/com/notes/sneaky/Sneaky.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.notes.sneaky/com/notes/sneaky/Sneaky.java:3: error: package com.notes.greet.internal is not visible
import com.notes.greet.internal.Formatter;     // a public class in a package that is not exported
                      ^
  (package com.notes.greet.internal is declared in module com.notes.greet, which does not export it)
1 error
```

## sneaky/com.notes.sneaky/module-info.java

Source: [sneaky/com.notes.sneaky/module-info.java](sneaky/com.notes.sneaky/module-info.java)

**A public class in an unexported package is invisible to other modules (Java 9+).** (Java 9+)

```bash
javac -d mods --module-source-path modules --module com.notes.greet
javac -p mods -d sneakyout $(find sneaky -name "*.java")
```

Expected output:

```text
sneaky/com.notes.sneaky/com/notes/sneaky/Sneaky.java:3: error: package com.notes.greet.internal is not visible
import com.notes.greet.internal.Formatter;     // a public class in a package that is not exported
                      ^
  (package com.notes.greet.internal is declared in module com.notes.greet, which does not export it)
1 error
```
