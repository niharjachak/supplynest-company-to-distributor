package com.supplynext.company_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pricing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pricing extends GlobalRecord{
    // PRICING-001
    private String priceId;

    @ManyToOne
    private  Product product;
    private Double basePrice;
    private Double tradePrice; // what rate are we selling to distributor
    private Double purchasePrice;
    private String currency;

    // Date from which the price will be effective
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;

    private String regionCode;
    private String priceType;
    private String discountType;
    private Double discountPercentage;
    private boolean isGSTIncluded;
}
