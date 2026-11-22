for v in 8 11 17 21; do
  rm -rf out$v; mkdir out$v
  $JAVAC --release $v -d out$v Probe.java ClassVersion.java 2>/dev/null
  echo "--- compiled with --release $v"
  $JAVA -cp out$v ClassVersion
done
