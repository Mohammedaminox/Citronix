package com.ferme.citronix.repository;

import com.ferme.citronix.domain.Harvest;
import com.ferme.citronix.domain.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HarvestRepository extends JpaRepository<Harvest, UUID> {
    List<Harvest> findBySeason(Season season);
    List<Harvest> findByTreeIdAndSeason(UUID treeId, Season season);

    List<Harvest> findByTreeId(UUID treeId);
}
