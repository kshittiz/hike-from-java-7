$JAVAC --enable-preview --release 21 -d out OrderA.java OrderB.java OrderC.java 2>/dev/null
for c in OrderA OrderB OrderC; do echo "$c -> $($JAVA --enable-preview -cp out $c)"; done
