mvn spring-boot:run

mvn clean package
java -jar target/ecom-0.0.1-SNAPSHOT.jar

APIs
http://localhost:8090/ecom/product/getall

=
DOCKER:
download MySQL
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=mypass -d mysql/mysql-server

=
PHP MyAdmin
docker run --name phpmyadmin --link mysql:db -p 8080:80 -d phpmyadmin/phpmyadmin

=
MongoDB
docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:latest

==
MYSQL ROOT USER
Change root user to login from any host:

docker exec -it mysql /bin/bash
mysql -u root -p
show databases;
use mysql;
show tables;
SELECT * FROM user;
update user set host='%' where user='root';
flush privileges;
==





{
"id": "36",
"date": "2025-03-19T11:37:54",
"computer": {
"id": 1,
"description": "Default Computer",
"price": 700.0,
"components": [],
"base": {
"id": 1,
"type": "Computer",
"name": "Default Computer",
"price": 700.0,
"img": "images/computer.jpg",
"quantity": 7
}
},
"description": "Default Computer",
"total": 700.0,
"products": []
}




{
"id": "5332",
"date": "2025-05-09T13:21:51",
"computer": {
"id": 1,
"description": "Default Computer",
"price": 700.0,
"components": [],
"base": {
"id": 1,
"type": "Computer",
"name": "Default Computer",
"price": 700.0,
"img": "images/computer.jpg",
"quantity": 7
}
},
"description": "Default Computer",
"total": 700.0,
"products": []
}
