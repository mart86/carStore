package com.martynov.carStore.services;

import com.martynov.carStore.model.Purchase;
import com.martynov.carStore.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private PurchaseRepository repository;

    public PurchaseService(PurchaseRepository repository) {
        this.repository = repository;
    }

    // Метод для вычисления выручки за заданный период
    public BigDecimal calculateRevenueByPeriod(LocalDate startDate, LocalDate endDate) {
        return repository.calculateRevenueByPeriod(startDate, endDate);
    }

    // Метод для поиска заказов, упорядоченных по дате заказа, внутри даты по ФИ клиента, внутри клиента по сумме заказа
    public List<Purchase> findOrdersByDateAndClientNameAndTotal() {
        return repository.findOrdersByDateAndClientNameAndTotal();
    }

    public List<Purchase> getAllPurchases() {
        return (List<Purchase>) repository.findAll();
    }

    public Purchase getPurchaseById(long id) {
        Optional<Purchase> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public Purchase savePurchase(Purchase purchase) {
        return repository.save(purchase);
    }

    public void deletePurchase(long id) {
        repository.deleteById(id);
    }
}
