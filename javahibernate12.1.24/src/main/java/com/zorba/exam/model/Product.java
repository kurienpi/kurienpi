package com.zorba.exam.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "productId", length = 50)
    private String productId;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productQuantity", nullable = false)
    private BigDecimal productQuantity;

    @ManyToOne
    @JoinColumn(name = "prod_type_id", nullable = false)
    private ProductType productType;

    // Constructors
    public Product() {}

    public Product(String productId, String productName, BigDecimal productQuantity, ProductType productType) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productType = productType;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(BigDecimal productQuantity) {
        this.productQuantity = productQuantity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}