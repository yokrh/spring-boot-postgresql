# spring-boot-postgresql

Try Spring Boot with PostgreSQL.


## Workflow

### Spring initializer

https://start.spring.io/

selected

* Project
  * Gradle - Groovy
* Language
  * Kotlin
* Spring Boot
  * 3.0.1
* Packaging
  * Jar
* Java
  * 17
* Dependencies
  * Lombok
  * Spring Web Services
  * Spring Security
  * OAuth2 Client
  * Spring Data JDBC
  * MyBatis Framework 
  * PostgreSQL Driver
  * Validation
  * Spring Boot Actuator

### IntelliJ

Note the setting problem I encountered.

Got the error:
```
Could not resolve org.springframework.boot:spring-boot-gradle-plugin:3.0.1.
...
Incompatible because this component declares an API of a component compatible with Java 17 and the consumer needed a runtime of a component compatible with Java 16
```
so, needed to change 
`File > Project Structure > Project Settings > Project > Project Structure`
and
`Preferences > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM`
to
`corretto-19`

### PostgreSQL

Use docker image.

```sh
# run
docker run --name postgres \
           -e POSTGRES_PASSWORD=password \
           -e POSTGRES_INITDB_ARGS="--encoding=UTF8 --no-locale" \
           -e TZ=Asia/Tokyo \
           -p 5432:5432 \
           -d postgres
#            -v postgresdb:/var/lib/postgresql/data \

# check db
docker exec -it postgres /bin/bash
# in the docker image
psql -U postgres
# psql -h localhost -U postgres -W
# password
```

PostgreSQL commands note.

```sql
-- show databases
\l
--                                             List of databases
--    Name    |  Owner   | Encoding | Collate | Ctype | ICU Locale | Locale Provider |   Access privileges
-- -----------+----------+----------+---------+-------+------------+-----------------+-----------------------
--  postgres  | postgres | UTF8     | C       | C     |            | libc            |
--  template0 | postgres | UTF8     | C       | C     |            | libc            | =c/postgres          +
--            |          |          |         |       |            |                 | postgres=CTc/postgres
--  template1 | postgres | UTF8     | C       | C     |            | libc            | =c/postgres          +
--            |          |          |         |       |            |                 | postgres=CTc/postgres
-- (3 rows)

-- check time stamp
select NOW();

-- create database
CREATE DATABASE testdb ENCODING 'UTF-8';
-- drop database
-- DROP DATABASE testdb;

-- use database
\c testdb

-- create table
-- CREATE TABLE IF NOT EXISTS `product` (
-- id bigint(13) NOT NULL AUTO_INCREMENT,
-- name VARCHAR(50),
-- created_at DATETIME(3) NOT NULL,
-- updated_at DATETIME(3) NOT NULL,
-- created_by VARCHAR(50) NULL,
-- updated_by VARCHAR(50) NULL,
-- version INT DEFAULT 1
-- );
CREATE TABLE IF NOT EXISTS product (
id SERIAL NOT NULL,
name VARCHAR(50),
created_at TIMESTAMP(3) NOT NULL DEFAULT NOW(),
updated_at TIMESTAMP(3) NOT NULL DEFAULT NOW(),
created_by VARCHAR(50),
updated_by VARCHAR(50),
version INT DEFAULT 1
);

-- desc table
\d product
--                                    Table "public.product"
--  Column |         Type          | Collation | Nullable |               Default
-- --------+-----------------------+-----------+----------+-------------------------------------
--  id     | integer               |           | not null | nextval('product_id_seq'::regclass)
--  name   | character varying(50) |           |          |

-- show tables
\dt
--           List of relations
--  Schema |  Name   | Type  |  Owner
-- --------+---------+-------+----------
--  public | product | table | postgres

-- create table 2
-- CREATE TABLE IF NOT EXISTS `user` (
-- id bigint(13) NOT NULL AUTO_INCREMENT,
-- name VARCHAR(50),
-- status VARCHAR(64) NOT NULL,
-- meta JSON NULL,
-- PRIMARY KEY(id)
-- INDEX `user_id`(`user_id`)
-- );
CREATE TABLE IF NOT EXISTS customer (
id SERIAL NOT NULL PRIMARY KEY,
name VARCHAR(50),
status VARCHAR(64) NOT NULL,
meta_json JSON,
meta_jsonb JSONB,
version INT DEFAULT 1
);
-- add index
CREATE INDEX ON customer(name);
CREATE INDEX ON customer(status);

-- drop table
DROP TABLE customer;

-- insert
INSERT INTO product(name, created_by, updated_by)
VALUES('name1', 'created_by1', 'updated_by1');
INSERT INTO product(name, created_by, updated_by)
VALUES('name2', 'created_by1', 'updated_by1');

-- create user
CREATE USER user1 WITH PASSWORD 'user1';

-- grant
GRANT CONNECT ON DATABASE testdb TO user1;
GRANT SELECT, UPDATE, INSERT ON product TO user1;
GRANT USAGE ON SEQUENCE product_id_seq TO user1;

-- check access permission
\l
\dp product

-- show users
\du

-- exit
\q

-- login with sub user
psql -U user1 -W -d postgres

-- select
select * from product;

-- exit
\q
```


### Implementation

#### Spring Boot

Postgres DB Connection configuration

`application.properties`
```
```



## Reference

* postgres - Official Image | Docker Hub
https://hub.docker.com/_/postgres

* DockerでPostgreSQLを使う - Qiita
https://qiita.com/zaburo/items/7ab51a7a4d9e1b2d1ec4

* MySQLとPostgreSQLコマンド比較表 - Qiita
https://qiita.com/aosho235/items/c657e2fcd15fa0647471

* Postgresだとcreate tableと同時にindex作成ができなそう - Qiita
https://qiita.com/unvavo/items/1396ec168274bb86680c

* PostgreSQLにおけるロケールの影響調査 - Qiita
https://qiita.com/fujii_masao/items/2a715fb5a3f718d22ab4

