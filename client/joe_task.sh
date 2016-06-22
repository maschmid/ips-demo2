#!/bin/sh

mvn -q exec:java -Dexec.mainClass=com.redhat.ManagerClaimTask -Dexec.args="joe joejoejoe123!" # -Dorg.kie.server.bypass.auth.user=true
