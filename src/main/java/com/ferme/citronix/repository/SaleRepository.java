package com.ferme.citronix.repository;


import com.ferme.citronix.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    boolean existsByHarvestId(UUID harvestId);
}
