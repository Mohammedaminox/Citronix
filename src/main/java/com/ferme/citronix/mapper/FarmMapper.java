package com.ferme.citronix.mapper;

import com.ferme.citronix.dto.request.FarmRequestDTO;
import com.ferme.citronix.dto.response.FarmResponseDTO;
import com.ferme.citronix.model.Farm;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    Farm toEntity(FarmRequestDTO dto);
    FarmResponseDTO toResponseDTO(Farm farm);
    List<FarmResponseDTO> toResponseDTOList(List<Farm> farms);
}

