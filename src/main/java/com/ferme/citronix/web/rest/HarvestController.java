package com.ferme.citronix.web.rest;

import jakarta.validation.Valid;
import com.ferme.citronix.domain.Harvest;
import com.ferme.citronix.domain.Tree;
import com.ferme.citronix.domain.enums.Season;
import com.ferme.citronix.service.HarvestService;
import com.ferme.citronix.service.TreeService;
import com.ferme.citronix.web.errors.harvest.HarvestNotFoundException;
import com.ferme.citronix.web.mapper.request.HarvestMapper;
import com.ferme.citronix.web.vm.request.harvest.HarvestCreateVM;
import com.ferme.citronix.web.vm.response.harvest.HarvestResponseVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/harvests")
public class HarvestController {
    private final HarvestService harvestService;
    private final HarvestMapper harvestMapper;
    private final TreeService treeService;

    public HarvestController(HarvestService harvestService, HarvestMapper harvestMapper, TreeService treeService) {
        this.harvestService = harvestService;
        this.harvestMapper = harvestMapper;
        this.treeService = treeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Harvest> saveHarvest(@Valid @RequestBody HarvestCreateVM harvestDTO) {
        Tree tree = treeService.findById(harvestDTO.getTreeId())
                .orElseThrow(() -> new IllegalArgumentException("Tree not found"));

        Harvest harvest = new Harvest();
        harvest.setSeason(Season.valueOf(harvestDTO.getSeason()));
        harvest.setHarvestDate(harvestDTO.getHarvestDate());
        harvest.setTotalQuantity(harvestDTO.getTotalQuantity());
        harvest.setTree(tree);
        Harvest savedHarvest = harvestService.save(harvest);

        return ResponseEntity.ok(savedHarvest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestResponseVM> getHarvest(@PathVariable UUID id) {
        Harvest harvest = harvestService.findById(id)
                .orElseThrow(() -> new HarvestNotFoundException("Harvest not found with ID: " + id));
        return ResponseEntity.ok(harvestMapper.toResponseVM(harvest));
    }

    @GetMapping("/tree/{treeId}")
    public List<HarvestResponseVM> getHarvestsByTree(@PathVariable UUID treeId) {
        return harvestService.findByTreeId(treeId)
                .stream()
                .map(harvestMapper::toResponseVM)
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable UUID id) {
        Harvest harvest = harvestService.findById(id)
                .orElseThrow(() -> new HarvestNotFoundException("Harvest not found with ID: " + id));
        harvestService.delete(harvest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/season/{season}")
    public ResponseEntity<List<Harvest>> getHarvestsBySeason(@PathVariable Season season) {
        List<Harvest> harvests = harvestService.getHarvestsBySeason(season);
        return ResponseEntity.ok(harvests);
    }
}