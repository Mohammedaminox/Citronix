package com.ferme.citronix.repository;

import com.ferme.citronix.model.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FieldRepository extends JpaRepository<Field, UUID> {
    Page<Field> findByFarmId(UUID farmId, Pageable pageable);

    List<Field> findFieldByFarmId(UUID farmId);
}
