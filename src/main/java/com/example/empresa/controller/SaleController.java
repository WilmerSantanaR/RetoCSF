package com.example.empresa.controller;

import com.example.empresa.model.Sale;
import com.example.empresa.services.SaleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody SaleRequest saleRequest) {
        Sale sale = saleService.createSale(
                saleRequest.getSaleDate(),
                saleRequest.getCustomerId(),
                saleRequest.getSaleDetails()
        );
        return ResponseEntity.ok(sale);
    }
}
