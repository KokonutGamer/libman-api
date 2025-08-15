package org.example.libman.dtos;

import org.example.libman.entities.Genre;
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
@Relation(itemRelation = "genre", collectionRelation = "genres")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GenreModel extends RepresentationModel<GenreModel> {
    private String name;

    public static GenreModel fromEntity(Genre genre) {
        GenreModel model = new GenreModel();
        model.setName(genre.getName());
        return model;
    }
}
