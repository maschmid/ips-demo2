#!/bin/sh

mvn -q exec:java -Dexec.mainClass=com.redhat.UserTasksList -Dexec.args="joe joejoejoe123!" # -Dorg.kie.server.bypass.auth.user=true
