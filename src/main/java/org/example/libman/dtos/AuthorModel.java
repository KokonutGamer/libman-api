package org.example.libman.dtos;

import org.example.libman.entities.Author;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Relation(itemRelation = "author", collectionRelation = "authors")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthorModel extends RepresentationModel<AuthorModel> {
    private String firstName;
    private String lastName;

    public static AuthorModel fromEntity(Author author) {
        AuthorModel model = new AuthorModel();
        model.setFirstName(author.getFirstName());
        model.setLastName(author.getLastName());
        return model;
    }
}
