package org.example.libman.dtos;

import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDTO {

    @NotBlank(message = "ISBN is required")
    @Size(min = 13, max = 13, message = "ISBN must be of length 13")
    private String isbn;

    @NotBlank(message = "Title is required")
    private String title;

    private String subtitile;

    @Digits(integer = 4, fraction = 0, message = "Volume must only contain digits")
    @DecimalMin(value = "1", message = "Volume must be a positive number")
    private String volume;

    @Digits(integer = 4, fraction = 0, message = "Edition must only contain digits")
    @DecimalMin(value = "1", message = "Edition must be a positive number")
    private String edition;

    @NotBlank(message = "Page count is required")
    @Positive(message = "Page count must be a positive number")
    private Integer pageCount;

    @NotBlank(message = "Publication date is required")
    @PastOrPresent
    private LocalDate publicationDate;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Dewey Decimal Number is required")
    @Digits(integer = 3, fraction = 11)
    private String ddn;

    // Relationships

    @NotBlank
    @Size(min = 1, max = 30, message = "Publisher name cannot exceed 30")
    private String publisherName;

    @NotEmpty
    private Set<@Valid AuthorDTO> authors;

    @NotEmpty
    private Set<@NotBlank String> genres;
}
