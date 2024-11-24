package com.ferme.citronix.web.rest;


import jakarta.validation.Valid;
import com.ferme.citronix.domain.Farm;
import com.ferme.citronix.domain.Field;
import com.ferme.citronix.service.FieldService;
import com.ferme.citronix.service.imp.FarmServiceImp;
import com.ferme.citronix.web.errors.field.ResourceNotFoundException;
import com.ferme.citronix.web.mapper.request.FieldMapper;
import com.ferme.citronix.web.vm.request.field.FieldCreateVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    private final FieldService fieldService;
    private final FieldMapper fieldMapper;
    private final FarmServiceImp farmServiceImp;

    public FieldController(FieldService fieldService, FieldMapper fieldMapper, FarmServiceImp farmServiceImp) {
        this.fieldService = fieldService;
        this.fieldMapper = fieldMapper;
        this.farmServiceImp = farmServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<Field> createField(@Valid @RequestBody FieldCreateVM fieldCreateVM) {
        Field field = fieldMapper.toEntity(fieldCreateVM);
        Optional<Farm> farm = farmServiceImp.findById(fieldCreateVM.getFarmId());
        field.setFarm(farm.get());
        Field savedField = fieldService.save(field);
        return ResponseEntity.ok(savedField);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Field> updateField(
            @PathVariable UUID id,
            @Valid @RequestBody FieldCreateVM fieldCreateVM) {

        Field existingField = fieldService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found with ID: " + id));

        fieldMapper.updateEntityFromVM(fieldCreateVM, existingField);
        Field updatedField = fieldService.save(existingField);

        return ResponseEntity.ok(updatedField);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteField(@PathVariable UUID id) {
        fieldService.delete(id);
        return ResponseEntity.ok("Deleted field with id " + id);
    }


    @GetMapping("/farm/{farmId}")
    public ResponseEntity<Page<Field>> getFieldsByFarmId(
            @PathVariable UUID farmId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Field> fields = fieldService.findByFarmId(farmId, PageRequest.of(page, size));
        return ResponseEntity.ok(fields);
    }
}
