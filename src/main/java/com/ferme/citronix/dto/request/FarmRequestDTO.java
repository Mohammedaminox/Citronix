package com.ferme.citronix.dto.request;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
public class FarmRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    @Positive
    private Double area;

    @NotNull
    private LocalDate creationDate;
}


