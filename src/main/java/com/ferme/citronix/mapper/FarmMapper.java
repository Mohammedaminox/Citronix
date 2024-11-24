package com.ferme.citronix.mapper;

import com.ferme.citronix.dto.FarmDto;
import com.ferme.citronix.model.Farm;
import com.ferme.citronix.web.vm.FarmVM;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface FarmMapper {
    FarmDto toDto(Farm farm);
    Farm toEntity(FarmDto farmDto);
    FarmVM toVm(Farm farm);
}

