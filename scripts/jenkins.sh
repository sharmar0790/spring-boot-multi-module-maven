#!/bin/sh

echo '#######################################'
echo '##########   Stage Clean     ##########'
echo '#######################################'
./mvnw clean install -DskipTests
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi

echo '#######################################'
echo '##########   Stage Tests     ##########'
echo '#######################################'
sleep 5

./mvnw test
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi

echo '#######################################'
echo '##########   Stage Package   ##########'
echo '#######################################'
sleep 5

./mvnw package -DskipTests
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi

echo '#######################################'
echo '##########   Stage Run App   ##########'
echo '#######################################'
sleep 5

cd application
mvn spring-boot:run
