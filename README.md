# Medical Prescriptions App backend
Microservices for a Medical Prescriptions App Backend in Java (Spring, Hibernate) with a Postgres data server

## Setting up
1. Clone the repository on your local machine.
2. Set up Postgres Microservice

	I. 		cd to postgres directory
	
	II. 	run: docker run -P --publish 127.0.0.1:5432:5432 --name postgres-container postgres
	
3. Set up needed database

	I.		run: docker exec -it postgres-container bash
	
	II.		in docker container bash run: psql -U postgres
	
	III.	in postgres run: CREATE DATABASE prescription_database;
	
	IV.		check database is created by running: \l (prescription_database should appear in the table)
	
	V.		run: quit
	
	VI.		run: exit
	
4. Run each microservice by cd to its directory and running spring-boot:run