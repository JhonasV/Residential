# Residential
Residential is a Residential management system

## Features
Management for:
- Appartments
- Buildings
- Users
- Roles
- Users Roles
- Mainentance
- Visitors


## Technologies
- Java 8
- Java Swing
- Javax Persistence

## Installation
Import the project into Netbeans


Add MySql connection string in  `Residential\src\META-INF\persistence.xml` file

```bash
<properties>
      <property name="javax.persistence.jdbc.url" value="jdbc:mysql://{host_name}:{port}/{database_name}?zeroDateTimeBehavior=convertToNull"/>
      <property name="javax.persistence.jdbc.user" value="{user}"/>
      <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
      <property name="javax.persistence.jdbc.password" value="{password}"/>
    </properties>
```
The MySQL script are the folder `Residential\scripts` to create the tables

## Run the project
Rigth click into Login form


