package com.ferme.citronix.controller;

import com.ferme.citronix.dto.request.FarmRequestDTO;
import com.ferme.citronix.dto.response.FarmResponseDTO;
import com.ferme.citronix.mapper.FarmMapper;
import com.ferme.citronix.model.Farm;
import com.ferme.citronix.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<FarmResponseDTO> createFarm(@RequestBody @Valid FarmRequestDTO dto) {
        return ResponseEntity.ok(farmService.createFarm(dto));
    }

    @GetMapping
    public ResponseEntity<List<FarmResponseDTO>> getAllFarms() {
        return ResponseEntity.ok(farmService.getAllFarms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmResponseDTO> getFarmById(@PathVariable Long id) {
        return ResponseEntity.ok(farmService.getFarmById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmResponseDTO> updateFarm(@PathVariable Long id, @RequestBody @Valid FarmRequestDTO dto) {
        return ResponseEntity.ok(farmService.updateFarm(id, dto));
    }
}
