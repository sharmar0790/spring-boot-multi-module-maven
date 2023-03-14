#!/bin/sh

./mvnw clean compile package
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi

java -jar application/target/demo-1.0.0-SNAPSHOT.jar
