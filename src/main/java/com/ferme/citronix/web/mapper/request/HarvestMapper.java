package com.ferme.citronix.web.mapper.request;

import jakarta.validation.Valid;
import com.ferme.citronix.domain.Harvest;
import com.ferme.citronix.web.vm.request.harvest.HarvestCreateVM;
import com.ferme.citronix.web.vm.response.harvest.HarvestResponseVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    @Mapping(target = "tree.id", source = "treeId")
    @Mapping(target = "season", expression = "java(com.ferme.citronix.domain.enums.Season.valueOf(harvestCreateVM.getSeason()))")
    Harvest toEntity(@Valid HarvestCreateVM harvestCreateVM);

    @Mapping(target = "season", expression = "java(harvest.getSeason().name())")
    @Mapping(target = "treeId", source = "tree.id")
    HarvestResponseVM toResponseVM(Harvest harvest);
}
