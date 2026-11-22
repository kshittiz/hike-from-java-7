$JAVAC -d out OrderA.java OrderB.java OrderC.java
for c in OrderA OrderB OrderC; do echo "$c -> $($JAVA -cp out $c)"; done
