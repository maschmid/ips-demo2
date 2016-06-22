#!/bin/sh

mvn -q exec:java -Dexec.mainClass=com.redhat.ProcessStart -Dexec.args="32 50 100"
