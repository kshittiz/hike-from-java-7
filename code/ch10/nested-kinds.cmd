$JAVAC -d out NestedKinds.java && $JAVA -cp out NestedKinds
echo "--- class files:"; ls out | LC_ALL=C sort
