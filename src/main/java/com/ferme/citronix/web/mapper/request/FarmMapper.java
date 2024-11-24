package com.ferme.citronix.web.mapper.request;


import com.ferme.citronix.domain.Farm;
import com.ferme.citronix.web.vm.request.farm.FarmCreateVM;
import com.ferme.citronix.web.vm.request.farm.FarmUpdateVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    @Mapping(target = "id", ignore = true)
    Farm toEntity(FarmCreateVM createFarmVM);

    Farm toEntity(FarmUpdateVM updateFarmVM);

    FarmCreateVM toCreateVM(Farm farm);

    FarmUpdateVM toUpdateVM(Farm farm);

}
