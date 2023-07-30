#!/bin/bash

sudo rm -r descry.tech/
git clone https://github.com/Derek-Fox/descry.tech.git
cd descry.tech/frontend/descry-frontend

dockerid=$(docker ps -a | grep descry | awk '{print $1}' | head -n 1)
echo "found the container $dockerid that had descry in it's name"
docker stop $dockerid
docker remove $dockerid
docker image rm descry --force
docker build -t descry .
docker run -d -p 3005:3000 --restart always --name descry descry

if docker ps -q -f name=descry | grep -q . ; then
    echo "Descry is successfully launched!"
else
    echo "Launch failed! :("
fi

