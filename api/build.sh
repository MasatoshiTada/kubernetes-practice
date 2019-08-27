#!/usr/bin/env bash
docker container stop api
mvn clean package
docker container rm api
docker image rm mtada/api:0.0.1
docker image build -t mtada/api:0.0.1 .
#docker container create --name api -p 8090:8090 -e SPRING_DATASOURCE_URL="jdbc:mysql://192.168.0.25:3306/spring?useSSL=false&serverTimezone=JST&allowPublicKeyRetrieval=true" -e SPRING_DATASOURCE_USERNAME=user -e SPRING_DATASOURCE_PASSWORD=password mtada/api:0.0.1
#docker container start api
#open -a "/Applications/Google Chrome.app" http://localhost:8090/customers
docker image push mtada/api:0.0.1