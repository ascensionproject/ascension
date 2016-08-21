#!/bin/sh

cd "$( dirname "$0" )"
rm -rf Ascension/build-tmp
mkdir Ascension/build-tmp
javac -cp "Ascension/code/*" -d Ascension/build-tmp Ascension/*.java
