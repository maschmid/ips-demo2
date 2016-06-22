#!/bin/sh

mvn -q exec:java -Dexec.mainClass=com.redhat.UserTasksList -Dexec.args="bill billbill12!" # -Dorg.kie.server.bypass.auth.user=true
