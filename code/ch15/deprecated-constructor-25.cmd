$JAVAC -Xlint:deprecation -d out DeprecatedConstructor.java 2>&1 | grep -o "warning: \[[a-z]*\].*"
