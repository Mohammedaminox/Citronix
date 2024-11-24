package com.ferme.citronix.service.imp;

import com.ferme.citronix.domain.Harvest;
import com.ferme.citronix.domain.Sale;
import com.ferme.citronix.repository.HarvestRepository;
import com.ferme.citronix.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleServiceImpl {

    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;

    public SaleServiceImpl(SaleRepository saleRepository, HarvestRepository harvestRepository) {
        this.saleRepository = saleRepository;
        this.harvestRepository = harvestRepository;
    }

    public Sale createSale(Sale sale) {
        if (sale.getHarvest() == null || sale.getHarvest().getId() == null) {
            throw new IllegalArgumentException("Harvest cannot be null.");
        }

        Optional<Harvest> harvestOpt = harvestRepository.findById(sale.getHarvest().getId());
        if (harvestOpt.isEmpty()) {
            throw new IllegalArgumentException("Harvest not found.");
        }

        boolean isHarvestAlreadySold = saleRepository.existsByHarvestId(sale.getHarvest().getId());
        if (isHarvestAlreadySold) {
            throw new IllegalArgumentException("Harvest is already sold.");
        }

        sale.setHarvest(harvestOpt.get());
        sale.setRevenue(sale.calculateRevenue(sale.getQuantity()));
        return saleRepository.save(sale);
    }
}