package com.supplynext.company_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends  GlobalRecord {

    private String prodId;

    @ManyToOne
    Company manufacturerCompany;
    @Column(unique = true)
    private String skuCode;

    private String category;
    private String status; // availabilty;
    private String packagingType;
    private String packagingSize;
    private String unitsPerCase;
    private String uom;
    private String weightPerUnit;
    private String  shelfLifeInDay;

    private String hsnCode;
    private Double taxRate;
    private boolean isReturnable;

    private String productDescription;

    @OneToMany
    private List<Document> productImages;

}
