#!/usr/bin/env bash
docker container stop api
mvn clean package
docker container rm api
docker image rm mtada/api:0.0.1
docker image build -t mtada/api:0.0.1 .
#docker container create --name ui -p 8080:8080 mtada/ui:0.0.1
#docker container start ui
#open -a "/Applications/Google Chrome.app" http://localhost:8080/
docker image push mtada/api:0.0.1