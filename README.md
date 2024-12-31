## Library Management API built using Java Spring Boot
### About this project
Libman is a basic REST API built using Spring Web, Spring Data Java Persistence API (JPA), and H2 Database
- [Spring Data JPA](https://www.geeksforgeeks.org/jpa-introduction/): provides a standardized way to manage relational data in Java applications
- [Spring Web](https://spring.io/projects/spring-ws): creates document-driven web services and facilitates contract-first Simple Object Access Protocol (SOAP) service development
- [H2 Database](https://www.geeksforgeeks.org/spring-boot-with-h2-database/): supports CRUD operations with data stored in an embedded, open-source, in-memory database

### API Endpoints
**GET**
- Book
  - by ID

**POST**
- Book
  - with title and author

**PUT**
- Book
  - by ID

**DELETE**
- Book
  - by ID

> [!WARNING]
> This version of the API (0.0.1-SNAPSHOT) is not considered to be a RESTful service yet.

## Todo list
- [X] Add a new field(s) to the `Book` entity, such as pages, date published, volume, and edition
- [X] Update POST methods to return a proper response
- [X] Update PUT methods to return a proper response
- [X] Update DELETE methods to return a proper response
- [X] Incorporate Lombok to reduce boilerplate on entities
- [ ] ~~Update the `Book` entity to handle different states, such as available, on hold, and unavailable~~
- [X] Change the `Book` entity `BookStatus` field to a numerical field with available copies
- [X] Change `BookController` structure to handle links with different states
- [ ] Build new controllers implementing Hypermedia as the Engine of Application State (HATEOAS)
- [ ] Add support for new entities such as `User` and/or `Librarian`
- [ ] Add JWT-based authentication in-memory
- [ ] Implement Spring Security to handle requests from different roles, such as:
  - **ADMIN**: manages users (CRUD operations)
  - **LIBRARIAN**: manages books (CRUD operations), mark books as returned, update holds, flag users
  - **USER**: borrow, hold, renew books

> [!CAUTION]
> The `User` entity uses annotations from both @Entity [(jakarta.persistence.Entity)](https://jakarta.ee/specifications/persistence/2.2/apidocs/javax/persistence/entity) and @Data [(lombok.Data)](https://projectlombok.org/features/Data).
> This may result in conflicts between Lombok's creation of getters and setters along with Hibernate's `@UpdateTimestamp`.
> Check [this StackOverflow post](https://stackoverflow.com/questions/67378688/how-do-i-create-a-safe-lombok-jpa-entity) again in case a breaking change occurs.

### [REST constraints (to keep in mind)](https://restfulapi.net/rest-architectural-constraints/)
- Uniform interface
- Client-server
- Stateless
- Cacheable
- Layered system
- Code on demand
