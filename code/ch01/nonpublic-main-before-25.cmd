$JAVAC -d out NonPublicMain.java && $JAVA -cp out NonPublicMain 2>&1 | grep -v "^Picked up " | head -1
