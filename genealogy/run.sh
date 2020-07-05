#!/bin/bash
jar=target/genealogy-1.0-SNAPSHOT.jar
if [ ! -f ${jar} ]; then
    mvn package
fi
java -jar ${jar} "$@"
