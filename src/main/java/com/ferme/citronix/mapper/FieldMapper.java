package com.ferme.citronix.mapper;

import com.ferme.citronix.model.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "farmId", target = "farm.id")
    Field toEntity(FieldCreateVM fieldCreateVM);

    void updateEntityFromVM(FieldCreateVM fieldCreateVM, @MappingTarget Field field);
}
