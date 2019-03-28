#!/bin/bash
$(find . -name "simulation*" -delete)
$(find . -name "*.java" > sources.txt)
$(javac -sourcepath . @sources.txt)
cd src
$(java avaj.simulator.Simulator scenario.txt)
