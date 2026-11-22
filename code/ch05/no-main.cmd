$JAVAC -d out NoMain.java && $JAVA -cp out NoMain 2>&1 | grep -v "^Picked up " | head -1; echo "static block lines printed: $($JAVA -cp out NoMain 2>&1 | grep -c "static work")"
