package org.example.libman.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.assemblers.AuthorModelAssembler;
import org.example.libman.entities.Author;
import org.example.libman.exceptions.AuthorNotFoundException;
import org.example.libman.repositories.AuthorRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorModelAssembler authorAssembler;

    public AuthorController(AuthorRepository authorRepository, AuthorModelAssembler authorAssembler) {
        this.authorRepository = authorRepository;
        this.authorAssembler = authorAssembler;
    }

    @GetMapping("/authors")
    public CollectionModel<RepresentationModel<?>> all() {
        List<RepresentationModel<?>> authors = authorRepository.findAll().stream().map(authorAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(authors, linkTo(methodOn(AuthorController.class).all()).withSelfRel());
    }

    @GetMapping("/authors/{id}")
    public RepresentationModel<?> one(@PathVariable Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        return authorAssembler.toModel(author);
    }
}
