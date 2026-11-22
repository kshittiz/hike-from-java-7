$JAVAC -d mods --module-source-path modules --module com.notes.greet
$JAVAC -p mods -d sneakyout $(find sneaky -name "*.java") 2>&1; true
