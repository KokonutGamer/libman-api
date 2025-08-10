package org.example.libman.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthorDTO {

    @Size(min = 1, max = 30)
    private String firstName;

    @Size(min = 1, max = 30)
    private String lastName;
}
