package com.ferme.citronix.web.mapper.request;

import com.ferme.citronix.domain.Field;
import com.ferme.citronix.web.vm.request.field.FieldCreateVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "farmId", target = "farm.id")
    Field toEntity(FieldCreateVM fieldCreateVM);

    void updateEntityFromVM(FieldCreateVM fieldCreateVM, @MappingTarget Field field);
}