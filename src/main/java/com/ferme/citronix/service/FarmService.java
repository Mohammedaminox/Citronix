package com.ferme.citronix.service;

import com.ferme.citronix.dto.request.FarmRequestDTO;
import com.ferme.citronix.dto.response.FarmResponseDTO;
import com.ferme.citronix.mapper.FarmMapper;
import com.ferme.citronix.model.Farm;
import com.ferme.citronix.repository.FarmRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    public FarmResponseDTO createFarm(FarmRequestDTO farmRequestDTO) {
        Farm farm = farmMapper.toEntity(farmRequestDTO);
        return farmMapper.toResponseDTO(farmRepository.save(farm));
    }

    public List<FarmResponseDTO> getAllFarms() {
        return farmMapper.toResponseDTOList(farmRepository.findAll());
    }

    public FarmResponseDTO getFarmById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm not found"));
        return farmMapper.toResponseDTO(farm);
    }

    public FarmResponseDTO updateFarm(Long id, FarmRequestDTO dto) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm not found"));

        farm.setName(dto.getName());
        farm.setLocation(dto.getLocation());
        farm.setArea(dto.getArea());
        farm.setCreationDate(dto.getCreationDate());

        return farmMapper.toResponseDTO(farmRepository.save(farm));
    }
}

