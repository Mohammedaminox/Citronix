package com.ferme.citronix.web.rest;

import com.ferme.citronix.domain.Harvest;
import com.ferme.citronix.domain.Sale;
import com.ferme.citronix.service.HarvestService;
import com.ferme.citronix.service.imp.SaleServiceImpl;
import com.ferme.citronix.web.vm.response.SaleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    private final SaleServiceImpl saleService;
    private final HarvestService harvestService;

    public SaleController(SaleServiceImpl saleService, HarvestService harvestService) {
        this.saleService = saleService;
        this.harvestService = harvestService;
    }

    @PostMapping
    public ResponseEntity<SaleResponse> createSale(@RequestBody Sale sale) {
        Harvest harvest = sale.getHarvest();
        if (harvest == null || harvestService.findById(harvest.getId()).isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Sale savedSale = saleService.createSale(sale);
        SaleResponse response = new SaleResponse();
        response.setId(savedSale.getId());
        response.setDate(savedSale.getDate());
        response.setUnitPrice(savedSale.getUnitPrice());
        response.setClient(savedSale.getClient());
        response.setRevenue(savedSale.getRevenue());
        response.setQuantity(savedSale.getQuantity());
        response.setHarvestId(savedSale.getHarvest().getId());
        return ResponseEntity.ok(response);
    }
}