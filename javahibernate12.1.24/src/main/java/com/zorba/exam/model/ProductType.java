package com.zorba.exam.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_type_id")
    private int prodTypeId;

    @Column(name = "product_type", nullable = false)
    private String productType;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    // Constructors
    public ProductType() {}

    public ProductType(String productType, BigDecimal rate) {
        this.productType = productType;
        this.rate = rate;
    }

    // Getters and Setters
    public int getProdTypeId() {
        return prodTypeId;
    }

    public void setProdTypeId(int prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}