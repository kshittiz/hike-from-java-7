$JAVAC -d out RunTimeError.java && $JAVA -cp out RunTimeError 2>&1 | grep -v "^Picked up " | head -1
