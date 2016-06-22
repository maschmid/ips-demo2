#!/bin/sh

mvn -q exec:java -Dexec.mainClass=com.redhat.ProcessStart -Dexec.args="26 120 1000"
