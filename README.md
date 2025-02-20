# people-api v1
A simple REST API for people management

## Versions
- Java 17
- Spring Boot 3
- PostgreSQL

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

## Postman
This project includes a Postman collection to execute CRUD operations on the API: 
<em>people-api CRUD.postman_collection.json</em>
