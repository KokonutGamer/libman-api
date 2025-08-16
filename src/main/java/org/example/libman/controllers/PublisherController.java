package org.example.libman.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.assemblers.PublisherModelAssembler;
import org.example.libman.entities.Publisher;
import org.example.libman.exceptions.PublisherNotFoundException;
import org.example.libman.repositories.PublisherRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

    private final PublisherRepository publisherRepository;
    private final PublisherModelAssembler publisherAssembler;

    public PublisherController(PublisherRepository publisherRepository, PublisherModelAssembler publisherAssembler) {
        this.publisherRepository = publisherRepository;
        this.publisherAssembler = publisherAssembler;
    }

    @GetMapping("/publishers")
    public CollectionModel<RepresentationModel<?>> all() {
        List<RepresentationModel<?>> publishers = publisherRepository.findAll().stream()
                .map(publisherAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(publishers, linkTo(methodOn(PublisherController.class).all()).withSelfRel());
    }

    @GetMapping("/publishers/{id}")
    public RepresentationModel<?> one(@PathVariable Integer id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));
        return publisherAssembler.toModel(publisher);
    }
}
