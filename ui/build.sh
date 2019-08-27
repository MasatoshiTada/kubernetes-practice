#!/usr/bin/env bash
docker container stop ui
mvn clean package
docker container rm ui
docker image rm mtada/ui:0.0.1
docker image build -t mtada/ui:0.0.1 .
#docker container create --name ui -p 8080:8080 -e API_HOST=192.168.0.25 mtada/ui:0.0.1
#docker container start ui
#open -a "/Applications/Google Chrome.app" http://localhost:8080/
docker image push mtada/ui:0.0.1