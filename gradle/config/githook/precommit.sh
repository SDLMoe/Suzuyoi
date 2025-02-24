#!/bin/bash

kotlin() {
  if [ ! -e "./gradlew" ]; then
    return 0
  fi

  CHANGED_FILES="$(git --no-pager diff --name-status --no-color --cached | awk '$1 != "D" && $NF ~ /\.kts?$/ { print $NF }')"

  if [ -z "$CHANGED_FILES" ]; then
    echo "No Kotlin staged files."
    return 0
  fi

  echo '[pre-commit] Executing Gradle spotlessCheck before commit'

  git stash --quiet --keep-index

  ./gradlew spotlessCheck --daemon

  RESULT=$?

  git stash pop -q

  if [ "$RESULT" -ne "0" ]; then
    echo -e "spotlessCheck failed..."
    echo -e 'You can try "./gradlew spotlessApply" to apply auto-fixes.'
  fi

  return $RESULT
}

if ! kotlin; then
  exit 1
fi
