package org.example.libman.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    // TODO write checks for volume
    private String volume;

    // TODO write checks for edition
    private String edition;

    @NotBlank(message = "Page count is required")
    @Min(value = 1, message = "Page count must be a positive number")
    private Integer pageCount;

    @NotBlank(message = "Publication date is required")
    private LocalDate publicationDate;

    private Integer numberOfAvailableCopies;

    private Integer totalNumberOfCopies;
}
