package com.martynov.carStore.model;

import com.martynov.carStore.utils.BMWModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase", schema = "public")
public class Purchase {
    public Purchase() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date",nullable = false)
    @DateTimeFormat(pattern="dd.MM.yyyy")
    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "model",nullable = false)
    private BMWModel model;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0", message = "Price cannot be negative")
    private BigDecimal price;

    @Column(name = "clientName",nullable = false)
    @NotBlank(message = "Client name cannot be blank")
    private String clientName;

    @Column(name = "clientPhone",nullable = false)
    @NotBlank(message = "Client phone cannot be blank")
    @Pattern(regexp="\\+7\\d{10}", message="Phone number should start with +7 and contain 11 digits")
    private String clientPhone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

