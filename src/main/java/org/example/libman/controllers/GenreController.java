package org.example.libman.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.assemblers.GenreModelAssembler;
import org.example.libman.entities.Genre;
import org.example.libman.exceptions.GenreNotFoundException;
import org.example.libman.repositories.GenreRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreController {

    private final GenreRepository genreRepository;
    private final GenreModelAssembler genreAssembler;

    public GenreController(GenreRepository genreRepository, GenreModelAssembler genreAssembler) {
        this.genreRepository = genreRepository;
        this.genreAssembler = genreAssembler;
    }

    @GetMapping("/genres")
    public CollectionModel<RepresentationModel<?>> all() {
        List<RepresentationModel<?>> genres = genreRepository.findAll().stream().map(genreAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(genres, linkTo(methodOn(GenreController.class).all()).withSelfRel());
    }

    @GetMapping("/genres/{id}")
    public RepresentationModel<?> one(@PathVariable Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        return genreAssembler.toModel(genre);
    }
}
