package org.example.libman.dtos;

import org.example.libman.entities.Publisher;
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
@Relation(itemRelation = "publisher", collectionRelation = "publishers")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PublisherModel extends RepresentationModel<PublisherModel> {
    private String name;

    public static PublisherModel fromEntity(Publisher publisher) {
        PublisherModel model = new PublisherModel();
        model.setName(publisher.getName());
        return model;
    }
}
