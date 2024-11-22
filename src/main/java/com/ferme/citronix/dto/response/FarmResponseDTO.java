package com.ferme.citronix.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FarmResponseDTO {
    private Long id;
    private String name;
    private String location;
    private Double area;
    private LocalDate creationDate;
    private List<FieldResponseDTO> fields;
}

