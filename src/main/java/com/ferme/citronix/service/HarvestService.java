package com.ferme.citronix.service;

import com.ferme.citronix.domain.Harvest;
import com.ferme.citronix.domain.enums.Season;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HarvestService {
    Harvest save(Harvest harvest);

    Optional<Harvest> findById(UUID id);

    List<Harvest> findAll();

    List<Harvest> findByTreeId(UUID treeId);

    void delete(Harvest harvest);

    boolean isSeasonAvailable(UUID treeId, String season);

    List<Harvest> getHarvestsBySeason(Season season);
}