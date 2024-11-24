package com.ferme.citronix.web.mapper.request;


import com.ferme.citronix.domain.Tree;
import com.ferme.citronix.web.vm.request.tree.TreeCreateVM;
import com.ferme.citronix.web.vm.response.tree.TreeResponseVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreeMapper {
    @Mapping(target = "field.id", source = "fieldId")
    Tree toEntity(TreeCreateVM treeCreateVM);

    @Mapping(target = "age", expression = "java(tree.calculateAge())")
    @Mapping(target = "productivity", expression = "java(tree.calculateProductivity())")
    @Mapping(target = "field", source = "field")
    TreeResponseVM toResponseVM(Tree tree);
}
