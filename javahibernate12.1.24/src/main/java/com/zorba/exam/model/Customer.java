package com.zorba.exam.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "custId", length = 50)
    private String custId;

    @Column(name = "custName", nullable = false)
    private String custName;

    @Column(name = "custEmail", nullable = false)
    private String custEmail;

    @Column(name = "custMobile", nullable = false)
    private Long custMobile;

    @Column(name = "finalProductPrice", nullable = false)
    private BigDecimal finalProductPrice;

    // Constructors
    public Customer() {}

    public Customer(String custId, String custName, String custEmail, Long custMobile, BigDecimal finalProductPrice) {
        this.custId = custId;
        this.custName = custName;
        this.custEmail = custEmail;
        this.custMobile = custMobile;
        this.finalProductPrice = finalProductPrice;
    }

    // Getters and Setters
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public Long getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(Long custMobile) {
        this.custMobile = custMobile;
    }

    public BigDecimal getFinalProductPrice() {
        return finalProductPrice;
    }

    public void setFinalProductPrice(BigDecimal finalProductPrice) {
        this.finalProductPrice = finalProductPrice;
    }
}