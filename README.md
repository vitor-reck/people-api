# people-api v1
Simples REST Api for people management, include basic CRUD operations on service layer architecture + repository pattern

## Versions
- Java 17
- Spring Framework: Boot, Data and Web
- Maven 3.9.6
- PostgreSQL 15

## Prerequisites
Need previous install of Docker and Docker-compose, if you already have installed on your system skip to the next steps:
- Docker: https://docs.docker.com/engine/install
- Docker-compose: https://docs.docker.com/compose/install

## Run the App
Run this command where <em>people-api/docker-compose.yml</em> is located:
```
docker compose up -d
```
Docker will pull the <em>postgres</em> and <em>people_app</em> images, build them and run the services on the background

## Stop the Docker-compose Services
Stopping all the running containers:
```
docker compose stop
```

If you need to stop and remove all containers, networks, and all images used by any service in <em>docker-compose.yml</em> file:
```
docker compose down --rmi all
```
