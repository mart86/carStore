package com.martynov.carStore.dto;

import com.martynov.carStore.utils.BMWModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PurchaseDTO {

    @DateTimeFormat(pattern="dd.MM.yyyy")
    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private BMWModel model;

    private int quantity;

    @DecimalMin(value = "0", message = "Price cannot be negative")
    private BigDecimal price;

    @NotBlank(message = "Client name cannot be blank")
    private String clientName;

    @NotBlank(message = "Client phone cannot be blank")
    @Pattern(regexp="\\+7\\d{10}", message="Phone number should start with +7 and contain 11 digits")
    private String clientPhone;

    public BMWModel getModel() {
        return model;
    }

    public void setModel(BMWModel model) {
        this.model = model;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }
}
