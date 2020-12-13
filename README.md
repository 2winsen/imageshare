# Imageshare
Project, where user can upload image and instantly share it

## Demo
![](https://github.com/2winsen/imageshare/blob/master/demo.gif)

## Requirements
Java 7
Mongo DB 3.6

## Technology Stack
* server:
  * Java 7
  * Spring MVC
  * Spring WEB
  * Spring Data
  * Apache Tiles
* client:
  * JQuery 1.9
  * Bootstrap 2.3.0

## Getting started

### Docker
To run app in docker make sure to have docker-compose and then run the following command
```
docker-compose up
```
To cleanup run
```
docker-compose down --rmi=local
```
Open [http://localhost:8080](http://localhost:8080) to view it in the browser.  
Project directory is a bind mount to docker container for ease of development.
