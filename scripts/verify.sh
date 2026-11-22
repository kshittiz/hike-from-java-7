#!/usr/bin/env bash
# Runs every code/**/NAME.cmd and compares its output with NAME.expected.
#   NAME.min / NAME.max (optional): lowest / highest Java feature version the test applies to.
# Usage:  bash scripts/verify.sh          (uses `java` on PATH; or JAVA=/path/to/bin/java)
set -u
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
export JAVA="${JAVA:-java}"
JDIR="$(dirname "$(command -v "$JAVA")")"
if [ -x "$JDIR/javac" ]; then export JAVAC="$JDIR/javac"
else export JAVAC="$JAVA -m jdk.compiler/com.sun.tools.javac.Main"; fi
if [ -x "$JDIR/jdeps" ]; then export JDEPS="$JDIR/jdeps"
else export JDEPS="$JAVA -m jdk.jdeps/com.sun.tools.jdeps.Main"; fi

MAJOR=$("$JAVA" -XshowSettings:properties -version 2>&1 | awk -F' = ' '/java.specification.version/{print $2}')
echo "Verifying with Java $MAJOR"
pass=0; fail=0; skip=0
while IFS= read -r cmd; do
  base="${cmd%.cmd}"; name="$(basename "$(dirname "$cmd")")/$(basename "$base")"
  min=0; max=999
  [ -f "$base.min" ] && min=$(cat "$base.min")
  [ -f "$base.max" ] && max=$(cat "$base.max")
  if [ "$MAJOR" -lt "$min" ] || [ "$MAJOR" -gt "$max" ]; then
    echo "SKIP  $name  (applies to Java $min..$max)"; skip=$((skip+1)); continue
  fi
  work="$(mktemp -d)"; cp -r "$(dirname "$cmd")"/. "$work"/
  actual="$(cd "$work" && bash "$cmd" 2>&1 | grep -v '^Picked up ')"
  if [ "$actual" == "$(cat "$base.expected")" ]; then
    echo "PASS  $name"; pass=$((pass+1))
  else
    echo "FAIL  $name"; diff <(echo "$actual") "$base.expected" | sed 's/^/      /'; fail=$((fail+1))
  fi
  rm -rf "$work"
done < <(find "$ROOT/code" -name '*.cmd' | sort)
echo "---- $pass passed, $fail failed, $skip skipped"
[ "$fail" -eq 0 ]
