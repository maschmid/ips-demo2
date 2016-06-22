#!/bin/sh

mvn -q exec:java -Dexec.mainClass=com.redhat.ProcessStart -Dexec.args="13 500 0"
