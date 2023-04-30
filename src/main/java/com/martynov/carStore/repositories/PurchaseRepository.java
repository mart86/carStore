package com.martynov.carStore.repositories;

import com.martynov.carStore.model.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    // Запрос на вычисление выручки за заданный период:
    @Query("SELECT SUM(p.price * p.quantity) FROM Purchase p WHERE p.date BETWEEN :startDate AND :endDate")
    BigDecimal calculateRevenueByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //массив заказов
    @Query("SELECT p FROM Purchase p " + "ORDER BY p.date, p.clientName, (p.price * p.quantity) DESC")
    List<Purchase> findOrdersByDateAndClientNameAndTotal();

}
