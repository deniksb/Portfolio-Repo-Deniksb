#!/bin/bash

module=seasons/nl.fontys.sebivenlo.seasons.TravelAgency

if [ ! -f target/modulepath ]; then
    mvn -q compile exec:exec
else
    java @target/modulepath --module ${module}
fi
