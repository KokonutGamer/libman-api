package org.example.libman.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.assemblers.BookCopyModelAssembler;
import org.example.libman.entities.BookCopy;
import org.example.libman.services.BookCopyService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookCopyController {

    private final BookCopyService bookCopyService;
    private final BookCopyModelAssembler bookCopyAssembler;

    public BookCopyController(BookCopyService bookCopyService, BookCopyModelAssembler bookCopyAssembler) {
        this.bookCopyService = bookCopyService;
        this.bookCopyAssembler = bookCopyAssembler;
    }

    // Public endpoints

    @GetMapping("/copies")
    public CollectionModel<RepresentationModel<?>> all() {
        List<RepresentationModel<?>> copies = bookCopyService.findAllBookCopies().stream()
                .map(bookCopyAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(copies, linkTo(methodOn(BookCopyController.class).all()).withSelfRel());
    }

    @GetMapping("/copies/{barcode}")
    public RepresentationModel<?> one(@PathVariable String barcode) {
        BookCopy copy = bookCopyService.findByBarcode(barcode);
        return bookCopyAssembler.toModel(copy);
    }
}
