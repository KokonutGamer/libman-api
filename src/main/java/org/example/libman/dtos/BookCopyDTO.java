package org.example.libman.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookCopyDTO {

    @NotBlank(message = "Barcode is required")
    @Size(min = 14, max = 14, message = "Barcode must be of length 14")
    private String barcode;

    @NotBlank(message = "ISBN is required")
    @Size(min = 13, max = 13, message = "ISBN must be of length 13")
    private String isbn;

    @NotBlank(message = "Library location is required")
    @Size(max = 30, message = "Library location name must be less than 30 characters long")
    private String libraryLocationName;
}
