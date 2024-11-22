package com.ferme.citronix.repository;

import com.ferme.citronix.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long>, JpaSpecificationExecutor<Farm> {
}

