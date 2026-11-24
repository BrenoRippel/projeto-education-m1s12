# Education API (SENAI mini-project)

A REST API for running a school: students, teachers, subjects, enrolments and grades. Built by a team of four in April 2024, during SENAI/SC's full-stack Java bootcamp.

**Stack:** Java 17 · Spring Boot (Web, Data JPA) · PostgreSQL · Lombok · Maven

## What it does

- Create, read, update and delete students (`alunos`), teachers (`professores`), subjects (`disciplinas`) and enrolments (`matriculas`)
- Record grades (`notas`) and filter them
- Calculate a student's overall average (média geral)

## My part

I created the repository and the JPA entities, wrote the scripts that populate the database with sample data, later fixed the database script, fixed a bug in the enrolment repository, and merged the team's feature branches.

Team: [marcosvnDias](https://github.com/marcosvnDias), [joaoxn](https://github.com/joaoxn), [andrewmatheus](https://github.com/andrewmatheus) and [BrenoRippel](https://github.com/BrenoRippel).

## Running it

1. Create a PostgreSQL database and set its URL, user and password in `src/main/resources/application.properties`. The schema and sample data are in `schema.sql` and `data.sql` in the same folder.
2. Run `./mvnw spring-boot:run`.
3. Try the endpoints with the Postman collection in `src/main/resources/M1S12.postman_collection.json`.
