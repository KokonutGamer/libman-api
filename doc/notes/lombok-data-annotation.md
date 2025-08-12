## The Pitfalls of Using Project Lombok's `@Data` Annotation

[Project Lombok](https://projectlombok.org) is a Java library commonly used to reduce [boilerplate](https://en.wikipedia.org/wiki/Boilerplate_code) when developing applications in Java. Java is NOTORIOUS for the amount of boilerplate code you need to write to mimic what you might find in other programming languages like Python. So, the developers of this project aim to assist Java developers by providing simple annotations that can automatically generate getters, setters, constructors, etc.

One of the first libraries I used while learning the Spring ecosystem was Project Lombok. I love Java as a language, but even I can't stand having to rewrite [POJO after POJO](https://en.wikipedia.org/wiki/Plain_old_Java_object) in each of my projects, especially when building web applications using [RDBMS](https://en.wikipedia.org/wiki/Relational_database). When I first got my hands on Project Lombok, it was relieving. I could write several different entities without having to rewrite and rewrite getters and setters.

## The Problem

As I was working on LibMan API, I constantly kept running into this one particular exception: the `ConcurrentModificationException`. This constantly occured when accessing the `Book` repository, specifically when using the `findById` method to search for one instance of a `Book`. This was unusual, however, because I knew that within my code, I hadn't been working with multiple threads, and not once did I find myself even updating the `Book` repository I was using. So what was the issue?

I tried asking ChatGPT about what could possibly be the issue. It suggested things like lazy loading, using `getReferenceById` instead of `findById`, using iterators even though I wasn't even iterating manually, etc. It wasn't until I looked into the stack trace a bit more to find the issue.

I kept seeing this one specific error:
```
at org.example.libman.entities.Book.hashCode(Book.java:26) ~[classes/:na]
```

I wasn't sure what was happening at first. What was wrong with the `hashCode` method? I ignored it for a while until I remembered Project Lombok.

A while ago, when I was still playing around with Lombok, I noticed that it included an all-in-one annotation that could significantly reduce boilerplate: `@Data`. This annotation automatically generated getters, setters, the constructor, the `equals` method, the `hashCode` method, and the `toString` method. It sounds really nice on paper. However, this hides the issue I came across later on with the `hashCode` method.

Lombok defaults to using *every* single field within classes marked by `@Data` for `hashCode` generation. Now, why would this be an issue? It has to do with how collections like `Set`s or `Map`s store unique objects, and how [Hibernate](https://docs.spring.io/spring-framework/reference/data-access/orm/hibernate.html) loads relationships between entities.

Entities may contain relationships between each other, such as `@OneToMany`, `@ManyToOne`, and `@ManyToMany`. In a `@ManyToOne` relationship or `@ManyToMany` relationship, these are usually expressed in a `Collection` with the generic being the type of the other object in the relationship. For example, this project's `Book` entity has all three of these relationships:
- `@ManyToOne` with `Publisher`
- `@OneToMany` with `BookCopy`
- `@ManyToMany` with `Author`
- `@ManyToMany` with `Genre`

These are mapped within `Book` to field as shown below:
- `Publisher publisher`
- `Set<BookCopy> libraryCopies`
- `Set<Author> authors`
- `Set<Genre> genres`


Hibernate doesn't automatically load the fields with type `Set` until they're needed (see [lazy loading](https://www.baeldung.com/hibernate-lazy-eager-loading)). This especially becomes an issue when [Jackson](https://github.com/FasterXML/jackson) serializes these fields into JSON.

When serializing into JSON, Jackson needs to access each object within the `Set` in order to serialize those into JSON as well.

> [!NOTE] There was actually a separate issue of infinite recursion occuring due to bidirectional relationships, but that didn't happen for this specific scenario.

My theory for the `ConcurrentModificationException` occurring is that as Hibernate is lazily loading the collection for Jackson to serialize, the `hashCode`, since it's generated using ALL fields within the class, changes at the same time. So, since `Author` and `Genre` are stored inside `Set`s that rely on the hash for uniquely identifying each, they are changed at the same time that they have their own collections populated by Hibernate.

That's a lot to take in, but TLDR; by using `@Data` from Project Lombok, `hashCode` relies on fields that can be mutated.

## The Solution
The best way to counter this was simply to bring back the boilerplate code for the `equals` and `hashCode` methods and make sure `hashCode` didn't use mutable fields. It was actually quite simple: use the primary key of the entity for the hash.

For book, this was:
```
@Override
public int hashCode() {
    return isbn.hashCode();
}
```

Yeah. That was the fix. I only need to change each entity to reflect that now... but it works so... yay!

Software engineering can be really stressful at times.