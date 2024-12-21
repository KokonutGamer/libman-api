## Library Management API built using Java Spring Boot
### About this project
Libman is a basic REST API built using Spring Web, Spring Data Java Persistence API (JPA), and H2 Database
- [Spring Data JPA](https://www.geeksforgeeks.org/jpa-introduction/): provides a standardized way to manage relational data in Java applications
- [Spring Web](https://spring.io/projects/spring-ws): creates document-driven web services and facilitates contract-first Simple Object Access Protocol (SOAP) service development
- [H2 Database](https://www.geeksforgeeks.org/spring-boot-with-h2-database/): supports CRUD operations with data stored in an embedded, open-source, in-memory database

### API Endpoints
__**GET**__
- Book
  - by ID

__**POST**__
- Book
  - with title and author

__**PUT**__
- Book
  - by ID

__**DELETE**__
- Book
  - by ID

> [!WARNING]
> This version of the API (0.0.1-SNAPSHOT) is not considered to be a RESTful service yet.

## Todo list
- [X] Add a new field(s) to the `Book` entity, such as pages, date published, volume, and edition
- [ ] Update the `Book` entity to handle different states, such as available, on hold, and unavailable 
- [X] Update POST methods to return a proper response
- [X] Update PUT methods to return a proper response
- [X] Update DELETE methods to return a proper response
- [ ] Build new controllers implementing Hypermedia as the Engine of Application State (HATEOAS)
- [ ] Add support for new entities such as `User` and/or `Librarian`
