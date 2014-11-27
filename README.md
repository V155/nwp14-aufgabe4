nwp14-aufgabe4

===

This script solves parts of the task 4 in lecture "NetworkPerformance" where the students have to compare tcpdump and trpt output of a data transmission between two hosts. Because I dont like such monkey work of finding ACK to an package and comparing sequence numbers of tcpdump with those in trpt output i wrote this small programm that automates most of the work.
It generates a csv on stdout to use it in a calculation programm and generate the 3 requested diagrams and the output of each tcpdump package and the corresponding entry in trpt output on stderr.
Capture with tcpdump -xx -S and redirect the output to tcpdump.dump and trpt -Ast and redirect the output to trptdump.dump and then run java -jar nwp14-aufgabe4.

