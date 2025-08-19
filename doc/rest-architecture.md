## LibMan API: Representational State Transfer in Practice
LibMan API will eventually use the Representational State Transfer architectural style to provide data to clients.

## Why REST?
REST is a common software paradigm used in various different APIs existing in the world today. This is why learning how REST works and how to develop a RESTful application is useful for any developer.

### REST constraints [^1]
- Uniform interface:
  - A resource (web page) should have only one logical URI.
  - A resource should contain links (HATEOAS) pointing to relative URIs.
  - A resource should follow specific guidelines such as naming conventions, link formats, or data format (XML and/or JSON).
  - A resource should be accessible through a common approach such as HTTP GET and similarly modified using a consistent approach.
- Client-server:
  - Client applications and server applications should be able to evolve separately without any dependency on each other.
  - A client should know only resource URIs.
- Stateless:
  - All client-sersver interactions should be stateless.
  - The server should not store anything about the latest HTTP request the client made.
- Cacheable:
  - Resources should be cached when applicable.
  - Cacheable resources should declare themselves cacheable.
  - Caching may be implemented on either the server or client side.
- Layered system:
  - The system should allow for a layered system architecture where each layer is responsible for only a piece of the system.
  - A client should not be able to tell whether it is connected directly to the end server or an intermediary along the way.
- Code on demand (optional):
  - The system should be free to return executable code to support a part of the application.

### REST Resources
- [RESTful API Tutorial](https://restfulapi.net/) by [Lokesh Gupta](https://x.com/HowToDoInJava)
- [HATEOAS REST Services With Spring](https://dzone.com/articles/hypermedia-driven-rest-services-with-spring-hateoa) by [Brian Hannaway](https://dzone.com/users/2798331/bhannawa.html)
- [HAL - Hypertext Application Language](https://stateless.co/hal_specification.html) by [Mike Kelly](https://stateless.co/)
- [StackOverflow - Why you should use DTOs in your REST API](https://stackoverflow.com/a/36175349/21415037) by [cassiomolin](https://stackoverflow.com/users/1426227/cassiomolin)
- [Spring Boot HATEOAS](https://howtodoinjava.com/spring/spring-hateoas-tutorial) by [Lokesh Gupta](https://x.com/HowToDoInJava)
- [Answer to "REST/HATEOAS - Available methods in HAL links"](https://stackoverflow.com/a/44950392/21415037) on StackOverflow by [VoiceOfUnreason](https://stackoverflow.com/users/54734/voiceofunreason)

[^1]: See [REST Architectural Constraints](https://restfulapi.net/rest-architectural-constraints/) for more information.