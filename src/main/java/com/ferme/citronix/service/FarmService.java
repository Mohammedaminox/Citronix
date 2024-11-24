package com.ferme.citronix.service;

import com.ferme.citronix.dto.FarmDto;
import com.ferme.citronix.mapper.FarmMapper;
import com.ferme.citronix.model.Farm;
import com.ferme.citronix.repository.FarmRepository;
import com.ferme.citronix.web.exception.CustomException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FarmMapper farmMapper;

    public List<FarmDto> getAllFarms() {
        List<Farm> farms = farmRepository.findAll();
        if (farms.isEmpty()) {
            throw new CustomException("No farms found.");
        }
        return farms.stream().map(farmMapper::toDto).collect(Collectors.toList());
    }

    public FarmDto getFarmById(Integer id) {
        Farm farm = farmRepository.findById(id).orElseThrow(() -> new CustomException("Farm not found with id: " + id));
        return farmMapper.toDto(farm);
    }

    public FarmDto createFarm(FarmDto farmDto) {
        try {
            Farm farm = farmMapper.toEntity(farmDto);
            return farmMapper.toDto(farmRepository.save(farm));
        } catch (Exception e) {
            throw new CustomException("Failed to create farm: " + e.getMessage());
        }
    }

    public FarmDto updateFarm(FarmDto farmDto) {
        if (!farmRepository.existsById(farmDto.getId())) {
            throw new CustomException("Farm not found with id: " + farmDto.getId());
        }
        try {
            Farm farm = farmMapper.toEntity(farmDto);
            return farmMapper.toDto(farmRepository.save(farm));
        } catch (Exception e) {
            throw new CustomException("Failed to update farm: " + e.getMessage());
        }
    }

    public void deleteFarm(Integer id) {
        if (!farmRepository.existsById(id)) {
            throw new CustomException("Farm not found with id: " + id);
        }
        try {
            farmRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException("Failed to delete farm: " + e.getMessage());
        }
    }

    public List<FarmDto> findByNameAndLocation(String name, String location) {
        List<Farm> farms = farmRepository.findByNameAndLocation(name, location);
        if (farms.isEmpty()) {
            throw new CustomException("No farms found with the given name and location.");
        }
        return farms.stream().map(farmMapper::toDto).collect(Collectors.toList());
    }

    public List<FarmDto> searchFarms(String name, String location, Double area) {
        List<Farm> farms = farmRepository.findAll((Root<Farm> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate namePredicate = cb.like(root.get("name"), "%" + name + "%");
            Predicate locationPredicate = cb.like(root.get("location"), "%" + location + "%");
            Predicate areaPredicate = cb.equal(root.get("area"), area);
            return cb.and(namePredicate, locationPredicate, areaPredicate);
        });

        if (farms.isEmpty()) {
            throw new CustomException("No farms found with the given criteria.");
        }

        return farms.stream().map(farmMapper::toDto).collect(Collectors.toList());
    }
}

