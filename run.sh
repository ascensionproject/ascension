#!/bin/sh

cd "$( dirname "$0" )"
exec java -Xms256m -Xmx1g -cp "Ascension/build-tmp:Ascension/code/*" Engine "${PWD}/Ascension"
