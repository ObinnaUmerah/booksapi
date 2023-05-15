# My Book API
This an API that generates a database of books with their designated authors. This 
API was designed for a user to hold and store information about the books that
they possess along with their corresponding authors.Below is a list of the dependencies 
that you will need to run this application.

### Tools and Technology
[Spring Boot Rest API](https://start.spring.io/)

[Postman](https://www.postman.com/downloads/)


### Project Execution/Flow
Initially I took some time to plan out the scope of my project. I planned to have all the endpoints done in a few days
but that took some time. It took some time to figure out the mapping and to get the database and other things such
as the application properties set up. This took up the majority of the first day. The next day I coded all four
endpoints by adding the necessary code in the controller and services classes.

I decided to use the Cucumber framework for testing. However, I didn't fully understand it well and I found out soon
enough that I was testing my code improperly. This took up the majority of my Wednesday and I later received some
help from the instructors on how to properly use this framework. The remaining part of my work on this project was me
running into testing errors, and figuring out how to properly test my code, as well as refactoring it.


### User Stories
As a user, I should be able to create an author.

As a user, I should be able to get a single author from the database.

As a user, I should be able to update the author's information.

As a user, I should be able to delete a user from the database.

As a user, I should be able to create a book for a single author.

As a user, I should be able to get a book that belongs to a single author.

As a user, I should be able to update a book that belongs to a single author.

As a user, I should be able to delete a book that belongs to a single author.


### ERD Diagram
![ERD Digaram](ERD.png)


### Planning Documentation
[My GitHub Project for the Books API](https://github.com/users/ObinnaUmerah/projects/5/views/1)

### Dependencies
![H2 Database](H2Database.png)

    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-java</artifactId>
			<version>${cucumber.version}</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-junit</artifactId>
			<version>${cucumber.version}</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-spring</artifactId>
			<version>${cucumber.version}</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.rest-assured</groupId>
			<artifactId>rest-assured</artifactId>
			<version>4.3.0</version>
			<scope>test</scope>
		</dependency>

### Installation Instructions
Visit the "Spring Boot Rest API" Link above and create a package via the initializer. Unzip the folder and open
a new project in IntelliJ. Once that is done you will need to copy and paste the dependencies that I have above into 
your own pom.xml file.

### Credits
This was a solo project that I worked but there are some credits that I would like to give. My instructors
[Suresh Sigera](https://github.com/sureshmelvinsigera) and [Drhubo Chowdhury](https://www.linkedin.com/in/dhrubo-hasan/)
provided great assistance and pointers during this course of this project. I am very grateful to them.

### Farewell
Thank you for your time. This project was created by Obinna Umerah.
