#!/usr/bin/env bash

export set JAVA_OPTS="${JAVA_OPTS} -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n,address=8000"

./mvnw clean install
java ${JAVA_OPTS} -jar target/e2-restful-1.0.0.jar
