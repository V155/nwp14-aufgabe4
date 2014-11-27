nwp14-aufgabe4

======

## Overview

This script solves parts of the task 4 in lecture "NetworkPerformance" where the students have to compare tcpdump and trpt output of a data transmission between two hosts. Because I dont like such monkey work of finding ACK to an package and comparing sequence numbers of tcpdump with those in trpt output i wrote this small programm that automates most of the work.

## Output
It generates a csv on stdout to use it in a calculation programm and generate the 3 requested diagrams and the output of each tcpdump package and the corresponding entry in trpt output on stderr.

## Requirements
Capture with `tcpdump -xx -S` and redirect the output to tcpdump.dump and `trpt -Ast` and redirect the output to trptdump.dump

## Invocation
`java -jar build/nwp14-aufgabe4`

## Build
To build it just get one-jar [here](https://sourceforge.net/projects/one-jar/files/one-jar/one-jar-0.97/one-jar-ant-task-0.97.jar/download) create a onejar folder, save it there and run `jar xf one-jar-ant-task-0.97.jar one-jar-ant-task.xml` afterwards run `ant jar` in the root directory.




