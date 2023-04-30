package com.martynov.carStore.controllers;

import com.martynov.carStore.dto.PurchaseDTO;
import com.martynov.carStore.model.Purchase;
import com.martynov.carStore.services.PurchaseService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final ModelMapper modelMapper;

    public PurchaseController(PurchaseService purchaseService, ModelMapper modelMapper) {
        this.purchaseService = purchaseService;
        this.modelMapper = modelMapper;
    }
// http://localhost:8080/api/purchases/revenue?start=01.01.2021&end=31.12.2021
    @GetMapping("/revenue")
    public BigDecimal calculateRevenueByPeriod(@RequestParam("start") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate startDate,
                                               @RequestParam("end") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate endDate) {
        return purchaseService.calculateRevenueByPeriod(startDate, endDate);
    }
//http://localhost:8080/api/purchases/sorted-orders
    @GetMapping("/sorted-orders")
    public List<Purchase> findOrdersByDateAndClientNameAndTotal() {
        return purchaseService.findOrdersByDateAndClientNameAndTotal();
    }

    @GetMapping("/all")
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable("id") long id) {
        Purchase purchase = purchaseService.getPurchaseById(id);
        if (purchase != null) {
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Purchase savePurchase(@Valid @RequestBody PurchaseDTO purchaseDTO) {
        Purchase purchase = modelMapper.map(purchaseDTO, Purchase.class);
        return purchaseService.savePurchase(purchase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") long id, @Valid @RequestBody PurchaseDTO purchaseDTO) {
        Purchase currentPurchase = purchaseService.getPurchaseById(id);
        if (currentPurchase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        modelMapper.map(purchaseDTO, currentPurchase);
        Purchase updatedPurchase = purchaseService.savePurchase(currentPurchase);
        return new ResponseEntity<>(updatedPurchase, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePurchase(@PathVariable("id") long id) {
        Purchase purchase = purchaseService.getPurchaseById(id);
        if (purchase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        purchaseService.deletePurchase(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
