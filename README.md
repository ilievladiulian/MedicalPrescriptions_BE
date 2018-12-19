# Medical Prescriptions App backend
Microservices for a Medical Prescriptions App Backend in Java (Spring, Hibernate) with a Postgres data server

## Setting up
1. Clone the repository on your local machine.

2. Set up Postgres Microservice

	i) 	`cd` to postgres directory

	ii) 	run `docker run -P --publish 127.0.0.1:5432:5432 --name postgres-container postgres`

3. Set up needed database

	i)	run: `docker exec -it postgres-container bash`
	
	ii)	in docker container bash run: `psql -U postgres`
	
	iii)	in postgres run: `CREATE DATABASE prescription_database;`
	
	iv)	check database is created by running: `\l` (prescription_database should appear in the table)
	
	v)	run: `quit`
	
	vi)	run: `exit`
	
4. Run each microservice 

	i) 	make sure you have JAVA_HOME env variable set
	
	ii)	in project directory run: `mvnw.cmd spring-boot:run`
